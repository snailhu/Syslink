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

	/************ Table ************/
	.xwtable {width: 60%;border-collapse: collapse;border: 1px solid #ccc;}                
	.xwtable thead th {font-size: 12px;color: #333333;text-align: center;border: 1px solid #ccc; font-weight:bold;}
	.xwtable tbody tr {background: #fff;font-size: 12px;color: #666666;}           
	.xwtable tbody tr.alt-row {background: #f2f7fc;}               
	.xwtable td{line-height:20px;text-align: left;padding:4px 10px 3px 10px;height: 18px;border: 1px solid #ccc;}

</style>
<link rel="stylesheet" href="${base}/static/jqwidgets/styles/jqx.base.css" type="text/css" />
<script type="text/javascript" src="${base}/static/jqwidgets/jqxcore.js"></script>
<script type="text/javascript" src="${base}/static/jqwidgets/jqxdata.js"></script>
<script type="text/javascript" src="${base}/static/jqwidgets/jqxbuttons.js"></script>
<script type="text/javascript" src="${base}/static/jqwidgets/jqxscrollbar.js"></script>
<script type="text/javascript" src="${base}/static/jqwidgets/jqxlistbox.js"></script>
<script type="text/javascript" src="${base}/static/jqwidgets/jqxdropdownlist.js"></script>
<script type="text/javascript" src="${base}/static/jqwidgets/jqxdatatable.js"></script>
<script type="text/javascript" src="${base}/static/jqwidgets/jqxtreegrid.js"></script>
<script type="text/javascript">
	try{ace.settings.check('navbar' , 'fixed')}catch(e){}
</script>
<script type="text/javascript"> 
	var modelVars;
	var editingId = 0;
	$(function () {
		 var source =
            {
                dataType: "json",
                dataFields: [
                    { name: 'id', type: 'number' },
                    { name: 'text', type: 'string' },
                    { name: 'value', type: 'string' },
                    { name: 'units', type: 'string' },
                ],
                hierarchy:
                {
                    keyDataField: { name: 'id' },
                    parentDataField: { name: 'text' }
                },
                id: 'id',
                url: '${base}/simulation/getModelVarsToJSON/${modelId}'
            };
        // var dataAdapter = new $.jqx.dataAdapter(source);
		// url: '${base}/simulation/getModelVars/${modelId}'
		 $("#modelVars").jqxTreeGrid({
		 	virtualModeCreateRecords: function (expandedRecord, done) {
                var dataAdapter = new $.jqx.dataAdapter(source,
                    {
                        formatData: function (data) {
                            if (expandedRecord == null) {
                                data.$filter = "(ReportsTo eq null)"
                            }
                            else {
                                data.$filter = "(ReportsTo eq " + expandedRecord.EmployeeID + ")"
                            }
                            return data;
                        },
                        loadComplete: function()
                        {
                            done(dataAdapter.records);
                        },
                        loadError: function (xhr, status, error) {
                            done(false);
                        }
                    }
                );   
                dataAdapter.dataBind();
            },
            virtualModeRecordCreating: function (record) {
                // record is creating.
            },
	        columns: [[ 
	         {
	            dataField: 'id',
	            width: 50,
	            text:'id',
	         },{
			    dataField: 'text',
			    text: '变量名',
			    width: 100,
			 },{
			    dataField: 'value',
			    text: '变量值',
			    width: 50,
			 },{
	            dataField: 'units',
	            text: '单位',
	            width: 50,
	        }
	        ]]
    	});
	 });
</script>
		<div class="main-content">
			<div class="page-content">
				<div>仿真配置${modelId}</div>
				<div id="masonry" class="container-fluid">
					<table id="modelVars" width="40%">
					</table>
				</div>
			</div><!-- /.page-content -->
	</div><!-- /.main-content -->
	
</@override>
<@extends name="index.ftl"/>