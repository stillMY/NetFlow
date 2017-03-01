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
</head>
<body>
<div class="col-md-12 col-sm-12" style="background-color: white;height: 450px">
    <div style="height: 30px"></div>
    <table border="5px" cellpadding="12px" bgcolor="white" align="center" >
        <tr>
            <td>
                <a href="#" class="icon-btn">
                    <i class="fa fa-sitemap"></i>
                    <p>分类器</p>
                    Classifier-41
                </a>
                <table class="pull-right">
                    <tr><td><a class="btn blue" data-toggle="modal" href="#basic">查看阈值</a></td></tr>
                    <tr><td><a class="btn red" data-toggle="modal" href="#basic">查看流量</a></td></tr>
                    <tr><td><a class="btn green" data-toggle="modal" href="#basic">查看OUT</a></td></tr>
                </table>
            </td>
            <td>
                <a href="#" class="icon-btn">
                    <i class="fa fa-sitemap"></i>
                    <p>分类器</p>
                    Classifier-41
                </a>
                <table class="pull-right">
                    <tr><td><a class="btn blue" data-toggle="modal" href="#basic">查看阈值</a></td></tr>
                    <tr><td><a class="btn red" data-toggle="modal" href="#basic">查看流量</a></td></tr>
                    <tr><td><a class="btn green" data-toggle="modal" href="#basic">查看OUT</a></td></tr>
                </table>
            </td>
            <td>
                <a href="#" class="icon-btn">
                    <i class="fa fa-sitemap"></i>
                    <p>分类器</p>
                    Classifier-41
                </a>
                <table class="pull-right">
                    <tr><td><a class="btn blue" data-toggle="modal" href="#basic">查看阈值</a></td></tr>
                    <tr><td><a class="btn red" data-toggle="modal" href="#basic">查看流量</a></td></tr>
                    <tr><td><a class="btn green" data-toggle="modal" href="#basic">查看OUT</a></td></tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <a href="#" class="icon-btn">
                    <i class="fa fa-sitemap"></i>
                    <p>分类器</p>
                    Classifier-41
                </a>
                <table class="pull-right">
                    <tr><td><a class="btn blue" data-toggle="modal" href="#basic">查看阈值</a></td></tr>
                    <tr><td><a class="btn red" data-toggle="modal" href="#basic">查看流量</a></td></tr>
                    <tr><td><a class="btn green" data-toggle="modal" href="#basic">查看OUT</a></td></tr>
                </table>
            </td>
            <td>
                <a href="#" class="icon-btn">
                    <i class="fa fa-sitemap"></i>
                    <p>分类器</p>
                    Classifier-41
                </a>
                <table class="pull-right">
                    <tr><td><a class="btn blue" data-toggle="modal" href="#basic">查看阈值</a></td></tr>
                    <tr><td><a class="btn red" data-toggle="modal" href="#basic">查看流量</a></td></tr>
                    <tr><td><a class="btn green" data-toggle="modal" href="#basic">查看OUT</a></td></tr>
                </table>
            </td>
            <td>
                <a href="#" class="icon-btn">
                    <i class="fa fa-sitemap"></i>
                    <p>分类器</p>
                    Classifier-41
                </a>
                <table class="pull-right">
                    <tr><td><a class="btn blue" data-toggle="modal" href="#basic">查看阈值</a></td></tr>
                    <tr><td><a class="btn red" data-toggle="modal" href="#basic">查看流量</a></td></tr>
                    <tr><td><a class="btn green" data-toggle="modal" href="#basic">查看OUT</a></td></tr>
                </table>
            </td>
        </tr>

    </table>

    <%--<a href="#" class="icon-btn">--%>
        <%--<i class="fa fa-sitemap"></i>--%>
        <%--<div>分类器</div>--%>
    <%--</a>--%>
    <%--<a href="#" class="icon-btn">--%>
        <%--<i class="fa fa-sitemap"></i>--%>
        <%--<div>分类器</div>--%>
    <%--</a>--%>
    <%--<a href="#" class="icon-btn">--%>
        <%--<i class="fa fa-sitemap"></i>--%>
        <%--<div>分类器</div>--%>
    <%--</a>--%>

    <%--<a class="btn default" data-toggle="modal" href="#basic">查看阈值</a>--%>

    <div class="modal fade" id="basic" tabindex="-1" role="basic" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title">各类阈值</h4>
                </div>
                <div class="modal-body">
                    <table>
                        <thead>
                        <th>类型</th>
                        <th>阈值</th>
                        </thead>
                        <tr>
                            <td>P2P</td>
                            <td>0.9811223737</td>
                        </tr>
                        <tr>
                            <td>WWW</td>
                            <td>0.9811223737</td>
                        </tr>
                        <tr>
                            <td>DATABASE</td>
                            <td>0.9811223737</td>
                        </tr>
                        <tr>
                            <td>FTP-PASV</td>
                            <td>0.9811223737</td>
                        </tr>
                        <tr>
                            <td>SERVICES</td>
                            <td>0.9811223737</td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn default" data-dismiss="modal">关闭</button>
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