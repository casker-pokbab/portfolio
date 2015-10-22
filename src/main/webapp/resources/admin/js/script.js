$(document).ready(function(){

	// gnb
	$('.dep2').hide();
	$('#gnb > li').click(function(){
		$(this).find('.dep2').slideToggle('fast');
	});
	$('.dep3').mouseleave(function(){
		$(this).fadeOut('fast');
	});

	// gnb active
	var $smenu=$('.dep2 > li');
	var $locTxt=$('.loc').text();
	for (var i=0; i<$smenu.length; i++){
		var menutxt=$.trim($smenu.eq(i).find('a').text());
		var loctxt=$.trim($locTxt);

		if (menutxt == loctxt){
			$smenu.eq(i).children('a').addClass('active')
			$smenu.eq(i).parent('.dep2').show();
		}
	}

	// 코드관리팝업
	$('.pop_register').hide();
	$('#btn_resister01, #btn_resister02, #btn_resister03').click(function(){
		$('.modal_bg').fadeIn('fast');
		$('.pop_register').fadeIn('slow');
	});
	$('.btn_submit02').click(function(){
		$('.modal_bg').fadeOut('slow');
		$('.pop_register').fadeOut('fast');
	});

	// datapicker
	$( "#date_start, #date_end" ).datepicker({
		showAnimation: 'slide',
		dateFormat: 'yy-mm-dd',
		showOtherMonths: true,
		selectOtherMonths: true,
		buttonImageOnly: true
	});
});