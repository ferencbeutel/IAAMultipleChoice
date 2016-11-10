/**
 * functionality of buttons
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
var descriptionHeight = $("#seminarDescriptionText").height() +"px";
$("#seminarDescriptionLabel").height(descriptionHeight);

$(".enrollButton").click(function (event) {
    window.location.href = "enroll-seminar?seminarId=" + $("#hiddenSeminarId").val();
});

$(function () {
    $('[data-toggle="tooltip"]').tooltip()
});

