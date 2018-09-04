package com.taxiassistant.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.taxiassistant.R;
import com.taxiassistant.service.FloatViewService;


public class MainActivity extends Activity {

    private FloatViewService mFloatViewService;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button btnShowFloat = (Button) findViewById(R.id.btn_show_float);
        btnShowFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFloatingView();
            }
        });

        Button btnHideFloat = (Button) findViewById(R.id.btn_hide_float);
        btnHideFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideFloatingView();
            }
        });

        try {
            Intent intent = new Intent(this, FloatViewService.class);
            startService(intent);
            bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ( keyCode == KeyEvent.KEYCODE_BACK ) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        destroy();
        super.onDestroy();
    }

    /**
     * 显示悬浮图标
     */
    public void showFloatingView() {
        if ( mFloatViewService != null ) {
            mFloatViewService.showFloat();
        }
    }

    /**
     * 隐藏悬浮图标
     */
    public void hideFloatingView() {
        if ( mFloatViewService != null ) {
            mFloatViewService.hideFloat();
        }
    }

    /**
     * 释放PJSDK数据
     */
    public void destroy() {
        try {
            stopService(new Intent(this, FloatViewService.class));
            unbindService(mServiceConnection);
        } catch (Exception e) {
        }
    }
    
    /**
     * 连接到Service
     */
    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mFloatViewService = ((FloatViewService.FloatViewServiceBinder) iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mFloatViewService = null;
        }
    };
}
