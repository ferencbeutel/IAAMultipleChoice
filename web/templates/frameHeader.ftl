<div id="contentContainer">
    <nav class="navbar navbar-dark bg-inverse" id="navBar">
        <div class="container-fluid">
            <button class="navbar-toggler hidden-lg-up pull-xs-right" type="button" data-toggle="collapse"
                    data-target="#collapsingNavbar" aria-controls="collapsingNavbar" aria-expanded="false" aria-label="Toggle navigation">
                &#9776;
            </button>
            <a class="navbar-brand" href="#">Prüfungssystem</a>
            <div class="collapse navbar-toggleable-md" id="collapsingNavbar">
                <ul class="nav navbar-nav">
                    <li class="nav-item">
                    <#if currentPage == "home">
                        <a class="nav-link active" href="#">Home</a>
                    <#else>
                        <a class="nav-link" href="home">Home</a>
                    </#if>
                    </li>
                    <li class="nav-item">
                    <#if currentPage == "testAdministration">
                        <a class="nav-link active" href="#">Test Verwalten</a>
                    <#else>
                        <a class="nav-link" href="testAdministration">Test Verwalten</a>
                    </#if>
                    </li>
                    <li class="nav-item">
                    <#if currentPage == "results">
                        <a class="nav-link active" href="#">Ergebnisse</a>
                    <#else>
                        <a class="nav-link" href="results">Ergebnisse</a>
                    </#if>
                    </li>
                    <li class="nav-item">
                    <#if currentPage == "chooseSeminar">
                        <a class="nav-link active" href="#">Seminar auswählen</a>
                    <#else>
                        <a class="nav-link" href="chooseSeminar">Seminar auswählen</a>
                    </#if>
                    </li>
                </ul>
                <ul class="nav navbar-nav pull-xs-right">
                    <li class="nav-item">
                    <#if currentPage == "login">
                        <a class="nav-link active" href="+">Login</a>
                    <#else>
                        <a class="nav-link" href="login-form">Login</a>
                    </#if>
                    </li>
                    <li class="nav-item">
                    <#if currentPage == "registration">
                        <a class="nav-link active" href="#">Register</a>
                    <#else>
                        <a class="nav-link" href="registration-form">Register</a>
                    </#if>
                    </li>
                    <li class="nav-item">
                    <#if currentPage == "logout">
                        <a class="nav-link active" href="#">Logout</a>
                    <#else>
                        <a class="nav-link" href="logout">Logout</a>
                    </#if>
                    </li>
                </ul>
            </div>
        </div>
    </nav>