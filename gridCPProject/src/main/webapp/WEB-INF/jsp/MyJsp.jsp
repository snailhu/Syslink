<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

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
	
<script type="text/javascript">
	var modelTree;
	var componentVar;
	$(function () {
	//获取模型树
	modelTree = $("#showModelTree").tree({
                url: '<%=request.getContextPath()%>/modelica/modelTree',
                onClick: function(node){
		                	if(node.nodeType!="package"){
		                		var orgId=node.id;
		                	var nodetype = node.nodeType;
		                	$("#componentVars").treegrid('load', {		                		
		    	                orgId:orgId,
		    	                type:nodetype  	                             
		    	                 });
		                }
                	}
                	
            });
     $("#componentVars").treegrid({
		url: '<%=request.getContextPath()%>/modelica/getComponetVarTree',
        idField: 'id',
        fitColumns:true,
        treeField: 'text',
        rownumbers: true,
        onLoadSuccess:function(data){
        alert("123");
            alert(data);
        },
        onLoadError:function(arguments){
        alert("12345");
            alert(data);
        },
        frozenColumns: [[{
            field: 'id',
            width: 50,
            checkbox: true          
        }, {
            field: 'text',
            title: '组件名',
            width: 100,
            sortable: true
        }]],
        columns: [[ 
		 {
		    field: 'varName',
		    title: '变量名',
		    width: 100,
		    sortable: true
		 },{
		    field: 'value',
		    title: '变量值',
		    width: 100
		 },{
            field: 'units',
            title: '单位',
            width: 100
        }, {
            field: 'description',
            title: '描述',
            width: 300
        }
        ]]     
    });
     	

    });
    


</script>
  </head>
  
  <body>
  	<div id="showModelTree" fit="true"></div>
	<table id="componentVars" ></table> 	
  </body>
</html>
