/**
 * Edit question form adaption with change of question type
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
var selectedType = $('#select-question-type').val();

$('.hiddenAnswer').each(function (i, e) {
    var text = $(e).data("text");
    var buttonState = $(e).data("value");

    switch (selectedType) {
        case "Single":
            buildSingleChoiceAnswer(text, buttonState);
            break;
        case "Multiple":
            buildMultiChoiceAnswer(text, buttonState);
            break;
        case "Gap":
            buildGapAnswer(i, text, $('#gapAnswers'));
    }
});