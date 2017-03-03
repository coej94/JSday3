var quote1 = document.getElementById("quote");
var onPage = '<div id="theQuoteID"></div>';
onPage += '<button id="quoteButton">New Quote!</button>';
onPage += '<h3>The quote below updates once every 5th second.</h3>';
onPage += '<div id="autoQuote"></div>';

quote1.innerHTML = onPage;
var btn = document.getElementById("quoteButton");
var qID = document.getElementById("theQuoteID");
btn.onclick = function () {
    var url = "http://localhost:8084/JSday3/QuoteServlet";
    
    fetch(url, {
        method: 'get'
    }).then(function (response) {
       // console.log(response.text());
        return response.text();
    }).then(function (text) {
        //console.log(text);
        qID.innerHTML = text;
    });
};

var autoQu = document.getElementById("autoQuote");
(function(){
    setInterval(function () {
        var url = "http://localhost:8084/JSday3/QuoteServlet";
        fetch(url, {method: 'get'})
            .then(function (response) {
                return response.text();
            })
            .then(function(text){
                autoQu.innerHTML = text;
            });
    }, 5000);
})();

