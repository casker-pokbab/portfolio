$(document).ready(function() {
	
	$(".btnArr").on("click", editPassword);
});

function editPassword() {
	if (!confirm("비밀번호를 바꾸시겠습니까?")) {
		return;
	}
	
	if ($("#password").val() != $("#passwordCheck").val()) {
		$("#passwordCheck").next().text("비밀번호가 맞지않습니다.");
		$("#passwordCheck").focus();
		return;
	}
	
	var data = $(".write_area form").serialize();
	$.ajax({
		  url : "/admin/password/edit"
		, type : "html"
		, method : "POST"
		, data : data
		, success : function(data) {
			if (data == "success") {
				alert("비밀번호가 정상적으로 수정되었습니다.")
			}
		}
	});
}