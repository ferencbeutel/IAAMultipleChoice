var descriptionHeight = $("#seminarDescriptionText").height() +"px";
console.log(descriptionHeight);
$("#seminarDescriptionLabel").height(descriptionHeight);

$(".enrollButton").click(function (event) {
    window.location.href = "enroll-seminar?seminarId=" + $("#hiddenSeminarId").val();
});

$(function () {
    $('[data-toggle="tooltip"]').tooltip()
});

