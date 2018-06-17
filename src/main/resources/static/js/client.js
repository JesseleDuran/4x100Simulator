$(document).ready(function(){
    $('#body').load('views/home.html');

});

var userName = 'user' + Math.floor((Math.random() * 1000) + 1);
var socket = io('http://localhost:9092/chat');
socket.connect();

//when a user connects
socket.on('connect', function () {
    output('<span class="connect-msg">Client has connected to the server!</span>');
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
    console.log('Received conectados', data.conectados);
    var conectados = data.conectados;
    $(".displayConectados").html(conectados);
});

//divide .html to different users
socket.on('htmlType', function (data) {
    document.getElementById("test").textContent = data;
});

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




