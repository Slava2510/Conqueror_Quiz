


function adaptive() {
	$("#map").css({
		"width":$(document).width(),
		"height":$(document).height()/*,
		/*"top":"50px" ,
		"left":"50px"*/
	}) ;


}

$(window).ready(function(e) {
	var w = $(document).width() ;
	var h = $(document).height() ;
	adaptive() ;
	$("#map").click(function(e) {
		alert((e.pageX/w)*100+"|"+(e.pageY/h)*100) ;
	}) ;

    $(".point").mouseover(function(e) {
        $(this).animate({width:"+=10px",height:"+=10px",top:"-=5px",left:"-=5px"},300) ;
    });
	 $(".point").mouseout(function(e) {
        $(this).animate({width:"-=10px",height:"-=10px",top:"+=5px",left:"+=5px"},300) ;
    });
	 $("#close").mouseover(function(e) {
        $(this).animate({width:"+=10px",height:"+=10px",top:"-=5px",left:"-=5px"},300) ;
    });
	 $("#close").mouseout(function(e) {
        $(this).animate({width:"-=10px",height:"-=10px",top:"+=5px",left:"+=5px"},300) ;
    });
	$(".point").click(function(e) {
		alert("Go!") ;
	});
});