$(document).ready(function() {
	
	$("#displayYN").on("click", function() {
		$("input[name=displayYN]").val($(this).prop("checked") ? "Y" : "N");
	});
	
});