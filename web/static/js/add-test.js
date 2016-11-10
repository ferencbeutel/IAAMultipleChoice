/**
 * Functionality for buttons in edit test site
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
$(".add-test-button").click(function (event) {
    event.stopPropagation();
    var seminarId = $(event.currentTarget).closest(".row").data("id");
    window.location.href = "add-test-form?seminarId=" + seminarId;
});

$(".edit-test-button").click(function (event) {
    event.stopPropagation();
    var seminarId = $(event.currentTarget).closest(".row").data("id");
    window.location.href = "edit-test-form?seminarId=" + seminarId;
});