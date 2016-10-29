/**
 * Created by Melanie on 26.10.2016.
 */
$(".add-testButton").click(function (event) {
    event.stopPropagation();
    var seminarId = $(event.currentTarget).closest(".row").data("id");
    window.location.href = "add-test-form?seminarId=" + seminarId;
});

$(".editTestButton").click(function (event) {
    event.stopPropagation();
    var seminarId = $(event.currentTarget).closest(".row").data("id");
    window.location.href = "edit-test-form?seminarId=" + seminarId;
});