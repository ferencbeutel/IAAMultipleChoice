<#assign currentPage = "home">

<html>
<head>
    <title><@s.text name="home.title"/></title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/home.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="row">
    <div class="col-xs-12">
    <#if Session?? && Session.userMail??>
        <#if Session?? && Session.userType?? && Session.userType == studentUser>
            <p><@s.text name="home.loggedIn.infoStudent"/></p>
            <p><@s.text name="home.loggedIn.testInfoStudent"/></p>
        </#if>
        <#if Session?? && Session.userType?? && Session.userType == lecturerUser>
            <p><@s.text name="home.loggedIn.infoLecturer"/></p>
        </#if>
    <#else>
        <p><@s.text name="home.notLoggedIn.info"/></p>
    </#if>
    </div>
</div>

<#if Session?? && Session.userMail?? && Session.userType??>
<div id="seminarList" class="seminarList">
    <#if seminarList?has_content>
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
            <#if Session.userType == lecturerUser>
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
            <#elseif Session.userType == studentUser>
                <div class="row seminarListItem studentItem" data-id="${seminar.seminarId}">
                    <#if seminar.test?? && student??>
                        <#list student.results as result>
                            <#if seminar.test.results?seq_contains(result) && result.points??>
                                <#if seminar.test.minScore <= result.points>
                                    <@s.hidden class="hiddenResultToken" value="${true?c}"/>
                                <#else>
                                    <@s.hidden class="hiddenResultToken" value="${false?c}"/>
                                </#if>
                            </#if>
                        </#list>
                    </#if>
                    <span class="overlay"></span>
                    <div class="col-xs-4 seminarListItemEntry">
                        <span>${seminar.name}</span>
                    </div>
                    <div class="col-xs-4 seminarListItemEntry">
                        <span>${seminar.beginDate} - ${seminar.endDate}</span>
                    </div>
                    <div class="col-xs-4 seminarListItemEntry">
                        <!-- TODO: man soll klicken können wenn endDate <= now ist -->
                        <#if seminar.test?? && (now.isEqual(seminar.test.beginDate) || now.isAfter(seminar.test.beginDate)) && (seminar.test.endDate.isEqual(now) || seminar.test.endDate.isAfter(now))>
                            <button class="btn btn-secondary startTestButton"
                                    data-id="${seminar.seminarId}"><@s.text name="home.StartTestButton"/></button>
                        <#else>
                            <button class="btn btn-secondary startTestButton disabled" data-toggle="tooltip"
                                    date-placement="top"
                                    data-title="No test available yet"><@s.text name="home.StartTestButton"/></button>
                        </#if>
                    </div>
                </div>
            </#if>
        </#list>
    <#else>
        <#if Session.userType == studentUser>
            <p><@s.text name="home.loggedIn.emptyListStudent"/></p>
        <#elseif  Session.userType == lecturerUser>
            <p><@s.text name="home.loggedIn.emptyListLecturer"/></p>
        </#if>
    </#if>
</div>


</#if>
<#include "/templates/frameFooter.ftl">
<script type="text/javascript" src="/static/js/add-test.js"></script>
<script type="text/javascript" src="/static/js/home.js"></script>
</body>
</html>