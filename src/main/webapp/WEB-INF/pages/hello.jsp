<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="/resources/js/jquery-1.11.3.min.js"></script>
</head>
<body>

<h1>${message}</h1>
<input id="clickme" type="button" value="Click me"/>
<p id="result"></p>
<script>
    $(document).ready(function () {
        $("#clickme").click(function () {
            $.ajax({
                url: "get",
                success: function (data) {
                    $("#result").html("<strong>" + data + "</strong> degrees");
                }
            });
        });
        $("#save").click(function(){
            var user ={id:null, name:null , family:null ,email:null};
            user.name = $("#name").val();
            user.family = $("#family").val();
            user.email = $("#email").val();
            $.ajax({
                url: "save",
                type:'POST',
                data:user,
                success: function (data) {
                    $("#result").html("<strong>" + data + "</strong> degrees");
                }
            });

        });
    });
</script>
<div>
    <p><input type="text" id="name"/></p>
    <p><input type="text" id="family"/></p>
    <p><input type="text" id="email" placeholder="Email"/></p>
    <p><input type="button" id="save" value="Save"/></p>
</div>
</body>
</html>