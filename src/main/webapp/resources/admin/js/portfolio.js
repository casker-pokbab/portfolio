var defaultPageSize = 10;

$(document).ready(function() {
	if (!isPcResolution()) {
		alert("PC에서 접속해주세요.")
	}
	
	getPortfolioList();
})

function isPcResolution() {
	return $(window).width() > 1024
}

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

function removePortfolio() {
	$(".list_area").find("input[type=checkbox]:checked").each(function() {
		var no = $(this).parent().next().text();
		$.ajax({
			  url : "/admin/manegement/portfolio/remove"
			, type : "html"
			, method : "POST"
			, data : "portfolioNo=" + no
			, success : function(data) {
				alert("선택한 프로젝트를 삭제했습니다.");
				getPortfolioList();
			}
		});
	});
}