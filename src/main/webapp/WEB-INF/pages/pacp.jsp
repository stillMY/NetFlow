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
                        <td>22</td>
                        <td>192.168.1.22</td>
                        <td>234.123.12.1</td>
                        <td>UDP</td>
                        <td>266</td>
                        <td>20141 → 20141  Len=224</td>
                    </tr>
                    <tr>
                        <td>23</td>
                        <td>192.168.1.88</td>
                        <td>59.111.160.195</td>
                        <td>TCP</td>
                        <td>66</td>
                        <td>51835 → 80 [SYN] Seq=0 Win=8192 Len=0 MSS=1460 WS=256 SACK_PERM=1</td>
                    </tr>
                    <tr>
                        <td>24</td>
                        <td>59.111.160.195</td>
                        <td>192.168.1.88</td>
                        <td>TCP</td>
                        <td>60</td>
                        <td>80 → 51835 [SYN, ACK] Seq=0 Ack=1 Win=2920 Len=0 MSS=1460</td>
                    </tr>
                    <tr>
                        <td>25</td>
                        <td>192.168.1.88</td>
                        <td>59.111.160.195</td>
                        <td>TCP</td>
                        <td>54</td>
                        <td>51835 → 80 [ACK] Seq=1 Ack=1 Win=64240 Len=0</td>
                    </tr>
                    <tr>
                        <td>26</td>
                        <td>Tp-LinkT_7a:8c:0e</td>
                        <td>HewlettP_5d:ba:a2</td>
                        <td>ARP</td>
                        <td>60</td>
                        <td>Who has 192.168.1.88? Tell 192.168.1.1</td>
                    </tr>
                    <tr>
                        <td>26</td>
                        <td>HewlettP_5d:ba:a2</td>
                        <td>Tp-LinkT_7a:8c:0e</td>
                        <td>ARP</td>
                        <td>42</td>
                        <td>192.168.1.88 is at e8:39:35:5d:ba:a2</td>
                    </tr>
                    <tr>
                        <td>27</td>
                        <td>59.111.160.195</td>
                        <td>192.168.1.88</td>
                        <td>HTTP</td>
                        <td>74</td>
                        <td>HTTP/1.1 200 OK  (text/plain)</td>
                    </tr>
                    <tr>
                        <td>26</td>
                        <td>HewlettP_5d:ba:a2</td>
                        <td>Tp-LinkT_7a:8c:0e</td>
                        <td>ARP</td>
                        <td>42</td>
                        <td>192.168.1.88 is at e8:39:35:5d:ba:a2</td>
                    </tr>
                    <tr>
                        <td>27</td>
                        <td>59.111.160.195</td>
                        <td>192.168.1.88</td>
                        <td>HTTP</td>
                        <td>74</td>
                        <td>HTTP/1.1 200 OK  (text/plain)</td>
                    </tr>
                    <tr>
                        <td>26</td>
                        <td>HewlettP_5d:ba:a2</td>
                        <td>Tp-LinkT_7a:8c:0e</td>
                        <td>ARP</td>
                        <td>42</td>
                        <td>192.168.1.88 is at e8:39:35:5d:ba:a2</td>
                    </tr>
                    <tr>
                        <td>27</td>
                        <td>59.111.160.195</td>
                        <td>192.168.1.88</td>
                        <td>HTTP</td>
                        <td>74</td>
                        <td>HTTP/1.1 200 OK  (text/plain)</td>
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
<script src="assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js" type="text/javascript" ></script>
<script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="assets/plugins/jquery.cookie.min.js" type="text/javascript"></script>
<script src="assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript" ></script>
<script type="text/javascript" src="assets/plugins/select2/select2.min.js"></script>
<%--<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>--%>
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