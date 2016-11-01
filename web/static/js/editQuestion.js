/**
 * Created by Melanie on 01.11.2016.
 */
$(".edit-question-button").click(function (event) {
    event.stopPropagation();
    var questionId = $(event.currentTarget).closest(".row").data("id");
    window.location.href = "edit-question-form?questionId=" + questionId;
});