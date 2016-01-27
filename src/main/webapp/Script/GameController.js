var AnswerSend = false;
var PlayerName ;

//timers
var startPoint;
var id1 ;
function setStartPointTimer() {
    var date = new Date();
    startPoint = date.getTime();
}
function getTime() {
    var date = new Date();
    return date.getTime() - startPoint;
}

function startTimer(time) {
    id1 = setInterval(function() {
        $("#timer").html(time) ;
        if (time > 0) time = time -1 ; else {
            if(AnswerSend==false) {
                AnswerSend=true ;
                if ($("#answerField").val() == "") sendAnswer(0); else
                    sendAnswer($("#answerField").val());
            }
            clearInterval(id1) ;
        }
    },1000) ;
}
//timers

function checker() {
    var id = setInterval(function () {
        $.post("/game2", InServer,
            function (data) {
                OutServer = data;
            }, "json");
    }, 500);
}

function sendAnswer(value){
    var time = getTime() ;
    AnswerSend=false ;
    var id = setInterval(function() {
    $.ajax({
        type:"POST",
        url: "/game",
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
            "name":PlayerName,
            "answer":value ,
            "time":time
        }),
        success:function(data) {
            clearInterval(id) ;
            $("#load").hide() ;
            $("#questionContainer").hide() ;
            $("#answerContainer").show() ;
            $("#trueResponse").html(data.trueAnswer) ;
            $("#Answer1 .playerName").html(data.name1) ;
            $("#Answer1 .answer").html(data.answer1) ;
            $("#Answer2 .playerName").html(data.name2) ;
            $("#Answer2 .answer").html(data.answer2) ;
            if(data.winner==data.name1) {
                $("#Answer1").css("background", "green") ;
                $("#Answer2").css("background", "red") ;
            } else {
                $("#Answer2").css("background","green") ;
                $("#Answer1").css("background", "red") ;
            }
            $("#Answer1 .time").html(data.time1) ;
            $("#Answer2 .time").html(data.time2) ;
            $("#p1").html(data.name1+":"+data.score1) ;
            $("#p2").html(data.name2+":"+data.score2) ;
            setTimeout(init,9000) ;
        }
    });
    },500) ;
    //checker() ;
}

function init() {
    var id = setInterval(function () {
        $.get("/game",{
                "type":"init",
                "name":PlayerName
            } ,
            function (data) {
                $("#init").hide() ;
                $("#answerContainer").hide();
                $("#question").html(data.question) ;
                $("#answerField").val("") ;
                startTimer(15) ;
                setStartPointTimer() ;
                $("#questionContainer").show() ;
                clearInterval(id) ;
            }, "json");
    }, 500);
}

$(document).ready(function () {
    $("#questionContainer").hide();
    $("#answerContainer").hide();
    $("#load").hide() ;
    $("#name").val("Enter your name !") ;
    $("#name").focus(function(e) {
        $("#name").val("") ;
    }) ;
    $("#name").keydown(function (e) {
        if (e.keyCode == 13 && $("#name").val() != "") {
            PlayerName = $("#name").val();
            $("#name").remove();
            $("#init").html("Зачекайте на вашого суперника !");
            init() ;
        }
    });
    $("#answerField").keydown(function (e) {
        if (e.keyCode == 13) {
            AnswerSend = true;
            $("#load").show() ;
            if ($("#answerField").val() == "") sendAnswer(0); else
                sendAnswer($("#answerField").val());
            clearInterval(id1);
        }
    });
});
