package com.feng.eventbus;

/**
 * 作者：张芙榕 on 2016/12/2 16:47
 * 加载失败事件
 */

public class LoadFailureEvent {

    private String errorMsg;

    public LoadFailureEvent(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
