<#assign currentPage = "seminarAdministration">

<html>
<head>
    <title>Seminar anlegen</title>
<#include "/templates/frameHeadImports.ftl">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<@s.form action="addSeminar" method="post">
<div class="form-group row">
    <label for="seminarName" class="col-md-4 col-form-label col-form-label-lg">Name des Seminars</label>
    <div class="col-md-8">
        <@s.textfield class="form-control form-control-lg" id="seminarName" name="seminar.name"/>
    </div>
</div>
<div class="form-group row">
    <label for="seminarDesc" class="col-md-4 col-form-label col-form-label-lg">Kurzbeschreibung</label>
    <div class="col-md-8">
        <@s.textfield class="form-control form-control-lg" id="seminarDesc" name="seminar.description"/>
    </div>
</div>
<div class="form-group row">
    <label for="startDate"
           class="col-md-5 col-form-label col-form-label-lg text-no-center">Anfangsdatum</label>
    <div class="col-md-7">
        <div class="input-group">
            <div class="input-group-addon">
                <i class="fa fa-calendar" aria-hidden="true"></i>
            </div>
            <input class="form-control form-control-lg" id="startDate" name="beginDate" placeholder="MM/DD/YYYY"
                   type="text"/>
        </div>
    </div>
</div>
<div class="form-group row">
    <label for="startDate"
           class="col-md-5 col-form-label col-form-label-lg text-no-center">Enddatum</label>
    <div class="col-md-7">
        <div class="input-group">
            <div class="input-group-addon">
                <i class="fa fa-calendar" aria-hidden="true"></i>
            </div>
            <input class="form-control form-control-lg" id="endDate" name="endDate" placeholder="MM/DD/YYYY"
                   type="text"/>
        </div>
    </div>
</div>
<div class="form-group row">
    <label for="seminarMaxPart" class="col-md-4 col-form-label col-form-label-lg">Teilnehmerobergrenze</label>
    <div class="col-md-8">
        <@s.textfield type="number" class="form-control form-control-lg" id="seminarMaxPart" name="seminar.maxParticipants"/>
    </div>
</div>

    <@s.submit class="btn btn-primary" value="Submit"/>
</@s.form>

<#include "/templates/frameFooter.ftl">
<script>
    $(document).ready(function () {
        var language = window.navigator.language;
        var start_date_input = $('#startDate');
        var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "body";
        start_date_input.datepicker({
            language: language,
            format: 'mm/dd/yyyy',
            container: container,
            todayHighlight: true,
            autoclose: true
        });
        var end_date_input = $('#endDate');
        end_date_input.datepicker({
            language: language,
            format: 'mm/dd/yyyy',
            container: container,
            todayHighlight: true,
            autoclose: true
        });
    })
</script>
</body>
</html>