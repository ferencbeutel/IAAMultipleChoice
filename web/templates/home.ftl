<#assign currentPage = "home">

<html>
<head>
    <title>Registration</title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/home.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="row">
    <div class="col-xs-12">
    <#if Session?? && Session.userMail??>
        <p><@s.text name="home.loggedIn.welcome"/>, ${name}</p>

        <#if Session?? && Session.userType?? && Session.userType == "Student">
            <p><@s.text name="home.loggedIn.infoStudent"/></p>
            <p><@s.text name="home.loggedIn.testInfoStudent"/></p>
        </#if>
        <#if Session?? && Session.userType?? && Session.userType == "Lecturer">
            <p><@s.text name="home.loggedIn.infoLecturer"/></p>
        </#if>
    <#else>
        <p><@s.text name="home.notLoggedIn.info"/></p>
    </#if>

    </div>
</div>
<#if Session?? && Session.userMail??>
<div class="seminarList">

    <div class="row seminarListItem seminarListHeader">
        <div class="col-xs-2 seminarListItemEntry">Seminar Name</div>
        <div class="col-xs-4 seminarListItemEntry">Begindate - Enddate</div>

        <#if Session?? && Session.userMail?? && Session.userType?? && Session.userType == "Student">
            <div class="col-xs-4 seminarListItemEntry">start the test</div>

        <#else>
            <div class="col-xs-4 seminarListItemEntry">Test verwalten</div>
        </#if>

    </div>
    <#list seminarList as seminar>
    <div class="row seminarListItem" data-name="${seminar.name}" data-id="${seminar.seminarId}">
        <div class="col-xs-2 seminarListItemEntry">
            <span>${seminar.name}</span>
        </div>
        <div class="col-xs-4 seminarListItemEntry">
            <span>${seminar.beginDate} - ${seminar.endDate}</span>
        </div>

    <div class="col-xs-3 seminarListItemEntry">
        <#if Session?? && Session.userMail?? && Session.userType?? && Session.userType == "Student">
                <!--ToDo: button nur anzeigen, wenn Test da -->
                <button class="btn btn-secondary startTestButton"><@s.text name="home.StartTestButton"/></button>

            <#elseif Session?? && Session.user?? && Session.user.getClass()?contains("Lecturer")&& seminar.test??>
                <button class="btn btn-secondary editTestButton"><@s.text name="home.EditTestButton"/></button>
            <#else>
                <button class="btn btn-secondary addTestButton"><@s.text name="home.AddTestButton"/></button>

            </#if>
        </div>
        </div>
    </#list>
</div>


</#if>
<#include "/templates/frameFooter.ftl">
<script type="text/javascript" src="/static/js/addTest.js"></script>
</body>
</html>