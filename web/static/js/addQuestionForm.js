var selectedVal;

$('#select-question-type').change(function (e) {
    var target = e.target;
    selectedVal = $(target).val();

    switch (selectedVal) {
        case "Single":
            console.log("single");
        case "Multiple":
            console.log("multiple");
        case "Cloze":
            console.log("cloze");
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
            $(checkbox).click(function(e) {
                clickBoxHandler(e)
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
            console.log("single");
        case "Multiple":
            console.log("multiple");
        case "Cloze":
            console.log("cloze");
    }
});

$('.singleChoiceCheckBox').click(function(e) {
    clickBoxHandler(e)
});

var clickBoxHandler = function(e) {
    var checkBoxState = e.target.checked;
    $('.singleChoiceCheckBox').each(function (i, ele) {
        ele.checked = false;
    });
    if (checkBoxState) {
        e.target.checked = true;
    }
};