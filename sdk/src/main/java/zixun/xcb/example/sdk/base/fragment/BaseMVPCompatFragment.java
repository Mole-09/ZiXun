package zixun.xcb.example.sdk.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import me.yokeyword.fragmentation.SupportFragment;
import zixun.xcb.example.sdk.base.BasePresenter;
import zixun.xcb.example.sdk.base.IBaseFragment;
import zixun.xcb.example.sdk.utils.ToastUtils;


/*
       MVP fragment基类
       实现IBaseView方法，绑定ButterKnife
 */
public abstract class BaseMVPCompatFragment<P extends BasePresenter>extends BaseCompatFragment implements IBaseFragment{

    public P mPresenter;

    /*
       在监听器之前把数据准备好
     */
    public void initData(){
        super.initData();
        mPresenter = (P) initPresenter();//在IbaseView里
        if(mPresenter != null){
            mPresenter.attachMV(this);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.deatchMV();
        }
    }

    @Override
    public void showWaitDialog(String msg){
        showProgressDialog(msg);
    }

    @Override
    public void hideWaitDialog(){
        hideProgressDialog();
    }

    @Override
    public void showToast(String msg){
        ToastUtils.showToast(mContext,msg, Toast.LENGTH_SHORT);
    }

    @Override
    public void back(){
        this.onBackPressedSupport();
    }

    @Override
    public void startNewFragment(@NonNull SupportFragment supportFragment){
        start(supportFragment);
    }

    @Override
    public void startNewFragmentWithPop(@NonNull SupportFragment supportFragment){
        startWithPop(supportFragment);
    }

    @Override
    public void startNewFragmentForResult(@NonNull SupportFragment supportFragment,int requestCode){
        startForResult(supportFragment,requestCode);
    }

    @Override
    public void popToFragment(Class<?> targetFragmentClass,boolean includeTargetFragment){
        popTo(targetFragmentClass,includeTargetFragment);
    }

    @Override
    public void hideKeyboard(){
        hideSoftInput();
    }

    @Override
    public void setOnFragmentResult(int ResultCode, Bundle data){
        setFramgentResult(ResultCode,data);
    }
    @Override
    public void startNewActivity(@NonNull Class<?> clz,Bundle bundle){
        ((BaseCompatActivity) mActivity).startActivity(clz);
    }
    @Override
    public void startNewActivity(@NonNull Class<?> clz,Bundle bundle){
        ((BaseCompatActivity) mActivity).startActivity(clz,bundle);
    }

    @Override
    public void startNewActivityForResult(@NonNull Class<?> clz, Bundle bundle, int requestCode) {
        ((BaseCompatActivity) mActivity).startActivityForResult(clz, bundle, requestCode);
    }

}
