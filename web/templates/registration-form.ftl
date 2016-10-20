<#assign currentPage = "registration">

<html>
<head>
    <title>Registration</title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/registration.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="container">
<@s.form action="register" method="post">
    <div class="form-group row">
        <label for="firstName" class="col-md-2 col-form-label col-form-label-lg">First Name</label>
        <div class="col-md-10">
            <@s.textfield class="form-control form-control-lg" id="firstName" name="firstName"/>
        </div>
    </div>
    <div class="form-group row">
        <label for="lastName" class="col-md-2 col-form-label col-form-label-lg">Last Name</label>
        <div class="col-md-10">
            <@s.textfield class="form-control form-control-lg" id="lastName" name="lastName"/>
        </div>
    </div>
    <div class="form-group row">
        <label for="email" class="col-md-2 col-form-label col-form-label-lg">Email</label>
        <div class="col-md-10">
            <@s.textfield class="form-control form-control-lg" id="email" name="mail"/>
        </div>
    </div>
    <div class="form-group row">
        <label for="password" class="col-md-2 col-form-label col-form-label-lg">Password</label>
        <div class="col-md-10">
            <@s.password class="form-control form-control-lg" id="password" name="password"/>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-md-6 centered" id="registration-errors">
            <@s.fielderror/>
        </div>
    </div>

    <@s.submit class="btn btn-primary" value="Register"/>
</@s.form>
</div>

<#include "/templates/frameFooter.ftl">
</body>
</html>