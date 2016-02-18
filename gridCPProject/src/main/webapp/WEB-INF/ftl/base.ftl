<#assign base=request.contextPath />
<!doctype html>
<html lang="en">
 <head>
 	<meta charset="UTF-8">
 	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="keywords" content="同元协同,tongyuan协同" />
	<meta name="description" content="同元协同,tongyuan协同" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
  	<title><@block name="title"></@block></title>
	<@block name="style"></@block>
	<@block name="link"></@block>
	<@block name="script"></@block>
 </head>
 <body>
 	<@block name="header"></@block>
 	<div class="main-container" id="main-container">
 		<@block name="content_left"></@block>
 		<@block name="content_right"></@block>
 	</div>  	
  	<@block name="foot"></@block>
 </body>
</html>