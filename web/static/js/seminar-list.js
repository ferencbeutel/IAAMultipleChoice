/**
 * Adaption of element heights in seminar list
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
$('#seminarList').children('.row').each(function(i, e) {
    var children = $(e).children('.seminarListItemEntry');
    var height = $(e).outerHeight();
    children.each(function(i, child) {
        child.style.height = height + "px";
    });
});

$(".seminarListItem:not('.seminarListHeader')").click(function (event) {
    window.location.href = "seminar-detail?seminarId=" + $(event.currentTarget).data("id");
});