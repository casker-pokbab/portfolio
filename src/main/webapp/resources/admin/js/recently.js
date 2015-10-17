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

function removeRecently() {
	$(".list_area").find("input[type=checkbox]:checked").each(function() {
		var no = $(this).parent().next().text();
		$.ajax({
			  url : "/admin/manegement/recently/remove"
			, type : "html"
			, method : "POST"
			, data : "recentlyNo=" + no
			, success : function(data) {
				alert("선택한 최근작업을 삭제했습니다.");
				getRecentlyList();
			}
		});
	});
}