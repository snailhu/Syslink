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
		for(var i=0; i<16; i++) {
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
			var title = $this.find('.title').text();
			var author = $this.find('.author').text();
			title += '&nbsp;&nbsp;(' + author + ')';
			var lightboxName = 'lightbox_'; // + imgNames[0];			
			var imgNames = imgsNode.find('input[type=hidden]').val().split(',');
			$.each(imgNames, function(index, item) {
				imgsNode.append('<div style="border:1px solid red"><a href="">12222222222</a></div>');
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
</script>
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
				<div id="masonry" class="container-fluid"></div>
				<div id="masonry_ghost" class="hide">
					<div class="thumbnail">
						<div class="imgs">
							<input type="hidden" value="2426.png">
						</div>
						<div class="caption">
							<div class="title">模型描述</div>
							<div class="content">nihao </div>
							<div class="author">
								<a target="_blank" href="">模型仿真</a>
							</div>
						</div>
					</div>					
					<div class="thumbnail">
						<div class="imgs">
							<input type="hidden" value="2426.png">
						</div>
						<div class="caption">
							<div class="title">简单OA管理系统</div>
							<div class="content">nihao </div>
							<div class="author">
								by <a target="_blank" href="">小小生</a>
							</div>
						</div>
					</div>

					<div class="thumbnail">
						<div class="imgs">
							<input type="hidden" value="2426.png">
						</div>
						<div class="caption">
							<div class="title">简单OA管理系统</div>
							<div class="content">nihao </div>
							<div class="author">
								by <a target="_blank" href="">小小生</a>
							</div>
						</div>
					</div>
				</div>
			</div><!-- /.page-content -->
	</div><!-- /.main-content -->
	
</@override>
<@extends name="index.ftl"/>