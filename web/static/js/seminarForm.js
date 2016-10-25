$(".seminarListItem").click(function (event) {
    window.location.href = "seminarDetail?seminarId=" + $(event.currentTarget).data("id");
});
$(".enrollButton").click(function (event) {
    event.stopPropagation();
    window.location.href = "enrollForSeminar?seminarId=" + $(event.currentTarget).data("id");
});