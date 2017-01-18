package com.feng.eventbus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

/**
 * 此界面充当发送和接受消息界面
 * Created by admin on 2017/1/18.
 */

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);
    }

    /**
     * 事件的发布------>NewsSuccessEvent类型,接收必须是一样的事件类型
     * @param view
     */
    public void post(View view){
        Toast.makeText(this, "发送成功消息类型", Toast.LENGTH_SHORT).show();
        NewsEntity news = new NewsEntity();
        news.setMsg("success-type-msg");
        news.setStatus(true);
        news.setTotal(100);
        EventBus.getDefault().post(new NewsSuccessEvent(news));
    }

    /**
     * 事件的发布--->LoadFailureEvent类型,接收必须是一样的事件类型
     * @param view
     */
    public void post_f(View view){
        Toast.makeText(this, "发送失败消息类型", Toast.LENGTH_SHORT).show();
        NewsEntity news = new NewsEntity();
        news.setMsg("failure-type-msg");
        news.setStatus(false);
        news.setTotal(200);
        EventBus.getDefault().post(new LoadFailureEvent(news.getMsg()));
    }
}
