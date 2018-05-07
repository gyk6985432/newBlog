package com.yorker.dao;

import com.yorker.model.bean.Comment;
import com.yorker.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by gyk on 2018/5/7.
 */
@Component
public class FindComment {
    @Autowired
    private CommentRepository repository;

    public List<Object[]> getComments(long id){
        return repository.findComment(id);
    }

    public Comment insertComment(String id, WebRequest request) {
        Comment comment = new Comment();
        comment.setArticleId(Long.parseLong(id));
        comment.setUser(request.getParameter("user"));
        comment.setSaying(request.getParameter("saying"));
        comment.setCommentDate(new Date());
        comment.setMail(request.getParameter("mail"));
        return repository.save(comment);
    }

    public long count(){
        return repository.count();
    }
}
