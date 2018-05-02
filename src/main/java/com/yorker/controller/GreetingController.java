package com.yorker.controller;

import com.yorker.model.Artical;
import com.yorker.dao.ListArticals;
import com.yorker.model.Comment;
import com.yorker.model.ViewModelList;
import com.yorker.service.ListBlogs;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class GreetingController {

//    private ListArticals listArticals = new ListArticals("/opt/resources/");
    private ListArticals listArticals = new ListArticals("E:\\resources\\");
    private ListBlogs listBlogs = new ListBlogs();

//    @RequestMapping("/blog")
//    public String list(Model model){
//        String[] names = listArticals.getArticalNames();
//        Map<String,String> map = new TreeMap<String, String>();
//        //获得最新文章
//        String newArticalName = listArticals.getLastArtical();
//        Artical newArtical;
//        try {
//            newArtical = listArticals.getHeaderWithIntroduction(newArticalName);
//            model.addAttribute("newArtical",newArtical);
//            Comment[] comments = newArtical.getComments();
//            int num = 0;
//            if (comments != null && comments.length > 0){
//                num = comments.length;
//            }
//            model.addAttribute("commentNum",num);
//            //文件名和标题的映射
//            for (int i=0;i<names.length;i++){
//                String title = listArticals.getHeaderWithIntroduction(names[i]).getTitle();
//                map.put(names[i],title);
//            }
//        }catch (IOException e){
//            return "fail";
//        }
//        model.addAttribute("map",map);
//        return "blogList";
//    }
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
    public String blog(){
        return "blog";
    }

    @RequestMapping("/blog/{simpleName}")
    public String blogWithSimpleName(@PathVariable String simpleName, Model model){
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
        throw new IllegalStateException();
    }
}
