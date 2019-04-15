package zixun.xcb.example.sdk.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

public interface IBaseActivity extends IBaseView {
    /*
       跳往新的Activity

       @param clz 要跳往的Activity
     */
    void startNewActivity(@NonNull Class<?> clz);

    /*
       跳往新的activity

       @param clz 要跳往的Activity
       @param bundle 携带的bundle数据
     */
    void startNewActivity(@NonNull Class<?> clz, Bundle bundle);

    /*
        跳往新的Activity
        @param clz 要跳往的Activity
        @param bundle bundle bundle数据
        @param requesteCode requestCode
     */
    void startNewActivityForResult(@NonNull Class<?> clz,Bundle bundle,int requestCode);
}
