val = ''
result = ''
userval = ''
compval = ''


// function for choices choosen by computer by math func
function getcomputerchoice() {

    const choices = ['R', 'P', 'S'];
    //Math.random is used to select the number between 0 to 1
    //Math.floor is used to select the integer value (number rounded to low number)
    const randomnumber = Math.floor(Math.random() * 3);
    val = choices[randomnumber]
    if (val = "R") {
        compval = "R"
        result = "Computer Choose Rock as weapon";
    }

    if (val = "P") {
        compval = "P"
        result = "Computer Choose paper as weapon";
    }
    if (val = "S") {
        compval = "S"
        result = "Computer Choose scissors as weapon";
    }
    return result

}


// when user clicks on Rock button
function myFunction1() {
    userval = "R"
    document.getElementById("R").innerHTML = "User Choose Rock as weapon";
    document.getElementById("P").style.display = "none";
    document.getElementById("S").style.display = "none";
    document.getElementById("para").style.display = "none";
    document.getElementById("C").innerHTML = getcomputerchoice();
    game();

}
//when user clicks on paper button
function myFunction2() {
    userval = "P"
    document.getElementById("P").innerHTML = "User Choose Paper as weapon";
    document.getElementById("R").style.display = "none";
    document.getElementById("S").style.display = "none";
    document.getElementById("para").style.display = "none";
    document.getElementById("C").innerHTML = getcomputerchoice();
    game();
}
// when user clicks on Scissors button
function myFunction3() {
    userval = "S"
    document.getElementById("S").innerHTML = "User Choose Scissors as weapon";
    document.getElementById("P").style.display = "none";
    document.getElementById("R").style.display = "none";
    document.getElementById("para").style.display = "none";
    document.getElementById("C").innerHTML = getcomputerchoice();
    game();
}

// used for redirecting the page
function myFunction4() {
    window.location = "../Task1/Task1.html";
}

// swith case used to identify the win lose or  tie
function game() {

    switch (userval + compval) {
        case "SR":
        case "RP":
        case "PS":
            document.getElementById("b").style.display = "block";
            document.getElementById("d").style.display = "block";

            break;
        case "RS":
        case "PR":
        case "SP":
            document.getElementById("a").style.display = "block";
            document.getElementById("d").style.display = "block";
            break;
        case "RR":
        case "PP":
        case "SS":
            document.getElementById("c").style.display = "block";
            document.getElementById("d").style.display = "block";
            break;
    }
}