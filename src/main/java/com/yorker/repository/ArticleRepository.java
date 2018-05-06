package com.yorker.repository;

import com.yorker.model.bean.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by gyk on 2018/5/5.
 */

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {

    @Query("select distinct t.createDate from Article t")
    public List<Object> findAllDateDistinct();

    @Query("select t.id, t.title, t.createDate from Article t where t.createDate between :from and :to")
    public List<Object[]> findArticlesByDate(@Param("from")Date from, @Param("to")Date to);

    @Query("select t.id, t.title, t.createDate from Article t")
    public List<Object[]> findAllArticles();
}
