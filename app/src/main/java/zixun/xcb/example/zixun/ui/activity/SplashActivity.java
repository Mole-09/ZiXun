package zixun.xcb.example.zixun.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zixun.xcb.example.zixun.R;

public class SplashActivity extends Activity {
    @BindView(R.id.start_pic)
    ImageView startPic;
    @BindView(R.id.start_saying)
    TextView startSaying;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_main);
        ButterKnife.bind(this);
        startAnim();
    }

    private void startAnim(){
        //传入一个ImageView对象，围绕X,Y 进行2D缩放，由原始的大小方法到原来的1.15倍
        ObjectAnimator animatorX=ObjectAnimator.ofFloat(startPic,"scaleX",1f,1.15f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(startPic,"scaleY",1f,1.15f);
        //多个动画的协同工作
        AnimatorSet set = new AnimatorSet();
        set.setDuration(2000).play(animatorX).with(animatorY);
        set.start();
        //对动画的监听，动画结束后立马跳转到主页面上
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ComponentName comp = new ComponentName(SplashActivity.this,MainActivity.class);
                Intent intent = new Intent();
                intent.setComponent(comp);
                startActivity(intent);
            }
        });
    }
}
