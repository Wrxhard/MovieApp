<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/views/SignIn/index.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <title>Moonvie</title>
</head>

<body>
<div class="center">
      <h1>Đăng Nhập</h1>
      <form id="loginForm" method="post" action="/register">
        <div class="txt_field">
          <input id="username2" type="text" name="username2" required>
          <span></span>
          <label>Tên người dùng</label>
        </div>
        <div class="txt_field">
          <input id="password2" type="password" name="password2" required>
          <span></span>
          <label>Mật khẩu</label>
        </div>
        <div class="txt_field">
          <input id="email" type="email" name="email" required>
          <span></span>
          <label>Email</label>
        </div>
        <div class="warningBox">
        </div>
        <div class="pass">Quên mật khẩu</div>
        <input id="submit2" type="submit" name="submit2" value="Đăng nhập">
        <div class="signup_link">
          Bạn chưa phải là thành viên? <a href="/register">Đăng kí ngay</a>
        </div>
      </form>
    </div>
</body>

</html>