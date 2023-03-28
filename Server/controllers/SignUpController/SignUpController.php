<?php 
    class SignUpController{
        public function showSignUpPage(){
            include('views/SignUp/index.php');
        }
        public function signUp($web,$id,$username,$password,$email,$role){
            require_once "./models/User/User.php";
            $user = new User();
            $register=$user -> signup($id,$username,$password,$email,$role,$web);
            if ($register->affected_rows>0){ 
                $data=array("title" => "register","User id" => $id,"status" => "success");
                exit(json_encode($data));
            }
            else{
                $data=array("title" => "register","message" => "Error","status" => "false");
                exit(json_encode($data));
            }
            $register->close(); 
        }
    }
?>