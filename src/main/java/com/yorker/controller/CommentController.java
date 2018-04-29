package com.yorker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gyk on 2016/9/23.
 */
@Controller
public class CommentController {

    private String base = "E:\\resources\\";
//    private String base = "/opt/resources/";

    @RequestMapping("/addComment/{id}/{simpleName}")
    public String addComment(@PathVariable String id, @PathVariable String simpleName, String text,String guest){
        try {
            File file = new File(base + "comments" + File.separator + id + ".md");
            if (!file.exists()){
                file.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file,true), Charset.forName("utf-8").newEncoder()));

            Date now = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

            bw.append("name:"+guest+"\r\n");
            bw.append("commentDate:" + sdf.format(now) + "\r\n");
            bw.append("saying:"+text+"\r\n");
            bw.append("---\r\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/blog/"+simpleName;
    }
}
