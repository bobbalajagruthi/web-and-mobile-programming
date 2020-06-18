function upDate(imgsrc) {
    console.log(imgsrc)
    var src = ''
    src = imgsrc.getAttribute("src");

    var alt = imgsrc.getAttribute("alt");
    console.log(alt)
    document.getElementById('image').style.backgroundImage = "url('" + src + "')";
    document.getElementById('image').innerHTML = alt;

}

function unDo() {
    document.getElementById('image').style.backgroundImage = "url('')";
    document.getElementById('image').innerHTML = "Hover over an images to display";

}