/**
 * Naviation for edit test, functionality of buttons
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
$('#questionList').children('.row').each(function(i, e) {
    var children = $(e).children('.questionListItemEntry');
    var height = $(e).outerHeight();
    children.each(function(i, child) {
        child.style.height = height + "px";
    });
});

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

