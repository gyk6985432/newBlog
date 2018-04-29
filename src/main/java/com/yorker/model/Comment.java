package com.yorker.model;

import java.util.Date;

/**
 * Created by gyk on 2016/9/20.
 */
public class Comment {
    private String name;
    private String saying;
    private Date commentDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSaying() {
        return saying;
    }

    public void setSaying(String saying) {
        this.saying = saying;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
