var defaultPageSize = 10;

$(document).ready(function() {
	
	
	
	getPortfolioList();
})

/**
 * 포트폴리오 리스트를 가져온다.
 * 
 * @param pageNum
 * @param pageSize
 */
function getPortfolioList(pageNum, pageSize) {
	
	pageNum = (pageNum == undefined) ? 1 : pageNum;
	pageSize = (pageSize == undefined) ? defaultPageSize : pageSize;
	
	$.ajax({
		  url : "/admin/portfolio"
		, type : "html"
		, method : "GET"
		, data : "pageNum=" + pageNum + "&pageSize=" + pageSize
		, success : function(data) {
			$(".list_area").empty().append(data);
//			$(".wrap_table_project").find(".subject").click(function() {
//				if (!$(this).find(">").is("a")) {
//					alert("URL이 등록되지 않았습니다.");
//				}
//			});
		}
	});
}

/**
 * 페이징처리
 * 
 * @param pageNum
 */
function goPage(pageNum) {
	getPortfolioList(pageNum, defaultPageSize)
}