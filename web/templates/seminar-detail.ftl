<#assign currentPage = "seminarList">

<html>
<head>
    <title>Seminar details</title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/seminarDetailList.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="seminarList">
    <div class="row seminarListItem">
        <div class="col-xs-4 seminarListItemEntry">
            <span>${seminar.name}</span>
        </div>
        <div class="col-xs-4 seminarListItemEntry">
            <span>${seminar.beginDate} - ${seminar.endDate}</span>
        </div>
        <div class="col-xs-4 seminarListItemEntry">
            <span>${seminar.lecturer.user.name} ${seminar.lecturer.user.surName}</span>
        </div>
    </div>
    <div class="row seminarListItem">
        <div class="col-xs-8 centered seminarListItemEntry">
            <span>${seminar.description}</span>
        </div>
    </div>
</div>

<#include "/templates/frameFooter.ftl">
</body>
</html>