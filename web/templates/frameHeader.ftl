<div id="contentContainer">
    <nav class="navbar navbar-dark bg-inverse" id="navBar">
        <div class="container-fluid">
            <button class="navbar-toggler hidden-lg-up pull-xs-right" type="button" data-toggle="collapse"
                    data-target="#collapsingNavbar" aria-controls="collapsingNavbar" aria-expanded="false"
                    aria-label="Toggle navigation">
                &#9776;
            </button>
            <a class="navbar-brand" href="#"><@s.text name="frameHeader.systemName"/></a>
            <div class="collapse navbar-toggleable-md" id="collapsingNavbar">
                <ul class="nav navbar-nav">
                    <li class="nav-item">
                    <#if currentPage == "home">
                        <a class="nav-link active" href="#"><@s.text name="frameHeader.linkHome"/></a>
                    <#else>
                        <a class="nav-link" href="home"><@s.text name="frameHeader.linkHome"/></a>
                    </#if>
                    </li>
                    <li class="nav-item">
                    <#if Session?? && Session.user?? && Session.user.getClass()?contains("Student")>
                        <#if currentPage == "seminarList">
                            <a class="nav-link active" href="#"><@s.text name="frameHeader.linkSeminarlist"/></a>
                        <#else>
                            <a class="nav-link" href="seminar-list"><@s.text name="frameHeader.linkSeminarlist"/></a>
                        </#if>
                    </#if>
                    </li>
                    <li class="nav-item">
                    <#if Session?? && Session.user?? && Session.user.getClass()?contains("Lecturer")>
                        <#if currentPage == "testAdministration">
                            <a class="nav-link active" href="#"><@s.text name="frameHeader.linkTestAdministration"/></a>
                        <#else>
                            <a class="nav-link" href="testAdministration"><@s.text name="frameHeader.linkTestAdministration"/></a>
                        </#if>
                    </#if>
                    </li>
                    <li class="nav-item">
                    <#if Session?? && Session.user?? && Session.user.getClass()?contains("Student")>
                        <#if currentPage == "results">
                            <a class="nav-link active" href="#"><@s.text name="frameHeader.linkResults"/></a>
                        <#else>
                            <a class="nav-link" href="results"><@s.text name="frameHeader.linkResults"/></a>
                        </#if>
                    </#if>
                    </li>

                    </li>
                    <li class="nav-item">
                    <#if Session?? && Session.user?? && Session.user.getClass()?contains("Lecturer")>
                        <#if currentPage == "seminar-form">
                            <a class="nav-link active" href="#"><@s.text name="frameHeader.linkCreateSeminar"/></a>
                        <#else>
                            <a class="nav-link" href="seminar-form"><@s.text name="frameHeader.linkCreateSeminar"/></a>
                        </#if>
                    </#if>
                    </li>
                </ul>
                <ul class="nav navbar-nav pull-xs-right">
                    <li class="nav-item">
                    <#if !Session?? || !Session.user??>
                        <#if currentPage == "login">
                            <a class="nav-link active" href="#"><@s.text name="frameHeader.linkLogin"/></a>
                        <#else>
                            <a class="nav-link" href="login-form"><@s.text name="frameHeader.linkLogin"/></a>
                        </#if>
                    </#if>
                    </li>
                    <li class="nav-item">
                    <#if !Session?? || !Session.user??>
                        <#if currentPage == "registration">
                            <a class="nav-link active" href="#"><@s.text name="frameHeader.linkRegister"/></a>
                        <#else>
                            <a class="nav-link" href="registration-form"><@s.text name="frameHeader.linkRegister"/></a>
                        </#if>
                    </#if>
                    </li>
                    <li class="nav-item">
                    <#if Session?? && Session.user??>
                        <#if currentPage == "logout">
                            <a class="nav-link active" href="#"><@s.text name="frameHeader.linkLogout"/></a>
                        <#else>
                            <a class="nav-link" href="logout"><@s.text name="frameHeader.linkLogout"/></a>
                        </#if>
                    </#if>
                    </li>
                </ul>
            </div>
        </div>
    </nav>