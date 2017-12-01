<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset=utf-8 />
    <title>Students</title>

    <script type="text/javascript" src="<c:url value="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" />"></script>
    <%--<script type="text/javascript">--%>
    <%--<jsp:include page="script.js" />--%>
    <%--</script>--%>

</head>
<body>
<select id="list1" onchange="fillSubjects()">
</select>
<script>
    $.getJSON('http://localhost:8080/students/getAllStudents', function(data) {
        for (var i in data) {
            $('#list1').append('<option value="' + data[i].id + '">' + data[i].name + '</option>');
        }
    });
</script>

<select id="list2">
</select>

<button onclick="saveSubject()"> Сохранить</button>
<button onclick="updatePage()"> Обновить</button>

</body>
<script>
    function fillSubjects() {
        $('#list2').empty();
        $.getJSON('http://localhost:8080/subjects/getAllSubjects/' + $("#list1" ).val(), function(data) {
            for (var i in data) {
                $('#list2').append('<option value="' + data[i].subjectID + '">' + data[i].subjectName + '</option>');
            }
        });
    }

    function saveSubject() {
        $.post( "http://localhost:8080/subjects/saveChanges", { student: $("#list1" ).val(), subject: $("#list2" ).val() } );
    }

    function updatePage() {
        location.reload();
    }
</script>

</html>
