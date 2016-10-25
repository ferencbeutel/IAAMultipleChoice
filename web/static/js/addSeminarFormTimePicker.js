$(document).ready(function () {
    /** TODO: Language beim Datepicker auch in ausgelagerter JS-Datei anpassen (AddSeminar) **/
    var language = window.navigator.language;
    var start_date_input = $('#startDate');
    var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "body";
    start_date_input.datepicker({
        language: language,
        format: 'mm/dd/yyyy',
        container: container,
        todayHighlight: true,
        autoclose: true
    });
    var end_date_input = $('#endDate');
    end_date_input.datepicker({
        language: language,
        format: 'mm/dd/yyyy',
        container: container,
        todayHighlight: true,
        autoclose: true
    });
});
