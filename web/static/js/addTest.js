/**
 * Created by Melanie on 26.10.2016.
 */
$(".addTestButton").click(function (event) {
    var seminarName = $(event.currentTarget).closest(".row").data("name");
    var seminarId = $(event.currentTarget).closest(".row").data("id");
    window.location.href = "addTest-form?seminarName=" + seminarName + "&seminarId=" + seminarId;
});