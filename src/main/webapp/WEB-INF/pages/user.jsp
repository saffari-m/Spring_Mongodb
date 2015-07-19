<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="/resources/js/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" href="/resources/css/bootstrap.css"/>
</head>
<body>

<h1>${message}</h1>
<input id="clickme" type="button" value="Get All"/>
<p id="result"></p>
<script>
    $(document).ready(function () {
        $("#clickme").click(function () {
            $.ajax({
                url: "get",
                success: function (data) {
                    $("#result").html(wrapper(data));
                }
            });
        });
        $("#save").click(function(){
            var user ={id:null, name:null , family:null ,email:null};
            user.name = $("#name").val();
            user.family = $("#family").val();
            user.email = $("#email").val();
            if(user.name == "" && user.family == "" && user.email == ""){
                alert("Please insert your information");
                return;
            }
            $.ajax({
                url: "save",
                type:'POST',
                data:user,
                success: function (data) {
                    if(data == "Success" || data == "Error")
                        $("#result").html(data);
                }
            });

        });

        $("#search").click(function(){
            var user ={id:null, name:null , family:null ,email:null};
            user.name = $("#name").val();
            user.family = $("#family").val();
            user.email = $("#email").val();
            $.ajax({
                url: "search",
                type:'POST',
                data:user,
                success: function (data) {

                    $("#result").html(wrapper(data));
                }
            });

        });
        function wrapper(data){
            var t =0 ,p = "";
            if(data.length > 0){
                for(t in data){
                    if(t != undefined){
                        p +="<p><span>"+ t +" ) </span>"+  data[t].name + "-" + data[t].family + "-" + data[t].email +"</p>";
                    }
                }
            }
            return p;
        }
    });
</script>
<div>
    <p><input type="text" id="name" placeholder="Name" required="required"/></p>
    <p><input type="text" id="family"placeholder="Family" required="required"/></p>
    <p><input type="email" id="email" placeholder="Email" required="required"/></p>
    <p><input class="button btn" type="button" id="save" value="Save"/>
        <input class="button btn" type="button" id="search" value="Search"/>
    </p>
</div>
</body>
</html>