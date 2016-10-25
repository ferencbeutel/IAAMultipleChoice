<#assign currentPage = "contact">

<html>
<head>
    <title><@s.text name="contact.title"/> </title>
<#include "/templates/frameHeadImports.ftl">
    <link rel="stylesheet" href="/static/css/contact.css">
</head>
<body>
<#include "/templates/frameHeader.ftl">
<div class="container"
    <div class="row">
        <div class="col-xs-12 ">
            <h1> <@s.text name="contact.contactHeadline"/> </h1>
            <p> <@s.text name="contact.uniTitle"/>
            <br> <@s.text name="contact.description"/>
            <br> <@s.text name="contact.street"/>
            <br> <@s.text name="contact.city"/>
            <br> <@s.text name="contact.telephone"/>
            <br> <@s.text name="contact.fax"/>
            <br> <@s.text name="contact.mail"/>
            <br> <@s.text name="contact.homepage"/>
        </div>
        <div class="col-xs-12 text-no-center">
            <h2> <@s.text name="contact.directions"/> </h2>
            <p><h5> <@s.text name="contact.directionsCar"/> </h5> </p>
            <span><@s.text name="contact.directionsCarDescription"/></span>
            <p><h5> <@s.text name="contact.directionsTrain"/> </h5></p>
            <span> <@s.text name="contact.directionsTrainDescription"/></span>
        </div>
        <div class="col-xs-12 ">
            <iframe
                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2358.9999397355696!2d9.66984631601366!3d53.75388415180739!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47b3d43c0b504191%3A0x952c628643783859!2sNordakademie+Hochschule+der+Wirtschaft!5e0!3m2!1sde!2sde!4v1447854271257"
                    width="100%" height="450"  style="border:0" allowfullscreen="">
            </iframe>
        </div>
    </div>
</div>


<#include "/templates/frameFooter.ftl">
</body>
</html>