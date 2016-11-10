/**
 * Disabling of inputs when seminar already started
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
var startDate = new Date($('#startDate').val());
if (startDate <= new Date()) {
    $(':input').prop('disabled', true);
}