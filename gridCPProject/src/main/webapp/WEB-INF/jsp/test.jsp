<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link
	href="<%=request.getContextPath()%>/static/content/jquery-easyui-1.4.3/themes/gray/easyui.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/static/content/jquery-easyui-1.4.3/themes/icon.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=request.getContextPath()%>/static/content/jquery-easyui-1.4.3/jquery.min.js"
	type="text/javascript"></script>
<script
	src="<%=request.getContextPath()%>/static/content/jquery-easyui-1.4.3/jquery.easyui.min.js"
	type="text/javascript"></script>
<script
	src="<%=request.getContextPath()%>/static/content/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/static/js/JScriptCommon.js"
	type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/static/content/css/Common.css"
	rel="stylesheet" type="text/css" />
	
	<style type="text/css">
	 .msgBody{
	 	background: #eee; 
	 	overflow: hidden;
	 }
	 .msgDIV{
	 	padding-top:100px;
	 	padding-left:500px;
	 	font-family: serif;
	 	font-size: xx-large;
	 }
	</style>
</head>  
<body class="msgBody">
	<div class="msgDIV">
		<div>
			<h6>添加modelica模型xml</h6>
			<form action="<%=request.getContextPath() %>/modelica/upload" method="post" enctype="multipart/form-data">
				<input type="file" name="uploadfile"> 
				<input type="submit" value="提交">
			</form>
		</div>
		<div>
			<h6>添加流程模型xml</h6>
			<form action="<%=request.getContextPath() %>/coprocessor/upload" method="post" enctype="multipart/form-data">
				<input name="pkgId" id="pkgId" class="easyui-combotree" required="true" value="${pkgId}"
					style="width: 218px;height: 25px;" />
				<input type="file" name="uploadfile"> 
				<input type="submit" value="提交">
				<textarea rows="" cols=""></textarea>
			</form>		
		</div>
		
		<div>
			<button onclick="history.go(-1)">GoBack</button>
		</div>
		
	</div>
  </body>
  <script type="text/javascript">
	$("#pkgId").combotree({
		url : '<%=request.getContextPath()%>/department/tree',
		onShowPanel : function() {
			$("#pkgId").combotree('reload');
		}
	});
</script>
</html>
