$(function () {
    logindata();
});

function logindata() {
    $("#loginbtn").click(function () {
        var username = $("#loginuser input").val();
        var passworld = $("#loginpass input").val();

        var d = {
            name:"zhangsan",
            password:passworld,
            department:"销售部",
            loginname:username
        }

        if(username == "" || username == undefined || username == null) {
            return false
        }
        if(passworld == "" || passworld == undefined || passworld == null) {
            return false
        }
        $.ajax({
            type: 'GET',
            url: 'api/user/register',
            cache: false,
            dataType: 'json',
            data:d,
            success: function (datas) {
                if (datas.success == true) {
                    console.log('登录成功')
                } else {
                    console.log(datas.errMsg);
                }
            }
        });
    })
}