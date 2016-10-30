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
<#if Session?? && Session.userMail?? && Session.userType??>
<div id="seminarList" class="seminarList">

    <div class="row seminarListItem seminarListHeader">
        <div class="col-xs-4 seminarListItemEntry"><@s.text name="home.tableHeaderName"/></div>
        <div class="col-xs-4 seminarListItemEntry"><@s.text name="home.tableHeaderDate"/></div>

        <#if Session?? && Session.userMail?? && Session.userType?? && Session.userType == "Student">
            <div class="col-xs-4 seminarListItemEntry"><@s.text name="home.startTest"/></div>

        <#else>
            <div class="col-xs-4 seminarListItemEntry"><@s.text name="home.AdministrateTest"/></div>
        </#if>

    </div>
    <#list seminarList as seminar>
        <#if Session.userType == "Lecturer">
            <div class="row seminarListItem lecturerItem" data-id="${seminar.seminarId}">
                <span class="overlay"></span>
                <div class="col-xs-4 seminarListItemEntry">
                    <span>${seminar.name}</span>
                </div>
                <div class="col-xs-4 seminarListItemEntry">
                    <span>${seminar.beginDate} - ${seminar.endDate}</span>
                </div>
                <div class="col-xs-4 seminarListItemEntry">
                    <#if seminar.test??>
                        <button class="btn btn-secondary edit-test-button"><@s.text name="home.EditTestButton"/></button>
                    <#else>
                        <button class="btn btn-secondary add-test-button"><@s.text name="home.add-testButton"/></button>
                    </#if>
                </div>
            </div>
        <#elseif Session.userType == "Student">
            <div class="row seminarListItem studentItem" data-id="${seminar.seminarId}">
                <span class="overlay"></span>
                <div class="col-xs-4 seminarListItemEntry">
                    <span>${seminar.name}</span>
                </div>
                <div class="col-xs-4 seminarListItemEntry">
                    <span>${seminar.beginDate} - ${seminar.endDate}</span>
                </div>
                <div class="col-xs-4 seminarListItemEntry">
                    <!-- TODO: man soll klicken können wenn endDate <= now ist -->
                    <#if seminar.test?? && seminar.test.beginDate == now>
                        <button class="btn btn-secondary startTestButton" data-id="${seminar.seminarId}"><@s.text name="home.StartTestButton"/></button>
                    <#else>
                        <button class="btn btn-secondary startTestButton disabled" data-toggle="tooltip" date-placement="top" data-title="No test available yet"><@s.text name="home.StartTestButton"/></button>
                    </#if>
                </div>
            </div>
        </#if>
    </#list>
</div>


</#if>
<#include "/templates/frameFooter.ftl">
<script type="text/javascript" src="/static/js/add-test.js"></script>
<script type="text/javascript" src="/static/js/home.js"></script>
</body>
</html>