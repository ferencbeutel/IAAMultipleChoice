<#assign currentPage = "testAdministration">

<html>
<head>
    <title>Fragen zum Test Hinzufügen</title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/question.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="container">
<#--
<@s.form action="addQuestion" method="post">
-->
    <div class="form-group row">
        <label for="selectquestiontype" class="col-md-2 col-form-label col-form-label-lg">Fragentyp</label>
        <div class="col-md-10">
            <select class="select form-control" id="selectQuestionType" name="selectQuestionType" onchange="changeQuestionType(this)">
                <option value="Single"> Single Choice</option>
                <option value="Multiple"> Multiple Choice</option>
                <option value="Gap"> Lückentext</option>
            </select>
        </div>
    </div>
    <div class="form-group row">
        <label for="points" class="col-md-2 col-form-label col-form-label-lg">Punktzahl</label>
        <div class="col-md-10">
        <@s.textfield class="form-control form-control-lg" id="points" name="points"/>
        </div>
    </div>
    <div class="form-group row">
        <label for="enterQuestion" class="col-md-2 col-form-label col-form-label-lg">Frage</label>
        <div class="col-md-10">
            <textarea name="enterQuestion" id="enterQuestion" class="form-control form-control-lg" onblur="calculateGaps()" placeholder="Ihre Frage"></textarea>
        </div>
    </div>


    <div class="container" id="answerChoice">
        <div class="form-group row" id="answerChoice1">
            <label for="answerChoice1Text" class="col-md-2 col-form-label col-form-label-lg">Antwort 1</label>
            <div class="col-md-10">
            <@s.textfield class="form-control form-control-lg answer" id="answerChoice1Text" name="answerChoice1Text"/>
                <input type="radio" id="answerChoice1Valid" name="choiceValid"/>
            </div>
        </div>
    </div>

    <div class="container none" id="answerGap">
        <div class="form-group row" id="answerGap1">
            <label for="answerGap1Text" class="col-md-2 col-form-label col-form-label-lg">Antwort 1</label>
            <div class="col-md-10">
            <@s.textfield class="form-control form-control-lg answer" id="answerGap1Text" name="answerGap1Text"/>
              <#--  <input type="radio" id="answerGap1Valid" name="gapValid"/>-->
                <select class="gapSelect" id="answerGap1Valid" name="gapValid">
                    <option value="1">1</option>
                </select>
            </div>
        </div>
    </div>

    <div class="form-group row" id="manageAnswers">
        <button class="manageAnswer" onclick="addAnswer()">+</button>
        <button class="manageAnswer" onclick="removeAnswer()">-</button>
    </div>
<@s.submit class="btn btn-primary" value="Submit"/>


<#--
</@s.form>
-->
</div>




<#include "/templates/frameFooter.ftl">


<script>
    var gapIdentificator = "\[\*\]"
    var countAnswers = 1;
    var questionType = "Single"
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

        var appendText = document.getElementById("answer"+answerType+"1Text").cloneNode(true);
        appendText.id = newId +"Text";
        var appendValidator =document.getElementById("answer"+answerType+"1Valid").cloneNode(true);
        appendValidator.id = newId +"Valid";
        appendValidator.checked = false;

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
        if (newType =="Gap"){
            resetForm("Choice","Gap");
            answerType = "Gap";
            questionType = newType;
            calculateGaps();
        }
        else if (newType == "Multiple" && questionType=="Single"){
            for (i = 0; i < countAnswers; i++) {
                changeInputType(document.getElementById("answerChoice" +(i+1).toString()+"Valid"),"checkbox")
            }
        }
        else if (newType == "Single" && questionType=="Multiple"){
            for (i = 0; i < countAnswers; i++) {
                changeInputType(document.getElementById("answerChoice" +(i+1).toString()+"Valid"),"radio")
            }
        }
        else if (newType == "Single"){
            updateGapSelect(1);
            resetForm("Gap","Choice");
            answerType = "Choice";
            changeInputType(document.getElementById("answerChoice1Valid"),"radio");
        }
        else{
            updateGapSelect(1);
            resetForm("Gap","Choice");
            answerType = "Choice";
            changeInputType(document.getElementById("answerChoice1Valid"),"checkbox");
        }
        questionType = newType;
    }

    function calculateGaps(){
        if (questionType == "Gap"){
            var questionText = document.getElementById("enterQuestion").value;
            var count = (questionText.match(new RegExp(gapIdentificator, "g")) || []).length;
            count = Math.max(count,1);
            if (count> countAnswers){
                updateGapSelect(count);
                for (i = countAnswers; i <count; i++) {
                    addAnswer();
                }
                console.log(countAnswers);
            }
            else if (count <  countAnswers){
                updateGapSelect(count);
            }

        }
    }

    function updateGapSelect(maxGaps){
        if (maxGaps> countAnswers){
            for (i = 1; i <= countAnswers; i++) {
                var selectElement = document.getElementById("answerGap" + i.toString() + "Valid");
                for (j=countAnswers+1; j<=maxGaps;j++) {
                    var opt = document.createElement('option');
                    opt.value = j;
                    opt.innerHTML = j;
                    selectElement.appendChild(opt);
                }
            }
        }else{
            for (i = 1; i <= countAnswers; i++) {
                var selectElement = document.getElementById("answerGap" + i.toString() + "Valid");
                for (j=countAnswers; j>=maxGaps;j--) {
                    selectElement.remove(j);
                }
            }
        }
    }
    function resetForm(oldType, newType){
        document.getElementById("answer"+oldType+"1Text").value ="";
        document.getElementById("answer"+oldType+"1Valid").checked =false;
        for (i = 1; i < countAnswers; i++) {
            removeAnswer();
        }
        document.getElementById("answer"+oldType).className ="container none";
        document.getElementById("answer"+newType).className ="container";
    }


    function changeInputType(oldObject, oType) {
        var newObject = document.createElement('input');
        newObject.type = oType;
        if(oldObject.size) newObject.size = oldObject.size;
        if(oldObject.value) newObject.value = oldObject.value;
        if(oldObject.name) newObject.name = oldObject.name;
        if(oldObject.id) newObject.id = oldObject.id;
        if(oldObject.className) newObject.className = oldObject.className;
        oldObject.parentNode.replaceChild(newObject,oldObject);
        return newObject;
    }
</script>
</body>
</html>