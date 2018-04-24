"use strict";

var testMode = false;
var sender;
var senderCurrentBalance;
function showPopup(agent) {
	$('#id01').show();
	sender=agent;
    /*makeTransaction(agent);*/
	var modal = document.getElementById('id01');
	document.getElementById('rec').value="";
	document.getElementById('amt').value='';
	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	    if (event.target == modal) {
	        modal.style.display = "none";
	    }
	}
}

function getAllAgents() {
	/*$('#myPopup1').hide();*/
    sendHttpRequest("GET", "agent/all", null, displayAllAgents);

    if (testMode) {
        displayAllAgents('[{"name":"Agent1","port":1001,"blockchain":[{"index":0,"creator":"Agent1","timestamp":1502193341671,"hash":"4f99b67b06b6831886815ffe66a55be2e34dcefdfc16b6214710313062a8a480","previousHash":"ROOT_HASH"}]}' +
            ', {"name":"Agent2","port":1002,"blockchain":[{"index":1,"creator":"Agent2","timestamp":1502193341671,"hash":"4f99b67b06b6831886815ffe66a55be2e34dcefdfc16b6214710313062a8a480","previousHash":"ROOT_HASH"}]}]');
    }
}

function deleteAllAgents() {
    sendHttpRequest("DELETE", "agent/all", null, getAllAgents);
}

function createAgent() {
    var idx = getNextCount();
    var name = "Agent" + idx;
    var port = 3000 + idx;
    var balance=1000;
    sendHttpRequest("POST", "agent/new?name=" + name + "&port=" + port + "&balance=" + balance, null, displayAgent);

    if (testMode) {
        displayAgent('{"name":"Agent1","port":1001,"blockchain":[{"index":2,"creator":"Agent1","timestamp":1502193341671,"hash":"4f99b67b06b6831886815ffe66a55be2e34dcefdfc16b6214710313062a8a480","previousHash":"ROOT_HASH"}]}');
    }
}

function deleteAgent(name) {
    sendHttpRequest("DELETE", "agent?name=" + name, null, getAllAgents);
}

function getAgent() {
    var name = document.getElementById("agentNameGet").value;
    sendHttpRequest("GET", "agent?name=" + name, null, null);
}

function makeTransaction() {
	//make popup and get receiver and value
	//{}
	//$('#id01').show();
	var rec=document.getElementById("rec").value;
	var amt=document.getElementById("amt").value;
	//sendHttpRequest("POST", "agent/balance?sender=" + sender,null,senderBalance)
	////if(senderCurrentBalance-amt>0){
		sendHttpRequest("POST", "agent/?sender=" + sender +"&amount="+ amt, null, null);
		sendHttpRequest("POST", "agent?receiver=" + rec +"&amount="+ amt, null, null);
		sendHttpRequest("POST", "agent/mine?agent=" + sender + "&rec=" + rec +
	    		"&amt="+ amt, null, getAllAgents);
		$('#id01').hide();
	//}
	
	
    
    
    if (testMode) {
        displayBlock('{"index":1,"creator":"Agent1","timestamp":1502194172250,"hash":"2461f27f811df15a969391c70f136869a282224e8cc6fe8b628d16a499515d21","previousHash":"4f99b67b06b6831886815ffe66a55be2e34dcefdfc16b6214710313062a8a480"}');
    }
}

function sendHttpRequest(action, url, data, callback) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            callback(xmlHttp.responseText);
        }
    };
    xmlHttp.open(action, url, true);
    xmlHttp.send(data);
}

var getNextCount = (function () {
    if (!sessionStorage.count) {
        sessionStorage.count = 0;
    }
    return function () {
        sessionStorage.count = Number(sessionStorage.count) + 1;
        return Number(sessionStorage.count);
    }
})();

function senderBalance(bal){
	senderCurrentBalance=bal;
}

