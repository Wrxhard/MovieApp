<?php
    class User{
        public function login($username,$password,$web){
            require_once "./models/Database/Database.php";
            $databaseIns=new Database();
            $database=$databaseIns->connect($web);
             //Check if username and password are defined
            if ( !isset($username, $password) ) {
                if ($web) {
                    exit('Please fill both the username and password fields!');
                }
                else{
                    $resp=array("title" => "connect to database","status" => false);
                    exit(json_encode($resp));
                }
            }
            //Prepare sql query to prevent SQL injection and for better performance
            if ($statement = $database->prepare('SELECT id, username, password, role FROM user WHERE username = ? AND password = ?')) {
                //Pass parameters and store results (id and password)
                $statement->bind_param('ss', $username,$password);
                $statement->execute();
                $statement->store_result();
                return $statement;
            }
        }

        public function getUserInfo($id){
            require_once "./models/Database/Database.php";
            $databaseIns=new Database();
            $database=$databaseIns->connect($web);

            //Check if username and password are defined
            if ( !isset($id) ) {
                exit('Error, can not find user thought this id');
            }

            //Prepare sql query to prevent SQL injection and for better performance
            if ($statement = $database->prepare('SELECT id, username, email, img_path FROM user WHERE id = ?')) {
                //Pass parameters and store results (id and password)
                $statement->bind_param('s', $id);
                $statement->execute();
                $statement->store_result();
                return $statement;
            }
        }

        public function signup($web,$id,$username2,$password2,$email,$role){
            require_once "./models/Database/Database.php";
            $databaseIns=new Database();
            $database=$databaseIns->connect($web);
            $result = $database->prepare("SELECT username FROM user WHERE username=?");
            $result->bind_param("s",$username);
            $result->execute();
            $result->store_result();
            $result->bind_result($username);
            if ($result->num_rows()>0){     
                $result->close();   
                $data=array("title" => "register","message" => "Username already exist","status" => "false");
                exit(json_encode($data));
            } else {
                $result->close();
                if($register = $database->prepare("INSERT INTO user VALUES (?, ?, ?, ?, ?)")){            
                    $register->bind_param("ssssi", $id, $username2, $password2, $email,$role);
                    $register->execute();
                    return $register;
                }

            }

        }
    
    } 
?>