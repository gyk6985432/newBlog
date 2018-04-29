<%--
  Created by IntelliJ IDEA.
  User: gyk
  Date: 2016/9/21
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <!--font-awesome.min.css"> -->
        <script src="https://use.fontawesome.com/d35a849ddd.js"></script>
        <link rel="stylesheet" href="./css/index.css">
    <title>首页</title>
  </head>
  <body>
    <div class="background">
        <div class="navi">
            <div class="nav" onmouseover="navHover(this)" onmouseout="navOut(this)"><i class="fa fa-minus" aria-hidden="true"></i>&nbsp;&nbsp;<a href="#">联系</a></div>
            <div class="nav" onmouseover="navHover(this)" onmouseout="navOut(this)"><i class="fa fa-minus" aria-hidden="true"></i>&nbsp;&nbsp;<a href="#">资料</a></div>
            <div class="nav" onmouseover="navHover(this)" onmouseout="navOut(this)"><i class="fa fa-minus" aria-hidden="true"></i>&nbsp;&nbsp;<a href="/blog">博客</a></div>
        </div>
        <div class="title">
            <div class="t t1">高&nbsp;&nbsp;&nbsp;悦&nbsp;&nbsp;&nbsp;凯&nbsp;&nbsp;&nbsp;</div>
            <div class="t t2">的&nbsp;</div>
            <div class="t t3">个&nbsp;&nbsp;&nbsp;人&nbsp;&nbsp;&nbsp;网&nbsp;&nbsp;&nbsp;站</div>
        </div>
        <div class="intro">#河东人氏，而立之年，编程人生，漫漫无边…#</div>
        <form action="/blog" class="form">
            <input type="button" class="enter" onclick="document.forms[0].submit()">
        </form>
    </div>

    <script src="https://use.fontawesome.com/d35a849ddd.js"></script>
    <script src="/js/index.js"></script>
    <script type="text/javascript">
        var pageWidth=window.innerWidth;
        var background = document.getElementsByClassName('background')[0];
        background.style.width = pageWidth;
        background.style.height = pageWidth*0.56;
    </script>

  </body>
</html>
