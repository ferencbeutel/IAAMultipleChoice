$('#submitButton').click(function (e) {
    $('.gapAnswerInput').each(function (i, e) {
        e.value = e.value + "|" + $(e).parent().data("id");
    });
    $('#testSubmitForm').submit();
});

$('.slickContainer').slick({
    adaptiveHeight: true,
    dots: true,
    prevArrow: '<div class="slickNavButtonWrapper slickLeft"><span class="mega-octicon octicon-arrow-left"></span></div>',
    nextArrow: '<div class="slickNavButtonWrapper slickRight"><span class="mega-octicon octicon-arrow-right"></span></div>',
});