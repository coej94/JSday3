// @author Thomas Hartmann - tha@cphbusiness.dk created on Feb 26, 2017 
var p = document.getElementById("p");
var content = '<div id="personContainer"></div>';
content += '<button id="personButton">Click to get new person</button>';

p.innerHTML = content;

var container = document.getElementById("personContainer");
var button = document.getElementById("personButton");

//Opgave a-d
button.onclick = function () {
    fetch("http://localhost:8084/JSday3/PersonServlet", {
        method: 'get'
    }).then(function (response) {
        //console.log(response.text());
        return response.text();
    }).then(function (text) {
        var person = JSON.parse(text);
        console.log(person);
        container.innerHTML = person.firstname + " " + person.lastname;
    });
};
var form = document.getElementById("frm1");
form.onsubmit = function(){
    var firstname = form.elements[0].value;
    var lastname = form.elements[1].value;
    var age = form.elements[2].value;
    var promise = fetch("http://localhost:8084/JSday3/PersonServlet", {
        method: 'post',
        body: JSON.stringify({
		firstname: firstname,
		lastname: lastname,
                age: age
	})
    });
    promise.then(function(response){
        return response.text();
    }).then(function(text){
        alert(text);
    });
    return false;
};

