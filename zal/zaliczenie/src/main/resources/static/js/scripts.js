$(document).ready(() => {
    $("#login").on('click', (e) => {
        e.preventDefault();
        var email = $("#email").val();
        var password = $("#password").val();
        $.ajax({
            type: "POST",
            url: "/login-user",
            data: JSON.stringify({
            "email": email,
            "password": password
            }),
            success: function(res){
                window.localStorage.setItem("username", res.username)
                window.localStorage.setItem("email", res.email)
                window.localStorage.setItem("token", res.password)
                if(res.email != null){
                    window.location.pathname = "/dashboard.html"
                }else{
                    window.location.pathname = "/bad-credentials.html"
                }
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr, ajaxOptions, thrownError)
            },
            dataType: "json",
            contentType : "application/json"
        });
    })

    $("#register").on('click', (e) => {
        e.preventDefault();
        var email = $("#email").val();
        var password = $("#password").val();
        var username =  $("#username").val();
        if(isEmail(email) && username != "" && password != ""){
            $.ajax({
                type: "POST",
                url: "/register-user",
                data: JSON.stringify({
                "username": username,
                "email": email,
                "password": password
                }),
                success: function(res){
                    if(res == false){
                        window.location.pathname = "/user-exists.html"
                    }else{
                        window.location.pathname = "/created-user.html"
                    }
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    console.log(xhr, ajaxOptions, thrownError)
                },
                dataType: "json",
                contentType : "application/json"
            });
        }
    })
})

function isEmail(email) {
    var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return regex.test(email);
  }