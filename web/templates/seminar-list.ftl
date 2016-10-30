<#assign currentPage = "seminarList">

<html>
<head>
    <title>Seminar List</title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/seminarList.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">
<div class="seminarList" id="seminarList">
    <div class="row seminarListHeader">
        <div class="col-xs-4 seminarListItemEntry">Seminar Name</div>
        <div class="col-xs-4 seminarListItemEntry">Begindate - Enddate</div>
        <div class="col-xs-4 seminarListItemEntry">Lecturer</div>
    </div>
<#list seminarList as seminar>
    <div class="row seminarListItem" data-id="${seminar.seminarId}">
        <span class="overlay"></span>
        <div class="col-xs-4 seminarListItemEntry">
            <span>${seminar.name}</span>
        </div>
        <div class="col-xs-4 seminarListItemEntry">
            <span>${seminar.beginDate} - ${seminar.endDate}</span>
        </div>
        <div class="col-xs-4 seminarListItemEntry">
            <span>${seminar.lecturer.name} ${seminar.lecturer.surName}</span>
        </div>
    </div>
</#list>

</div>
<script type="text/javascript" src="/static/js/seminar-list.js"></script>
<#include "/templates/frameFooter.ftl">
</body>
</html>