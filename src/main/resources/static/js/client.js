var userName = 'user' + Math.floor((Math.random() * 1000) + 1);
var socket = io('http://localhost:9092/chat');
socket.connect();

socket.on('connect', function () {
    output('<span class="connect-msg">Client has connected to the server!</span>');
});

socket.on('chat', function (data) {
    console.log('Received message', data);
    output('<span class="username-msg">' + data.userName + ':</span> ' + data.message);
});

socket.on('disconnect', function () {
    output('<span class="disconnect-msg">The client has disconnected!</span>');
});

socket.on('cantidadConectados', function (data) {
    console.log('Received conectados', data.conectados);
});

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