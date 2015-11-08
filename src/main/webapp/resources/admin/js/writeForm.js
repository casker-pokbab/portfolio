$(document).ready(function() {
	
	$("#displayYN").on("click", function() {
		$("input[name=displayYN]").val($(this).prop("checked") ? "Y" : "N");
	});
	
	$("input[type=file]").on("change", function() {
		if(window.FileReader){  // modern browser
			var filename = $(this)[0].files[0].name;
		} else {  // old IE
			var filename = $(this).val().split('/').pop().split('\\').pop();
		}
		$(this).siblings('.file-name').val(filename);
	});
});

function addSubImageForm() {
	$.ajax({
		  url : "/admin/manegement/portfolio/subImageForm"
		, type : "html"
		, method : "GET"
		, data : "index=" + ($(".subImage").size())
		, success : function(data) {
			$(".subImage").last().after(data);
		}
	});
}

function removeSubImageForm() {
	if ($(".subImage").size() == 1) {
		alert("마지막 서브이미지 입니다.");
		return;
	}
	$(".subImage").last().remove();
}