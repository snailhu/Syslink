<@override name="content_right">
	<link
	href="${base}/static/content/jquery-easyui-1.4.3/themes/gray/easyui.css"
	rel="stylesheet" type="text/css" />
<link
	href="${base}/static/content/jquery-easyui-1.4.3/themes/icon.css"
	rel="stylesheet" type="text/css" />
<script
	src="${base}/static/content/jquery-easyui-1.4.3/jquery.min.js"
	type="text/javascript"></script>
<script
	src="${base}/static/content/jquery-easyui-1.4.3/jquery.easyui.min.js"
	type="text/javascript"></script>
<script
	src="${base}/static/content/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script src="${base}/static/js/JScriptCommon.js"
	type="text/javascript"></script>
<link href="${base}/static/content/css/Common.css"
	rel="stylesheet" type="text/css" />
    
<script type="text/javascript">
	try{ace.settings.check('navbar' , 'fixed')}catch(e){}
</script>
<script type="text/javascript">
	var modelTree;
	var componentVar;
	$(function () {
	//获取模型树
	modelTree = $("#showModelTree").tree({
                url: '${base}/modelica/modelTree',
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
		url: '${base}/modelica/getComponetVarTree',
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
</@override>
<@extends name="index.ftl"/>