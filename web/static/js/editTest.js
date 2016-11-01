/**
 * Created by melanie on 30.10.2016.
 */
$(".add-question-button").click(function (event) {
    event.stopPropagation();
    var seminarId = $('#hiddenSeminarId').val();
    window.location.href = "add-question-form?seminarId=" + seminarId;
});