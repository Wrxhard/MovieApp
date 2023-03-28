<?php 
    require_once "./config.php";

    class HomeController{
        public function showHome(){
            $isLogin = $this->checkLogin();
            include('views/includes/header/index.php');
            include('views/Home/index.php');
            include('views/includes/footer/index.php');
        }

        public function checkLogin(){
            if(isset($_SESSION['logged_in'])){
                return true;
            }else{
                return false;
            }
        }

        public function getUserInf(){
            require_once "./models/User/User.php";

            //check that user already login
            if(isset($_SESSION['id'])){
                $id_client = $_SESSION['id'];
                $user = new User();

                $statement = $user->getUserInfo($id_client);

                if ($statement->num_rows > 0) {
                    $statement->bind_result($id, $usernameDB, $email, $path);
                    $statement->fetch();

                    //check that user data have img or not
                    if($path == null){
                        $path = 'gs://moonvie-a3d56.appspot.com/img/default_avatar.png';
                    }

                    $user_data = array(
                        "id" => $id_client,
                        "username" => $usernameDB,
                        "email" => $email,
                        "path" => $path,
                    );

                    $data=array("title" => "getUserInfo","id" => $id_client, "data" => $user_data, "status" => true);
                    exit(json_encode($data));
                }else{
                    $data=array("title" => "getUserInfo","message" => "Not match any user","status" => false);
                    exit(json_encode($data));   
                }
            }else{
                $data=array("title" => "login","message" => "Not logged in","status" => false);
                exit(json_encode($data));
            }
            //Close prepare statement
            $statement->close();
        }

        public function logOut(){
            if(isset($_SESSION['id']))
                session_destroy();
            $data=array("title" => "logout","message" => "success logout","status" => true);
            exit(json_encode($data));
        }
        
    }
?>