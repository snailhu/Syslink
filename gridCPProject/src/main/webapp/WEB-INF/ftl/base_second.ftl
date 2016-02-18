<@override name="link">
	<!-- basic styles -->
	<link href="${base}/static/assets/css/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="${base}/static/assets/css/font-awesome.min.css" />
	<!--[if IE 7]>
	  <link rel="stylesheet" href="${base}/static/assets/css/font-awesome-ie7.min.css" />
	<![endif]-->
	<!-- page specific plugin styles -->
	<!-- fonts -->
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
	<!-- ace styles -->
	<link rel="stylesheet" href="${base}/static/assets/css/ace.min.css" />
	<link rel="stylesheet" href="${base}/static/assets/css/ace-rtl.min.css" />
	<link rel="stylesheet" href="${base}/static/assets/css/ace-skins.min.css" />
	<!--[if lte IE 8]>
	  <link rel="stylesheet" href="${base}/static/assets/css/ace-ie.min.css" />
	<![endif]-->
	<!-- inline styles related to this page -->
	<!-- ace settings handler -->
	<script src="${base}/static/assets/js/ace-extra.min.js"></script>
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<![endif]-->
	<link rel="stylesheet" href="${base}/static/waterfall/css/bootstrap-responsive.css" />
	<link rel="stylesheet" href="${base}/static/waterfall/css/docs.css" />
	<link rel="stylesheet" href="${base}/static/waterfall/css/lightbox.css" />
</@override>
<@override  name="script">
		<!-- basic scripts -->
		<!--[if !IE]> -->

		<!--script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>  -->
		<script src="${base}/static/jqwidgets/jquery2.0.3.min.js"></script>

		<script src="${base}/static/content/jquery-1.11.3/jquery-1.11.3.min.js"></script> 
		<!-- <![endif]-->

		<!--[if IE]>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<![endif]-->

		<!--[if !IE]> -->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='${base}/static/assets/js/jquery-1.11.3.js'>"+"<"+"script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='${base}/static/assets/js/jquery-1.10.2.min.js'>"+"<"+"script>");
		</script>
		<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("${base}/static/assets/js/jquery.mobile.custom.min.js'>"+"<"+"script>");
		</script>
		<script src="${base}/static/assets/js/bootstrap.min.js"></script>
		<script src="${base}/static/assets/js/typeahead-bs2.min.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="${base}/static/assets/js/excanvas.min.js"></script>
		<![endif]-->

		<script src="${base}/static/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script src="${base}/static/assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="${base}/static/assets/js/jquery.slimscroll.min.js"></script>
		<script src="${base}/static/assets/js/jquery.easy-pie-chart.min.js"></script>
		<script src="${base}/static/assets/js/jquery.sparkline.min.js"></script>
		<script src="${base}/static/assets/js/flot/jquery.flot.min.js"></script>
		<script src="${base}/static/assets/js/flot/jquery.flot.pie.min.js"></script>
		<script src="${base}/static/assets/js/flot/jquery.flot.resize.min.js"></script>

		<!-- ace scripts -->

		<script src="${base}/static/assets/js/ace-elements.min.js"></script>
		<script src="${base}/static/assets/js/ace.min.js"></script>
		
		<!-- waterfall scripts -->
		<script src="${base}/static/waterfall/js/main.js"></script>
		<script src="${base}/static/waterfall/js/masonry.pkgd.min.js"></script>	
		<script src="${base}/static/waterfall/js/imagesloaded.pkgd.min.js"></script>
		<script src="${base}/static/waterfall/js/lightbox-2.6.min.js"></script>
		
			
</@override>
<@extends name="base.ftl"/>
