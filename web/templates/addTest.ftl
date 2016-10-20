<#assign currentPage = "testAdministration">

<html>
<head>
    <title>Test erstellen</title>
<#include "/templates/frameHeadImports.ftl">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="container">
<@s.form action="register" method="post">
    <div class="form-group row">
        <label for="startDate" class="col-md-2 col-form-label col-form-label-lg">Startdatum</label>
        <div class="col-md-10">
            <@s.textfield class="form-control form-control-lg" id="startDate" name="startDate"/>
        </div>
    </div>
    <div class="form-group row">
        <label for="endDate" class="col-md-2 col-form-label col-form-label-lg">Enddatum</label>
        <div class="col-md-10">
            <@s.textfield class="form-control form-control-lg" id="endDate" name="endDate"/>
        </div>
    </div>
    <div class="form-group row">
        <label for="duration" class="col-md-2 col-form-label col-form-label-lg">Dauer</label>
        <div class="col-md-10">
            <@s.textfield class="form-control form-control-lg" id="duration" name="duration"/>
        </div>
    </div>
    <div class="form-group row">
        <label for="passThreshold" class="col-md-2 col-form-label col-form-label-lg">Bestehensgrenzen</label>
        <div class="col-md-10">
            <@s.textfield class="form-control form-control-lg" id="passThreshold" name="passThreshold"/>
        </div>
    </div>



    <@s.submit class="btn btn-primary" value="Add"/>
</@s.form>
</div>

<#include "/templates/frameFooter.ftl">
</body>
</html>