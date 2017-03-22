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
            <div class="caption"><i class="fa fa-comments"></i>包详细信息</div>
        </div>
        <div class="portlet-body">
            <table class="table table-striped table-bordered table-hover" id="sample_1">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>源地址</th>
                    <th>目的地址</th>
                    <th>协议</th>
                    <th>长度</th>
                    <th>信息</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>192.168.1.88</td>
                    <td>112.80.23.112</td>
                    <td>TCP</td>
                    <td>137</td>
                    <td>50609 → 20014 [PSH, ACK] Seq=1 Ack=1 Win=65074 Len=83</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>112.80.23.112</td>
                    <td>192.168.1.88</td>
                    <td>TCP</td>
                    <td>97</td>
                    <td>20014 → 50609 [PSH, ACK] Seq=1 Ack=84 Win=14600 Len=43</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>192.168.1.88</td>
                    <td>112.80.23.112</td>
                    <td>TCP</td>
                    <td>54</td>
                    <td>50609 → 20014 [ACK] Seq=84 Ack=44 Win=65031 Len=0</td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>192.168.1.88</td>
                    <td>45.35.71.119</td>
                    <td>TCP</td>
                    <td>54</td>
                    <td>58777 → 29398 [ACK] Seq=1 Ack=1 Win=65160 Len=0</td>
                </tr>
                <tr>
                    <td>5</td>
                    <td>45.35.71.119</td>
                    <td>192.168.1.88</td>
                    <td>TCP</td>
                    <td>60</td>
                    <td>29398 → 58777 [ACK] Seq=1 Ack=557 Win=30024 Len=0</td>
                </tr>
                <tr>
                    <td>6</td>
                    <td>192.168.1.88</td>
                    <td>45.35.71.119</td>
                    <td>TCP</td>
                    <td>1502</td>
                    <td>58777 → 29398 [ACK] Seq=1180 Ack=173 Win=64988 Len=1448</td>
                </tr>
                <tr>
                    <td>7</td>
                    <td>192.168.1.88</td>
                    <td>27.33.189.7</td>
                    <td>TCP</td>
                    <td>1466</td>
                    <td>58730 → 27426 [ACK] Seq=275557 Ack=1 Win=64676 Len=1412</td>
                </tr>
                <tr>
                    <td>8</td>
                    <td>192.168.1.88</td>
                    <td>27.33.189.7</td>
                    <td>TCP</td>
                    <td>1466</td>
                    <td>58730 → 27426 [ACK] Seq=278381 Ack=1 Win=64676 Len=1412</td>
                </tr>
                <tr>
                    <td>9</td>
                    <td>192.168.1.88</td>
                    <td>27.33.189.7</td>
                    <td>TCP</td>
                    <td>1466</td>
                    <td>58730 → 27426 [ACK] Seq=288708 Ack=1 Win=64676 Len=1412</td>
                </tr>
                <tr>
                    <td>10</td>
                    <td>203.208.48.34</td>
                    <td>192.168.1.88</td>
                    <td>TCP</td>
                    <td>60</td>
                    <td>443 → 58792 [ACK] Seq=157 Ack=569 Win=30016 Len=0</td>
                </tr>
                <tr>
                    <td>11</td>
                    <td>192.168.1.88</td>
                    <td>27.33.189.7</td>
                    <td>TCP</td>
                    <td>1466</td>
                    <td>58730 → 27426 [ACK] Seq=294356 Ack=1 Win=64676</td>
                </tr>
                <tr>
                    <td>12</td>
                    <td>119.188.48.108</td>
                    <td>192.168.1.88</td>
                    <td>HTTP</td>
                    <td>261</td>
                    <td>HTTP/1.1 200 OK (text/plain)</td>
                </tr>
                <tr>
                    <td>13</td>
                    <td>192.168.1.88</td>
                    <td>119.188.48.108</td>
                    <td>HTTP</td>
                    <td>237</td>
                    <td>GET
                        /stat?appid=1018&ver=2.4.1.12&peerid=E839355DBAA2VIOQ&statnum=8&retry=1&errcode=&success=0&length=0&begin=1490085982&end=1490086882
                        HTTP/1.1
                    </td>
                </tr>
                </tbody>
            </table>
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