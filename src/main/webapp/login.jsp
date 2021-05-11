<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String basePath = request.getScheme() + "://" +
	request.getServerName() + ":" + request.getServerPort() +
	request.getContextPath() + "/";
%>
<html>
<head>
	<base href="<%=basePath%>">
<meta charset="UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function () {
			if (window.top != window){
				window.top.location=window.location;
			}
			//进入页面账号的文本框自动获取焦点
			$("#actId").focus(function () {
				$("#msg").html("");
			});
			//点击登录按钮，进行账号验证
			$("#butid").click(function () {
				login()
			})
			//按下键盘回车键，进行账号验证
			$(window).keydown(function (even) {
				if (even.keyCode == 13){
					login()
				}
			})
		})
		function login() {
			//判断账号和密码是否为空
			//获取账号和密码,并且去除前后空白
			var act = $.trim($("#actId").val());
			var pwd = $.trim($("#pwdId").val());
			if (act == "" || pwd == ""){
				//在msg中显示提醒
				$("#msg").html("账号和密码不能为空");
				//如果为空，就终止这个方法
				return false;
			}
			//如果执行到了这一步，表示账号和密码不为空，进行账号和密码的验证
			$.ajax({
				url:"settings/user/login.do",
				data:{
					"act":act,
					"pwd":pwd
				},
				dataType:"json",
				type:"post",
				success:function (data) {
					//此时从后台只能收到两种结果一种是，账号密码正确，一种是失败
					//data:{"success":"true"/"success":"false","msg","哪错了"}
					if (data.success){
						//表示账号密码是正确的，跳转到下个页面
						window.location.href = "workbench/index.jsp";
					}else {
						//表示账号密码输入错误
						//在msg中打印错误信息
						$("#msg").html(data.msg);
					}
				}
			})
		}
	</script>
</head>
<body>
	<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
		<img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
	</div>
	<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;2017&nbsp;动力节点</span></div>
	</div>
	
	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1>登录</h1>
			</div>
			<form action="workbench/index.jsp" class="form-horizontal" role="form">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input class="form-control" type="text" placeholder="用户名" id="actId">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input class="form-control" type="password" placeholder="密码" id="pwdId">
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
						
							<span id="msg" style="color: red"></span>
						
					</div>
					<button type="button" id="butid" class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>