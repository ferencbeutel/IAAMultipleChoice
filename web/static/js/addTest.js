/**
 * Created by Melanie on 26.10.2016.
 */
$(".addTestButton").click(function (event) {
    var test = $(event.currentTarget).closest(".row").data("id");
   // window.location.href = "addTest-form?seminarName=" + $(event.currentTarget).data("name");
    window.location.href = "addTest-form?seminarId=" + $(event.currentTarget).closest(".row").data("id");
});