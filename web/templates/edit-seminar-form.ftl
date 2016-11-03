<#assign currentPage = "seminar-form">

<html>
<head>
    <title><@s.text name="edit-seminar-form.title"/></title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/edit-seminar-form.css">
    <link rel="stylesheet" href="/static/css/validationError.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="container">
<@s.form action="edit-seminar" method="post">
    <@s.hidden name="seminarId" value="${seminar.seminarId}"/>
    <div class="form-group row">
        <label for="seminarName"
               class="col-md-4 col-form-label col-form-label-lg text-no-center"><@s.text name="seminar-form.seminarName"/></label>
        <div class="col-md-8">
            <@s.textfield class="form-control form-control-lg" id="seminarName" name="seminar.name" value="${seminar.name}"/>
        </div>
    </div>
    <div class="form-group row">
        <label for="startDate"
               class="col-md-4 col-form-label col-form-label-lg text-no-center"><@s.text name="seminar-form.startDate"/></label>
        <div class="col-md-8">
            <div class="input-group">
                <div class="input-group-addon">
                    <i class="fa fa-calendar" aria-hidden="true"></i>
                </div>
                <input class="form-control form-control-lg" id="startDate" name="beginDateString"
                       placeholder="MM/DD/YYYY"
                       type="text" value="${seminar.beginDate}"/>
            </div>
        </div>
    </div>
    <div class="form-group row">
        <label for="startDate"
               class="col-md-4 col-form-label col-form-label-lg text-no-center"><@s.text name="seminar-form.endDate"/></label>
        <div class="col-md-8">
            <div class="input-group">
                <div class="input-group-addon">
                    <i class="fa fa-calendar" aria-hidden="true"></i>
                </div>
                <input class="form-control form-control-lg" id="endDate" name="endDateString" placeholder="MM/DD/YYYY"
                       type="text" value="${seminar.endDate}"/>
            </div>
        </div>
    </div>
    <div class="form-group row">
        <label for="seminarMaxPart"
               class="col-md-4 col-form-label col-form-label-lg text-no-center"><@s.text name="seminar-form.maxParticipants"/></label>
        <div class="col-md-8">
            <@s.textfield type="number" class="form-control form-control-lg" id="seminarMaxPart" name="seminar.maxParticipants" value="${seminar.maxParticipants}"/>
        </div>
    </div>
    <div class="form-group row">
        <label for="seminarDesc"
               class="col-md-4 col-form-label col-form-label-lg text-no-center"><@s.text name="seminar-form.shortDescription"/></label>
        <div class="col-md-8">
            <@s.textarea class="form-control form-control-lg" id="seminarDesc" name="seminar.description" value="${seminar.description}" rows="5"/>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-md-6 centered" id="validation-errors">
            <@s.fielderror/>
        </div>
    </div>
    <button type="submit" class="btn btn-primary"><@s.text name="button.save"/></button>
</@s.form>
</div>

<#include "/templates/frameFooter.ftl">
<script type="text/javascript" src="/static/js/seminar-form-time-picker.js"></script>
<script type="text/javascript" src="/static/js/edit-seminar.js"></script>
</body>
</html>