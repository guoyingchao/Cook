package com.xiaoguo.caipuapp.bean;

import android.graphics.Bitmap;

/**
 * Created by lenovo on 2016/7/2.
 */
public class ClassfiyImageAndTitleBean {
    private String textTitle;
    private Bitmap image;

    public String getTextTitle() {
        return textTitle;
    }

    public void setTextTitle(String textTitle) {
        this.textTitle = textTitle;
    }

    public Bitmap getUrlImage() {
        return image;
    }

    public void setUrlImage(Bitmap urlImage) {
        this.image = urlImage;
    }
}
