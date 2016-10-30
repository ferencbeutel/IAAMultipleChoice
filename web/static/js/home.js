//TODO: This is a gross hack and should definitely be replaced; Also, if we already have JQuery, we should use it instead of plain JS
NodeList.prototype.forEach = Array.prototype.forEach;

var semlist = document.getElementById("seminarList").childNodes;
semlist.forEach (function(seminarItem){
    var H = seminarItem.offsetHeight;
    var children = seminarItem.childNodes;
    children.forEach(function(childItem){
        console.log(childItem);
        if (childItem.nodeName == "DIV"){
            childItem.style.height=H+"px";
        }
    });
});

$(".studentItem").click(function (event) {
    event.stopPropagation();
    window.location.href = "seminar-detail?seminarId=" + $(event.currentTarget).data("id");
});

$(".lecturerItem").click(function (event) {
    event.stopPropagation();
    window.location.href = "edit-seminar-form?seminarId=" + $(event.currentTarget).data("id");
});

$(function () {
    $('[data-toggle="tooltip"]').tooltip()
});

$('.startTestButton.disabled').click(function (event) {
    event.stopPropagation();
});

$('.startTestButton:not(".disabled")').click(function (event) {
    event.stopPropagation();
    window.location.href = "start-test?seminarId=" + $(event.currentTarget).data("id");
});