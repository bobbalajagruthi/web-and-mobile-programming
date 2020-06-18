//when used clicks submit button getVal func is called
function getVal() {
    var username = document.getElementById("username").value
    getgithubuserdetails(username)
}
//after taking the input from input box we are making XMLHttpRequest
function getgithubuserdetails(user) {
    // Create a request variable and assign a new XMLHttpRequest object to it.
    var request = new XMLHttpRequest()
        //opening the request
    request.open('GET', "https://api.github.com/users/" + user, true)
        //sending the request
    request.send();
    //we will get the response
    request.onload = function() {
        if (request.status == 200) {
            // Begin accessing JSON data here
            var data = JSON.parse(request.responseText)
                //from the json respose we are using the fields which are necessary for us
            document.getElementById("Name").innerHTML = data.login
            document.getElementById("id1").innerHTML = data.id
            document.getElementById("photo").style.backgroundImage = "url('" + data.avatar_url + "')"
            document.getElementById("link").innerHTML = data.html_url
            document.getElementById("profile").style.display = "block"
            document.getElementById("comment").innerHTML = data.login + " found Successfully!!!!"
        } else {
            document.getElementById("profile").style.display = "None"
            document.getElementById("comment").innerHTML = " Error!!!! Didnot find the user "
        }
    }
}