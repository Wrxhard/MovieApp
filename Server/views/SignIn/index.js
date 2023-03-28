$(document).ready(function () {
    let submitBtn = $("#submit")

    //bắt sự kiện click nút đăng nhập
    submitBtn.click(function (e) {
        // chặn hành vi gửi form mặc định của button
        e.preventDefault()
        let username = $("#username").val()
        let password = $("#password").val()

        //call api kiểm tra đăng nhập
        $.post(`http://localhost:80/login`, { username, password, submit: true }, function (data) {
            if(!data.status){
                //không thành công thì hiện lỗi
                $(".warningBox").text(data.message).addClass("shake-horizontal")
                $(".txt_field").addClass('warning')
            }else{
                //nếu thành công chuyển hướng đến trang chủ
                window.location.replace("/");
            }
        }, "json");
    })
})