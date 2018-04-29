package com.yorker.model;

/**
 * Created by gyk on 2016/10/5.
 */
public class ViewModelList {
    private String imgPath;
    private ViewModel[] models;

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public ViewModel[] getModels() {
        return models;
    }

    public void setModels(ViewModel[] models) {
        this.models = models;
    }
}
