<#assign currentPage = "application-administration">

<html>
<head>
    <title><@s.text name="applicationAdministration.title"/> </title>
<#include "/templates/frameHeadImports.ftl">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="container">
<@s.form action="initializeApplicationData" method="post">
    <div class="form-group row">
        <label for="name" class="col-md-4 col-form-label col-form-label-lg"><@s.text name="applicationAdministration.quantLecturer"/></label>
        <div class="col-md-8">
            <@s.textfield class="form-control form-control-lg" id="quantLecturer" name="quantLecturer"/>
        </div>
    </div>
    <div class="form-group row">
        <label for="name" class="col-md-4 col-form-label col-form-label-lg"><@s.text name="applicationAdministration.quantStudent"/></label>
        <div class="col-md-8">
            <@s.textfield class="form-control form-control-lg" id="quantStudents" name="quantStudents"/>
        </div>
    </div>
    <div class="form-group row">
        <label for="name" class="col-md-4 col-form-label col-form-label-lg"><@s.text name="applicationAdministration.quantSeminar"/></label>
        <div class="col-md-8">
            <@s.textfield class="form-control form-control-lg" id="quantSeminars" name="quantSeminars"/>
        </div>
    </div>

    <@s.submit class="btn btn-primary" value="Initialize"/>
</@s.form>
    <span> <@s.text name="applicationAdministration.infoText"/></span>
</div>


<#include "/templates/frameFooter.ftl">
</body>
</html>