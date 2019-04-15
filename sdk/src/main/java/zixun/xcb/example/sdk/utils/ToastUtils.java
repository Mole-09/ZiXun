package zixun.xcb.example.sdk.utils;


import android.content.Context;
import android.widget.Toast;

/*

    Toast工具类封装
 */
public class ToastUtils {
    private static Toast mToast = null;
    /*
        显示一个toast提示

        @param resourceId toast字符串资源id
     */
    public static void showToast(int resourceId){
        showToast(ResourcesUtils.getString(resourceId));
    }

    /*
        显示一个toast提示

        @param text toast字符串
     */
    public  static void showToast(String text){
        showToast(text,Toast.LENGTH_SHORT);
    }

    /*
         显示一个Toast提示

         @param text toast字符串
         @param duration toast显示时间
     */
    public static void showToast(String text,int duration){
        showToast(AppUtils.getContext(),text,duration);
    }

    /*
        显示一个toast提示

        @param context context上下文对象
        @param text toast字符串
        @param duration toast显示时间
     */
    public static void showToast(final Context context,final String text,final int duration){
        /*
            保证运行在主线程
         */
        AppUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                if(mToast == null){
                    mToast =Toast.makeText(context,text,duration);
                }else{
                    mToast.setText(text);
                    mToast.setDuration(duration);
                }
                mToast.show();
            }
        });
    }
}
