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
    <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="col-md-13 col-sm-13">
    <div class="portlet box blue">
        <div class="portlet-title">
            <div class="caption"><i class="fa fa-comments"></i>流特征信息</div>
        </div>
        <div class="portlet-body">
            <div class="table-responsive">
                <table class="table table-striped table-hover" id="sample_editable_1">
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
                    <c:forEach items="${list}" var="flow">
                        <tr>
                            <td>${flow.id}</td>
                            <td><span class="label label-sm label-success">${flow.type}</span></td>
                            <td>${flow.d1}</td>
                            <td>${flow.d2}</td>
                            <td>${flow.d3}</td>
                            <td>${flow.d4}</td>
                            <td>${flow.d5}</td>
                            <td>${flow.d6}</td>
                            <td>${flow.d7}</td>
                            <td>${flow.d8}</td>
                            <td>${flow.d9}</td>
                            <td>${flow.d10}</td>
                            <td>${flow.d11}</td>
                            <td>${flow.d12}</td>
                            <td>${flow.d13}</td>
                            <td>${flow.d14}</td>
                            <td>${flow.d15}</td>
                            <td>${flow.d16}</td>
                            <td>${flow.d17}</td>
                            <td>${flow.d18}</td>
                            <td>${flow.d19}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<%--<script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>--%>
<%--<script src="assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>--%>
<%--<script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>--%>

</body>
</html>