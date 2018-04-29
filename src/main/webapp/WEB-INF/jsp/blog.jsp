<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <!--font-awesome.min.css"> -->
    <script src="https://use.fontawesome.com/d35a849ddd.js"></script>
    <title>博客</title>
    <script src="/js/markdown.min.js"></script>
    <link href="/css/blog.css" rel="stylesheet"/>
</head>
<body>
<div class="background">
    <div class="navi">
        <div class="name"><a href="/blog"><font size="4px" color="#366ce8">Y</font>ORKER WORK</a></div>
        <div class="nav"><i class="fa fa-minus" aria-hidden="true"></i>&nbsp;&nbsp;联系</div>
        <div class="nav"><i class="fa fa-minus" aria-hidden="true"></i>&nbsp;&nbsp;资料</div>
        <div class="nav"><i class="fa fa-minus" aria-hidden="true"></i>&nbsp;&nbsp;博客</div>
    </div>

    <div class="main">
        <div class="sidebar">
            <div class="dateNav">
                <dl>
                    <dt>2016</dt>
                    <dd>10月</dd>
                    <dt>2015</dt>
                    <dt>2014</dt>
                </dl>
            </div>
            <div class="statistics">
                <div><p class="snum">20</p><p class="scon">文章</p></div>
                <div><p class="snum">9</p><p class="scon">评论</p></div>
                <div><p class="snum">27</p><p class="scon">点赞</p></div>
            </div>
            <div class="blogNav">
                <ul style="list-style-type: none;">
                    <li><i class="fa fa-minus" aria-hidden="true"></i>&nbsp;&nbsp;[前端]前端你辣么美</li>
                </ul>
            </div>
        </div>
        <div class="blogs">
            <div class="blog">
                <div class="head">
                    <div class="title">${title}</div>
                    <div class="likes"><i class="fa fa-heart" aria-hidden="true"></i>&nbsp;{likes}</div>
                    <div class="date">${date}</div>
                </div>
                <div class="detail">
                    <div class="content">
                        ${content}
                    </div>
                    <div class="showAll"><input type="button" value="收起全文"/></div>
                    <div class="comments">
                        <c:forEach items="${comments}" varStatus="i" var="item" >
                            <div class="comment">
                                <div class="userIcon"><img src="images/user.jpg"/></div>
                                <div class="username">${item.name}&nbsp; &nbsp;${item.commentDate}</div>
                                <div class="commentContent">
                                        ${item.saying}
                                </div>
                            </div>
                        </c:forEach>
                        <form id="input" action="/addComment/${id}/${simpleName}">
                            <div class="typeComment">
                                <div class="userIcon"><img src="/images/user.jpg"/></div>
                                <textarea cols="58" rows="4" form="input" placeholder="请输入你要评论的内容～～"></textarea>
                            </div>
                            <div class="submitButton"><input type="submit" value="评论" /></div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="foot"></div>
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
        function comment() {
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


<%--<div id="render">--%>
<%--<div id="back"><a href="/blog">返回</a></div>--%>
<%--<div id="title">${title}</div>--%>
<%--<div id="createdate">${date}</div>--%>
<%--<div id="content">${content}</div>--%>
<%--<div id="num" >--%>
<%--<div class="num">赞${likes}</div>--%>
<%--<div class="num" onclick="onview()">评论${commentNUm}</div>--%>
<%--</div>--%>
<%--<!--评论列表-->--%>
<%--<div id="comments" style="visibility: hidden" >--%>
<%--<div>--%>
<%--<c:forEach items="${comments}" varStatus="i" var="item" >--%>
<%--<div id="comment">${item.name} &nbsp; &nbsp; ${item.commentDate}<br>${item.saying}</div>--%>
<%--</c:forEach>--%>
<%--</div>--%>
<%--<div id="guest">--%>
<%--<form id="input" action="/addComment/${id}/${simpleName}">--%>
<%--游客：&nbsp;&nbsp;<input type="text" name = "guest" size="5"/><br>--%>
<%--<textarea name="text" rows="3" cols="30" form="input"></textarea><br>--%>
<%--<input type="submit">--%>
<%--</form>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
