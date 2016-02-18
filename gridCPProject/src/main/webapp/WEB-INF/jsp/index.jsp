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
</head>
<script type="text/javascript">
	try{ace.settings.check('navbar' , 'fixed')}catch(e){}
</script>
<body>
	<div class="navbar navbar-default" id="navbar">
		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand">
					<small>
						<i class="icon-leaf"></i>
						同元Syslink管理系統
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
	</div>
</body>
<script type="text/javascript">


</script>