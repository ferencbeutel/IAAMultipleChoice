$(document).ready(function () {
    var language = window.navigator.language;
    var start_date_input = $('input[name="startDate"]');
    var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "body";
    start_date_input.datepicker({
        language: language,
        format: 'mm/dd/yyyy',
        container: container,
        todayHighlight: true,
        autoclose: true
    });
    var end_date_input = $('input[name="endDate"]');
    end_date_input.datepicker({
        language: language,
        format: 'mm/dd/yyyy',
        container: container,
        todayHighlight: true,
        autoclose: true
    });
});
