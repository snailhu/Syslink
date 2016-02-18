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
			width: 330px;
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

</style>
<link rel="stylesheet" href="${base}/static/jqwidgets/styles/jqx.base.css" type="text/css" />
<script type="text/javascript" src="${base}/static/jqwidgets/jqxcore.js"></script>
<script type="text/javascript" src="${base}/static/jqwidgets/jqxdata.js"></script>
<script type="text/javascript" src="${base}/static/jqwidgets/jqxbuttons.js"></script>
<script type="text/javascript" src="${base}/static/jqwidgets/jqxscrollbar.js"></script>
<script type="text/javascript" src="${base}/static/jqwidgets/jqxmenu.js"></script>
<script type="text/javascript" src="${base}/static/jqwidgets/jqxgrid.js"></script>
<script type="text/javascript" src="${base}/static/jqwidgets/jqxgrid.selection.js"></script> 
<script type="text/javascript" src="${base}/static/jqwidgets/jqxgrid.columnsresize.js"></script> 
<script type="text/javascript" src="${base}/static/jqwidgets/jqxgrid.filter.js"></script>
<script type="text/javascript" src="${base}/static/jqwidgets/jqxlistbox.js"></script>
<script type="text/javascript" src="${base}/static/jqwidgets/jqxdropdownlist.js"></script>
<script type="text/javascript" src="${base}/static/jqwidgets/jqxcheckbox.js"></script>
<script type="text/javascript" src="${base}/static/jqwidgets/jqxwindow.js"></script>
<script type="text/javascript" src="${base}/static/scripts/demos.js"></script>
<script type="text/javascript">
	try{ace.settings.check('navbar' , 'fixed')}catch(e){}
</script>
<script type="text/javascript"> 
	var modelVars;
	var editingId = 0;
	$(document).ready(function () {
		 
		 var data = ${pkgTypeListJSON};
		 var source =
            {
                localdata: data,
                datatype: "json",
                datafields: [
                    { name: 'typeId', type: 'number' },
                    { name: 'name', type: 'string' },
                    { name: 'description', type: 'string' }
                ],
                updaterow: function (rowid, rowdata, commit) {
                    commit(true);
                },
                deleterow: function (rowid, commit) {
                    commit(true);
                }
            };
                 // initialize the input fields.
            $("#typeName").addClass('jqx-input');
            $("#typeDescription").addClass('jqx-input');
            $("#typeName").width(150);
            $("#typeName").height(23);

            
            var dataAdapter = new $.jqx.dataAdapter(source);
            var editrow = -1;
            var addrow = -1;
            $("#jqxgrid").jqxGrid(
            {
                width: 850,
                source: dataAdapter,
                columnsresize: true,
                columns: [
                    { text: 'Id', datafield: 'typeId', width: 50 },
                    { text: 'Name', datafield: 'name', width: 200 },
                    { text: 'Description', datafield: 'description', width: 600 }
                ]
            });
            // create context menu
            var contextMenu = $("#Menu").jqxMenu({ width: 200, height: 90, autoOpenPopup: false, mode: 'popup'});
            $("#jqxgrid").on('contextmenu', function () {
                return false;
            });
            // handle context menu clicks.
            $("#Menu").on('itemclick', function (event) {
                var args = event.args;
                var rowindex = $("#jqxgrid").jqxGrid('getselectedrowindex');
                if ($.trim($(args).text()) == "Edit") {
                    editrow = rowindex;
                    var offset = $("#jqxgrid").offset();
                    $("#popupWindow").jqxWindow({ position: { x: parseInt(offset.left) + 60, y: parseInt(offset.top) + 60} });
                    // 将数据添加到输入框
                    var dataRecord = $("#jqxgrid").jqxGrid('getrowdata', editrow);
                    $("#typeName").val(dataRecord.name);
                    $("#typeDescription").val(dataRecord.description);
                    // show the popup window.
                    $("#popupWindow").jqxWindow('show');
                }
                else if ($.trim($(args).text()) == "Add"){
                	addrow = 1;
                	var offset = $("#jqxgrid").offset();
                    $("#popupWindow").jqxWindow({ position: { x: parseInt(offset.left) + 60, y: parseInt(offset.top) + 60} });
                    // show the popup window.
                    $("#popupWindow").jqxWindow('show');
                }
                else {
                	var dataRecord = $("#jqxgrid").jqxGrid('getrowdata', rowindex);
                	var effectRow = new Object();
                    var typeId = dataRecord.typeId;
                    effectRow["type_id"] = typeId; 
                    $.post("${base}/modelPackage/delete_pkgType", effectRow, function(data) {
	                    var rowid = $("#jqxgrid").jqxGrid('getrowid', rowindex);
	                    $("#jqxgrid").jqxGrid('deleterow', rowid);
                    });
                }
            });
            $("#jqxgrid").on('rowclick', function (event) {
                if (event.args.rightclick) {
                    $("#jqxgrid").jqxGrid('selectrow', event.args.rowindex);
                    var scrollTop = $(window).scrollTop();
                    var scrollLeft = $(window).scrollLeft();
                    contextMenu.jqxMenu('open', parseInt(event.args.originalEvent.clientX) + 5 + scrollLeft, parseInt(event.args.originalEvent.clientY) + 5 + scrollTop);
                    return false;
                }
            });
            // initialize the popup window and buttons.
            $("#popupWindow").jqxWindow({ width: 250, resizable: false,  isModal: true, autoOpen: false, cancelButton: $("#Cancel"), modalOpacity: 0.01 });
            $("#Cancel").jqxButton({ theme: theme });
            $("#Save").jqxButton({ theme: theme });
            // update the edited row when the user clicks the 'Save' button.
            $("#Save").click(function () {
                if (editrow >= 0) {                    
			        var dataRecord = $("#jqxgrid").jqxGrid('getrowdata', editrow);
                    var effectRow = new Object();
                    var typeId = dataRecord.typeId;
                	var type_name = $("#typeName").val();
                	var type_des = $("#typeDescription").val();
                	effectRow["type_id"] = typeId; 
			        effectRow["type_name"] = type_name; 
			        effectRow["type_des"] = type_des;
			        $.post("${base}/modelPackage/edit_pkgType", effectRow, function(data) {
			       		 var row = {  typeId: typeId, name: type_name, description: type_des};
			        	 var rowid = $("#jqxgrid").jqxGrid('getrowid', editrow);
                         $('#jqxgrid').jqxGrid('updaterow', rowid, row);
			        	 $("#popupWindow").jqxWindow('hide');
		            });
                }
                if (addrow >= 0) {
                	var effectRow = new Object();
                	var type_name = $("#typeName").val();
                	var type_des = $("#typeDescription").val();
			        effectRow["type_name"] = type_name; 
			        effectRow["type_des"] = type_des;
			        $.post("${base}/modelPackage/save_pkgType", effectRow, function(data) {
			       		 var row = { typeId: data, name: type_name, description: type_des};
			        	 $("#jqxgrid").jqxGrid('addrow', null, row);
			        	 $("#popupWindow").jqxWindow('hide');
		            });
                }
            });
	 });
</script>
		<div class="main-content">
			<div class="page-content">
				<div>包类型管理</div>
				<div id="masonry" class="container-fluid">
				    <div id='jqxWidget'>
				        <div id="jqxgrid"></div>
				        <div style="margin-top: 30px;">
				            <div id="cellbegineditevent"></div>
				            <div style="margin-top: 10px;" id="cellendeditevent"></div>
				       </div>
				       <div id="popupWindow">
				            <div>包类型信息</div>
				            <div style="overflow: hidden;">
				                <table>
				                    <tr>
				                        <td align="right">类型名称:</td>
				                        <td align="left"><input id="typeName" /></td>
				                    </tr>
				                    <tr>
				                        <td align="right">类型描述:</td>
				                        <td align="left"><textarea id="typeDescription" style="width:152px;height:50px;"></textarea></td>
				                    </tr>
				                    <tr>
				                        <td align="right"></td>
				                        <td style="padding-top: 10px;" align="right"><input style="margin-right: 5px;" type="button" id="Save" value="Save" /><input id="Cancel" type="button" value="Cancel" /></td>
				                    </tr>
				                </table>
				            </div>
				       </div>
				       <div id='Menu'>
				        <ul>
				        	 <li>Add</li>
				            <li>Delete</li>
				            <li>Edit</li>
				        </ul>
				       </div>
				    </div>
				</div>
			</div><!-- /.page-content -->
	</div><!-- /.main-content -->
	
</@override>
<@extends name="index.ftl"/>