$('#seminarList').children('.row').each(function(i, e) {
    var children = $(e).children('.seminarListItemEntry');
    var height = $(e).outerHeight();
    children.each(function(i, child) {
        child.style.height = height + "px";
    });
});

$(".studentItem").click(function (event) {
    event.stopPropagation();
    window.location.href = "seminar-detail?seminarId=" + $(event.currentTarget).data("id");
});

$(".lecturerItem").click(function (event) {
    event.stopPropagation();
    window.location.href = "edit-seminar-form?seminarId=" + $(event.currentTarget).data("id");
});

$(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();
});

$('.startTestButton.disabled').click(function (event) {
    event.stopPropagation();
});

$('.startTestButton:not(".disabled")').click(function (event) {
    event.stopPropagation();
    window.location.href = "start-test?seminarId=" + $(event.currentTarget).data("id");
});

$('.hiddenResultToken').each(function(i, e) {
    var passed = $(e).val();
    if(passed == 'true') {
        $(e).parent().css('background-color', 'green')
    } else {
        $(e).parent().css('background-color', 'red')
    }
});