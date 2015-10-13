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
		minDate : 0,
		showOtherMonths: true,
		selectOtherMonths: true,
		buttonImageOnly: true
	});

	// 투자정보목록 datapicker
	$( "#date_invest_start" ).datepicker({
		showAnimation: 'slide',
		dateFormat: 'yy-mm-dd',
		minDate : 0,
		showOtherMonths: true,
		selectOtherMonths: true,
		buttonImageOnly: true
	});
	$( "#date_invest_end" ).datepicker({
		showAnimation: 'slide',
		dateFormat: 'yy-mm-dd',
		minDate : 0,
		showOtherMonths: true,
		selectOtherMonths: true,
		buttonImageOnly: true
	});

	$( "#date_case_start").datepicker({
		showAnimation: 'slide',
		dateFormat: 'yy-mm-dd',
		minDate : 0,
		showOtherMonths: true,
		selectOtherMonths: true,
		buttonImageOnly: true
	});
	$( "#date_case_end").datepicker({
		showAnimation: 'slide',
		dateFormat: 'yy-mm-dd',
		minDate : 0,
		showOtherMonths: true,
		selectOtherMonths: true,
		buttonImageOnly: true
	});

	$( "#date_notice_write").datepicker({
		showAnimation: 'slide',
		dateFormat: 'yy-mm-dd',
		minDate : 0,
		showOtherMonths: true,
		selectOtherMonths: true,
		buttonImageOnly: true
	});
	$( "#date_notice_modify").datepicker({
		showAnimation: 'slide',
		dateFormat: 'yy-mm-dd',
		minDate : 0,
		showOtherMonths: true,
		selectOtherMonths: true,
		buttonImageOnly: true
	});

	$( "#project_start01").datepicker({
		showAnimation: 'slide',
		dateFormat: 'yy-mm-dd',
		minDate : 0,
		showOtherMonths: true,
		selectOtherMonths: true,
		buttonImageOnly: true
	});
	$( "#project_end01").datepicker({
		showAnimation: 'slide',
		dateFormat: 'yy-mm-dd',
		minDate : 0,
		showOtherMonths: true,
		selectOtherMonths: true,
		buttonImageOnly: true
	});

	$( "#basic_date_start").datepicker({
		showAnimation: 'slide',
		dateFormat: 'yy-mm-dd',
		minDate : 0,
		showOtherMonths: true,
		selectOtherMonths: true,
		buttonImageOnly: true
	});
	$( "#basic_date_end").datepicker({
		showAnimation: 'slide',
		dateFormat: 'yy-mm-dd',
		minDate : 0,
		showOtherMonths: true,
		selectOtherMonths: true,
		buttonImageOnly: true
	});
	
});