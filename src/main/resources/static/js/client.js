var userName = 'user' + Math.floor((Math.random() * 1000) + 1);
var socket = io('http://localhost:9092/chat');
var equipos;
var equipoFase1;
var equipoFase2 ;
var equipoFase3;
var equipoFase4;
socket.connect();

$(document.body).on('webkitAnimationEnd', function(e){
    var animName = e.originalEvent.animationName;
    console.log(animName);
    //emit when a animation finish
    socket.emit('finishAnimation', animName);

})

socket.on('getEquipoFase2', function (data) {

    console.log("get equipo fase 2", data);
    equipoFase2 = data;

});

//when an animation finish
socket.on('animationType', function (data) {
    switch(data.animation){
        case 'myOrbit2' :
            activateAnimation(10, 2);
            break;
        case 'myOrbit3' :
            activateAnimation(11, 2);
            break;
        case'move10':
            activateAnimation(18, speedToTime(equipoFase2[1].velocidad_recta));
            break;
    }
});

//when a user connects
socket.on('connect', function () {
    output('<span class="connect-msg">Client has connected to the server!</span>');
});

//master
socket.on('master', function (data) {
    console.log(data);
    displayEquipos();
});

//send chat message
socket.on('chat', function (data) {
    console.log('Received message', data);
    output('<span class="username-msg">' + data.userName + ':</span> ' + data.message);
});

//disconnect from network
socket.on('disconnect', function () {
    output('<span class="disconnect-msg">The client has disconnected!</span>');
});

//quantity of connected
socket.on('cantidadConectados', function (data) {
    console.log('Received conectados', data);
    conectados = data.id_conectados;

    $(".displayConectados").html(data.conectados);
});

//divide .html to different users
socket.on('htmlType', function (data) {

    console.log("antes del req");

    $('#connectedModal').modal('hide');
    $('#body').load('views/' + data );
    $('#equiposModal').modal({backdrop: 'static', keyboard: false});

});

$(document).ready(function(){
    goHome();
});

function goHome() {
    $('#body').load('views/home.html');
}

function goHistorialCarreras() {
    displayHistorialCarreras();
    $('#body').load('views/historial-carreras.html');

}

function goCorredores() {
    displayCorredores();
    $('#body').load('views/corredores.html');

}

//accept begin with that quantity of users
function startSimulator() {

    console.log("He dado click a empezar");
    var jsonObject = {start: true};
    socket.emit('firstStart', jsonObject);

}

function activateAnimation(i, time)
{
    var classId = 'circle' + i;
    var circle = document.getElementById(classId);
    circle.style["animation-duration"] = time + "s";
    circle.style["animation-name"] = "move" + i;
    circle.style.animationIterationCount = "1";
    circle.style.animationFillMode = "forwards";
}



function activeEquiposFase1(equipos)
{

    var k = 0;
    var time = 0;
    for(var i = 1; i <= 8; i++){

        var classId = 'circle' + i;

        if(equipos[k].fase === 1 || equipos[k].fase === 3) {
            time = speedToTime(equipos[k].velocidad_curva);
        }
        else {
            time = speedToTime(equipos[k].velocidad_recta);
        }

        console.log("clase",classId);
        console.log("tiempo", time);
        equipos[k]['circleId'] =  i;
        console.log("un requipo", equipos[k]);

        var circle = document.getElementById(classId);
        circle.style["animation-duration"] = time + "s";
        circle.style["animation-name"] = "myOrbit" + i;
        circle.style.animationIterationCount = "1";
        circle.style.animationFillMode = "forwards";

    }

}

socket.on('cerrarMyModal', function (data) {

    console.log("cerrar modal", data);
    $('#equiposModal').modal('hide');

});




//start simulator
function startRace() {

    socket.emit('cerrarModal', 'hide');

    equipoFase1 = orderArrays(equipos, 1);
    equipoFase2 = orderArrays(equipos, 2);
    equipoFase3 = orderArrays(equipos, 3);
    equipoFase4 = orderArrays(equipos, 4);

    socket.emit('sendEquipoFase2', equipoFase2);


    console.log("He dado click a iniciar carrera");


    activeEquiposFase1(equipoFase1);
    console.log("equipos de fase 1", equipoFase1);
    console.log("equipos de fase 2", equipoFase2);
}


function sendDisconnect() {
    socket.disconnect();
}

function sendMessage() {
    var $msg = $('#msg');
    var message = $msg.val();
    $msg.val('');
    var jsonObject = {userName: userName, message: message, actionTime: new Date()};
    socket.emit('chat', jsonObject);
}

function output(message) {
    var currentTime = "<span class='time'>" + moment().format('HH:mm:ss.SSS') + "</span>";
    var element = $("<div>" + currentTime + " " + message + "</div>");
    $('#console').prepend(element);
}

$(document).keydown(function (e) {
    if (e.keyCode == 13) {
        $('#send').click();
    }
});

socket.on('equipoList', function (data) {

    console.log("equipos para todos", data);
    equipos = data.equipos;
    $('.tableEquipos').bootstrapTable({
        data: data.equipos
    });

});


function orderArrays(equipos, fase)
{

    var equiposFase = {};
    for(var x in equipos){

        if(equipos[x].fase === fase) {

            equiposFase.push(equipos[x]);

        }

    }
    console.log("ordenado equipo!!", equiposFase);
    return equiposFase;

}



function speedToTime(speed)
{
    return ((0.1 / speed)*3600);

}


function displayEquipos()
{
    $.ajax({
        url: "http://localhost:8080/equipo/all",
        type: 'GET',
        success: function (entry)
        {
            equipos = entry;
            var jsonObject = {equipos: entry};
            socket.emit('getEquipos', jsonObject);


        }
    });

}

function displayHistorialCarreras()
{
    $.ajax({
        url: "http://localhost:8080/historial/carrera",
        type: 'GET',
        success: function (entry)
        {

            $('.tableCarreras').bootstrapTable({
                data: entry
            });
        }
    });

}

function displayCorredores()
{
    $.ajax({
        url: "http://localhost:8080/corredores",
        type: 'GET',
        success: function (entry)
        {
            console.log(entry);
            $('.tableCorredores').bootstrapTable({
                data: entry
            });
        }
    });

}