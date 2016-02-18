<@override name="header">
	<script type="text/javascript">
		try{ace.settings.check('navbar' , 'fixed')}catch(e){}
	</script>
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
						<img class="nav-user-photo" src="${base}/static/assets/avatars/user.jpg" alt="Jason's Photo" />
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
</@override>
	
<@override name="content_left">
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
						<button class="btn btn-success btn-primary" data-toggle="modal" data-target="#exampleModal" >
							<i class="icon-plus"></i>
						</button>

						<button class="btn btn-info">
							<i class="icon-trash"></i>
						</button>

						<button class="btn btn-warning">
							<i class="icon-edit"></i>
						</button>

						<button class="btn btn-danger">
							<i class="icon-cogs"></i>
						</button>
					</div>			
					<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
					  <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					        <h4 class="modal-title" id="exampleModalLabel">增加顶层包目录</h4>
					      </div>
					      <div class="modal-body">
					        <form id="pkg_des">
					          <div class="form-group">
					            <label for="recipient-name" class="control-label">包名称:</label>
					            <input type="text" class="form-control" id="pkg-name">
					          </div>
					          <div class="form-group">
					            <label for="message-text" class="control-label">包描述:</label>
					            <textarea class="form-control" id="pkg-text"></textarea>
					          </div>
					        </form>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					        <button type="button" class="btn btn-primary" onclick="save_pkg()">保存</button>
					      </div>
					    </div>
					  </div>
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
									<a href="${base}/modelica/showTypes">
										<i class="icon-double-angle-right"></i>
										modelica模型
									</a>
								</li>
								<li>
									<a href="${base}/coprocessor/showTypes">
										<i class="icon-double-angle-right"></i>
										流程模型
									</a>
								</li>
								<li>
									<a href="${base}/coprocessor/test">
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
					<li>
						<a href="#" class="dropdown-toggle">
							<i class="icon-edit"></i>
							<span class="menu-text">系统管理</span>
						</a>
						<ul class="submenu">
								<li>
									<a href="<%=request.getContextPath() %>/modelica/showTypes">
										<i class="icon-double-angle-right"></i>
										用户管理
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
						<a href="${base}/modelica/showTypes">
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
		<script type="text/javascript">
			function save_pkg(){
			alert("123");
				$.post('save_pkg',
					{
						"pkg_name":$('#pkg-name').val(),
						"pkg_des":$('#pkg-text').val()
					},function(data){
						$('#exampleModal').modal('hide')
					}												
				)
			
			}		
		</script>		
</@override>
<@extends name="base_second.ftl"/>
