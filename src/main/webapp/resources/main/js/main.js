var defaultPageSize = 10;

$(document).ready(function() {
	
	$("#work .more_work").on("click", function() {
		getPortfolioList(true)
	});
	
	$("#contact .more_work").on("click", function() {
		if (!$("#privacypolicy").prop("checked") && confirm("개인정보보호정책에 동의합니까?")) {
			$(".btn_privacypolicy").click();
		}
	});
	
	$(".btn_privacypolicy").on("click", function() {
		if ($(this).hasClass("checked")) {
			$("#privacypolicy").prop("checked", true);
		} else {
			$("#privacypolicy").prop("checked", false);
		}
	});
	
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