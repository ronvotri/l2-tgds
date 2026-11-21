package org.l2jmobius.gameserver.custom;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.l2jmobius.commons.util.ConfigReader;
import org.l2jmobius.commons.util.Rnd;
import org.l2jmobius.gameserver.config.PlayerConfig;
import org.l2jmobius.gameserver.data.xml.ItemData;
import org.l2jmobius.gameserver.managers.PhantomManager;
import org.l2jmobius.gameserver.model.actor.Attackable;
import org.l2jmobius.gameserver.model.actor.Creature;
import org.l2jmobius.gameserver.model.actor.Player;
import org.l2jmobius.gameserver.model.groups.Party;
import org.l2jmobius.gameserver.model.groups.PartyDistributionType;
import org.l2jmobius.gameserver.model.item.instance.Item;
import org.l2jmobius.gameserver.util.LocationUtil;

/** Optional server-wide policy, scoped strictly to mixed player/AI parties. */
public final class LootToPlayers {
    private static volatile boolean enabled;
    private static final Map<Party,Integer> turns=Collections.synchronizedMap(new WeakHashMap<>());
    private static Method original;
    public static void load(String configDir) {
        enabled=new ConfigReader("./"+configDir+"/Custom/FakePlayers.ini").getBoolean("FakePlayerPartyLootToPlayers",false);
    }
    static boolean ai(Player p) {
        PhantomManager pm=PhantomManager.getInstance();
        return p!=null && !pm.isAutopilot(p) && (pm.isPhantom(p)||pm.isLivingPopulation(p));
    }
    static boolean applies(Party party) {
        if(!enabled||party==null)return false;
        boolean human=false,bot=false;
        for(Player p:party.getMembers())if(p!=null){if(ai(p))bot=true;else human=true;}
        return human&&bot;
    }
    public static boolean eligible(boolean nativeResult,Party party,Player p) {
        return applies(party)?p!=null&&!p.isDead()&&!ai(p):nativeResult;
    }
    private static List<Player> candidates(Party party,int itemId,int count,Creature source) {
        List<Player> out=new ArrayList<>();
        for(Player p:party.getMembers()) {
            if(p==null||ai(p)||p.isDead()||!LocationUtil.checkIfInRange(PlayerConfig.ALT_PARTY_RANGE,source,p,true))continue;
            if(itemId!=57) {
                var template=ItemData.getInstance().getTemplate(itemId);
                if(template==null||!p.getInventory().validateCapacityByItemId(itemId)||!p.getInventory().validateWeight((long)template.getWeight()*Math.max(1,count)))continue;
            }
            out.add(p);
        }
        return out;
    }
    /** Called before native adena handling, which otherwise destroys a picked stack even if nobody can receive it. */
    public static boolean keepOnGround(Party party,Player picker,Item item) {
        if(!applies(party)||!candidates(party,item.getId(),item.getCount(),picker).isEmpty())return false;
        restore(picker,item);return true;
    }
    public static boolean keepGenerated(Party party,Player picker,int id,int count,Attackable source) {
        if(!applies(party)||!candidates(party,id,count,source).isEmpty())return false;
        source.dropItem(picker,id,count);return true;
    }
    public static void restore(Player picker,Item item) {
        if(!item.isSpawned())item.dropMe(picker,picker.getX(),picker.getY(),picker.getZ());
    }
    /** Native distribution still adds the item and records its real recipient in the loot ledger. */
    public static Player actual(Party party,Player picker,int id,boolean spoil,Creature source,int count) {
        if(!applies(party)) {
            try {
                if(original==null){original=Party.class.getDeclaredMethod("getActualLooter",Player.class,int.class,boolean.class,Creature.class);original.setAccessible(true);}
                return (Player)original.invoke(party,picker,id,spoil,source);
            }catch(ReflectiveOperationException e){throw new IllegalStateException("Native loot distribution unavailable",e);}
        }
        List<Player> valid=candidates(party,id,count,source);
        if(valid.isEmpty())return null;
        PartyDistributionType mode=party.getDistributionType();
        boolean random=mode==PartyDistributionType.RANDOM_INCLUDING_SPOIL||mode==PartyDistributionType.RANDOM&&!spoil;
        boolean turn=mode==PartyDistributionType.BY_TURN_INCLUDING_SPOIL||mode==PartyDistributionType.BY_TURN&&!spoil;
        if(random)return valid.get(Rnd.get(valid.size()));
        if(turn) {
            // Track the member index rather than the eligible-list index so an intermittently full bag does not bias turns.
            synchronized(turns) {
                List<Player> members=party.getMembers();int last=turns.getOrDefault(party,-1);
                for(int i=1;i<=members.size();i++){int index=(last+i)%members.size();Player p=members.get(index);if(valid.contains(p)){turns.put(party,index);return p;}}
            }
        }
        if(valid.contains(picker))return picker;
        if(valid.contains(party.getLeader()))return party.getLeader();
        return valid.getFirst();
    }
}
