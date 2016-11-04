function submitForm() {
    $('.gapAnswerInput').each(function (i, e) {
        e.value = e.value + "|" + $(e).parent().data("id");
    });
    $('#testSubmitForm').submit();
}

$('#submitButton').click(function (e) {
    submitForm();
});

var slickSettings = {
    adaptiveHeight: true,
    dots: true,
    prevArrow: '<div class="slickNavButtonWrapper slickLeft"><span class="mega-octicon octicon-arrow-left"></span></div>',
    nextArrow: '<div class="slickNavButtonWrapper slickRight"><span class="mega-octicon octicon-arrow-right"></span></div>',
}

$('#slickContainer').slick(slickSettings);

$('.postponeQuestionButton').click(function (e) {
    var divToMove = $(e.target).closest('.question');
    var index = divToMove.data('slick-index');
    var slickContainer = $('#slickContainer');
    slickContainer.slick('slickRemove', index);
    slickContainer.slick('slickAdd', divToMove);
});

var duration = $('#hiddenDuration').html();
var split = duration.split(":");
var hours = split[0];
var minutes = split[1];
var toAdd = (parseInt(hours) * 60 + parseInt(minutes)) * 60000;
$('#countdown').countdown(new Date(new Date().getTime() + toAdd), function(e) {
    $(this).text(e.strftime('%H:%M:%S'));
    if(e.elapsed) {
        submitForm();
    }
});