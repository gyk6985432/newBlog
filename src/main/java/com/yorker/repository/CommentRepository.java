package com.yorker.repository;

import com.yorker.model.bean.Comment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by gyk on 2018/5/7.
 */
@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{
    @Query("select t.id,t.user,t.saying,t.commentDate from Comment t where t.articleId=:id")
    public List<Object[]> findComment(@Param("id")long articleId);

//    @Modifying
//    @Query("insert into Comment (articleId, user, saying, commentDate, mail) select :id, :user, :saying, :date, :mail from Dual")
//    @Query(value="insert into Comment (articleId, user, saying, commentDate, mail) values (:id, :user, :saying, :date, :mail)", nativeQuery = true)
//    public int insert(@Param("id")long id, @Param("user")String user, @Param("saying")String saying, @Param("date")Date date, @Param("mail")String mail);
}
