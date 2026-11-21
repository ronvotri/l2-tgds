import java.lang.classfile.*;import java.lang.classfile.instruction.*;import java.lang.constant.*;import java.nio.file.*;
public class PatchHtml {
 public static void main(String[] args)throws Exception{
  var cf=ClassFile.of();int[] hits={0};
  byte[] result=cf.transformClass(cf.parse(Files.readAllBytes(Path.of(args[0]))),(b,e)->{
   if(e instanceof MethodModel m && m.methodName().equalsString("getHtm")){
    b.transformMethod(m,MethodTransform.transformingCode((c,i)->{
     if(i instanceof InvokeInstruction call && call.owner().asInternalName().equals("java/util/Map") && call.name().equalsString("get")){
      if(++hits[0]==2){c.invokestatic(ClassDesc.of("org.l2jmobius.gameserver.custom.HtmlLanguageFallback"),"get",MethodTypeDesc.of(ConstantDescs.CD_Object,ClassDesc.of("java.util.Map"),ConstantDescs.CD_Object));return;}
     }
     c.with(i);
    }));
   }else b.with(e);
  });
  if(hits[0]!=2 || !cf.verify(result).isEmpty())throw new AssertionError("Unexpected HtmCache layout: "+hits[0]+cf.verify(result));
  Files.write(Path.of(args[1]),result);System.out.println("Verified: only original-language fallback cache lookup patched");
 }
}
