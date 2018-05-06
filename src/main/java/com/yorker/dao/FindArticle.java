package com.yorker.dao;

import com.yorker.model.bean.Article;
import com.yorker.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gyk on 2018/5/5.
 */
@Component
public class FindArticle {
    @Autowired
    private ArticleRepository repository;

    //select title ,content, date where id
    public Article findArticleById(long id){
        return repository.findOne(id);
    }

    public List<Date> findAllCreatedDate(){
        List<Date> dates = new ArrayList<Date>();
        List<Object> objs = repository.findAllDateDistinct();
        for (Object o : objs) {
            dates.add((Date) o);
        }
        return dates;
    }

    public List<Object[]> findArticlesList(Date from, Date to){
        return repository.findArticlesByDate(from, to);
    }

    public List<Object[]> findAllArticles(){
        return repository.findAllArticles();
    }

}
