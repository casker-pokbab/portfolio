var defaultPageSize = 10;

$(document).ready(function() {
	if (!isPcResolution()) {
		alert("PC에서 접속해주세요.")
	}
	
	$("#btn_search").on("click", function() {
		getRecentlyList();
	});
	
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
		, data : "pageNum=" + pageNum + "&pageSize=" + pageSize + "&" + $("#form").serialize()
		, success : function(data) {
			$(".list_area").empty().append(data);
		}
	});
}

/**
 * 페이징처리
 * 
 * @param pageNum
 */
function goPage(pageNum) {
	if (pageNum == 0) {
		alert("첫페이지 입니다.");
		return;
	} else if (pageNum == $(".board_num_list .num").size() + 1) {
		alert("마지막페이지 입니다.");
		return;
	}
	
	getRecentlyList(pageNum, defaultPageSize)
}

/**
 * 최근작업 삭제
 */
function removeRecently() {
	if (!confirm("게시글을 삭제하시겠습니까?")) {
		return;
	}
	
	$(".list_area").find("input[type=checkbox]:checked").each(function() {
		var no = $(this).attr("no");
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