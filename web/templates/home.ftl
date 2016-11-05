<#assign currentPage = "home">

<html>
<head>
    <title><@s.text name="home.title"/></title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/home.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
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
            <p><@s.text name="home.loggedIn.testInfoLecturer"/></p>
        </#if>
        <hr/>
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
                            <button class="btn btn-secondary edit-test-button fa fa-pencil"/>
                        <#else>
                            <button class="btn btn-secondary add-test-button fa fa-plus"/>
                        </#if>
                    </div>
                </div>
            <#elseif Session.userType == studentUser>
                <#assign testResultExist = false/>
                <div class="row seminarListItem studentItem" data-id="${seminar.seminarId}">
                    <#if seminar.test?? && student??>
                        <#list student.results as result>
                            <#if seminar.test.results?seq_contains(result) && result.points??>
                                <#assign testResultExist = true/>
                                <#if (seminar.test.passingThreshold * seminar.test.maxScore) / 100 <= result.points>
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
                        <#if !testResultExist>
                            <#if seminar.test?? && (now.isEqual(seminar.test.beginDate) || now.isAfter(seminar.test.beginDate)) && (seminar.test.endDate.isEqual(now) || seminar.test.endDate.isAfter(now))>
                                <button class="btn btn-secondary startTestButton fa fa-play"
                                        data-id="${seminar.seminarId}"/>
                            <#else>
                                <#assign tooltip><@s.text name="home.noTest"/></#assign>
                                <button class="btn btn-secondary startTestButton disabled fa fa-play" data-toggle="tooltip"
                                        data-placement="top"
                                        data-title="${tooltip}"/>
                            </#if>
                        <#else>
                            <#assign tooltip><@s.text name="home.testAbsolved"/></#assign>
                            <button class="btn btn-secondary startTestButton disabled fa fa-play" data-toggle="tooltip"
                                    data-placement="top"
                                    data-title="${tooltip}"/>
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