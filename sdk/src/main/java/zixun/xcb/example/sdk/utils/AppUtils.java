package zixun.xcb.example.sdk.utils;


import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.File;

import zixun.xcb.example.sdk.global.GlobalApplication;

/*
   App工具类
 */
public class AppUtils {

    /*
       获取上下文对象
       @return 上下文对象
     */
    public static Context getContext(){
        return GlobalApplication.getContext();
    }

    /*
       获取全局Handler
       @return 全局handler
     */
    public static Handler getHandler(){
        return GlobalApplication.getHandler();
    }

    /*
       获取主线程id
       @return 主线程id
     */
    public static int getMainThread(){
        return GlobalApplication.getMainThreadId();
    }

    /*
       获取版本名称

       没做
     */

    /*
       获取版本号

       没做
     */

    /*
       显示软键盘
     */
    public static void openSoftInput(EditText editText){
        InputMethodManager inputMethodManager=(InputMethodManager) editText.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText,InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /*
       隐藏软键盘
     */
    public static void hideSoftInput(EditText editText){
        InputMethodManager inputMethodManager = (InputMethodManager)editText.getContext().
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /*
        根据传入控件的坐标和用户的焦点坐标，判断是否隐藏键盘，如果点击的位置在控件内，则不隐藏键盘

        @param view 控件view
        @param event 焦点位置
        @return 是否隐藏
     */
    public static void hideKeyboard(MotionEvent event, View view, Activity activity){
        try{
            if(view != null && view instanceof EditText){
                int[] location = {0,0};
                view.getLocationInWindow(location);
                int left = location[0],top=location[1],right = left+view.getWidth(),bottom = top+view.getHeight();
                //判断焦点位置坐标是否在空间内，如果位置在外，则隐藏键盘
                if(event.getRawX()<left||event.getRawX()>right||event.getRawY()<top||event.getRawY()>bottom){
                    //隐藏键盘
                    IBinder token = view.getWindowToken();
                    InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(token,InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }

            }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
        获取SD卡路径

        @return 如果sd卡不存在则返回null
     */
    public static File getSDPath(){
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if(sdCardExist){
            sdDir = Environment.getExternalStorageDirectory();//获取目录
        }
        return sdDir;
    }

    /*
        安装文件
        @param data
     */
    public static void promptInstall(Context context,Uri data){
        Intent promptInstall = new Intent(Intent.ACTION_VIEW).setDataAndType(data,"application/vnd.android.package-archive");
        //FLAG_ACTIVITY_NEW_TASK 可以保证安装成功时可以正常打开APP
        promptInstall.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(promptInstall);
    }

    /*
       复制  剪切板功能
     */
    public static void copy2clipboard(Context context,String text){}

    /*
        判断是否运行在主线程

        @return true 当前线程运行在主线程
        false: 当前线程没有运行在主线程
     */
    public static boolean isRunOnUIThread(){
        //获取当前主线程id,如果当前线程id和主线程id相同，则当前就是主线程
        int myTid = android.os.Process.myTid();
        if(myTid == getMainThread()){
            return true;
        }
        return false;
    }

    /*
        运行在主线程
        @param r 运行的Runable对象
     */
    public static void runOnUIThread(Runnable r){
        if(isRunOnUIThread()){
            //已经是主线程，直接运行
            r.run();
        }
        else{
            //如果是子线程，借助handler让其运行在主线程
            getHandler().post(r);
        }
    }


}
