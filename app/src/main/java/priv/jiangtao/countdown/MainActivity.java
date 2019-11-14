package priv.jiangtao.countdown;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.huxq17.floatball.libarary.FloatBallManager;
import com.huxq17.floatball.libarary.floatball.FloatBallCfg;
import com.huxq17.floatball.libarary.utils.BackGroudSeletor;
import com.huxq17.floatball.libarary.utils.DensityUtil;

import priv.jiangtao.countdown.pers.FloatPermissionManager;

public class MainActivity extends Activity {
    private FloatBallManager mFloatballManager;
    private FloatPermissionManager mFloatPermissionManager;

    public void showFloatBall(View v) {
        mFloatballManager.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //5 如果没有添加菜单，可以设置悬浮球点击事件
        mFloatballManager.setOnFloatBallClickListener(new FloatBallManager.OnFloatBallClickListener() {
            @Override
            public void onFloatBallClick() {
                toast("点击了悬浮球");
            }
        });
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mFloatballManager.show();
        mFloatballManager.onFloatBallClick();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mFloatballManager.hide();
    }


    private void init() {
        //1 初始化悬浮球配置，定义好悬浮球大小和icon的drawable
        int ballSize = DensityUtil.dip2px(this, 45);
        Drawable ballIcon = BackGroudSeletor.getdrawble("ic_floatball", this);
        FloatBallCfg ballCfg = new FloatBallCfg(ballSize, ballIcon, FloatBallCfg.Gravity.RIGHT_CENTER, false);
        mFloatballManager = new FloatBallManager(getApplicationContext(), ballCfg);
        setFloatPermission();
    }

    private void setFloatPermission() {
        // 设置悬浮球权限，用于申请悬浮球权限的，这里用的是别人写好的库，可以自己选择
        //如果不设置permission，则不会弹出悬浮球
        mFloatPermissionManager = new FloatPermissionManager();
        mFloatballManager.setPermission(new FloatBallManager.IFloatBallPermission() {
            @Override
            public boolean onRequestFloatBallPermission() {
                requestFloatBallPermission(MainActivity.this);
                return true;
            }

            @Override
            public boolean hasFloatBallPermission(Context context) {
                return mFloatPermissionManager.checkPermission(context);
            }

            @Override
            public void requestFloatBallPermission(Activity activity) {
                mFloatPermissionManager.applyPermission(activity);
            }

        });
    }


    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
