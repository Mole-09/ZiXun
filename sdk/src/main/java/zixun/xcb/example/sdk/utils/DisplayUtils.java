package zixun.xcb.example.sdk.utils;


import android.app.Activity;
import android.content.Context;

import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

import zixun.xcb.example.sdk.R;
import jp.wasabeef.glide.transformations.BlurTransformation;
/*
    显示相关工具类
 */
public class DisplayUtils {
    /*
        将px值转换为dp值
     */
    public static int px2dp(float pxValue){
        final float scale=AppUtils.getContext().getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5f);
    }

    /*
        将dp值转换为px值
     */
    public static int dp2px(float dpValue){
        final float scale=AppUtils.getContext().getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }

    /*
       将px值转换为sp值
     */
    public static int px2sp(float pxValue){
        final float scale=AppUtils.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int)(pxValue/scale+0.5f);
    }

    /*
        将sp值转换为px值
     */
    public static int sp2px(int spValue){
        final float scale=AppUtils.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int)(spValue*scale+0.5f);
    }

    /*
        获取屏幕宽度
     */
    public static int getScreenWidthPixels(Activity activity){
        DisplayMetrics metrics=new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    /*
       获取屏幕高度
     */
    public static int getScreenHeightPixels(Activity activity){
        DisplayMetrics metrics=new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

   /*
       显示网络虚化图片
       @param context context
       @param imgUrl 图片url
       @param imageView 要显示的imageView
    */
   public static void displayBlurImg(Context context,final String imgUrl,ImageView imageView){
       //"23"模糊度
       //"4" 图片缩放4倍后再进行模糊
       Glide.with(context).load(imgUrl).error(R.drawable.stackblur_default).placeholder(R.drawable.stackblur_default)
               .crossFade(300).bitmapTransform(new BlurTransformation(context,23,4)).into(imageView);
   }

   /*
       显示本地虚化图片
       @param context context
       @param file 本地图片file
       @param imageView 要显示的ImageView
    */
   public static void displayBlurImg(Context context, File file,ImageView imageView){
       Glide.with(context)
               .load(file)
               .error(R.drawable.stackblur_default)
               .placeholder(R.drawable.stackblur_default)
               .crossFade(300)
               .bitmapTransform(new BlurTransformation(context,23,4))
               .into(imageView);
   }

   /*
       显示资源虚化图片
       @param context
       @param resourceId
       @param imageView
    */
   public static void displayBlurImg(Context context,Integer resourceId,ImageView imageView){
       Glide.with(context)
               .load(resourceId)
               .error(R.drawable.stackblur_default)
               .placeholder(R.drawable.stackblur_default)
               .crossFade(300)
               .bitmapTransform(new BlurTransformation(context,23,4))
               .into(imageView);
   }

}
