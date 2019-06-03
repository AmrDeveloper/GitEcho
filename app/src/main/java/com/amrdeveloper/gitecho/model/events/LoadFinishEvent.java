package com.amrdeveloper.gitecho.model.events;

public class LoadFinishEvent<T> {

    private T resultData;

    public LoadFinishEvent(T resultData){
        this.resultData = resultData;
    }

    public T getResultData() {
        return resultData;
    }
}
