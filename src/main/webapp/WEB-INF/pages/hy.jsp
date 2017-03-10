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
    <link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2_metro.css"/>
    <link rel="stylesheet" href="assets/plugins/data-tables/DT_bootstrap.css"/>
    <!-- END PAGE LEVEL STYLES -->
    <!-- BEGIN THEME STYLES -->
    <link href="assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="assets/css/custom.css" rel="stylesheet" type="text/css"/>
    <!-- END THEME STYLES -->
    <link rel="shortcut icon" href="favicon.ico"/>

</head>
<body>
<div class="col-md-13 col-sm-13">
    <div class="portlet box blue">
        <div class="portlet-title">
            <div class="caption"><i class="fa fa-comments"></i>流详细信息</div>
        </div>
        <div class="portlet-body">
            <div class="table-responsive">
                <table class="table table-striped table-hover" id="sample_editable_1">
                    <tbody>
                        <tr>
                            <td>流ID</td>
                            <td>包ID</td>
                            <td>源地址</td>
                            <td>目的地址</td>
                            <td>协议</td>
                            <td>长度</td>
                            <td>信息</td>
                            <td>类型</td>
                        </tr>
                        <tr class="success">
                            <td>1</td>
                            <td>22</td>
                            <td>192.168.1.22</td>
                            <td>234.123.12.1</td>
                            <td>UDP</td>
                            <td>266</td>
                            <td>20141 → 20141  Len=224</td>
                        </tr>
                        <tr class="success">
                            <td>1</td>
                            <td>22</td>
                            <td>192.168.1.22</td>
                            <td>234.123.12.1</td>
                            <td>UDP</td>
                            <td>266</td>
                            <td>20141 → 20141  Len=224</td>
                            <td>WWW</td>
                        </tr>
                        <tr class="success">
                            <td>1</td>
                            <td>22</td>
                            <td>192.168.1.22</td>
                            <td>234.123.12.1</td>
                            <td>UDP</td>
                            <td>266</td>
                            <td>20141 → 20141  Len=224</td>
                        </tr>
                        <tr class="warning">
                            <td>2</td>
                            <td>22</td>
                            <td>192.168.1.22</td>
                            <td>234.123.12.1</td>
                            <td>UDP</td>
                            <td>266</td>
                            <td>20141 → 20141  Len=224</td>
                        </tr>
                        <tr class="warning">
                            <td>2</td>
                            <td>22</td>
                            <td>192.168.1.22</td>
                            <td>234.123.12.1</td>
                            <td>UDP</td>
                            <td>266</td>
                            <td>20141 → 20141  Len=224</td>
                            <td>P2P</td>
                        </tr>
                        <tr class="warning">
                            <td>2</td>
                            <td>22</td>
                            <td>192.168.1.22</td>
                            <td>234.123.12.1</td>
                            <td>UDP</td>
                            <td>266</td>
                            <td>20141 → 20141  Len=224</td>
                        </tr>
                        <tr class="danger">
                            <td>3</td>
                            <td>22</td>
                            <td>192.168.1.22</td>
                            <td>234.123.12.1</td>
                            <td>UDP</td>
                            <td>266</td>
                            <td>20141 → 20141  Len=224</td>
                        </tr>
                        <tr class="danger">
                            <td>3</td>
                            <td>22</td>
                            <td>192.168.1.22</td>
                            <td>234.123.12.1</td>
                            <td>UDP</td>
                            <td>266</td>
                            <td>20141 → 20141  Len=224</td>
                        </tr>
                        <tr class="danger">
                            <td>3</td>
                            <td>22</td>
                            <td>192.168.1.22</td>
                            <td>234.123.12.1</td>
                            <td>UDP</td>
                            <td>266</td>
                            <td>20141 → 20141  Len=224</td>
                            <td>MAIL</td>
                        </tr>
                        <tr class="danger">
                            <td>3</td>
                            <td>22</td>
                            <td>192.168.1.22</td>
                            <td>234.123.12.1</td>
                            <td>UDP</td>
                            <td>266</td>
                            <td>20141 → 20141  Len=224</td>
                        </tr>
                        <tr class="danger">
                            <td>3</td>
                            <td>22</td>
                            <td>192.168.1.22</td>
                            <td>234.123.12.1</td>
                            <td>UDP</td>
                            <td>266</td>
                            <td>20141 → 20141  Len=224</td>
                        </tr>
                        <tr class="danger">
                            <td>3</td>
                            <td>22</td>
                            <td>192.168.1.22</td>
                            <td>234.123.12.1</td>
                            <td>UDP</td>
                            <td>266</td>
                            <td>20141 → 20141  Len=224</td>
                            <td>MAIL</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="assets/plugins/respond.min.js"></script>
<script src="assets/plugins/excanvas.min.js"></script>
<script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js"
        type="text/javascript"></script>
<script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="assets/plugins/jquery.cookie.min.js" type="text/javascript"></script>
<script src="assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script type="text/javascript" src="assets/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="assets/plugins/data-tables/DT_bootstrap.js"></script>
<script src="assets/scripts/app.js"></script>
<script src="assets/scripts/table-advanced.js"></script>
<script>
    jQuery(document).ready(function () {
        App.init();
        TableAdvanced.init();
    });
</script>
</body>
</html>