var selectedVal;

$('#select-question-type').change(function (e) {
    var target = e.target;
    selectedVal = $(target).val();

    var singleChoiceAnswers = $('#singleChoiceAnswers');
    var multipleChoiceAnswers = $('#multipleChoiceAnswers');
    var gapAnswers = $('#gapAnswers');
    singleChoiceAnswers.addClass('none');
    multipleChoiceAnswers.addClass('none');
    gapAnswers.addClass('none');
    var addAnswerButton = $('#addAnswerButton');
    var removeAnswerButton = $('#removeAnswerButton');
    addAnswerButton.removeClass('none');
    removeAnswerButton.removeClass('none');

    switch (selectedVal) {
        case "Single":
            singleChoiceAnswers.removeClass('none');
            break;
        case "Multiple":
            multipleChoiceAnswers.removeClass('none');
            break;
        case "Gap":
            addAnswerButton.addClass('none');
            removeAnswerButton.addClass('none');
            gapAnswers.removeClass('none');
            break;
    }
});

$('#addAnswerButton').click(function (e) {
    switch (selectedVal) {
        case "Single":
            var answerList = $('#singleChoiceAnswers');
            var answerContainer = document.createElement('div');
            var answerAmount = answerList.children().length;
            var newListItem = document.createElement('div');
            var label = document.createElement('label');
            var labelBaseVal = $('#singleChoiceAnswerBaseItem').children('label').html().split(" ")[0];
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
            $(checkbox).addClass("singleChoiceCheckBox");
            $(checkbox).click(function (e) {
                clickBoxHandler(e);
            });
            $(checkboxContainer).addClass("col-xs-2");
            checkboxContainer.appendChild(checkbox);
            answerInput.type = "text";
            answerInput.name = "singleChoiceAnswers";
            answerInput.id = "singleChoiceAnswer-" + answerAmount;
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
            break;
        case "Multiple":
            var answerList = $('#multipleChoiceAnswers');
            var answerContainer = document.createElement('div');
            var answerAmount = answerList.children().length;
            var newListItem = document.createElement('div');
            var label = document.createElement('label');
            var labelBaseVal = $('#multipleChoiceAnswerBaseItem').children('label').html().split(" ")[0];
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
            $(checkbox).addClass("multipleChoiceCheckBox");
            $(checkboxContainer).addClass("col-xs-2");
            checkboxContainer.appendChild(checkbox);
            answerInput.type = "text";
            answerInput.name = "multipleChoiceAnswers";
            answerInput.id = "multipleChoiceAnswer-" + answerAmount;
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
            break;
    }
});

$('.singleChoiceCheckBox').click(function (e) {
    clickBoxHandler(e);
});

$('#questionText').focusout(function(e) {

    if(selectedVal != "Gap") {
        return;
    }
    var target = e.target;
    var questionString = $(target).val();
    var amountOfGaps = (questionString.match(/\[\*]/g) || []).length;

    var answerList = $('#gapAnswers');
    answerList.empty();
    
    for(let i of Array(amountOfGaps).keys()) {
        var answerContainer = document.createElement('div');
        var newListItem = document.createElement('div');
        var label = document.createElement('label');
        var labelBaseVal = $('#singleChoiceAnswerBaseItem').children('label').html().split(" ")[0];
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