/**
 * Created by melanie on 30.10.2016.
 */
var startDate = new Date($('#startDate').val());
if (startDate <= new Date()){
    $(':input').prop('disabled', true);
}

$(".add-question-button").click(function (event) {
    event.stopPropagation();
    var seminarId = $('#hiddenSeminarId').val();
    window.location.href = "add-question-form?seminarId=" + seminarId;
});

$(".edit-question-button").click(function (event) {
    event.stopPropagation();
    var seminarId = $('#hiddenSeminarId').val();
    var questionId = $(event.target).closest('.questionListItem').data("id");
    window.location.href = "edit-question-form?seminarId=" + seminarId + "&questionId=" + questionId;
});

$(".delete-question-button").click(function (event) {
    event.stopPropagation();
    var seminarId = $('#hiddenSeminarId').val();
    var questionId = $(event.target).closest('.questionListItem').data("id");
    window.location.href = "edit-test-delete-question?seminarId=" + seminarId + "&questionId=" + questionId;
});


$(function () {
    $('[data-toggle="tooltip"]').tooltip()
});

