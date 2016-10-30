<#assign currentPage = "registration">

<html>
<head>
    <title><@s.text name="registrationForm.title"/> </title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/validationError.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="container">
<@s.form action="register" method="post">
    <div class="form-group row">
        <label for="name" class="col-md-2 col-form-label col-form-label-lg"><@s.text name="registrationForm.firstName"/></label>
        <div class="col-md-10">
            <@s.textfield class="form-control form-control-lg" id="name" name="student.name"/>
        </div>
    </div>
    <div class="form-group row">
        <label for="surName" class="col-md-2 col-form-label col-form-label-lg"><@s.text name="registrationForm.lastName"/></label>
        <div class="col-md-10">
            <@s.textfield class="form-control form-control-lg" id="surName" name="student.surName"/>
        </div>
    </div>
    <div class="form-group row">
        <label for="email" class="col-md-2 col-form-label col-form-label-lg"><@s.text name="registrationForm.eMail"/></label>
        <div class="col-md-10">
            <@s.textfield class="form-control form-control-lg" id="email" name="student.email"/>
        </div>
    </div>
    <div class="form-group row">
        <label for="password" class="col-md-2 col-form-label col-form-label-lg"><@s.text name="registrationForm.password"/></label>
        <div class="col-md-10">
            <@s.password class="form-control form-control-lg" id="password" name="student.password"/>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-md-6 centered" id="validation-errors">
            <@s.fielderror/>
        </div>
    </div>

    <@s.submit class="btn btn-primary" value="Register"/>
</@s.form>
</div>
<p><@s.text name="global.alreadyRegistered"/> <a class="nav-link" href="login-form"><@s.text name="global.clickHereLink"/></a>.</p>

<#include "/templates/frameFooter.ftl">
</body>
</html>