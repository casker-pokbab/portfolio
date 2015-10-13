var defaultPageSize = 10;

$(document).ready(function() {
	
	$(".more_work").on("click", function() {
		getPortfolioList(true)
	})
	
	getPortfolioList(false);
	getProjectList();
});

/**
 * 포트폴리오 리스트를 가져온다.
 * 
 * @param all
 */
function getPortfolioList(all) {
	
	$.ajax({
		  url : "/main/portfolio"
		, type : "html"
		, method : "GET"
		, data : "all=" + all
		, success : function(data) {
			$("#masonrybox").empty().append(data);
			initMasonry();
		}
	});
}

/**
 * 프로젝트 리스트를 가져온다.
 * 
 * @param pageNum
 * @param pageSize
 */
function getProjectList(pageNum, pageSize) {
	
	pageNum = (pageNum == undefined) ? 1 : pageNum;
	pageSize = (pageSize == undefined) ? defaultPageSize : pageSize;
	
	$.ajax({
		  url : "/main/project"
		, type : "html"
		, method : "GET"
		, data : "pageNum=" + pageNum + "&pageSize=" + pageSize
		, success : function(data) {
			$(".wrap_table_project").empty().append(data);
			$(".wrap_table_project").find(".subject").click(function() {
				if (!$(this).find(">").is("a")) {
					alert("URL이 등록되지 않았습니다.");
				}
			});
		}
	});
}

/**
 * 페이징처리
 * 
 * @param pageNum
 */
function goPage(pageNum) {
	getProjectList(pageNum, defaultPageSize)
}