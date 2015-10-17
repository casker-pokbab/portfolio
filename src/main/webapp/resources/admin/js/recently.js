var defaultPageSize = 10;

$(document).ready(function() {
	if (!isPcResolution()) {
		alert("PC에서 접속해주세요.")
	}
	
	getRecentlyList();
})

function isPcResolution() {
	return $(window).width() > 1024
}

/**
 * 최근작업 리스트를 가져온다.
 * 
 * @param pageNum
 * @param pageSize
 */
function getRecentlyList(pageNum, pageSize) {
	
	pageNum = (pageNum == undefined) ? 1 : pageNum;
	pageSize = (pageSize == undefined) ? defaultPageSize : pageSize;
	
	$.ajax({
		  url : "/admin/recently"
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
	getRecentlyList(pageNum, defaultPageSize)
}