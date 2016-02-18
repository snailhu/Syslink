<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>协同管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="keywords" content="同元协同,tongyuan协同" />
	<meta name="description" content="同元协同,tongyuan协同" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<jsp:include page="basic_css.jsp"></jsp:include>
	<jsp:include page="basic_script.jsp"></jsp:include>
    
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
	<style>
		.nav-list>li>a {
	    display: block;
	    height: 38px;
	    line-height: 24px;
	    padding: 9px;
	    background-color: #f9f9f9;
	    color: #585858;
	    text-shadow: none!important;
	    font-size: 13px;
	    text-decoration: none;}

	</style>
<script type="text/javascript">
	try{ace.settings.check('navbar' , 'fixed')}catch(e){}
</script>
<script type="text/javascript">
	var modelTree;
	var componentVar;
	$(function () {
	//获取模型树
	modelTree = $("#showModelTree").tree({
                url: '<%=request.getContextPath()%>/coprocessor/modelTree',
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
		url: '<%=request.getContextPath()%>/coprocessor/getComponetVarTree',
        idField: 'id',
        fitColumns:true,
        treeField: 'text',
        rownumbers: true,
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
	
	<div class="navbar navbar-default" id="navbar">
		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand">
					<small>
						<i class="icon-leaf"></i>
						同元協同管理系統
					</small>
				</a><!-- /.brand -->
			</div><!-- /.navbar-header -->
		</div>
		<div class="navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">
				<li class="light-blue">
					<a data-toggle="dropdown" href="#" class="dropdown-toggle">
						<img class="nav-user-photo" src="<%=request.getContextPath() %>/static/assets/avatars/user.jpg" alt="Jason's Photo" />
						<span class="user-info">
							<small>欢迎光临,</small>
							Jason
						</span>
						<i class="icon-caret-down"></i>
					</a>
					<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
						<li>
							<a href="#">
								<i class="icon-cog"></i>
								设置
							</a>
						</li>
						<li>
							<a href="#">
								<i class="icon-user"></i>
								个人资料
							</a>
						</li>
						<li class="divider"></li>
						<li>
							<a href="#">
								<i class="icon-off"></i>
								退出
							</a>
						</li>
					</ul>
				</li>		
			</ul>
		</div>
	</div>		
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>	
		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#">
				<span class="menu-text"></span>
			</a>
			<div class="sidebar" id="sidebar">
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
				</script>
				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<button class="btn btn-success">
							<i class="icon-signal"></i>
						</button>

						<button class="btn btn-info">
							<i class="icon-pencil"></i>
						</button>

						<button class="btn btn-warning">
							<i class="icon-group"></i>
						</button>

						<button class="btn btn-danger">
							<i class="icon-cogs"></i>
						</button>
					</div>

					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span>

						<span class="btn btn-info"></span>

						<span class="btn btn-warning"></span>

						<span class="btn btn-danger"></span>
					</div>
				</div><!-- #sidebar-shortcuts -->
				<ul class="nav nav-list">
					<li>
						<a href="#" class="dropdown-toggle">
							<i class="icon-edit"></i>
							<span class="menu-text">模型管理</span>
							<b class="arrow icon-angle-down"></b>
						</a>
						<ul class="submenu">
								<li>
									<a href="<%=request.getContextPath() %>/modelica/showTypes">
										<i class="icon-double-angle-right"></i>
										modelica模型
									</a>
								</li>
								<li>
									<a href="<%=request.getContextPath() %>/coprocessor/showTypes">
										<i class="icon-double-angle-right"></i>
										流程模型
									</a>
								</li>
								<li>
									<a href="<%=request.getContextPath() %>/coprocessor/test">
										<i class="icon-double-angle-right"></i>
										模型上传
									</a>
								</li>
						</ul>
						
					</li>
					
					<li class="active">
						<a href="#">
							<i class="icon-dashboard"></i>
							<span class="menu-text">仿真管理</span>
						</a>
					</li>
					<li class="active">
						<a href="#">
							<i class="icon-dashboard"></i>
							<span class="menu-text">系统管理</span>
						</a>
					</li>
					<li class="active">
						<a href="<%=request.getContextPath() %>/modelica/showTypes">
							<i class="icon-dashboard"></i>
							<span class="menu-text">仓库管理</span>
						</a>
					</li>
					
				</ul>
				<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
				</div>
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
				</script>
			</div>
		</div>
		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
				</script>
				<div class="nav-search" id="nav-search">
					<form class="form-search">
						<span class="input-icon">
							<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
							<i class="icon-search nav-search-icon"></i>
						</span>
					</form>
				</div><!-- #nav-search -->
			</div>

			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<div class="row">
							<div class="col-sm-3">
								<div class="widget-box">
									<div class="widget-header header-color-blue2">
										<h4 class="lighter smaller">模型组件</h4>
									</div>
									<div class="widget-body">
										<div class="widget-main padding-8">
<!-- 											<div id="jqxTree"></div> -->
											<ul id="showModelTree" fit="true"></ul>
										</div>
									</div>
								</div>
							</div>
							<div class="col-sm-9">
								<div class="widget-box">
									<div class="widget-header header-color-green2">
										<h4 class="lighter smaller">组件变量</h4>
									</div>
									<div class="widget-body">
 										<div class="widget-main padding-8"> 
											<table id="componentVars" ></table> 							
 										</div> 
									</div>
								</div>
							</div>
						</div>					
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.page-content -->
		</div><!-- /.main-content -->
	</div>
</body>

<script type="text/javascript">


</script>