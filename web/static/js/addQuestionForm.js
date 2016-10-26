var gapIdentificator = "\[\*\]";
var countAnswers = 1;
var questionType = "Single";
var answerType = "Choice";
function addAnswer() {
    countAnswers += 1;
    var answerContainer = "answer" + answerType;
    var answerElement = document.createElement("div");
    var newId = answerContainer + countAnswers.toString();
    answerElement.className = "form-group row";
    answerElement.id = newId;

    var answerLabel = document.createElement("label");
    answerLabel.className = "col-md-2 col-form-label col-form-label-lg";
    answerLabel.innerHTML = "Antwort " + countAnswers.toString();
    answerElement.appendChild(answerLabel);

    var innerDiv = document.createElement("div");
    innerDiv.className = "col-md-10";

    var appendText = document.getElementById("answer" + answerType + "1Text").cloneNode(true);
    appendText.id = newId + "Text";
    var appendValidator = document.getElementById("answer" + answerType + "1Valid").cloneNode(true);
    appendValidator.id = newId + "Valid";
    appendValidator.value = true;
    appendValidator.value = false;
    //appendValidator.fieldValue = true;

    innerDiv.appendChild(appendText);
    innerDiv.appendChild(appendValidator);
    answerElement.appendChild(innerDiv);
    document.getElementById(answerContainer).appendChild(answerElement);
}

function removeAnswer() {
    if (countAnswers > 1) {
        var removeId = "answer" + answerType + countAnswers.toString();
        var element = document.getElementById(removeId);
        element.parentNode.removeChild(element);
        countAnswers -= 1;
    }

}

function changeQuestionType(sel) {
    var newType = sel.value;
    if (newType == "Gap") {
        resetForm("Choice", "Gap");
        answerType = "Gap";
        questionType = newType;
        calculateGaps();
    }
    else if (newType == "Multiple" && questionType == "Single") {
        for (i = 0; i < countAnswers; i++) {
            changeInputType(document.getElementById("answerChoice" + (i + 1).toString() + "Valid"), "checkbox")
        }
    }
    else if (newType == "Single" && questionType == "Multiple") {
        for (i = 0; i < countAnswers; i++) {
            changeInputType(document.getElementById("answerChoice" + (i + 1).toString() + "Valid"), "radio")
        }
    }
    else if (newType == "Single") {
        //updateGapSelect(1);
        resetForm("Gap", "Choice");
        answerType = "Choice";
        changeInputType(document.getElementById("answerChoice1Valid"), "radio");
    }
    else {
        //updateGapSelect(1);
        resetForm("Gap", "Choice");
        answerType = "Choice";
        changeInputType(document.getElementById("answerChoice1Valid"), "checkbox");
    }
    questionType = newType;
}

function calculateGaps() {
    if (questionType == "Gap") {
        var questionText = document.getElementById("enterQuestion").value;
        var count = (questionText.match(new RegExp(gapIdentificator, "g")) || []).length;
        count = Math.max(count, 1);
        if (count > countAnswers) {
            //updateGapSelect(count);
            for (i = countAnswers; i < count; i++) {
                addAnswer();
            }
            console.log(countAnswers);
        }
        else if (count < countAnswers) {
            for (i = count; i < countAnswers; i++) {
                removeAnswer();
            }
            //updateGapSelect(count);
        }

    }
}

function updateGapSelect(maxGaps) {
    if (maxGaps > countAnswers) {
        for (i = 1; i <= countAnswers; i++) {
            var selectElement = document.getElementById("answerGap" + i.toString() + "Valid");
            for (j = countAnswers + 1; j <= maxGaps; j++) {
                var opt = document.createElement('option');
                opt.value = j;
                opt.innerHTML = j;
                selectElement.appendChild(opt);
            }
        }
    } else {
        for (i = 1; i <= countAnswers; i++) {
            var selectElement = document.getElementById("answerGap" + i.toString() + "Valid");
            for (j = countAnswers; j >= maxGaps; j--) {
                selectElement.remove(j);
            }
        }
    }
}
function resetForm(oldType, newType) {
    document.getElementById("answer" + oldType + "1Text").value = "";
    document.getElementById("answer" + oldType + "1Valid").checked = false;
    for (i = 1; i < countAnswers; i++) {
        removeAnswer();
    }
    document.getElementById("answer" + oldType).className = "container none";
    document.getElementById("answer" + newType).className = "container";
}


function changeInputType(oldObject, oType) {
    var newObject = document.createElement('input');
    newObject.type = oType;
    if (oldObject.size) newObject.size = oldObject.size;
    if (oldObject.value) newObject.value = oldObject.value;
    if (oldObject.name) newObject.name = oldObject.name;
    if (oldObject.id) newObject.id = oldObject.id;
    if (oldObject.className) newObject.className = oldObject.className;
    oldObject.parentNode.replaceChild(newObject, oldObject);
    return newObject;
}
