<#assign currentPage = "login">

<html>
<head>
    <title><@s.text name="loginForm.title"/></title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/login.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">

<div class="row">
    <div class="col-xs-12">
        <p><@s.text name="loginForm.welcomeText"/></p>
        <p><@s.text name="loginForm.instruction"/></p>
        <p><@s.text name="loginForm.ifNotRegisteredText"/> <a class="nav-link" href="registration-form"><@s.text name="loginForm.clickHereLink"/></a>.</p>

    </div>
</div>

<div class="container">
<@s.form action="login" method="post">
    <div class="form-group row">
        <label for="email" class="col-md-2 col-form-label col-form-label-lg"><@s.text name="loginForm.eMail"/></label>
        <div class="col-md-10">
            <@s.textfield class="form-control form-control-lg" id="email" name="mail"/>
        </div>
    </div>
    <div class="form-group row">
        <label for="password" class="col-md-2 col-form-label col-form-label-lg"><@s.text name="loginForm.password"/></label>
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