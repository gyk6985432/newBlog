package com.yorker.service;

import com.yorker.dao.ListArticals;
import com.yorker.model.Artical;
import com.yorker.model.ViewModelList;
import com.yorker.model.ViewModel;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by gyk on 2016/10/5.
 */
public class ListBlogs {
//        private ListArticals listArticals = new ListArticals("/opt/resources/");
    private ListArticals listArticals = new ListArticals("E:\\resources\\");

    private ViewModel[] getAllModels() throws IOException {
        String[] names = listArticals.getArticalNames();
        if (names == null || names.length<1){
            return null;
        }
        ViewModel[] models = new ViewModel[names.length];
        for (int i=0;i<names.length;i++){
            Artical artical = listArticals.getHeaderWithIntroduction(names[i]);
            models[i]=new ViewModel();
            if (artical==null){
                models[i].setTitle("文章无法显示！请通知博主或留言，谢谢～～");
                models[i].setContent("");
                models[i].setDay("");
                models[i].setMonth("");
                models[i].setYear("");
                models[i].setImgPath("/images/error.jpg");
                continue;
            }

            models[i].setTitle(artical.getTitle());
            models[i].setContent(artical.getContent());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String date = format.format(artical.getCreateDate());
            models[i].setYear(date.substring(0,4));
            models[i].setMonth(date.substring(5,7));
            models[i].setDay(date.substring(8,10));
            //只能支持一种图片格式
            models[i].setImgPath("/images/post/"+artical.getId()+".jpg");
        }
        return models;
    }

    public ViewModelList getThreeBlogs() throws IOException {
        ViewModelList viewModelList = new ViewModelList();
        ViewModel[] models = new ViewModel[3];
        ViewModel[] allModels = getAllModels();
        for (int i=0;i<3;i++){
                models[i] = allModels[i];
        }
        String content = models[0].getContent();
        if (content.length()>100){
            models[0].setContent(content.substring(0,98)+"......");
        }
        content = models[1].getContent();
        if (content.length()>38){
            models[1].setContent(content.substring(0,36)+"......");
        }
        content = models[2].getContent();
        if (content.length()>38){
            models[2].setContent(content.substring(0,36)+"......");
        }
        viewModelList.setModels(models);
        viewModelList.setImgPath(models[0].getImgPath());
        return viewModelList;
    }
}
