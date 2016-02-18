<@override name="header">
	<link
	href="${base}/static/content/jquery-easyui-1.4.3/themes/gray/easyui.css"
	rel="stylesheet" type="text/css" />
<link
	href="${base}/static/content/jquery-easyui-1.4.3/themes/icon.css"
	rel="stylesheet" type="text/css" />
<script
	src="${base}/static/content/jquery-easyui-1.4.3/jquery.easyui.min.js"
	type="text/javascript"></script>
<script
	src="${base}/static/content/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script src="${base}/static/content/js/jquery.edatagrid.js"
	type="text/javascript"></script>
<script src="${base}/static/js/JScriptCommon.js"
	type="text/javascript"></script>
<link href="${base}/static/content/css/Common.css"
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
		    text-decoration: none;
	    }
		.sidebar-shortcuts-large {
		    padding-bottom: 5px;
		}
		

.tree-title {
    font-size: 14px;
    font-weight: bold;
    color: #428BCA;
    display: inline-block;
    text-decoration: none;
    vertical-align: top;
    white-space: nowrap;
    padding: 0 2px;
    height: 18px;
    line-height: 18px;
}
.tree-node {
    height: 18px;
    white-space: nowrap;
    cursor: pointer;
}
.tree li {
    list-style-type:none;
    margin:0;
    padding:10px 5px 0 5px;
    /*position:relative*/
}
.tree li::before, .tree li::after {
    /*content:'';*/
    left:-20px;
    position:absolute;
    right:auto
}
.tree li::before {
    border-left:1px solid #999;
    bottom:50px;
    height:100%;
    top:0;
    width:1px
}
.tree li::after {
    border-top:1px solid #999;
    height:20px;
    top:25px;
    width:25px
}
.tree li span {
    -moz-border-radius:5px;
    /*-webkit-border-radius:5px;*/
    /*border:1px solid #999;*/
    /*border-radius:5px;*/
    display:inline-block;
    padding:0px 8px;
    text-decoration:none
}

.tree li.parent_li>span {
    cursor:pointer
}
.tree>ul>li::before, .tree>ul>li::after {
    border:0
}
.tree li:last-child::before {
    height:30px
}
.tree li.parent_li>span:hover, .tree li.parent_li>span:hover+ul li span {
    background:#eee;
    border:1px solid #94a0b4;
    color:#000
}
.icon-folder{
	background: url('${base}/static/img/folder.png');
    background-color: gray; 
	background-repeat:no-repeat;
	background-position:center;
}
.tree-node-hover {
  background: #E3E3E2;
  color: #000000;
}
#navbar-container a {
	font-size:18px;
	text-decoration:none;
	cursor: pointer;
}
	</style>
	<script type="text/javascript">
		$(function(){		
		//获取包目录树
		modelTree = $("#showPkgTree").tree({
	                url: '${base}/modelPackage/getInipkg',
	                onClick: function(node){
							window.location.href='${base}/model/getModeList/'+node.id+'/';
	                	}                	
	            });	
	     $('#showPkgTree').tree({
				onContextMenu: function(e, node){
					e.preventDefault();
					// 查找节点
					$('#showPkgTree').tree('select');
					$('#show_right').attr('name',node.id);
					// 显示快捷菜单
					$('#show_right').menu('show', {
						left: e.pageX,
						top: e.pageY
					});
				}
			});
	      $('#modelPackageType').click(function(){
	      	window.location.href='${base}/modelPackage/getpkgTypeList';
	      });    	       					
		})
	</script>
	<script type="text/javascript">
		try{ace.settings.check('navbar' , 'fixed')}catch(e){}
	</script>
	<div class="navbar navbar-default" id="navbar">
		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left" style="margin:3px 0px 3px 80px !important;">
				<div class="navbar-brand">
					<small>
						<i class="icon-leaf"></i>
						<font size="5px">Syslink</font>
					</small>
					
				</div><!-- /.brand -->
				<a  href="http://www.gogs.syslink.cn:3000/" class="navbar-brand">
					<small>
						控制面板	
					</small>
				</a>
				<a href="http://www.gogs.syslink.cn:3000/issues" class="navbar-brand">
					<small>
						工单管理	
					</small>
				</a>
				<a href="http://www.gogs.syslink.cn:3000/pulls" class="navbar-brand">
					<small>
						合并请求
					</small>
				</a>
				<a href="http://www.gogs.syslink.cn:3000/explore" class="navbar-brand">
					<small>
						探索	
					</small>
				</a>

			</div><!-- /.navbar-header -->
		</div>
		<div class="navbar-header pull-right" role="navigation" style="margin:3px 0px !important; ">
			<ul class="nav ace-nav">
				<li class="light-blue">
					<a data-toggle="dropdown" href="#" class="dropdown-toggle">
						<img class="nav-user-photo" src="http://www.gogs.syslink.cn:3000/avatars/${user.id}" alt="Jason's Photo" />
						<span class="user-info">
							<small>欢迎光临,</small>
							${user.name}
						</span>
						<i class="icon-caret-down"></i>
					</a>
					<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
						<li>
							<a href="http://www.gogs.syslink.cn:3000/user/settings" target="_blank">
								<i class="icon-cog"></i>
								用户设置
							</a>
						</li>
						<li>
							<a href="http://www.gogs.syslink.cn:3000/admin" target="_blank">
								<i class="icon-cog"></i>
								管理面板
							</a>
						</li>
						<li>
							<a href="http://www.gogs.syslink.cn:3000/${username}" target="_blank">
								<i class="icon-user"></i>
								个人资料
							</a>
						</li>
						<li class="divider"></li>
						<li>
							<a href="javascript:void(0)" onclick="user_logout()">
								<i class="icon-off"></i>
								退出
							</a>
							<a href="http://www.gogs.syslink.cn:3000/user/logout" id="gogs_logout" style="display:none">
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
					<button class="btn btn-success btn-primary" data-toggle="modal" style="margin-top:4px;margin-left:20px;width:70px" data-target="#exampleModal" title="新建包目录">
						<i class="icon-plus"></i>
					</button>
					<button id="modelPackageType" class="btn btn-danger" style="margin-top:4px;width:70px" title="包类型管理">
						<i class="icon-cogs"></i>
					</button>				
				</div>
			
				<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				        <h4 class="modal-title" id="exampleModalLabel">增加目录</h4>
				      </div>
				      <div class="modal-body">
				        <form id="pkg_des">
				          <div class="form-group">
				            <label for="recipient-name" class="control-label">目录名称:</label>
				            <input type="text" class="form-control" id="pkg-name">
				          </div>
				           <div class="form-group">
				            <label for="recipient-name" class="control-label">父级目录:</label>
				            <input type="text" class="form-control" data-options="required:false" id="pkg-select">
				          </div>
				          
				          <div class="form-group">
				            <label for="message-text" class="control-label">目录描述:</label>
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
			<div class="nav nav-list" >
				<div id="showPkgTree" fit="true"></div>					
			</div>							
		</div>
	</div>
	<!--右键菜单信息-->
	<div id="show_right" class="easyui-menu" style="width:120px;">
		<div data-toggle="modal" data-target="#exampleModal" data-options="iconCls:'icon-add'">添加目录</div>
		<div onclick="remove_model()" data-options="iconCls:'icon-remove'">移除该目录</div>
		<div onclick="edit_model()" data-options="iconCls:'icon-edit'">编辑该目录</div>
		<div onclick="$('#file_upload').click();" data-options="iconCls:'icon-add'">上传模型</div>
		<form id="upload_form" action="${base}/model/uploadModel" method="post" enctype="multipart/form-data">
			<input type=text id=upload_pkg_id name='pkg_id' style="display:none">
			<input type=file id=file_upload  name='file_name' style="display:none">
		</form>
	</div>
	
	<script type="text/javascript">
		$(function(){
			$('#pkg-select').combotree({    
			    url: '${base}/modelPackage/getInipkg'   
			});
			
		 	$('#file_upload').change(function(){		 	
		 		$('#upload_pkg_id').val($('#show_right').attr('name'));
				$('#upload_form').submit();
		 	
		 	})	
		})			
	</script>
	<script type="text/javascript">
		function save_pkg(){
			var t = $('#pkg-select').combotree("tree");
			var node =  t.tree('getSelected');
			var nodeId =0
			if(node!=null){
				nodeId = node.id
			}
			$.post('${base}/modelPackage/save_pkg',
				{
					"pkg_name":$('#pkg-name').val(),
					"pkg_des":$('#pkg-text').val(),
					"pkg_select":nodeId
				},function(data){
					//$('#exampleModal').modal('hide');
					//$("#showPkgTree").tree('reload');
					document.location.reload();//当前页面
				}												
			)
		
		}
		
		function remove_model(){
			var pkgId = $('#show_right').attr('name');
			$.post('${base}/modelPackage/delete_pkg',
				{
					"pkgId":pkgId
				},function(data){
					//$('#exampleModal').modal('hide');
					//$("#showPkgTree").tree('reload');
					//document.location.reload();//当前页面
					document.location.href="${base}/common/goIndex2";
				});
		}
		
		function edit_model(){
			///alert("??");
		}
		
		function user_logout(){
			$.get('${base}/common/logout',function(){
			document.getElementById('gogs_logout').click()			
			})
		
		}
				
	</script>		
</@override>
<@extends name="base_second.ftl"/>
