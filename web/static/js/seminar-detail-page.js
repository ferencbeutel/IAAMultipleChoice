$(".enrollButton").click(function (event) {
    window.location.href = "enroll-seminar?seminarId=" + $("#hiddenSeminarId").val();
});