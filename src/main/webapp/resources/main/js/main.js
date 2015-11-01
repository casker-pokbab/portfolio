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
	
	$(".go_admin").on("click", function() {
		if (!isPcResolution()) {
			alert("PC에서 로그인해주세요.");
			return;
		}
		window.open("/admin/loginForm");
	});
	
	
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
			var $items = $(data);
			if ($items == 0) {
				alert("포트폴리오가 모두 게시되었습니다.")
				return;
			}
			
			$masonrybox.append($items)
				.masonry("appended", $items);
			
			initMasonry();
		}
	});
}

/**
 * 최근작업 리스트를 가져온다.
 * 
 * @param pageNum
 * @param pageSize
 */
function getRecentlyList(pageNum) {
	
	pageNum = (pageNum == undefined) ? 1 : pageNum;
	var pageSize = 11;
	
	$.ajax({
		  url : "/main/recently"
		, type : "html"
		, method : "GET"
		, data : "pageNum=" + pageNum + "&pageSize=" + pageSize
		, success : function(data) {
			$(".wrap_table_project").empty().append(data);
			$(".wrap_table_project").find(".subject a").click(function() {
				if (!$(this).attr("target")) {
					alert("URL 정보가 없는 프로젝트입니다.");
				}
			});
			$(".board_num_list .page").on("click", function() {
				goPage($(this).attr("page"));
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
	if (pageNum < 1) {
		alert("첫페이지 입니다.");
		return;
	} else if (pageNum > $(".page.next_all").attr("page")) {
		alert("마지막페이지 입니다.");
		return;
	}
	
	getRecentlyList(pageNum)
}

function sendEmail() {
	$("#contact_form").find("[name]").each(function() {
		if ($(this).val() == "") {
			alert("비어있는 양식을 작성해주세요");
			$(this).focus();
			return false;
		}
	});
	
	var contactForm = $("#contact_form");
	$.ajax({
		  url : "/main/mail/send"
		, type : "html"
		, method : "POST"
		, data : contactForm.serialize()
		, success : function(data) {
			if (data == "success") {
				alert("메일이 발송되었습니다.");
			} else {
				alert("메일 발송이 실패했습니다.");
			}
		}
	});
}

function isPcResolution() {
	return $(window).width() > 1024
}