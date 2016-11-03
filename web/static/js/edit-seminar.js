/**
 * Created by Max on 02.11.2016
 */
var startValue =$('#startDate').val();
var startDate = new Date(startValue);
if (startDate.getDay() <= Date.today().getDay()){
    $(':input').prop('disabled', true);
}