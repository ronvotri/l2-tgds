import java.lang.classfile.*;
import java.lang.classfile.instruction.*;
import java.lang.constant.*;
import java.nio.file.*;
import java.util.*;
public final class PatchLoot {
 public static void main(String[] a)throws Exception{
  var cf=ClassFile.of();var party=ClassDesc.of("org.l2jmobius.gameserver.model.groups.Party");
  var player=ClassDesc.of("org.l2jmobius.gameserver.model.actor.Player");var creature=ClassDesc.of("org.l2jmobius.gameserver.model.actor.Creature");
  var item=ClassDesc.of("org.l2jmobius.gameserver.model.item.instance.Item");var attack=ClassDesc.of("org.l2jmobius.gameserver.model.actor.Attackable");
  var helper=ClassDesc.of("org.l2jmobius.gameserver.custom.LootToPlayers");
  int[] counts=new int[5];
  byte[] out=cf.transformClass(cf.parse(Files.readAllBytes(Path.of(a[0]))),(classBuilder,element)->{
   if(!(element instanceof MethodModel m)){classBuilder.with(element);return;}
   classBuilder.transformMethod(m,MethodTransform.transformingCode(CodeTransform.ofStateful(()->new CodeTransform(){boolean first=true;String method=m.methodName().stringValue(),desc=m.methodType().stringValue();
    public void accept(CodeBuilder b,CodeElement e){
     if(first){first=false;
      boolean pickup=method.equals("distributeItem")&&desc.equals("(Lorg/l2jmobius/gameserver/model/actor/Player;Lorg/l2jmobius/gameserver/model/item/instance/Item;)V");
      boolean generated=method.equals("distributeItem")&&desc.equals("(Lorg/l2jmobius/gameserver/model/actor/Player;IIZLorg/l2jmobius/gameserver/model/actor/Attackable;)V");
      if(pickup||generated){Label keep=b.newLabel();b.aload(0).aload(1);
       if(pickup)b.aload(2).invokestatic(helper,"keepOnGround",MethodTypeDesc.of(ConstantDescs.CD_boolean,party,player,item));
       else b.iload(2).iload(3).aload(5).invokestatic(helper,"keepGenerated",MethodTypeDesc.of(ConstantDescs.CD_boolean,party,player,ConstantDescs.CD_int,ConstantDescs.CD_int,attack));
       b.ifeq(keep).return_().labelBinding(keep);counts[pickup?0:1]++;
      }
     }
     if(method.equals("isLootEligible")&&e instanceof ReturnInstruction r&&r.opcode()==Opcode.IRETURN){
      b.aload(0).aload(1).invokestatic(helper,"eligible",MethodTypeDesc.of(ConstantDescs.CD_boolean,ConstantDescs.CD_boolean,party,player));counts[2]++;
     }
     if(method.equals("distributeItem")&&e instanceof InvokeInstruction i&&i.owner().asSymbol().equals(party)&&i.name().equalsString("getActualLooter")){
      boolean pickup=desc.contains("instance/Item;");
      if(pickup)b.aload(2).invokevirtual(item,"getCount",MethodTypeDesc.of(ConstantDescs.CD_int));else b.iload(3);
      b.invokestatic(helper,"actual",MethodTypeDesc.of(player,party,player,ConstantDescs.CD_int,ConstantDescs.CD_boolean,creature,ConstantDescs.CD_int));
      Label valid=b.newLabel();b.dup().ifnonnull(valid).pop();
      if(pickup)b.aload(1).aload(2).invokestatic(helper,"restore",MethodTypeDesc.of(ConstantDescs.CD_void,player,item));
      else b.aload(5).aload(1).iload(2).iload(3).invokevirtual(attack,"dropItem",MethodTypeDesc.of(item,creature,ConstantDescs.CD_int,ConstantDescs.CD_int)).pop();
      b.return_().labelBinding(valid);counts[3]++;return;
     }
     if(method.equals("load")&&e instanceof ReturnInstruction r&&r.opcode()==Opcode.RETURN){b.aload(0).invokestatic(helper,"load",MethodTypeDesc.of(ConstantDescs.CD_void,ConstantDescs.CD_String));counts[4]++;}
     b.with(e);
    }
   })));
  });
  if(!cf.verify(out).isEmpty())throw new AssertionError(cf.verify(out));
  int[] expected=a[2].equals("party")?new int[]{1,1,1,2,0}:new int[]{0,0,0,0,1};
  if(!Arrays.equals(counts,expected))throw new AssertionError(Arrays.toString(counts));
  Files.write(Path.of(a[1]),out);System.out.println(a[2]+" hooks verified: "+Arrays.toString(counts));
 }
}
