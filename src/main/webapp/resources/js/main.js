$(document).ready(function() {
	
	$(".more_work").on("click", function() {
		getPortfolioList(true)
	})

	getPortfolioList(false);
	getProjectList();
});

function getPortfolioList(all) {
	
	$.ajax({
		  url : "/portfolio"
		, type : "html"
		, method : "GET"
		, data : "all=" + all
		, success : function(data) {
			$("#masonrybox").empty().append(data);
			initMasonry();
		}
	});
}

function getProjectList(pageNum, pageSize) {
	
	pageNum = (pageNum == undefined) ? 1 : pageNum;
	pageSize = (pageSize == undefined) ? 12 : pageSize;
	
	$.ajax({
		  url : "/project"
		, type : "html"
		, method : "GET"
		, data : "pageNum=" + pageNum + "&pageSize=" + pageSize
		, success : function(data) {
			$(".wrap_table_project").empty().append(data);
		}
	});
}