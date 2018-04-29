<%--
  Created by IntelliJ IDEA.
  User: gyk
  Date: 2016/9/21
  Time: 20:25
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <script src="https://use.fontawesome.com/d35a849ddd.js"></script>
    <title>博客</title>
    <link rel="stylesheet" href="/css/bloglist.css">
  </head>
  <body>
    <div class="background">
        <div class="navi">
                <div class="name"><a href="/"><strong>Y</strong>ORKER WORK</a></div>
                <div class="nav" onmouseover="navHover(this)" onmouseout="navOut(this)"><i class="fa fa-minus" aria-hidden="true"></i>&nbsp;&nbsp;<a href="#">联系</a></div>
                <div class="nav" onmouseover="navHover(this)" onmouseout="navOut(this)"><i class="fa fa-minus" aria-hidden="true"></i>&nbsp;&nbsp;<a href="#">资料</a></div>
                <div class="nav" onmouseover="navHover(this)" onmouseout="navOut(this)"><i class="fa fa-minus" aria-hidden="true"></i>&nbsp;&nbsp;<a href="#">博客</a></div>
        </div>

        <div class="boxes">
                <div class="bigone">
                    <div class="figure" style="background-image:url(${blogs.imgPath})"></div>
                    <div class="arti">
                        <div class="artiDate">
                            <div class="day">${blogs.models[0].day}</div>
                            <div class="mon">${blogs.models[0].year}.${blogs.models[0].month}</div>
                        </div>
                        <div class="artiTitle">${blogs.models[0].title}&nbsp;&nbsp;<span class="new"></span></div>
                        <div class="artiContent">${blogs.models[0].content}</div>
                    </div>
                </div>

                <div class="small">
                    <div class="smallItem smallone">
                        <div class="artiDate">
                            <div class="day">${blogs.models[1].day}</div>
                            <div class="mon">${blogs.models[1].year}.${blogs.models[1].month}</div>
                        </div>
                        <div class="artiTitle">${blogs.models[1].title}</div>
                        <div class="artiContent">${blogs.models[1].content}</div>
                    </div>
                    <div class="smallItem smalltwo">
                        <div class="artiDate">
                            <div class="day">${blogs.models[2].day}</div>
                            <div class="mon">${blogs.models[2].year}.${blogs.models[2].month}</div>
                        </div>
                        <div class="artiTitle">${blogs.models[2].title}</div>
                        <div class="artiContent">${blogs.models[2].content}</div>
                    </div>
                </div>

                <div class="more">更多文章</div>
        </div>

        <div class="bott">
            <!--
            <div class="intro">
                <div class="slogan"></div>
                <div class="portrait"></div>
            </div>
            -->
            <div class="foot">
                <div class="email">
                    <i class="fa fa-envelope" aria-hidden="true"></i> &nbsp;444720556@qq.com
                </div>
                <div class="wechat">
                    <i class="fa fa-weixin"></i> &nbsp;storming
                </div>
                <div class="twitter">
                    <i class="fa fa-twitter" aria-hidden="true"></i> &nbsp;gyk6985432
                </div>
            </div>
        </div>
    </div>

        <script type="text/javascript">
            var pageWidth=window.innerWidth;
            var pageHeight=window.innerHeight;

            var background = document.getElementsByClassName('background')[0];
            background.style.width = pageWidth;
            background.style.height = pageWidth*1.1;
            if (pageHeight>pageWidth*1.1) {
                background.style.height = pageHeight;
            }
        </script>
  </body>
</html>