package com.taxiassistant.activity;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.taxiassistant.R;
import com.yhao.floatwindow.PermissionListener;
import com.yhao.floatwindow.ViewStateListener;

/**
 * Created by yhao on 2017/12/18.
 * https://github.com/yhaolpz
 */

public class BaseApplication extends Application implements View.OnClickListener{


    private static final String TAG = "FloatWindow";
    private View view;
    private ImageView icon;
    private LinearLayout floats;
    private LinearLayout btn1;
    private LinearLayout btn2;
    private LinearLayout btn3;
    @Override
    public void onCreate() {
        super.onCreate();
        /*
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.icon);
        View  view = LayoutInflater.from(this).inflate(R.layout.layout, null);
        FloatWindow
                .with(getApplicationContext())
                .setView(view)
                .setWidth(Screen.width, 0.2f) //设置悬浮控件宽高
                .setHeight(Screen.width, 0.2f)
                .setX(Screen.width, 0.8f)
                .setY(Screen.height, 0.3f)
                .setMoveType(MoveType.slide,100,-100)
                .setMoveStyle(500, new BounceInterpolator())
                .setFilter(true, A_Activity.class, C_Activity.class)
                .setViewStateListener(mViewStateListener)
                .setPermissionListener(mPermissionListener)
                .setDesktopShow(true)
                .build();

        icon = (ImageView) view.findViewById(R.id.icon);
        floats = (LinearLayout) view.findViewById(R.id.floats);
        btn1 = (LinearLayout) floats.findViewById(R.id.btn1);
        btn2 = (LinearLayout) floats.findViewById(R.id.btn2);
        btn3 = (LinearLayout) floats.findViewById(R.id.btn3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BaseApplication.this, "onClick", Toast.LENGTH_SHORT).show();
                if (floats.getVisibility() == View.VISIBLE)
                {
                    floats.setVisibility(View.GONE);
                } else {
                    floats.setVisibility(View.VISIBLE);
                }
            }
        });*/
    }

    private PermissionListener mPermissionListener = new PermissionListener() {
        @Override
        public void onSuccess() {
            Log.d(TAG, "onSuccess");
        }

        @Override
        public void onFail() {
            Log.d(TAG, "onFail");
        }
    };

    private ViewStateListener mViewStateListener = new ViewStateListener() {
        @Override
        public void onPositionUpdate(int x, int y) {
            Log.d(TAG, "onPositionUpdate: x=" + x + " y=" + y);
        }

        @Override
        public void onShow() {
            Log.d(TAG, "onShow");
        }

        @Override
        public void onHide() {
            Log.d(TAG, "onHide");
        }

        @Override
        public void onDismiss() {
            Log.d(TAG, "onDismiss");
        }

        @Override
        public void onMoveAnimStart() {
            Log.d(TAG, "onMoveAnimStart");
        }

        @Override
        public void onMoveAnimEnd() {
            Log.d(TAG, "onMoveAnimEnd");
        }

        @Override
        public void onBackToDesktop() {
            Log.d(TAG, "onBackToDesktop");
        }
    };

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick");
        switch (view.getId())
        {
            case R.id.icon:
                if (floats.getVisibility() == View.VISIBLE)
                {
                    floats.setVisibility(View.GONE);
                } else {
                    floats.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btn1:
                Toast.makeText(this,"亲，我是论坛！", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn2:
                Toast.makeText(this,"亲，我是手机验证！", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn3:
//                windowManager.removeView(view);
//                android.os.Process.killProcess(android.os.Process.myPid());
                break;
            default:
                break;
        }
    }
}
