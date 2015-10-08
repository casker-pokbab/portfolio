/* loader & header
----------------------------------------------------------------------------------------------*/
$(window).load(function() {
	$('#loader').fadeOut();
});


$(document).ready(function(){

	// work 목록
	$('#masonrybox').masonry({
		itemSelector: '.item'
	});

	// work list & height값
	var $workHeight = $('#masonrybox .item').width();
	$('#masonrybox .item').css("height", $workHeight);
	
	$(window).resize(function(){
		var $workHeight = $('#masonrybox .item').width();
		$('#masonrybox .item').css("height", $workHeight);

		//browser resize
		//다운로드 목록 확인부분 없앨시 height 리사이즈
		var intro_h1 = $(window).height();
		$('.wrap_intro').css("height", intro_h1);

		// gnb
		var $width_cover = $(window).width() - 300;
		$('.cover_gnb').css('width', $width_cover);

		//visual
		var visual_h = $(window).height() - 96;
		$('.main_visual').css('height', visual_h);

		// work pattern height
		$height_work_con = $('#work').height() - 560;
		$('.bg_ptn').css("height", $height_work_con);
	});

	// work list mouseenter event
	$('.bg_text').hide();
	$('#masonrybox .item .cont_work').mouseover(function(){
		$(this).children('.bg_text').fadeIn(300);
	});
	$('#masonrybox .item .cont_work').mouseleave(function(){
		$(this).children('.bg_text').fadeOut(100);
	});	

	// work pattern height
	$height_work_con = $('#work').height() - 560;
	$('.bg_ptn').css("height", $height_work_con);


	//header ↔ minHeader
	$(window).scroll(function(){
		if($(window).scrollTop() >= 50){
			$('#wrap').addClass('minHeader');
		};
		if($(window).scrollTop() == 0){
			$('#wrap').removeClass('minHeader');
		};
	});

	// gnb
	var $width_cover = $(window).width() - 300;
	$('.cover_gnb').css('width', $width_cover);

	$('#navbutton > a').click(function(){
		$(this).toggleClass("on");
		$('#nav').toggleClass("show");
		$('.cover_gnb').toggleClass("show");
		// $(body).css("overflow-y", "none")
	});

	//visual
	//비주얼 높이 조정
	var visual_h = $(window).height() - 96;
	$('.main_visual').css('height', visual_h);

	// Scroll To, 상단으로 가기, 가속도값
	$('.skip_intro, .go_work, .go_about, .go_contact').on('click',function (e) {
		$('#navbutton > a').removeClass("on");
		$('#nav').removeClass("show");
		$('.cover_gnb').removeClass("show");
		e.preventDefault();
		var target = this.hash,
		$target = $(target);
		$('html, body').stop().animate({
			'scrollTop': $target.offset().top - 59
		}, 800, 'easeInOutExpo', function () {
			window.location.hash = target;
		});
	});
	// !- 59
	$('.go_top').on('click',function (e) {
		$('#navbutton > a').removeClass("on");
		$('#nav').removeClass("show");
		$('.cover_gnb').removeClass("show");
		e.preventDefault();
		$('html, body').stop().animate({
			'scrollTop': 0
		}, 800, 'easeInOutExpo', function () {
			window.location.hash = target;
		});
	});

	// 상단으로 가기 버튼 숨기기
	$(".btn_letgo").hide();
	$(window).scroll(function(){		
		var srolled = $(window).scrollTop();		
		if(srolled >= 230){
			 $(".btn_letgo").stop().fadeIn();
		}
		else{			
			$(".btn_letgo").stop().fadeOut();
		}
	});

	// 인트로 배경 슬라이드
	$('.bg_slides').bxSlider({
		mode: 'fade',
		pause: 3000,
		speed: 800,
		// randomStart: true,
		easing: 'ease-in-out',
		touchEnabled: true,
		responsive: true,
		pager: false,
		controls: false,
		auto: true
	});

	// 메인 비주얼 슬라이드
	$('.main_visual').bxSlider({
		mode: 'horizontal',
		speed: 500,
		easing: 'ease-in-out',
		touchEnabled: true,
		responsive: true,
		pager: true,
		controls: true,
		auto: true
	});
	// 메인 비주얼 height 설정
	var main_visual_height = $(window).height()-96;
	// alert(main_visual_height);
	$('.main_visual').height(main_visual_height);

	// bx슬라이드 플러그인
	$('#').bxSlider({
		mode: 'horizontal',
		speed: 500,
		easing: 'ease-in-out',
		touchEnabled: true,
		responsive: true,
		pager: true,
		controls: true,
		auto: true
	});
	// slide dot 가운데 정렬
	var bxpager = $(".bx-pager").width() / 2;
	$(".bx-pager").css("marginLeft",-bxpager);

	// 개인정보보호정책 체크박스
	$('.btn_privacypolicy').click(function(){
		$(this).toggleClass("checked");
	});
});

// datapicker
$(document).ready(function(){
	$( ".call_datepicker" ).datepicker({
		showAnimation: 'slide',
		dateFormat: 'yy-mm-dd',
		minDate : 0,
		showOtherMonths: true,
		selectOtherMonths: true,
		buttonImageOnly: true
	});
		
	$( ".call_datepicker" ).change(function(){
		$(this).css("backgroundImage","none");
	});
});