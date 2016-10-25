<#assign currentPage = "seminarList">

<html>
<head>
    <title>Seminar List</title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/seminarList.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">
<div class="seminarList">
    <div class="row seminarListHeader">
        <div class="col-xs-2 seminarListItemEntry">Seminar Name</div>
        <div class="col-xs-4 seminarListItemEntry">Begindate - Enddate</div>
        <div class="col-xs-3 seminarListItemEntry">Lecturer</div>
        <div class="col-xs-3 seminarListItemEntry">Enroll for Seminar</div>
    </div>
<#list seminarList as seminar>
    <div class="row seminarListItem" data-id="${seminar.seminarId}">
        <span class="overlay"></span>
        <div class="col-xs-2 seminarListItemEntry">
            <span>${seminar.name}</span>
        </div>
        <div class="col-xs-4 seminarListItemEntry">
            <span>${seminar.beginDate} - ${seminar.endDate}</span>
        </div>
        <div class="col-xs-3 seminarListItemEntry">
            <span>${seminar.lecturer.user.name} ${seminar.lecturer.user.surName}</span>
        </div>
        <div class="col-xs-3 seminarListItemEntry">
            <button class="btn btn-secondary enrollButton">enroll</button>
        </div>
    </div>
</#list>

</div>

<#include "/templates/frameFooter.ftl">

<script type="text/javascript" src="/static/js/seminarList.js"></script>
</body>
</html>