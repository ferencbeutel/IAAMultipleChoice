/**
 * Submitting of test
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
$(document).ready(function(){
    history.pushState({ page: 1 }, "Test submitted", "#disable-browser-back");
    window.onhashchange = function (event) {
        window.location.hash = "nbb";

    };
});