<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>在线网络流量分类系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta charset="utf-8">
    <link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2_metro.css" />
    <link rel="stylesheet" href="assets/plugins/data-tables/DT_bootstrap.css" />
    <!-- END PAGE LEVEL STYLES -->
    <!-- BEGIN THEME STYLES -->
    <link href="assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="assets/css/custom.css" rel="stylesheet" type="text/css"/>
    <!-- END THEME STYLES -->
    <link rel="shortcut icon" href="favicon.ico" />

</head>
<body style="font-size: 18px">
<div style="height: 700px;width: 1200px;background-color: white">
<div class="portlet box blue col-md-6" style="height: 400px;width: 800px;margin-left: 50px;margin-top: 50px">
    <div class="portlet-title">
        <div class="caption"><i class="fa fa-reorder"></i>网络流量分类系统首页</div>
    </div>
    <div class="portlet-body" style="height: 340px;">
        <div class="tabbable-custom nav-justified">

            <%--<li style="height: 280px;font-size: 58px"><span class="glyphicon glyphicon-play"></span>开始数据采集和处理</li>--%>
            <%--<li><span class="glyphicon glyphicon-tags green"></span>分类</li>--%>
            <%--<li ><span class="glyphicon glyphicon-list-alt purple"></span>查看所有流量</li>--%>
            <ul class="nav nav-tabs nav-justified">
                <li class="active"><a href="#tab_1_1_1" data-toggle="tab">数据采集和处理</a></li>
                <li><a href="#tab_1_1_2" data-toggle="tab">分类</a></li>
                <li><a href="#tab_1_1_3" data-toggle="tab">查看所有流量</a></li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane active" id="tab_1_1_1">
                    <p style="height: 200px;">
                        数据采集和处理页面主要包括采集数据页面和特征统计页面。<br/><br/>
                        在采集数据页面点击开始采集按钮之后，系统会开始进行抓包。<br/><br/>
                        点击停止抓包系统会把已抓到的包保存到pcap文件中，并显示在页面列表中。
                    </p>
                    <a href="picker" class="btn btn-lg green m-icon-big">数据采集和处理页面<i class="m-icon-big-swapright m-icon-white"></i></a>
                </div>
                <div class="tab-pane" id="tab_1_1_2">
                    <p style="height: 200px;">分类介绍</p>
                    <%--<a href="#" class="btn btn-lg purple"><i class="fa fa-sitemap"></i>分类页面</a>--%>
                    <a href="classifier" class="btn btn-lg purple m-icon-big">分类页面<i class="m-icon-big-swapright m-icon-white"></i></a>
                </div>
                <div class="tab-pane" id="tab_1_1_3">
                    <p style="height: 200px;">查看流量介绍</p>
                    <a href="index" class="btn btn-lg red m-icon-big">查看所有流量页面<i class="m-icon-big-swapright m-icon-white"></i></a>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script src="assets/plugins/respond.min.js"></script>
<script src="assets/plugins/excanvas.min.js"></script>
<script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js" type="text/javascript" ></script>
<script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="assets/plugins/jquery.cookie.min.js" type="text/javascript"></script>
<script src="assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript" ></script>
<script type="text/javascript" src="assets/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/plugins/data-tables/DT_bootstrap.js"></script>
<script src="assets/scripts/app.js"></script>
<script src="assets/scripts/table-advanced.js"></script>
<script>
    jQuery(document).ready(function() {
        App.init();
        TableAdvanced.init();
    });
</script>
</body>
</html>