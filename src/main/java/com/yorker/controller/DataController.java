package com.yorker.controller;

import com.yorker.dao.FindArticle;
import com.yorker.dao.FindComment;
import com.yorker.model.bean.Comment;
import com.yorker.service.RequestDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by gyk on 2018/5/6.
 */
@Controller
public class DataController {

    @Autowired
    private FindArticle findArticle;
    @Autowired
    private FindComment findComment;
    @Autowired
    private RequestDateService service;

    @RequestMapping("/1/fragment/{id}")
    String getArticle(@PathVariable String id, Model model){
        long idl = Long.parseLong(id);
        model.addAttribute("article", findArticle.findArticleById(idl));
        List<Object[]> objects = findComment.getComments(idl);
        model.addAttribute("comments", objects);
        return "/1/blogFragment";
    }

    @RequestMapping("/1/{date}")
    String getlist(@PathVariable String date, Model model) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        Date day = format.parse(date);
        Calendar calender = new GregorianCalendar();
        calender.setTime(day);
        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH);
        int daysInMonth = calender.getActualMaximum(Calendar.DAY_OF_MONTH);
        calender.set(year, month, 1);
        Date from = calender.getTime();
        calender.set(year, month, daysInMonth);
        Date to = calender.getTime();
        List<Object[]> results = findArticle.findArticlesList(from, to);
        model.addAttribute("dates", service.getDate());
        model.addAttribute("blogNum", findArticle.count());
        model.addAttribute("commentNum", findComment.count());
        model.addAttribute("list", results);
        model.addAttribute("id", results.get(0)[0]);
        return "/1/blog";
    }

    @RequestMapping("/1/blog")
    String getAllList(Model model){
        model.addAttribute("dates", service.getDate());
        model.addAttribute("blogNum", findArticle.count());
        model.addAttribute("commentNum", findComment.count());
        model.addAttribute("list", findArticle.findAllArticles());
        model.addAttribute("id", 1);
        return "/1/blog";
    }

    @RequestMapping("/1/blog/{id}")
    String getBlogById(@PathVariable String id, Model model){
        model.addAttribute("dates", service.getDate());
        model.addAttribute("blogNum", findArticle.count());
        model.addAttribute("commentNum", findComment.count());
        model.addAttribute("list", findArticle.findAllArticles());
        model.addAttribute("id", id);
        return "/1/blog";
    }

    @RequestMapping(value = "/1/addComment/{id}", method = RequestMethod.POST)
    String addComment(@PathVariable String id, WebRequest request, Model model){
        findComment.insertComment(id, request);
        model.addAttribute("dates", service.getDate());
        model.addAttribute("blogNum", findArticle.count());
        model.addAttribute("commentNum", findComment.count());
        model.addAttribute("list", findArticle.findAllArticles());
        model.addAttribute("id", id);
        return "/1/blog";
    }


}
