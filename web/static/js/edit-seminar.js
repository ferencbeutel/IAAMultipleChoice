/**
 * Created by Max on 02.11.2016
 */
var startValue =$('#startDate').val();
var startDate = new Date(startValue);
if (startDate.getDate() <= Date.today().getDate()){
    $(':input').prop('disabled', true);
}