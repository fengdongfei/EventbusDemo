package com.feng.eventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 此界面充当发送和接受消息的界面
 * 在要接收消息的页面注册eventbus
 * 注意: 发送事件类型和接收事件类型必须是一样的事件类型
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化eventbus
        EventBus.getDefault().register(this);
        findViewById(R.id.dd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent().setClass(MainActivity.this, SecondActivity.class);
                startActivity(s);
            }
        });

    }
    //订阅方法，当接收到事件的时候，会调用该方法-->事件类型LoadFailureEvent
    @Subscribe
    public void onEvent(LoadFailureEvent event) {
        Log.e("onEvent="+getLocalClassName()+"  ",event.getErrorMsg());
    }

    /**
     * 订阅方法，当接收到事件的时候，会调用该方法-->事件类型NewsSuccessEvent
     *
     * 如果使用onEvent作为订阅函数，那么该事件在哪个线程发布出来的，onEvent就会在这个线程中运行，
     * 也就是说发布事件和接收事件线程在同一个线程。
     * 使用这个方法时，在onEvent方法中不能执行耗时操作，如果执行耗时操作容易导致事件分发延迟。
     * @param event
     */
    @Subscribe
    public void onEvent(NewsSuccessEvent event) {
        Log.e("onEvent="+getLocalClassName()+"  ",event.getNewsEntity().getMsg()+"  "+event.getNewsEntity().getTotal());
    }

    /**
     * 订阅方法，当接收到事件的时候，会调用该方法----->事件类型NewsSuccessEvent
     *
     * 如果使用onEventMainThread作为订阅函数，那么不论事件是在哪个线程中发布出来的，onEventMainThread都会在UI线程中执行，
     * 接收事件就会在UI线程中运行，这个在Android中是非常有用的，
     * 因为在Android中只能在UI线程中更新UI，所以在onEvnetMainThread方法中是不能执行耗时操作的。
     * @param event
     */
    @Subscribe
    public void onEventMainThread(NewsSuccessEvent event){
        Log.e("onEventMainThread="+getLocalClassName()+"  ",event.getNewsEntity().getMsg()+"  "+event.getNewsEntity().getTotal());
    }

    /**
     * 订阅方法，当接收到事件的时候，会调用该方法----->事件类型NewsSuccessEvent
     *
     * 如果使用onEventBackgrond作为订阅函数，那么如果事件是在UI线程中发布出来的，那么onEventBackground就会在子线程中运行，
     * 如果事件本来就是子线程中发布出来的，
     * 那么onEventBackground函数直接在该子线程中执行。
     * @param event
     */
    @Subscribe
    public void onEventBackgroundThread(NewsSuccessEvent event){
        Log.e("onEventBackgroundThread="+getLocalClassName()+"  ",event.getNewsEntity().getMsg()+"  "+event.getNewsEntity().getTotal());
    }

    /**
     * 订阅方法，当接收到事件的时候，会调用该方法------>NewsSuccessEvent
     *
     * 使用这个函数作为订阅函数，那么无论事件在哪个线程发布，都会创建新的子线程在执行onEventAsync.
     * @param event
     */
    @Subscribe
    public void onEventAsync(NewsSuccessEvent event){
        Log.e("onEventAsync="+getLocalClassName()+"  ",event.getNewsEntity().getMsg()+"  "+event.getNewsEntity().getTotal());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
