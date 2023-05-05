<?php
require './config.php';
session_set_cookie_params(EXPIRE_TIME);
session_start();
require_once './controllers/HomeController/HomeController.php';
require_once './controllers/SignInController/SignInController.php';
require_once './controllers/SignUpController/SignUpController.php';
require_once './controllers/ForgotPassController/ForgotPassController.php';
require_once './controllers/DetailMovieController/DetailMovieController.php';
require_once './controllers/UserDetailController/UserDetailController.php';
require_once './controllers/MovieController/MovieController.php';

$request = $_SERVER['REQUEST_URI'];
$split_request = explode("?", $request);
$request_parts = explode('/', $split_request[0]);

switch ($request_parts[1]) {
    case "":{
        $homeController = new HomeController();
        if(isset($_GET['getUserInf'])){
            $homeController->getUserInf();
        }else if(isset($_GET['logOut'])){
            $homeController->logOut();
        }else if(isset($_GET['getNewestMovie'])){
            $homeController->getNewestMovie();
        }else if(isset($_GET['getPopularMovie'])){
            $homeController->getPopularMonthMovie();
        }else if(isset($_GET['getGenres'])){
            $homeController->getGenres();
        }else if(isset($_GET['getSpecialMovie'])){
            $homeController->getSpecialMovies();
        }
        else{
            $homeController->showHome();
        }
        break;
    }
    case "login":{
        $signInController = new SignInController();
        if(isset($_POST['submit'])){
            $user = $_POST['username'];
            $password = $_POST['password'];
            $signInController->userAuth(true, $user, $password);
        }else{
            $signInController->showSignInPage();
        }
        break;
    }
    case "loginonmobile":{
        $signInController = new SignInController();
        $user = $_POST['username'];
        $password = $_POST['password'];
        $signInController->userAuth(false, $user, $password);
        break;
    }
    case "register":{
        $signUpController = new SignUpController();
        if(isset($_POST['submit'])){
            $signUpController->register(false);
        }
        else if(isset($request_parts[2])){
            if($request_parts[2] == "notification")
            $signUpController->notification();
        }
        else{
            $signUpController->showSignUpPage();
        }
        break;
    }
    case "forgotPass":{
        $forgotPassController = new ForgotPassController();
        $forgotPassController->showForgotPassPage();
        break;
    }
    case "type":{
        $signInController = new SignInController();
        $signInController->showSignInPage();
        break;
    }
    case "detail":{
        $detailMovieController = new DetailMovieController();
        $detailMovieController->showDetailMOviePage();
        break;
    }
    case "user": {
        $userDetailController = new UserDetailController();
        $userDetailController->showUserPage();
        break;
    }
    case "movies": {
        $movieController=new MovieController();
        $movieController->showMovie($_GET['page']);
    }
    default: require __DIR__ . '/views/404.php';
}
