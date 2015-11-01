var defaultPageSize = 10;

$(document).ready(function() {
	$("#btn_search").on("click", function() {
		getPortfolioList();
	});
	
	getPortfolioList();
	

	$("input[type=file]").on("change", function() {
		if(window.FileReader){  // modern browser
			var filename = $(this)[0].files[0].name;
		} else {  // old IE
			var filename = $(this).val().split('/').pop().split('\\').pop();
		}
		$(this).siblings('.file-name').val(filename);
	});
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
		, data : "pageNum=" + pageNum + "&pageSize=" + pageSize + "&" + $("#form").serialize()
		, success : function(data) {
			$(".list_area").empty().append(data);
			adjustSort("high");
			adjustSort("low");
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
	
	getPortfolioList(pageNum, defaultPageSize)
}

/**
 * 포트폴리오 삭제
 */
function removePortfolio() {
	if (!confirm("포트폴리오를 삭제하시겠습니까?")) {
		return;
	}
	
	$(".list_area").find("input[type=checkbox]:checked").each(function() {
		var no = $(this).attr("no");
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

function adjustSort(sortType) {
	$(".list_area ." + sortType).on("click", function() {
		$.ajax({
			  url : "/admin/portfolio/sort/adjust"
			, type : "html"
			, method : "POST"
			, data : "sort=" + $(this).parent().attr("sort") + "&sortType=" + sortType.toUpperCase()
			, success : function(data) {
				goPage($(".board_num_list .num.on").text());
			}
		});
	});
}