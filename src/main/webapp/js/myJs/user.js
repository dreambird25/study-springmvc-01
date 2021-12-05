//退出系统
function loginOut() {
    layer.alert('确定退出系统吗？', {
        time: 0 //不自动关闭
        ,btn: ['确定', '取消']
        ,yes: function(index){
            layer.close(index);
            location.href = "/blog/user/loginOut";
        }
    });
}

//异步更新用户信息
function updateUser() {
    if($("#oldPwd").val().trim().length <= 0){
        layer.alert("请输入旧密码", {icon: 5});
        $(this).focus();
        return;
    }
    if($("#password").val().trim().length <= 0){
        layer.alert("请输入新密码", {icon: 5});
        $(this).focus();
        return;
    }
    if($("#new_password").val().trim().length <= 0){
        layer.alert("请输入确认密码", {icon: 5});
        $(this).focus();
        return;
    }

    $.post("/blog/user/verifyOldPwd",{'oldPwd':$('#oldPwd').val()},function (data) {
        if(!data.ok){
            layer.msg(data.mess, {offset:'t'});
            $('#oldPwd').val("");
            $('#oldPwd').focus();
            return;
        }else{
            var form = $('#updateUserForm').serialize();
            $.post("/blog/user/updateUser",form,function (data) {
                if(data.ok){
                    layer.alert(data.mess, {icon: 6});

                    setTimeout(function () {
                        //重新登录,跳转到登录页面
                        //setTimeout:隔多长时间执行指定代码
                        location.href = "/blog/login.jsp";
                    },1000);

                }else{
                    layer.alert(data.mess, {icon: 6});
                    $("#password").val("");
                    $("#new_password").val("");
                }
            },'json');
        }
    },'json');

    //表单序列化 能够把表单的内容拼接成 key=值&key=值...,返回值是字符串
    // var form = $('#updateUserForm').serialize();
    // $.post("/blog/user/updateUser",form,function (data) {
    //     if(data.ok){
    //         layer.alert(data.mess, {icon: 6});
    //
    //         setTimeout(function () {
    //             //重新登录,跳转到登录页面
    //             //setTimeout:隔多长时间执行指定代码
    //             location.href = "/blog/login.jsp";
    //         },1000);
    //
    //     }else{
    //         layer.alert(data.mess, {icon: 6});
    //         $("#password").val("");
    //         $("#new_password").val("");
    //     }
    // },'json');
}
