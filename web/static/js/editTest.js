/**
 * Created by melanie on 30.10.2016.
 */
$(".add-question-button").click(function (event) {
    event.stopPropagation();
    var seminarId = $(event.currentTarget).closest(".row").data("id");
    window.location.href = "addQuestion-form?seminarId=" + seminarId;
});