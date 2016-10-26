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

$(".seminarListItem:not('.seminarListHeader')").click(function (event) {
    window.location.href = "seminar-detail?seminarId=" + $(event.currentTarget).data("id");
});
$(".enrollButton").click(function (event) {
    event.stopPropagation();
    window.location.href = "enroll-seminar?seminarId=" + $(event.currentTarget).closest(".row").data("id");
});