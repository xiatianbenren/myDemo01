<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>欢迎注册EasyMall</title>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="css/regist.css" />
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type="text/javascript">
	var formObj={
			"checkForm": function(){
				var flag=true;
				flag=this.checkNull("username","用户名不能为空")&&flag;
				flag = this.checkNull("password", "密码不能为空") && flag;
				flag = this.checkNull("password2", "确认密码不能为空") && flag;
				flag = this.checkNull("nickname", "昵称不能为空") && flag;
				flag = this.checkNull("email", "邮箱不能为空") && flag;
				flag = this.checkNull("valistr", "验证码不能为空") && flag;
				
				flag=this.checkPassword("password","两次密码不一致")&&flag;
				
				flag=this.checkEmail("email","邮箱格式不正确")&&flag;
				return flag;
			},
			"checkNull": function(name,msg){
				var $input=$("input[name='"+name+"']");
				var value=$input.val();
				
				//清空之前的消息
				this.setMsg(name,"");
				
				if($.trim(value)==""){
					this.setMsg(name,msg);
					return false;
				}
				return true;
			},
			"checkEmail": function(name,msg){
				var email=$("input[name='"+name+"']").val();
				
				this.setMsg(name,"");
				
				this.checkNull(name,"邮箱不能为空");
				
				if(email!=""){
					var reg=/^\w+@\w+(\.\w+)+$/;
					if(!reg.test(email)){
						this.setMsg(name,msg);
						return false;
					}
				}
				return true;
			},
			"checkPassword": function(name,msg){
				var psw1=$("input[name='"+name+"']").val();
				var psw2=$("input[name='"+name+"2']").val();
				
				//清空之前的消息
				this.setMsg(name+"2","");
				
				this.checkNull(name+"2","确认密码不能为空");
				
				if($.trim(psw1)!="" &&$.trim(psw2)!=""){
					if(psw1!=psw2){
						this.setMsg(name+"2",msg);
						return false;
					}
				}
				return true;
			},
			
			"setMsg":function(name,msg){
				$("input[name='"+name+"']").nextAll("span").html(msg).css("color","red");
				
			}
	}
	
	$(function(){
		$("input[name='username']").blur(function(){
			formObj.checkNull("username","用户名不能为空");
		});
		$("input[name='password']").blur(function(){
			formObj.checkNull("password","密码不能为空");
		});
		$("input[name='password2']").blur(function(){
			formObj.checkNull("password2","确认密码不能为空");
			formObj.checkPassword("password","两次密码不一致");
		});
		
		$("input[name='nickname']").blur(function(){
			formObj.checkNull("nickname","昵称不能为空");
		});
		$("input[name='email']").blur(function(){
			formObj.checkNull("email","邮箱不能为空");
			formObj.checkEmail("email","邮箱格式不正确");
		});
		$("input[name='valistr']").blur(function(){
			formObj.checkNull("valistr","验证码不能为空");
		});
		
		$("#img").click(function(){
			var time=new Date().getTime();
			$(this).attr("src","<%=request.getContextPath()%>/ValiImageServlet?time="+time);
		});
	});
</script>
<body>
	<form action="<%=request.getContextPath()%>/RegistServlet"
		method="POST" onsubmit="return formObj.checkForm()">
		<h1>欢迎注册EasyMall</h1>
		<table>
			<tr>
				<td class="tds" colspan="2" style="color: red; text-align: center;">
					<%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg")%>
				</td>
			</tr>
			<tr>
				<td class="tds">用户名：</td>
				<td><input type="text" name="username"
					value="<%=request.getParameter("username") == null ? "" : request.getParameter("username")%>" />
					<span></span>
				</td>
			</tr>
			<tr>
				<td class="tds">密码：</td>
				<td><input type="password" name="password"
					value="<%=request.getParameter("password") == null ? "" : request.getParameter("password")%>" />
					<span></span>
				</td>
			</tr>
			<tr>
				<td class="tds">确认密码：</td>
				<td><input type="password" name="password2"
					value="<%=request.getParameter("password2") == null ? "" : request.getParameter("password2")%>" />
					<span></span>
				</td>
			</tr>
			<tr>
				<td class="tds">昵称：</td>
				<td><input type="text" name="nickname"
					value="<%=request.getParameter("nickname") == null ? "" : request.getParameter("nickname")%>" />
				<span></span>
				</td>
			</tr>
			<tr>
				<td class="tds">邮箱：</td>
				<td><input type="text" name="email"
					value="<%=request.getParameter("email") == null ? "" : request.getParameter("email")%>" />
				<span></span>
				</td>
			</tr>
			<tr>
				<td class="tds">验证码：</td>
				<td><input type="text" name="valistr" /> <img id="img"
					src="<%=request.getContextPath()%>/ValiImageServlet" />
					<span></span>
					</td>
			</tr>
			<tr>
				<td class="sub_td" colspan="2" class="tds"><input type="submit"
					value="注册用户" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
