<%@ page import="com.springapp.mvc.domain.NetFlow" %>
<%@ page import="com.springapp.mvc.service.NetFlowService" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="java.util.List" %>
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
    <%
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
        NetFlowService service = context.getBean(NetFlowService.class);
        List<NetFlow> list = service.getAllFlow();
        request.setAttribute("list", list);
    %>
</head>
<body>
<div style="background-color: white;font-size: 19px">
    <div class="portlet box blue col-md-8">
        <div class="portlet-title">
            <div class="caption"><i class="fa fa-reorder"></i>流量分类页面</div>
        </div>
        <div class="portlet-body">
            <div class="tabbable-custom nav-justified">
                <ul class="nav nav-tabs nav-justified">
                    <li class="active"><a href="#tab_1_1_1" data-toggle="tab">查看所有分类器</a></li>
                    <li><a href="#tab_1_1_2" data-toggle="tab">查看分类器阈值</a></li>
                    <li><a href="#tab_1_1_3" data-toggle="tab">新类型发现</a></li>
                </ul>
                <div class="tab-content" style="height: 555px;">
                    <div class="tab-pane active" id="tab_1_1_1">
                        <table border="5px" cellpadding="12px" bgcolor="white" align="center">
                            <tr>
                                <td>
                                    <a href="#" class="icon-btn pull-left" style="margin-top: 50px">
                                        <i class="fa fa-sitemap"></i>

                                        <p>分类器</p>
                                        Classifier-21
                                    </a>

                                    <div class="col-md-6 col-sm-6" id="starBar" style="height: 200px;width: 350px">
                                    </div>
                                    <p>分歧流量达到200，进行<a style="background-color: darkorange">新类型发现</a></p>
                                </td>
                                <td>
                                    <a href="#" class="icon-btn pull-left" style="margin-top: 50px">
                                        <i class="fa fa-sitemap"></i>

                                        <p>分类器</p>
                                        Classifier-22
                                    </a>

                                    <div class="col-md-6 col-sm-6" id="starBar1" style="height: 200px;width: 350px">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <a href="#" class="icon-btn pull-left" style="margin-top: 50px">
                                        <i class="fa fa-sitemap"></i>

                                        <p>分类器</p>
                                        Classifier-24
                                    </a>

                                    <div class="col-md-6 col-sm-6" id="starBar3" style="height: 200px;width: 350px">
                                    </div>
                                </td>
                                <td>
                                    <a href="#" class="icon-btn pull-left" style="margin-top: 50px">
                                        <i class="fa fa-sitemap"></i>

                                        <p>分类器</p>
                                        Classifier-25
                                    </a>

                                    <div class="col-md-6 col-sm-6" id="starBar4" style="height: 200px;width: 350px">
                                    </div>
                                </td>
                            </tr>
                        </table>
                        <div class="pull-right">
                            <div class="checker" id="uniform-closeButton">
                                    <span class="checked"><input id="closeButton" type="checkbox" value="checked"
                                                                 checked="" class="input-small">
                                    </span>
                            </div>
                            &nbsp;
                            <span class="pull-right">是否主动学习</span>
                        </div>
                        <div>
                            <span style="background-color: red;margin-left: 135px">
                                分类器数量达到上限，向管理员<a class="" data-toggle="modal" href="#basic">查询</a>!!!
                            </span>
                        </div>
                    </div>

                    <div class="tab-pane" id="tab_1_1_2">
                        <table border="5px" cellpadding="12px" bgcolor="white" align="center">
                            <tr>
                                <td>分类器1</td>
                                <td>分类器2</td>
                                <td>分类器3</td>
                                <td>分类器4</td>
                            </tr>
                            <tr>
                                <td>
                                    <table border="1px" cellpadding="5px" bgcolor="white" align="center">
                                        <thead>
                                        <th>类型</th>
                                        <th>阈值</th>
                                        </thead>
                                        <tr>
                                            <td>P2P</td>
                                            <td>0.8977</td>
                                        </tr>
                                        <tr>
                                            <td>WWW</td>
                                            <td>0.9725</td>
                                        </tr>
                                        <tr>
                                            <td>DATABASE</td>
                                            <td>0.9368</td>
                                        </tr>
                                        <tr>
                                            <td>FTP-PASV</td>
                                            <td>0.8979</td>
                                        </tr>
                                        <tr>
                                            <td>SERVICES</td>
                                            <td>0.9497</td>
                                        </tr>
                                        <tr>
                                            <td>ATTACK</td>
                                            <td>0.9312</td>
                                        </tr>
                                        <tr>
                                            <td>FTP-CONTROL</td>
                                            <td>0.978</td>
                                        </tr>
                                        <tr>
                                            <td>FTP-DATA</td>
                                            <td>0.9889</td>
                                        </tr>
                                        <tr>
                                            <td>MAIL</td>
                                            <td>0.9934</td>
                                        </tr>
                                    </table>
                                </td>

                                <td>
                                    <table border="1px" cellpadding="5px" bgcolor="white" align="center">
                                        <thead>
                                        <th>类型</th>
                                        <th>阈值</th>
                                        </thead>
                                        <tr>
                                            <td>P2P</td>
                                            <td>0.8977</td>
                                        </tr>
                                        <tr>
                                            <td>WWW</td>
                                            <td>0.9725</td>
                                        </tr>
                                        <tr>
                                            <td>DATABASE</td>
                                            <td>0.9368</td>
                                        </tr>
                                        <tr>
                                            <td>FTP-PASV</td>
                                            <td>0.8979</td>
                                        </tr>
                                        <tr>
                                            <td>SERVICES</td>
                                            <td>0.9497</td>
                                        </tr>
                                        <tr>
                                            <td>ATTACK</td>
                                            <td>0.9312</td>
                                        </tr>
                                        <tr>
                                            <td>FTP-CONTROL</td>
                                            <td>0.978</td>
                                        </tr>
                                        <tr>
                                            <td>FTP-DATA</td>
                                            <td>0.9889</td>
                                        </tr>
                                        <tr>
                                            <td>MAIL</td>
                                            <td>0.9934</td>
                                        </tr>
                                    </table>
                                </td>

                                <td>
                                    <table border="1px" cellpadding="5px" bgcolor="white" align="center">
                                        <thead>
                                        <th>类型</th>
                                        <th>阈值</th>
                                        </thead>
                                        <tr>
                                            <td>P2P</td>
                                            <td>0.8977</td>
                                        </tr>
                                        <tr>
                                            <td>WWW</td>
                                            <td>0.9725</td>
                                        </tr>
                                        <tr>
                                            <td>DATABASE</td>
                                            <td>0.9368</td>
                                        </tr>
                                        <tr>
                                            <td>FTP-PASV</td>
                                            <td>0.8979</td>
                                        </tr>
                                        <tr>
                                            <td>SERVICES</td>
                                            <td>0.9497</td>
                                        </tr>
                                        <tr>
                                            <td>ATTACK</td>
                                            <td>0.9312</td>
                                        </tr>
                                        <tr>
                                            <td>FTP-CONTROL</td>
                                            <td>0.978</td>
                                        </tr>
                                        <tr>
                                            <td>FTP-DATA</td>
                                            <td>0.9889</td>
                                        </tr>
                                        <tr>
                                            <td>MAIL</td>
                                            <td>0.9934</td>
                                        </tr>
                                    </table>
                                </td>

                                <td>
                                    <table border="1px" cellpadding="5px" bgcolor="white" align="center">
                                        <thead>
                                        <th>类型</th>
                                        <th>阈值</th>
                                        </thead>
                                        <tr>
                                            <td>P2P</td>
                                            <td>0.8977</td>
                                        </tr>
                                        <tr>
                                            <td>WWW</td>
                                            <td>0.9725</td>
                                        </tr>
                                        <tr>
                                            <td>DATABASE</td>
                                            <td>0.9368</td>
                                        </tr>
                                        <tr>
                                            <td>FTP-PASV</td>
                                            <td>0.8979</td>
                                        </tr>
                                        <tr>
                                            <td>SERVICES</td>
                                            <td>0.9497</td>
                                        </tr>
                                        <tr>
                                            <td>ATTACK</td>
                                            <td>0.9312</td>
                                        </tr>
                                        <tr>
                                            <td>FTP-CONTROL</td>
                                            <td>0.978</td>
                                        </tr>
                                        <tr>
                                            <td>FTP-DATA</td>
                                            <td>0.9889</td>
                                        </tr>
                                        <tr>
                                            <td>MAIL</td>
                                            <td>0.9934</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>

                    </div>

                    <div class="tab-pane" id="tab_1_1_3">
                        <div class="tabbable-custom nav-justified">
                            <ul class="nav nav-tabs nav-justified">
                                <li class="active"><a href="#tab_1_2_1" data-toggle="tab">类型1</a></li>
                                <li><a href="#tab_1_2_2" data-toggle="tab">类型2</a></li>
                                <li><a href="#tab_1_2_3" data-toggle="tab">类型3</a></li>
                            </ul>
                            <div class="tab-content" style="height: 555px;">
                                <div class="tab-pane active" id="tab_1_2_1">
                                    <iframe src="pacp" width="100%" height="400px"></iframe>
                                    <span class="pull-right">
                                        类型：<input type="text">
                                        <button type="button" class="btn blue">确定</button>
                                    </span>
                                </div>

                                <div class="tab-pane" id="tab_1_2_2">a</div>
                                <div class="tab-pane" id="tab_1_2_3">as
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal fade" id="basic" tabindex="-1" role="basic" aria-hidden="true">
                    <div class="modal-dialog pull-left">
                        <div class="modal-content pull-left" style="width: 1500px">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                <h4 class="modal-title">分类器中的流量</h4>
                            </div>
                            <div class="modal-body">
                                <div class="portlet box blue">
                                    <div class="portlet-title">
                                        <div class="caption"><i class="fa fa-comments"></i>流详细信息</div>
                                    </div>
                                    <div class="portlet-body">
                                        <table class="table table-striped table-hover table-bordered"
                                               id="sample_editable_1">
                                            <thead>
                                            <tr>
                                                <th>id</th>
                                                <th>class</th>
                                                <th>d1</th>
                                                <th>d2</th>
                                                <th>d3</th>
                                                <th>d4</th>
                                                <th>d5</th>
                                                <th>d6</th>
                                                <th>d7</th>
                                                <th>d8</th>
                                                <th>d9</th>
                                                <th>d10</th>
                                                <th>d11</th>
                                                <th>d12</th>
                                                <th>d13</th>
                                                <th>d14</th>
                                                <th>d15</th>
                                                <th>d16</th>
                                                <th>d17</th>
                                                <th>d18</th>
                                                <th>d19</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <td>${list.get(0).id}</td>
                                                <td><span
                                                        class="label label-sm label-default">${list.get(0).type}</span>
                                                </td>
                                                <td>${list.get(0).d1}</td>
                                                <td>${list.get(0).d2}</td>
                                                <td>${list.get(0).d3}</td>
                                                <td>${list.get(0).d4}</td>
                                                <td>${list.get(0).d5}</td>
                                                <td>${list.get(0).d6}</td>
                                                <td>${list.get(0).d7}</td>
                                                <td>${list.get(0).d8}</td>
                                                <td>${list.get(0).d9}</td>
                                                <td>${list.get(0).d10}</td>
                                                <td>${list.get(0).d11}</td>
                                                <td>${list.get(0).d12}</td>
                                                <td>${list.get(0).d13}</td>
                                                <td>${list.get(0).d14}</td>
                                                <td>${list.get(0).d15}</td>
                                                <td>${list.get(0).d16}</td>
                                                <td>${list.get(0).d17}</td>
                                                <td>${list.get(0).d18}</td>
                                                <td>${list.get(0).d19}</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                        <p>标注：<input type="text" data-tabindex="1" style="width: 200px;"></p>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn default blue" data-dismiss="modal">保存</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="js/echarts-2.2.7/src/echarts-all.js"></script>
<script type="text/javascript">
    var starBar = echarts.init(document.getElementById('starBar'));
    var starBar1 = echarts.init(document.getElementById('starBar1'));
    var starBar3 = echarts.init(document.getElementById('starBar3'));
    var starBar4 = echarts.init(document.getElementById('starBar4'));
    option = {
        title: {
            text: '分类器中各类数量',
            x: 'right'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data: ['SERVICES', 'DATABSE', 'FTP', 'MAIL', 'WWW']
        },
        calculable: true,
        series: [
            {
                name: '类型',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: [
                    {value: 335, name: 'SERVICES'},
                    {value: 310, name: 'DATABSE'},
                    {value: 234, name: 'FTP'},
                    {value: 135, name: 'MAIL'},
                    {value: 1548, name: 'WWW'}
                ]
            }
        ]
    };

    starBar.setOption(option);
    starBar1.setOption(option);
    starBar3.setOption(option);
    starBar4.setOption(option);
</script>

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
<%--<script src="assets/scripts/table-editable.js"></script>--%>
<script src="assets/scripts/table-advanced.js"></script>
<script>
    jQuery(document).ready(function () {
        App.init();
        TableAdvanced.init();
    });
</script>
</body>
</html>