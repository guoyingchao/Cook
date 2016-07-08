package com.xiaoguo.caipuapp.bean;

import android.graphics.Bitmap;

/**
 * Created by lenovo on 2016/6/28.
 */
public class RecommendBean {
    private String title;
    private Bitmap bitmap;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
