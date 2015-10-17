var defaultPageSize = 10;

$(document).ready(function() {
	
	$("#work .more_work").on("click", function() {
		getPortfolioList($("#work .item").size(), 8);
	});
	
	$("#contact .more_work").on("click", function() {
		if (!$("#privacypolicy").prop("checked") && confirm("개인정보보호정책에 동의합니까?")) {
			$(".btn_privacypolicy").click();
		} else {
			sendEmail();
		}
	});
	
	$(".btn_privacypolicy").on("click", function() {
		if ($(this).hasClass("checked")) {
			$("#privacypolicy").prop("checked", true);
		} else {
			$("#privacypolicy").prop("checked", false);
		}
	});
	
	getPortfolioList(0, 12);
	getRecentlyList();
});

/**
 * 포트폴리오 리스트를 가져온다.
 * 
 * @param all
 */
function getPortfolioList(skipRows, pageSize) {
	
	skipRows = (skipRows == undefined) ? 1 : skipRows;
	pageSize = (pageSize == undefined) ? defaultPageSize : pageSize;
	
	$.ajax({
		  url : "/main/portfolio"
		, type : "html"
		, method : "GET"
		, data : "skipRows=" + skipRows + "&pageSize=" + pageSize
		, success : function(data) {
			if ($(data).size() == 0) {
				alert("포트폴리오가 모두 게시되었습니다.")
				return;
			}
			
			$("#masonrybox").append(data);
			$("#masonrybox").masonry("appended", $(data));
			
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
function getRecentlyList(pageNum, pageSize) {
	
	pageNum = (pageNum == undefined) ? 1 : pageNum;
	pageSize = (pageSize == undefined) ? defaultPageSize : pageSize;
	
	$.ajax({
		  url : "/main/recently"
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
	getRecentlyList(pageNum, defaultPageSize)
}

function sendEmail() {
	$("#contact_form").find("[name]").each(function() {
		if ($(this).val() == "") {
			alert("비어있는 양식을 작성해주세요");
			$(this).focus();
			return false;
		}
	});
	
	
}