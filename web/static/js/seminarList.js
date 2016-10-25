$(".seminarListItem:not('.seminarListHeader')").click(function (event) {
    window.location.href = "seminar-detail?seminarId=" + $(event.currentTarget).data("id");
});
$(".enrollButton").click(function (event) {
    event.stopPropagation();
    window.location.href = "enroll-seminar?seminarId=" + $(event.currentTarget).data("id");
});