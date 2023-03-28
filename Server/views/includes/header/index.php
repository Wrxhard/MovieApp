<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="/assets/img/cuttedblack.png">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/views/includes/header/index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="/views/includes/header/index.js"></script>
    <title>Moonvie</title>
</head>

<body>
    <nav class="header navbar navbar-expand-lg navbar-dark bg-transparent fixed-top">
        <div class="container px-5">
            <a class="navbar-brand" href="/"><img src="/assets/img/logo_light.png" width="100" height="40" alt=""></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/">Trang chủ</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link" href="#" id="navbarDropdown" role="button" aria-expanded="false">
                            Phim lẻ
                        </a>
                        <ul class="slide-in-top-drop-down dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="#">Hành động</a></li>
                            <li><a class="dropdown-item" href="#">Lãng mạn</a></li>
                            <li><a class="dropdown-item" href="#">Hài hước</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link" href="#" id="navbarDropdown" role="button" aria-expanded="false">
                            Phim bộ
                        </a>
                        <ul class="slide-in-top-drop-down dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="#">Action</a></li>
                            <li><a class="dropdown-item" href="#">Another action</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#" tabindex="-1" aria-disabled="true">Danh sách phim</a>
                    </li>
                </ul>
                <form id="end-content" class="d-flex justify-content-end">
                    <div class="search">
                        <i class="searchIcon fa-solid fa-magnifying-glass"></i>
                        <input class="me-2" type="search" aria-label="Search">
                        <a href="">
                            <i class="fa-solid fa-arrow-right"></i>
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </nav>
    <div class="container-fluid g-0">