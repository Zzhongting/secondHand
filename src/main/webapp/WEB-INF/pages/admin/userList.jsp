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
			<h2>用户信息</h2>
			<a class="btn btn-primary" href="/admin/exportUser">导出数据为excel</a>
			<a class="btn btn-primary" href="#" id="add">添加管理员</a>
			<div class="btn-group">
				<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					查看接口数据 <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="/admin/users?current=1&rowCount=10&sort[sender]=asc&searchPhrase=&id=b0df282a-0d67-40e5-8558-c9e93b7befed" target="_blank">JSON</a></li>
				</ul>
			</div>
			<table id="grid-data" class="table table-condensed table-hover table-striped">
				<thead>
				<tr>
					<th data-column-id="id"  data-identifier="true" data-type="numeric">序号</th>
					<th data-column-id="phone">手机号</th>
					<th data-column-id="username">姓名</th>
					<th data-column-id="qq">QQ</th>
					<th data-column-id="createAt">开通时间</th>
					<th data-column-id="goodsNum">商品数量</th>
					<th data-column-id="power">用户活跃度</th>
					<th data-column-id="commands" data-formatter="commands" data-sortable="false">Commands</th>
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
            url:"/admin/users",
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
                $.post('/admin/getUserInfo',{id:$(this).data("row-id")},function(data){
                    $("#userId2").val(data.id);
                    $("#userName2").val(data.username);
                    $("#userPower2").val(data.power);
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
			<form:form action="/admin/updateInfoPower" commandName="user" role="form" method="post">
				<div class="modal-body">
					<div class="form-group">
						<label for="userId2">userId</label>
						<input type="text" name="userId" class="form-control" id="userId2" readonly="true">
					</div>
					<div class="form-group">
						<label for="userName2">userName</label>
						<input type="text" name="userName" class="form-control" id="userName2" readonly="true">
					</div>
					<div class="form-group">
						<label for="userPower2">userPower</label>
						<input type="text" name="userPower" class="form-control" id="userPower2">
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
			<form:form action="/admin/becomeManage" commandName="user" role="form" method="post">
				<div class="modal-body">
					<div class="form-group">
						<label for="userName1">userName</label>
						<input type="text" name="userName" class="form-control" id="userName1">
					</div>
					<div class="form-group">
						<label for="phone">phone</label>
						<input type="text" name="userMajor" class="form-control" id="Phone">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Add User</button>
				</div>
			</form:form>
		</div>
	</div>
</div>
</body>
</html>