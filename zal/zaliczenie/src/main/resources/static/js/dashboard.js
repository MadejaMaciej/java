$(document).ready(()=>{
    $.ajax({
        type: "POST",
        url: "/check-if-valid",
        data: JSON.stringify({
            "email": window.localStorage.getItem("email"),
            "password": window.localStorage.getItem("token")
        }),
        success: function(res){
            if(res == false){
                window.location.pathname = "/not-authorized.html"
            }
        },
        error: function (xhr, ajaxOptions, thrownError) {
            console.log(xhr, ajaxOptions, thrownError)
        },
        dataType: "json",
        contentType : "application/json"
    });

    $.ajax({
        type: "POST",
        url: "/users-not-viewers",
        data: JSON.stringify({
        "email": window.localStorage.getItem("email"),
        "password": window.localStorage.getItem("token")
        }),
        success: function(res){
            for(var i = 0; i < res.length; i++){
                $("#users").append(`<div class="d-grid one-user max-width-300 p-4 mb-4" style="margin:20px 20px;"><img src="/img/logo.png" class="mx-auto profile-pic" /><p class="mx-auto my-4">${res[i]}</p><button onClick=addUser('${res[i]}') class="watch-button">OBSERWUJ</button></div>`)
            }
        },
        error: function (xhr, ajaxOptions, thrownError) {
            console.log(xhr, ajaxOptions, thrownError)
        },
        dataType: "json",
        contentType : "application/json"
    });

    $.ajax({
        type: "POST",
        url: "/get-posts",
        data: JSON.stringify({
        "email": window.localStorage.getItem("email"),
        "password": window.localStorage.getItem("token")
        }),
        success: function(res){
            var dates = []
            var helpDates = []
            for(var i = 0; i < res.length; i++){
                if(i%3 == 2){
                    dates.push(Date.parse(res[i]))
                    helpDates.push(Date.parse(res[i]))
                }
                
            }
            dates = dates.sort()
            for(var i = 0; i < dates.length; i++){
                for(var j = 0; j < helpDates.length; j++){
                    if(dates[i] == helpDates[j]){
                        var d = new Date(res[(j*3)+2]);
                        $("#posts").append(`<div class="post white-color"><div class="name"><p class="uname white-font">${res[j*3]}</p></div><div class="date"><p class="time white-font px-4 py-2">${d.toUTCString()}</p></div><div class="post"><p class="pos one-user py-4 px-2">${res[(j*3)+1]}</p></div></div><hr class="white-font hr">`);
                    }
                }
            }
            console.log(dates.sort())
        },
        error: function (xhr, ajaxOptions, thrownError) {
            console.log(xhr, ajaxOptions, thrownError)
        },
        dataType: "json",
        contentType : "application/json"
    });
    $("#send-post").on('click', () => {
        $.ajax({
            type: "POST",
            url: "/add-post",
            data: JSON.stringify({
            "email": window.localStorage.getItem("email"),
            "password": window.localStorage.getItem("token"),
            "username": $("#post").val()
            }),
            success: function(res){
                location.reload();
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr, ajaxOptions, thrownError)
            },
            dataType: "json",
            contentType : "application/json"
        });
    })
     
    
})

var addUser = (email) =>{
    $.ajax({
        type: "POST",
        url: "/add-watcher",
        data: JSON.stringify({
        "email": window.localStorage.getItem("email"),
        "password": window.localStorage.getItem("token"),
        "username": email
        }),
        success: function(res){
            location.reload();
        },
        error: function (xhr, ajaxOptions, thrownError) {
            console.log(xhr, ajaxOptions, thrownError)
        },
        dataType: "json",
        contentType : "application/json"
    });  
}