package com.yorker.dao;


import com.yorker.model.Artical;
import com.yorker.model.Comment;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gyk on 2016/9/20.
 */
public class Resolver {
    private Artical[] articals;
    private String base = "E:\\Demo\\java-web\\myblog\\resources\\";

    public Resolver() {
    }

    public Resolver(String base) {
        this.base = base;
    }

    public Artical[] getArticals(){
        File file = new File(base+"posts"+File.separator);
        String[] names = file.list();
        int num = names.length;
        articals = new Artical[num];
        for (int i=0;i<num;i++){
            articals[i] = resolveArtical(names[i]);
        }
        return articals;
    }

    public Artical resolveArtical(String name){
        Artical artical = new Artical();
        try {
            //创建buffer，channel，将文件读入缓冲区
            RandomAccessFile file = new RandomAccessFile(base+"posts"+File.separator+name,"r");
            FileChannel channel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(500);
            int in = channel.read(buffer);
            buffer.flip();
            //编码格式
            CharBuffer cb = Charset.forName("utf-8").newDecoder().decode(buffer);
            StringBuffer sb = new StringBuffer(cb);
            artical = getArticalParams(sb);
//            artical.setSimpleName(getSimpleName(name));
            artical.setCreateDate(getCreateDate(name));
            return artical;
        } catch (FileNotFoundException e) {
            System.out.println("resolveArtical read from file failure");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return artical;
    }

    private Date getCreateDate(String name){
        String[] ss = name.split("-");
        try {
            if (Integer.valueOf(ss[0])>1971 &&
                    Integer.valueOf(ss[1])>0 &&
                    Integer.valueOf(ss[1])<13 &&
                    Integer.valueOf(ss[1])>0 &&
                    Integer.valueOf(ss[1])<31 ) {
                Date date = Date.valueOf(ss[0] + "-" + ss[1] + "-" + ss[2]);
                return date;
            }
        } catch (Exception e){
                e.printStackTrace();
        }
        return null;
    }

    private Artical getArticalParams(StringBuffer sb){
        Artical artical = new Artical();
        int contentPosition = sb.lastIndexOf("---");
        String id = getParam("id",sb,0);
        String title = getParam("title",sb,0);
        String likes = getParam("likes",sb,0);
        String content = sb.substring(contentPosition+4);
        Comment[] comments = getComments(id);

        artical.setTitle(title);
        artical.setLikes(Integer.valueOf(likes));
        artical.setContent(content);
        artical.setComments(comments);
        return artical;
    }

    private String getParam(String key, StringBuffer sb, int start){
        int paramPosition = sb.indexOf(key,start);
        int splitPosition = sb.indexOf(":",paramPosition)+1;
        return sb.substring(splitPosition,nextWrap(paramPosition,sb)).trim();
    }

    private int nextWrap(int start, StringBuffer sb){
        return sb.indexOf("\r\n",start);
    }

    private Comment[] getComments(String id){
        RandomAccessFile file = null;
        List<Comment> list=new ArrayList<Comment>();
        try {
            file = new RandomAccessFile(base+"comments"+File.separator+id+".md","r");
            FileChannel channel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024*30);
            int in = channel.read(buffer);
            buffer.flip();
            //编码格式
            CharBuffer cb = Charset.forName("utf-8").newDecoder().decode(buffer);
            StringBuffer sb = new StringBuffer(cb);
            int position = 0;
            while (sb.indexOf("---",position)>=0){
                int start = position;
                position = sb.indexOf("---",start+4);
                if (position<0){
                    break;
                }
                String name = getParam("name",sb,start);
                String date = getParam("date",sb,start);
                int sayingPosition = sb.indexOf("saying",start);
                int splitPosition = sb.indexOf(":",sayingPosition);
                String saying = sb.substring(splitPosition,position);

                Comment comment = new Comment();
                comment.setName(name);
                comment.setCommentDate(Date.valueOf(date));
                comment.setSaying(saying);
                list.add(comment);
            }
            Comment[] comments = new Comment[list.size()];
            for (int i=0;i<list.size();i++){
                comments[i] = list.get(i);
            }
            return comments;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



//    public static void com(String[] args) {
//        Resolver r = new Resolver();
//        Artical[] articals = r.getArticals();
//        System.out.println(articals[0].getContent()+articals[0].getCreateDate().toString());
//    }


}
