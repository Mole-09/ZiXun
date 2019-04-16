package zixun.xcb.example.sdk.utils;




import java.io.Closeable;
import java.io.IOException;

/*
   IO流工具
 */
public class IOUtils {
    /*
       关闭流
     */
    public static boolean close(Closeable io){
        if(io !=null){
            try {
                io.close();
            }catch (IOException e){
                LogUtils.e(e);
            }
        }
        return true;
    }
}
