$(document).ready(function() {
	
	$("#displayYN").on("click", function() {
		$("#displayYN").val($(this).prop("checked") ? "Y" : "N");
	});
	
});