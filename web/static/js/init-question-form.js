/**
 * Processing of question creation
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
var selectedVal;

function buildSingleChoiceAnswer(text, buttonState) {
    var answerList = $('#singleChoiceAnswers');
    var answerContainer = document.createElement('div');
    var answerAmount = answerList.children().length;
    var newListItem = document.createElement('div');
    var label = document.createElement('label');
    var labelBaseVal = $('#hiddenAnswerLabelBaseValue').html();
    var checkbox = document.createElement('input');
    var answerInput = document.createElement('input');
    var answerInputContainer = document.createElement('div');
    var checkboxContainer = document.createElement('div');

    $(newListItem).addClass("row");
    $(newListItem).addClass("answerListItem");
    $(label).addClass("col-xs-2");
    $(label).addClass("col-form-label");
    $(label).addClass("col-form-label-lg");
    $(label).html(labelBaseVal + " " + (answerAmount + 1));
    label.htmlFor = "singleChoiceAnswer-" + answerAmount;
    checkbox.type = "checkbox";
    checkbox.name = "singleChoiceAnswerValues";
    checkbox.value = answerAmount;
    checkbox.checked = buttonState;
    $(checkbox).addClass("singleChoiceCheckBox");
    $(checkbox).click(function (e) {
        clickBoxHandler(e);
    });
    $(checkboxContainer).addClass("col-xs-2");
    checkboxContainer.appendChild(checkbox);
    answerInput.type = "text";
    answerInput.name = "singleChoiceAnswers";
    answerInput.id = "singleChoiceAnswer-" + answerAmount;
    answerInput.value = text;
    $(answerInput).addClass("form-control");
    $(answerInput).addClass("form-control-lg");
    $(answerInputContainer).addClass("col-xs-8");
    $(answerContainer).addClass('row');
    $(answerContainer).addClass('answerListItem');
    answerInputContainer.appendChild(answerInput);
    answerContainer.appendChild(label);
    answerContainer.appendChild(answerInputContainer);
    answerContainer.appendChild(checkboxContainer);
    answerList.append(answerContainer);
}

function buildMultiChoiceAnswer(text, buttonState) {
    var answerList = $('#multipleChoiceAnswers');
    var answerContainer = document.createElement('div');
    var answerAmount = answerList.children().length;
    var newListItem = document.createElement('div');
    var label = document.createElement('label');
    var labelBaseVal = $('#hiddenAnswerLabelBaseValue').html();
    var checkbox = document.createElement('input');
    var answerInput = document.createElement('input');
    var answerInputContainer = document.createElement('div');
    var checkboxContainer = document.createElement('div');

    $(newListItem).addClass("row");
    $(newListItem).addClass("answerListItem");
    $(label).addClass("col-xs-2");
    $(label).addClass("col-form-label");
    $(label).addClass("col-form-label-lg");
    $(label).html(labelBaseVal + " " + (answerAmount + 1));
    label.htmlFor = "multipleChoiceAnswer-" + answerAmount;
    checkbox.type = "checkbox";
    checkbox.name = "multipleChoiceAnswerValues";
    checkbox.value = answerAmount;
    checkbox.checked = buttonState;
    $(checkbox).addClass("multipleChoiceCheckBox");
    $(checkboxContainer).addClass("col-xs-2");
    checkboxContainer.appendChild(checkbox);
    answerInput.type = "text";
    answerInput.name = "multipleChoiceAnswers";
    answerInput.id = "multipleChoiceAnswer-" + answerAmount;
    answerInput.value = text;
    $(answerInput).addClass("form-control");
    $(answerInput).addClass("form-control-lg");
    $(answerInputContainer).addClass("col-xs-8");
    $(answerContainer).addClass('row');
    $(answerContainer).addClass('answerListItem');
    answerInputContainer.appendChild(answerInput);
    answerContainer.appendChild(label);
    answerContainer.appendChild(answerInputContainer);
    answerContainer.appendChild(checkboxContainer);
    answerList.append(answerContainer);
}

function buildGapAnswer(i, text, answerList) {
    var answerContainer = document.createElement('div');
    var newListItem = document.createElement('div');
    var label = document.createElement('label');
    var labelBaseVal = $('#hiddenAnswerLabelBaseValue').html();
    var answerInput = document.createElement('input');
    var answerInputContainer = document.createElement('div');

    $(newListItem).addClass("row");
    $(newListItem).addClass("answerListItem");
    $(label).addClass("col-xs-2");
    $(label).addClass("col-form-label");
    $(label).addClass("col-form-label-lg");
    $(label).html(labelBaseVal + " " + (i + 1));
    label.htmlFor = "gapAnswer-" + i;
    answerInput.type = "text";
    answerInput.name = "gapAnswers";
    answerInput.id = "gapAnswer-" + i;
    answerInput.value = text;
    $(answerInput).addClass("form-control");
    $(answerInput).addClass("form-control-lg");
    $(answerInputContainer).addClass("col-xs-8");
    $(answerContainer).addClass('row');
    $(answerContainer).addClass('answerListItem');
    answerInputContainer.appendChild(answerInput);
    answerContainer.appendChild(label);
    answerContainer.appendChild(answerInputContainer);
    answerList.append(answerContainer);
}

// actions on change of questiontype
$('#select-question-type').change(function (e) {
    var target = e.target;
    selectedVal = $(target).val();
    var singleChoiceAnswers = $('#singleChoiceAnswers');
    var multipleChoiceAnswers = $('#multipleChoiceAnswers');
    var gapAnswers = $('#gapAnswers');
    singleChoiceAnswers.addClass('none');
    multipleChoiceAnswers.addClass('none');
    gapAnswers.addClass('none');
    var addAnswerButton = document.getElementById("addAnswerButton")
    var removeAnswerButton =document.getElementById("removeAnswerButton")
    addAnswerButton.className="manageAnswersButtons fa fa-plus";
    removeAnswerButton.className="manageAnswersButtons fa fa-minus";
    console.log(removeAnswerButton.className);


    switch (selectedVal) {
        case "Single":
            if (singleChoiceAnswers.children().length == 0) {
                buildSingleChoiceAnswer("", false);
            }
            singleChoiceAnswers.removeClass('none');
            document.getElementById("gapAnswersText").className="row none"
            document.getElementById("infoTextSingle").className="row"
            document.getElementById("infoTextGap").className="row none"
            document.getElementById("infoTextMultiple").className="row none"
            break;
        case "Multiple":
            if (multipleChoiceAnswers.children().length == 0) {
                buildMultiChoiceAnswer("", false);
            }
            multipleChoiceAnswers.removeClass('none');
            document.getElementById("gapAnswersText").className="row none"
            document.getElementById("infoTextMultiple").className="row"
            document.getElementById("infoTextSingle").className="row none"
            document.getElementById("infoTextGap").className="row none"
            break;
        case "Gap":
            addAnswerButton.className="manageAnswersButtons none";
            removeAnswerButton.className="manageAnswersButtons none";
            gapAnswers.removeClass('none');
            document.getElementById("gapAnswersText").className="row"
            document.getElementById("infoTextGap").className="row"
            document.getElementById("infoTextMultiple").className="row none"
            document.getElementById("infoTextSingle").className="row none"
            break;
    }
});

$('#addAnswerButton').click(function (e) {
    switch (selectedVal) {
        case "Single":
            buildSingleChoiceAnswer("", false);
            break;
        case "Multiple":
            buildMultiChoiceAnswer("", false);
            break;
    }
});


$('#removeAnswerButton').click(function (e) {
    switch (selectedVal) {
        case "Single":
            if ($('#singleChoiceAnswers').children().length > 1) {
                $('#singleChoiceAnswers').children(':last').remove();
            }
            break;
        case "Multiple":
            if ($('#multipleChoiceAnswers').children().length > 1) {
                $('#multipleChoiceAnswers').children(':last').remove();
            }
            break;
    }
});


$('.singleChoiceCheckBox').click(function (e) {
    clickBoxHandler(e);
});

$('#questionText').focusout(function (e) {

    if (selectedVal != "Gap") {
        return;
    }
    var target = e.target;
    var questionString = $(target).val();
    var amountOfGaps = (questionString.match(/\[\.\.\.]/g) || []).length;

    var answerList = $('#gapAnswers');
    answerList.empty();

    for (let i of Array(amountOfGaps).keys()) {
        buildGapAnswer(i, "", answerList);
    }
});

var clickBoxHandler = function (e) {
    var checkBoxState = e.target.checked;
    $('.singleChoiceCheckBox').each(function (i, ele) {
        ele.checked = false;
    });
    if (checkBoxState) {
        e.target.checked = true;
    }
};

$(document).ready(function () {
    if ($('#select-question-type').val() != -1) {
        $('#select-question-type').change();
    }
});
