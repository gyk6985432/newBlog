package com.yorker.dao;


import com.yorker.model.Artical;
import com.yorker.model.Comment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gyk on 2016/9/22.
 *
 */
public class ListArticals {
    private String excerptSeparator  = "/separator/";

    private String base = "";
    //private String base = String.valueOf(Config.BASE);
    private String[] articalNames;

    public ListArticals(String base) {
        this.base = base;
        this.articalNames = readFileNames();
        sortNames();
    }

    public ListArticals(){
        this.articalNames = readFileNames();
        sortNames();
    }

    public String[] getArticalNames(){
        return articalNames;
    }

    private String[] readFileNames(){
        File file = new File(base+"posts"+File.separator);
        return file.list();
    }

    private Date getCreateDate(String fileName){
        String[] ss = fileName.split("-");
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

    private String getSimpleName(String name){
        return name.substring(0, name.indexOf("."));
    }

    public String getLastArtical(){
        return articalNames[articalNames.length-1];
    }

    private void sortNames(){
        String[] names = readFileNames();
        String temp;
        Date date1,date2;
        if (names == null || names.length<=0){
            return;
        }
        int num = names.length;
        for (int i=num-1;i>0;i--){
            for (int j=0;j<i;j++){
                date1 = getCreateDate(names[j]);
                date2 = getCreateDate(names[j+1]);
                if (date1.before(date2)){
                    temp = names[j];
                    names[j] = names[j+1];
                    names[j+1] = temp;
                }
            }
        }
        this.articalNames = names;
    }

    private Artical resolveArtical(String name) throws IOException{
        Artical artical;

        //创建buffer，channel，将文件读入缓冲区
        RandomAccessFile file = new RandomAccessFile(base+"posts"+File.separator+name,"r");
        FileChannel channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(100*1024);
        int in = channel.read(buffer);
        buffer.flip();
        channel.close();
        file.close();
        //编码格式
        CharBuffer cb;
        try {
            cb = Charset.forName("utf-8").newDecoder().decode(buffer);
        }catch (MalformedInputException e){
            System.out.println("字数超出限制10000+！");
            return null;
        }
        StringBuffer sb = new StringBuffer(cb);
        artical = getArticalParams(sb);
        artical.setSimpleName(getSimpleName(name));
        artical.setCreateDate(getCreateDate(name));
        return artical;
    }

    public Artical getHeaderWithIntroduction(String name) throws IOException{
        Artical artical = resolveArtical(name);
        if (artical==null){
            return null;
        }
        String content = artical.getContent();
        int position = content.indexOf(excerptSeparator);
        if (position<=0){
            StringBuffer sb = new StringBuffer(content);
            position = nextWrap(15,sb);
        }
        if (position>=0){
            content = content.substring(0,position);
        }
        artical.setContent(content);
        return artical;
    }

    public Artical getHeaderWithContent(String name) throws IOException{
        Artical artical = resolveArtical(name);
        if (artical==null){
            return null;
        }
        String content = artical.getContent();
        String[] ss = content.split(excerptSeparator);
        if (ss.length>1){
            content = ss[0].concat(ss[1]);
        }
        artical.setContent(content);
        return artical;
    }


    private Artical getArticalParams(StringBuffer sb){
        Artical artical = new Artical();
        String content = "";
        int contentPosition = sb.lastIndexOf("---");
        String id = getParam("id",sb,0);
        String title = getParam("title",sb,0);
        String likes = getParam("likes",sb,0);
        if (contentPosition>=0){
             content = sb.substring(contentPosition+4);
        }
        Comment[] comments = getComments(id);
        artical.setTitle(title);
        try {
            artical.setLikes(Integer.valueOf(likes));
        }catch (Exception e){
            artical.setLikes(0);
        }

        artical.setId(id);
        artical.setContent(content);
        artical.setComments(comments);
        return artical;
    }

    private String getParam(String key, StringBuffer sb, int start){
        String s = "";
        int paramPosition = sb.indexOf(key,start);
        int splitPosition = sb.indexOf(":",paramPosition)+1;
        if (paramPosition>=0 && splitPosition>0){
            s = sb.substring(splitPosition,nextWrap(paramPosition,sb)).trim();
        }
        return s;
    }

    private int nextWrap(int start, StringBuffer sb){
        return sb.indexOf("\r\n",start);
    }

    private Comment[] getComments(String id){
        RandomAccessFile file;
        List<Comment> list=new ArrayList<Comment>();
        try {
            file = new RandomAccessFile(base+"comments"+File.separator+id+".md","r");
            FileChannel channel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024*30);
            int in = channel.read(buffer);
            buffer.flip();
            channel.close();
            file.close();
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
                int splitPosition = sb.indexOf(":",sayingPosition)+1;
                String saying = "";
                if (splitPosition>=0 && position>0){
                    saying = sb.substring(splitPosition,position);
                }
                Comment comment = new Comment();
                comment.setName(name);
                Date temp;
                try {
                    temp = Date.valueOf(date);
                }catch (IllegalArgumentException e){
                    temp= new Date(System.currentTimeMillis());
                }
                comment.setCommentDate(temp);
                comment.setSaying(saying);
                list.add(comment);
            }
            Comment[] comments = new Comment[list.size()];
            for (int i=0;i<list.size();i++){
                comments[i] = list.get(i);
            }
            return comments;

        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
