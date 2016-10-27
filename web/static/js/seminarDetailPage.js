$(".enrollButton").click(function (event) {
    event.stopPropagation();
    window.location.href = "enroll-seminar?seminarId=" + $("#hiddenSeminarId").val();
});