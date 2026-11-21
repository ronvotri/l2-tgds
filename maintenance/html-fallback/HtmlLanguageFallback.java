package org.l2jmobius.gameserver.custom;
import java.io.File;
import java.util.Map;
import org.l2jmobius.gameserver.cache.HtmCache;
import org.l2jmobius.gameserver.config.GeneralConfig;
import org.l2jmobius.gameserver.config.ServerConfig;
/** Preserve localized-first lookup; load the original on a lazy-cache language miss. */
public final class HtmlLanguageFallback {
 public static Object get(Map<?,?> cache,Object key){
  Object value=cache.get(key);
  if(value!=null || GeneralConfig.HTM_CACHE)return value;
  String path=(String)key;
  if(path.contains(".."))return null;
  value=HtmCache.getInstance().loadFile(new File(ServerConfig.DATAPACK_ROOT,path));
  if(value==null)value=HtmCache.getInstance().loadFile(new File(ServerConfig.SCRIPT_ROOT,path));
  return value;
 }
}
