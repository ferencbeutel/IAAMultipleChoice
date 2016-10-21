<#assign currentPage = "testAdministration">

<html>
<head>
    <title>Test erstellen</title>
<#include "/templates/frameHeadImports.ftl">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="container">
<#--<@s.form action="addTest" method="post"> -->

    <div class="form-group row">
        <label for="selectseminar" class="col-md-2 col-form-label col-form-label-lg">Seminar</label>
        <div class="col-md-10">
            <select class="select form-control" id="selectseminar" name="selectseminar">
                <option value="Seminar1"> Seminar 12 </option>
                <option value="Seminar2"> Seminar 2 </option>
                <option value="Seminar3"> Seminar 3 </option>
                <option value="Seminar4"> Seminar 4 </option>
            </select>
        </div>
    </div>

    <div class="form-group row">
        <label for="selectcredits" class="col-md-2 col-form-label col-form-label-lg">Credits</label>
        <div class="col-md-10">
            <select class="select form-control" id="selectcredits" name="selectcredits">
                <option value="Credit050"> 0,55 CP </option>
                <option value="Credit075"> 0,75 CP</option>
                <option value="Credit100"> 1 CP </option>
            </select>
        </div>
    </div>

    <div class="form-group row">
        <label for="startDate" class="col-md-2 col-form-label col-form-label-lg">Startdatum</label>
        <div class="col-md-10">
            <div class="input-group">
                <div class="input-group-addon">
                    <i class="fa fa-calendar" aria-hidden="true"></i>
                </div>
                <input class="form-control form-control-lg" id="startDate" name="startDate" placeholder="MM/DD/YYYY" type="text"/>
            </div>
        </div>
    </div>


    <div class="form-group row">
        <label for="endDate" class="col-md-2 col-form-label col-form-label-lg">Enddatum</label>
        <div class="col-md-10">
            <div class="input-group">
                <div class="input-group-addon">
                    <i class="fa fa-calendar" aria-hidden="true"></i>
                </div>
                <input class="form-control form-control-lg" id="endDate" name="endDate" placeholder="MM/DD/YYYY" type="text"/>
            </div>
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

  <#--  <@s.submit class="btn btn-primary" value="Add"/>
</@s.form> -->
</div>

<script>
    $(document).ready(function(){
        var date_input=$('input[name="startDate"]'); //our date input has the name "date"
        var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
        date_input.datepicker({
            format: 'mm/dd/yyyy',
            container: container,
            todayHighlight: true,
            autoclose: true,
        })
    })
</script>

<script>
    $(document).ready(function(){
        var date_input=$('input[name="endDate"]'); //our date input has the name "date"
        var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
        date_input.datepicker({
            format: 'mm/dd/yyyy',
            container: container,
            todayHighlight: true,
            autoclose: true,
        })
    })
</script>

<#include "/templates/frameFooter.ftl">
</body>
</html>