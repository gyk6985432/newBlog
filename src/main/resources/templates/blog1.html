<%--
  Created by IntelliJ IDEA.
  User: gyk
  Date: 2016/9/22
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
    <script src="/js/markdown.min.js"></script>
    <link href="/css/render.css" rel="stylesheet"   type="text/css"/>
</head>
<body>
<div id="render">
    <div id="back"><a href="/blog">返回</a></div>

    <div id="title">${title}</div>

    <div id="createdate">${date}</div>

    <div id="content">${content}</div>

    <div id="num" >
        <div class="num">赞${likes}</div>
        <div class="num" onclick="onview()">评论${commentNUm}</div>
    </div>

    <!--评论列表-->
    <div id="comments" style="visibility: hidden" >
        <div>
            <c:forEach items="${comments}" varStatus="i" var="item" >
                <div id="comment">${item.name} &nbsp; &nbsp; ${item.commentDate}<br>${item.saying}</div>
            </c:forEach>
        </div>
        <div id="guest">
            <form id="input" action="/addComment/${id}/${simpleName}">
              游客：&nbsp;&nbsp;<input type="text" name = "guest" size="5"/><br>
              <textarea name="text" rows="3" cols="30" form="input"></textarea><br>
              <input type="submit">
            </form>
        </div>
    </div>
</div>
    <!-- markdown parser-->
    <script>
        function parser(content) {
            this.update = function () {
                content.innerHTML = markdown.toHTML(content.innerHTML);
            };
            this.update();
        }
        var get = function (id) {
            return document.getElementById(id);
        }
        new parser(get("content"));
    </script>

    <!--评论点击-->
    <script>
        function onview() {
            var com = document.getElementById("comments");
            if (com.style.visibility == "visible"){
                com.style.visibility = "hidden";
            }else{
                com.style.visibility = "visible";
            }

        }
    </script>
    //ajax添加评论和点赞
    <script>
        function like() {
            var xmlhttp;
            if (window.XMLHttpRequest){
                xmlhttp = new XMLHttpRequest();
                xmlhttp.open("post","/like",true);
                xmlhttp.send()
            }else{
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
        }
        function commnet() {
            var xmlhttp;
            if (window.XMLHttpRequest){
                xmlhttp = new XMLHttpRequest();
                xmlhttp.open("post","/comment",true);
                xmlhttp.send()
            }else{
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
        }

    </script>

</body>
</html>
