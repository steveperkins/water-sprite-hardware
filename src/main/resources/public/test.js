////Establish the WebSocket connection and set up event handlers
var webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/flow");
webSocket.onmessage = function (msg) { 
	updateChat(msg.data); 
};
webSocket.onclose = function () { alert("WebSocket connection closed") };

//Send message if "Send" is clicked
document.getElementById("send").addEventListener("click", function () {
	var obj = { player: parseInt(document.getElementById("player").value),
			speed: parseFloat(document.getElementById("speed").value),
			volume: parseFloat(document.getElementById("volume").value)
	};
    sendMessage(obj);
});

//Send message if enter is pressed in the input field
//document.getElementById("message").addEventListener("keypress", function (e) {
//    if (e.keyCode === 13) { sendMessage(e.target.value); }
//});

//Send a message if it's not empty, then clear the input field
function sendMessage(obj) {
    webSocket.send(JSON.stringify(obj));
}

//Update the chat-panel, and the list of connected users
function updateChat(msg) {
    insert("chat", msg);
}

//Helper function for inserting HTML as the first child of an element
function insert(targetId, message) {
	document.getElementById(targetId).insertAdjacentHTML("afterbegin", message);
}
