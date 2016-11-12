/**
 * Time picker for startdate and endate for seminar creation and editing
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
$(document).ready(function () {
    var language = window.navigator.language;
    var start_date_input = $('#startDate');
    var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "body";
    start_date_input.datepicker({
        language: language,
        format: 'yyyy-mm-dd',
        container: container,
        todayHighlight: true,
        autoclose: true
    });
    var end_date_input = $('#endDate');
    end_date_input.datepicker({
        language: language,
        format: 'yyyy-mm-dd',
        container: container,
        todayHighlight: true,
        autoclose: true
    });
});
