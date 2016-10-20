<#assign currentPage = "login">

<html>
<head>
    <title>Login</title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/login.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="row">
    <div class="col-xs-12">
        <p>Herzlich Willkommen auf dem Seminarportal der NORDAKADEMIE.</p>
        <p>Bitte melden Sie sich an.</p>
        <p>Wenn Sie noch nicht registriert sind, klicken Sie bitte <a class="nav-link" href="registration-form">hier</a>.</p>

    </div>
</div>

<div class="container">
<@s.form action="login" method="post">
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
        <div class="col-md-6 centered" id="login-errors">
            <@s.fielderror/>
        </div>
    </div>

    <@s.submit class="btn btn-primary" value="Login"/>
</@s.form>
</div>

<#include "/templates/frameFooter.ftl">
</body>
</html>