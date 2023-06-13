<?php
require_once "./config.php";
class SignInController
{
    public function showSignInPage()
    {  
        include_once('views/SignIn/index.php');
    }
    public function signInGoogle($username, $password)
    {
        require_once "./models/User/User.php";
        require_once "./controllers/SignUpController/SignUpController.php";
        $user = new User();
        $stm=$user->signInGoogle($username, $password);
        if ($stm->num_rows > 0) {
            $stm->bind_result($id, $usernameDB, $passwordDB, $role);
            $stm->fetch();
            $client_password = $password;
            if (password_verify($client_password, $passwordDB)) {
                //send json data to client
                $data=array("title" => "login","id" => $id,"username" => $usernameDB, "role" => $role, "status" => true);
                exit(json_encode($data));
            } else {
                $data=array("title" => "login","message" => "Incorrect username and/or password!","status" => false);
                exit(json_encode($data));
            }
        }else{
            $signUpController = new SignUpController();
            $signUpController->register(false);
        }
    }

    public function userAuth($web, $username, $password)
    {               
        require_once "./models/User/User.php";
        $user = new User();
        //query data through to user object
        $statement = $user -> login($username,$password,$web);

        //kiem tra du lieu
        if ($statement->num_rows > 0) {
            $statement->bind_result($id, $usernameDB, $passwordDB, $role);
            $statement->fetch();
            //Hash client typed password
            $client_password = $password;
            //Check if the client password and the database password are matches
            if (password_verify($client_password, $passwordDB)) {
                //create session to save user's login information
                session_regenerate_id();
                $_SESSION['logged_in'] = TRUE;
                $_SESSION['name'] = $_POST['username'];
                $_SESSION['id'] = $id;

                //set expire time of session

                //send json data to client
                $data=array("title" => "login","id" => $id,"username" => $usernameDB, "role" => $role, "status" => true);
                exit(json_encode($data));
            } else {
                $data=array("title" => "login","message" => "Incorrect username and/or password!","status" => false);
                exit(json_encode($data));
            }
        } else {
            $data=array("title" => "login","message" => "Incorrect username and/or password","status" => false);
            exit(json_encode($data));
        }
        //Close prepare statement
        $statement->close();
    }
}
?>
