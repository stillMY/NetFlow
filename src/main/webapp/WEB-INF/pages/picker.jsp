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
<div class="portlet box blue col-md-12" style="height: 650px;width: 1000px;margin-top: 50px;margin-left: 50px">
    <div class="portlet-title">
        <div class="caption"><i class="fa fa-reorder"></i>数据采集</div>
    </div>
    <div class="portlet-body">
        <div class="tabbable-custom nav-justified">

            <ul class="nav nav-tabs nav-justified">
                <li class="active"><a href="#tab_1_1_1" data-toggle="tab">采集数据</a></li>
                <%--<li><a href="#tab_1_1_2" data-toggle="tab">还原流</a></li>--%>
                <li><a href="#tab_1_1_3" data-toggle="tab">特征统计</a></li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane active" id="tab_1_1_1" style="height: 480px;">
                    <iframe src="pacp" width="100%" height="450px"></iframe>
                    <span>共采集到32个包</span>
                    <span class="glyphicon glyphicon-stop pull-right" style="height: 30px;font-size: 28px">结束采集</span>
                    <span class="glyphicon glyphicon-play pull-right" style="height: 30px;font-size: 28px">开始采集</span>
                </div>

                <%--<div class="tab-pane" id="tab_1_1_2">--%>
                    <%--<iframe src="hy" width="100%" height="450px"></iframe>--%>
                    <%--<span>共7个流</span>--%>
                <%--</div>--%>

                <div class="tab-pane" id="tab_1_1_3">
                    <iframe src="index" width="100%" height="450px"></iframe>
                    <span>共231个流</span>
                    <span class="glyphicon glyphicon-play pull-right" style="height: 30px;font-size: 28px">生成特征集</span>
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
</body>
</html>