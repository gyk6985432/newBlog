package com.yorker.controller;

import com.yorker.model.*;
import com.yorker.dao.ListArticals;
import com.yorker.service.ListBlogs;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class GreetingController {

//    private ListArticals listArticals = new ListArticals("/opt/resources/");
    private ListArticals listArticals = new ListArticals("E:\\resources\\");
    private ListBlogs listBlogs = new ListBlogs();

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/intro")
    public String list(Model model) {
        ViewModelList viewModelList;
        try {
            viewModelList = listBlogs.getThreeBlogs();
        } catch (IOException e) {
            return "fail2";
        }
        model.addAttribute("blogs", viewModelList);
        return "blogList";
    }

    @RequestMapping("/blog")
    public String blog(Model model){
        List<ViewModelNavi> list = getList();
        model.addAttribute("list", list);
        model.addAttribute("num", list.size());
        return "blog";
    }

    @RequestMapping("/blog/{simpleName}")
    public String blogWithSimpleName(@PathVariable String simpleName, Model model){
        List<ViewModelNavi> list = getList();
        model.addAttribute("list", list);
        model.addAttribute("num", list.size());
        model.addAttribute("simpleName", simpleName);
        return "blog";
    }

    @RequestMapping("/fragment/{simpleName}")
    public String getTest(@PathVariable String simpleName, Model model) {
        try {
            if (simpleName==null || simpleName.length()<1 || simpleName.equals("default")){
                String s = listArticals.getLastArtical();
                simpleName = s.substring(0, s.indexOf("."));
            }
            Artical artical = listArticals.getHeaderWithContent(simpleName);
            model.addAttribute("title",artical.getTitle());
            model.addAttribute("date",artical.getCreateDate());
            model.addAttribute("content",artical.getContent());
            model.addAttribute("id",artical.getId());
            model.addAttribute("simpleName",simpleName);
            int num = 0;
            Comment[] comments = artical.getComments();
            if (comments != null && comments.length>0){
                num = comments.length;
            }
            model.addAttribute("commentNUm",num);
            model.addAttribute("comments", comments);
        }catch (IOException e){
            return "fail2";
        }
        return "blogFragment";
    }

    @RequestMapping("/fail2")
    public String fail2() {
        return "fail2";
    }

    private List<ViewModelNavi> getList(){
        List<ViewModelNavi> list = new ArrayList<ViewModelNavi>();
        try {
            List<ViewModel> listModel = Arrays.asList(listBlogs.getAllModels());
            for (ViewModel v : listModel) {
                ViewModelNavi nav = new ViewModelNavi();
                nav.setSimpleName(v.getSimpleName());
                nav.setTitle(v.getTitle());
                list.add(nav);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
