<#assign currentPage = "seminarList">

<html>
<head>
    <title><@s.text name="seminarList.title"/></title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/seminarList.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">
<div class="seminarList" id="seminarList">
    <div class="row seminarListHeader">
        <div class="col-xs-4 seminarListItemEntry"><@s.text name="seminarList.name"/></div>
        <div class="col-xs-4 seminarListItemEntry"><@s.text name="seminarList.period"/></div>
        <div class="col-xs-3 seminarListItemEntry"><@s.text name="seminarList.lecturer"/></div>
        <div class="col-xs-1 seminarListItemEntry"><@s.text name="seminarList.participants"/></div>
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
        <div class="col-xs-3 seminarListItemEntry">
            <span>${seminar.lecturer.name} ${seminar.lecturer.surName}</span>
        </div>
        <div class="col-xs-1 seminarListItemEntry">
            <span>${seminar.participants?size}/${seminar.maxParticipants}</span>
        </div>
    </div>
</#list>

</div>
<#include "/templates/frameFooter.ftl">
<script type="text/javascript" src="/static/js/seminar-list.js"></script>
</body>
</html>