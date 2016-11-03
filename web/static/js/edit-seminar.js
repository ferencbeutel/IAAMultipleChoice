/**
 * Created by Max on 02.11.2016
 */
var startDate = new Date($('#startDate').val());
if (startDate <= new Date()) {
    $(':input').prop('disabled', true);
}