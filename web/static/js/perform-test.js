$('#submitButton').click(function (e) {
    $('.gapAnswerInput').each(function (i, e) {
        e.value = e.value + "|" + $(e).parent().data("id");
    });
    $('#testSubmitForm').submit();
});

