<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title id='Description'>This sample demonstrates how to load Tree Items via Ajax
    </title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/jqwidgets/styles/jqx.base.css" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/scripts/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/scripts/demos.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jqwidgets/jqxcore.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jqwidgets/jqxbuttons.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jqwidgets/jqxscrollbar.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jqwidgets/jqxpanel.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jqwidgets/jqxtree.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/jqwidgets/jqxexpander.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            // Create jqxTree
            var tree = $('#jqxTree');
            var source = null;
            $.ajax({
                async: false,
                url: "<%=request.getContextPath() %>/static/ajaxroot.htm",
                success: function (data, status, xhr) {
                alert(data);
                    source = jQuery.parseJSON(data);
                }
            });
            tree.jqxTree({ source: source,  height: 300, width: 200 });
            tree.on('expand', function (event) {
                var label = tree.jqxTree('getItem', event.args.element).label;
                var $element = $(event.args.element);
                var loader = false;
                var loaderItem = null;
                var children = $element.find('ul:first').children();
                $.each(children, function () {
                    var item = tree.jqxTree('getItem', this);
                    if (item && item.label == 'Loading...') {
                        loaderItem = item;
                        loader = true;
                        return false
                    };
                });
                if (loader) {
                    $.ajax({
                        url: loaderItem.value,
                        success: function (data, status, xhr) {
                        	data=JSON.stringify(data);
                            var items = jQuery.parseJSON(data);
                            tree.jqxTree('addTo', items, $element[0]);
                            tree.jqxTree('removeItem', loaderItem.element);
                        }
                    });
                }
            });
        });
    </script>
</head>
<body class='default'>
    <div id="jqxTree">
    </div>
</body>
</html>