package com.feng.eventbus;

/**
 * Created by admin on 2017/1/18.
 */
public class NewsSuccessEvent {

    private NewsEntity newsEntity;
    public NewsSuccessEvent(NewsEntity newsEntity){
        this.newsEntity=newsEntity;
    }

    public NewsEntity getNewsEntity() {
        return newsEntity;
    }

    public void setNewsEntity(NewsEntity newsEntity) {
        this.newsEntity = newsEntity;
    }
}
