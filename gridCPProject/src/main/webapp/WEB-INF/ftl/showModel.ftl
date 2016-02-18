<@override name="content_right">
<style>
	#masonry
		{
			padding: 0;
			min-height: 450px;
			margin: 0 auto;
		}
	#masonry .thumbnail
		{
			width: 200px;
    		height: 250px;
			margin: 20px;
			padding: 0;
			border-width: 1px;
			-webkit-box-shadow: 0 6px 12px rgba(0, 0, 0, 0.175);
					box-shadow: 0 6px 12px rgba(0, 0, 0, 0.175);
		}
	#masonry .thumbnail .imgs
		{
			padding: 10px;
		}
	#masonry .thumbnail .imgs img
		{
			margin-bottom: 5px;
		}
	#masonry .thumbnail .caption
		{
			background-color: #fff;
			padding-top: 0;
			font-size: 13px;
		}
	#masonry .thumbnail .caption .title
		{
			font-size: 13px;
			font-weight: bold;
			margin: 5px 0;
			text-align: left;
		}
	#masonry .thumbnail .caption .author
		{
			font-size: 11px;
			text-align: right;
		}
				
	.lightbox .lb-image
		{
			max-width: none;
		}

	/************ Table ************/
	.xwtable {width: 100%;border-collapse: collapse;border: 1px solid #ccc;}                
	.xwtable thead th {font-size: 12px;color: #333333;text-align: center;border: 1px solid #ccc; font-weight:bold;}
	.xwtable tbody tr {background: #fff;font-size: 12px;color: #666666;}           
	.xwtable tbody tr.alt-row {background: #f2f7fc;}               
	.xwtable td{line-height:20px;text-align: left;padding:4px 10px 3px 10px;height: 18px;border: 1px solid #ccc;}
	
	a:link {
	 TEXT-DECORATION: none
	}
	a:visited {
	 TEXT-DECORATION: none
	}
	a:hover {
	 COLOR: #ff7f24;
	 text-decoration: underline;
	}
	a:active {
	 COLOR: #ff7f24;  
	 text-decoration: underline;
	}
	.nav-head {
		padding-left: 15px;
		float: left;
	}
	.nav-head-list{
		float: left;
		line-height: 40px;
		font-size: 14px;
		color: rgba(135, 136, 142, 0.61);

	}
		.img_svg{
			width:150px;
			height:150px;		
		}
</style>
<script>
$(function() {		
	var ghostNode = $('#masonry_ghost').find('.thumbnail'), i, ghostIndexArray = [];
	var ghostCount = ghostNode.length;
	for(i=0; i<ghostCount; i++){
		ghostIndexArray[i] = i; 
	}
	ghostIndexArray.sort(function(a, b) {
		if(Math.random() > 0.5) {
			return a - b;
		} else {
			return b - a;
		}
	});	
	var currentIndex = 0;
	var masNode = $('#masonry');
	var imagesLoading = false;
		
	function getNewItems() {
		var newItemContainer = $('<div/>');
		for(var i=0; i<10; i++) {
			if(currentIndex < ghostCount) {
				newItemContainer.append(ghostNode.get(ghostIndexArray[currentIndex]));
				currentIndex++;
			}
		}
		return newItemContainer.find('.thumbnail');
	}
	
	function processNewItems(items) {
			items.each(function() {
			var $this = $(this);
			var imgsNode = $this.find('.imgs');
			var id = $this.find('.model_id').text();
			var author = $this.find('.author').text();
			var className = $this.find('.className').text();
			var imgNames = imgsNode.find('input[type=hidden]').val().split(',');
			$.each(imgNames, function(index, item) {
				imgsNode.append('<div><a href="${base}/model/getModelListChild/'+id+'/'+className+'/"><img class="img_svg" src="${base}/static/img/svg.png"/></a></div>');
			});
		});
	}
	
	function initMasonry(){
		var items = getNewItems().css('opacity', 0);
		processNewItems(items);
		masNode.append(items);
		imagesLoading = true;
		items.imagesLoaded(function(){
			imagesLoading = false;
			items.css('opacity', 1);
			masNode.masonry({
						itemSelector: '.thumbnail',
						isFitWidth: true
					});
		});
	}
				
	function appendToMasonry() {
		var items = getNewItems().css('opacity', 0);
		processNewItems(items);
		masNode.append(items);
		
		imagesLoading = true;
		items.imagesLoaded(function(){
			imagesLoading = false;
			items.css('opacity', 1);
			masNode.masonry('appended',  items);
		});
	}
			

	initMasonry();
	$(window).scroll(function() {		
		if($(document).height() - $(window).height() - $(document).scrollTop() < 10) {			
			if(!imagesLoading) {
				appendToMasonry();
			}
			
		}
		
	});
				
	function randomColor() {
		var rand = Math.floor(Math.random() * 0xFFFFFF).toString(16);
		for (; rand.length < 6;) {
			rand = '0' + rand;
		}
		return '#' + rand;
	}						
});
</script>
<script type="text/javascript">
	try{ace.settings.check('navbar' , 'fixed')}catch(e){}
</script>
<script type="text/javascript"> 
	var modelVars;
	$(function () {
		 modelVars=$("#modelVars").edatagrid({
			data: ${modelVarJSON},
	        fitColumns:true,
	        rownumbers: true,
	        singleSelect:true,
	        idField: 'id',
	        onLoadError:function(data){
                $.messager.alert("信息", "暂无数据信息"+data, "error");
            },
	        frozenColumns: [[{
	            field: 'id',
	            width: 50,
	            checkbox: true ,
	            hidden :true         
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
			    width: 100,
			    editor:{type:'numberbox',options:{required:true}}
			 },{
	            field: 'units',
	            title: '单位',
	            width: 100
	        }, {
	            field: 'description',
	            title: '描述',
	            width: 250
	        }
	        ]], 
	        toolbar: [
                {
                    text: '提交数据',
                    iconCls: 'icon-add',
                    handler: function () {
                     doSubmit();
                    }
                },  '-', {
                    text: '重置',
                    iconCls: 'icon-reload',
                    handler: function () {
                       // modelVars.edatagrid({data: ${modelVarJSON}});
                        modelVars.edatagrid('rejectChanges');
                    }
                }, '-', {
                    text: '取消选中',
                    iconCls: 'icon-redo',
                    handler: function () {
                       modelVars.edatagrid('unselectAll');
                       modelVars.edatagrid('cancelRow');
                    }
                }, '-', {
                    text: '仿真',
                    iconCls: 'icon-simulation',
                    handler: function () {
                       doSimulation();
                    }
                }]    
    	});
	 });
	 function doSubmit(){
	      modelVars.edatagrid('saveRow');
	      var updated = modelVars.edatagrid('getChanges','updated');
	      if(updated != null){
		      var effectRow = new Object();
		      effectRow["modelVarJSON"] = JSON.stringify(updated); 
		      effectRow["modelId"] = '${modelId}';
		      effectRow["className"] = '${className}';
		      $.post("${base}/simulation/saveModelVar", effectRow, function(data) {
	                        modelVars.datagrid('acceptChanges');
	         });
	      }
	 }
	 function doSimulation(){
	 	 modelVars.edatagrid('saveRow');
	      var updated = modelVars.edatagrid('getChanges','updated');
	      var effectRow = new Object();
	      effectRow["modelVarJSON"] = JSON.stringify(updated); 
	      effectRow["modelId"] = '${modelId}';
	      effectRow["className"] = '${className}';
	      $.post("${base}/simulation/modelSimulation", effectRow, function(data) {
				      if(updated != null){
                      	modelVars.datagrid('acceptChanges');
				      }
         });
	 }
</script>
		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
				</script>
				<div class="nav-head">
					<#list parentList as pl>
						<#if !pl_has_next>
								<div class="nav-head-list">${pl.name}</div>
							<#else>
								<div class="nav-head-list">
									<a href="<#if (pl.type)=='pkg'>${base}/model/getModeList/${pl.id}/<#else>${base}/model/getModelListChild/${pl.id}/${pl.className}/</#if>">${pl.name}</a>			
								</div>
								<div style="float:left;line-height:40px;font-size:12px;color:#428BCA;padding-left:5px;padding-right:5px;">></div>							
						</#if>
					</#list>
				</div>
				<div class="nav-search" id="nav-search">
					<form class="form-search" action="${base}/model/searchModel" method="post">
						<span class="input-icon">
							<input type="text" name="modelName" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
							<i class="icon-search nav-search-icon"></i>
						</span>
					</form>
				</div><!-- #nav-search -->
			</div>
			<#if flag ==2>
				<div class="container-fluid" style="height:${modelVarCount}*30px">
					<table id="modelVars" >
					</table>
				</div>
			</#if>
			<div class="page-content">
				<div id="masonry" class="container-fluid"></div>
				<div id="masonry_ghost" class="hide">
					<#if (flag == 1)>
						<#if (modelDtos?size>0)>
							<#list modelDtos as model>
								<div class="thumbnail">
									<div class="imgs">
										<input type="hidden" value="2426.png">
									</div>
									<div class="caption">
										<div class="className" style="display:none">${model.className}</div>
										<div class="content">名称${model.name}</div>
										<div class="des">描述：${model.description}</div>										
										<div class="model_id" style="display:none">${model.id}</div>
										<div class="author">
											<a href="${base}/simulation/getModelSimulationList/${model.id}/${model.className}/">模型仿真</a>&nbsp;&nbsp;
											<a href="">版本信息</a>
										</div>
									</div>
								</div>
							</#list> 		
						</#if>
					<#else>
						<#if modelDto.models??>
							<#if (modelDto.models?size>0)>
								<#list modelDto.models as model>
									<div class="thumbnail">
										<div class="imgs">
											<input type="hidden" value="2426.png">
										</div>
										<div class="caption">
											<div class="className" style="display:none">${model.className}</div>
											<div class="content">名称：${model.name}</div>
											<div class="des">描述：${model.description}</div>	
											<div class="model_id" style="display:none">${model.id}</div>
											<div class="author">
												<a href="">版本信息</a>
											</div>
										</div>
									</div>
								</#list> 		
							</#if>	
						</#if>
					</#if>
				</div>
			</div><!-- /.page-content -->

	</div><!-- /.main-content -->
	
</@override>
<@extends name="index.ftl"/>