<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style>
	.manage-right{
		width:75%;
		float: left;
	}
</style>
<c:import url="../admin/main.jsp"></c:import>
<div class="manage-right">
	<div class="row">
		<div class="col-md-12">
			<h2>管理员信息</h2>
			<a class="btn btn-primary" href="/admin/exportAdmin">导出数据为excel</a>
			<c:if test="${cur_admin.status == 1}">
				<a class="btn btn-primary" href="#" id="add">添加管理员</a>
			</c:if>
			<div class="btn-group">
<<<<<<< HEAD
				<c:if test="${cur_user.status == 1}">
				<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					查看接口数据 <span class="caret"></span>
				</button>
				</c:if>
=======
				<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					查看接口数据 <span class="caret"></span>
				</button>
>>>>>>> bf14b1afce697f4148e462c054988775fd4219a8
				<ul class="dropdown-menu">
					<li><a href="/admin/adminis?current=1&rowCount=10&sort[sender]=asc&searchPhrase=&id=b0df282a-0d67-40e5-8558-c9e93b7befed" target="_blank">JSON</a></li>
				</ul>
			</div>
			<table id="grid-data" class="table table-condensed table-hover table-striped">
				<thead>
				<tr>
					<th data-column-id="id"  data-identifier="true" data-type="numeric">编号</th>
					<th data-column-id="name">姓名</th>
					<th data-column-id="password">密码</th>
				</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
</div>
<script>
    $(document).ready(function(){
        var grid = $("#grid-data").bootgrid({
            ajax:true,
            url:"/admin/adminis",
            formatters: {
                "commands": function(column, row)
                {
                    return "<button type=\"button\" class=\"btn btn-xs btn-default command-edit\" data-row-id=\"" + row.id + "\">编辑<span class=\"fa fa-pencil\"></span></button> " +
                        "<button type=\"button\" class=\"btn btn-xs btn-default command-delete\" data-row-id=\"" + row.id + "\">删除<span class=\"fa fa-trash-o\"></span></button>"
                 }
            }
        }).on("loaded.rs.jquery.bootgrid", function()
        {
            grid.find(".command-edit").on("click", function(e)
            {
                $(".stumodal").modal();
                $.post('/admin/getAdminInfo',{id:$(this).data("row-id")},function(data){
                    $("#userId2").val(data.id);
                    $("#userName2").val(data.name);
                    $("#password").val(data.password);
                });
            }).end().find(".command-delete").on("click", function(e)
            {
                alert("您确定要删除:‘" + $(this).data("row-id")+"’用户吗");
                $.post("/admin/blackList",{id:$(this).data("row-id")},function(data){
                    if(data==true){
                        alert("删除成功");
					}
                    else{
                        alert("删除失败");
					}
                    $("#grid-data").bootgrid("reload");
                });
            });
        });
    });

    $(document).ready(function(){
        $("#add").click(function(){
            $(".addmodal").modal();
        });
    });

</script>

<div class="modal fade stumodal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">信息修改</h4>
			</div>
			<form:form action="/admin/updateInfoPower" commandName="admin" role="form" method="post">
				<div class="modal-body">
					<div class="form-group">
						<label for="userId2">id</label>
						<input type="text" name="userId" class="form-control" id="userId2" readonly="true">
					</div>
					<div class="form-group">
						<label for="userName2">name</label>
						<input type="text" name="userName" class="form-control" id="userName2">
					</div>
					<div class="form-group">
						<label for="password1">password</label>
						<input type="text" name="password" class="form-control" id="password1">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Save</button>
				</div>
			</form:form>
		</div>
	</div>
</div>

<div class="modal fade addmodal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">添加管理员</h4>
			</div>
			<form:form action="/admin/becomeManage" commandName="admin" role="form" method="post">
				<div class="modal-body">
					<div class="form-group">
						<label for="Id">id</label>
						<input type="text" name="userName" class="form-control" id="Id">
					</div>
					<div class="form-group">
						<label for="userName1">name</label>
						<input type="text" name="userName" class="form-control" id="userName1">
					</div>
					<div class="form-group">
						<label for="password">password</label>
						<input type="text" name="userMajor" class="form-control" id="password">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Add</button>
				</div>
			</form:form>
		</div>
	</div>
</div>
</body>
</html>