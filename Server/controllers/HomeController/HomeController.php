<?php
    require_once "./config.php";
    class HomeController
    {

        //[GET] xử lý trả về trang home
        public function showHome()
        {
            include('views/includes/header/index.php');
            include('views/Home/index.php');
            include('views/includes/footer/index.php');
        }

        //[GET] xử lý trả về thông tin người dùng
        public function getUserInf()
        {
            require_once "./models/User/User.php";

            //check that user already login
            if (isset($_SESSION['id'])) {
                $id_client = $_SESSION['id'];

                $user = new User();
                $statement = $user->getUserInfo($id_client);

                if ($statement->num_rows > 0) {
                    $statement->bind_result($id, $usernameDB, $email, $path);
                    $statement->fetch();

                    //check that user data have img or not
                    if ($path == null) {
                        $path = 'gs://moonvie-a3d56.appspot.com/img/default_avatar.png';
                    }

                    $user_data = array(
                        "id" => $id_client,
                        "username" => $usernameDB,
                        "email" => $email,
                        "path" => $path,
                    );

                    $data = array("title" => "getUserInfo", "id" => $id_client, "data" => $user_data, "status" => true);
                    exit(json_encode($data));
                } else {
                    $data = array("title" => "getUserInfo", "message" => "Not match any user", "status" => false);
                    exit(json_encode($data));
                }
                //Close prepare statement
                $statement->close();
            } else {
                $data = array("title" => "login", "message" => "Not logged in", "status" => false);
                exit(json_encode($data));
            }
        }

        // xử lý yêu câu đăng xuất
        public function logOut()
        {
            if (isset($_SESSION['id']))
                session_destroy();
            $data = array("title" => "logout", "message" => "success logout", "status" => true);
            exit(json_encode($data));
        }

        //[GET] xử lý trả về danh sách các phim mới
        public function getNewestMovie()
        {
            require_once "./models/Movies/Movie.php";
            $movie = new Movie();
            $statement = $movie->getNewestMovie();
            
            $statement->bind_result($id, $title, $poster_path, $backdrop_path, $release_date);

            $dataMovies = array();
            while ($statement->fetch()) {
                array_push($dataMovies, array(
                    "id" => $id,
                    "title" => $title,
                    "poster_path" => $poster_path,
                    "backdrop_path" => $backdrop_path,
                    "release_date" => $release_date,
                ));
            }

            //Close prepare statement
            $statement->close();

            if(isset($dataMovies)){
                $data = array("title" => "get newest movies", "data" => $dataMovies, "status" => true);
                exit(json_encode($data));
            }else{
                $data = array("title" => "get newest movies", "status" => false);
                exit(json_encode($data));
            }
        }

        //[GET] xử lý lấy danh sách các phim phổ biến trong tháng
        public function getPopularMonthMovie(){
            require_once "./models/Movies/Movie.php";
            $movie = new Movie();
            $statement = $movie->getPopularMonthMovie();

            $statement->bind_result($id, $title, $poster_path, $backdrop_path, $release_date);

            $dataMovies = array();
            while ($statement->fetch()) {
                array_push($dataMovies, array(
                    "id" => $id,
                    "title" => $title,
                    "poster_path" => $poster_path,
                    "backdrop_path" => $backdrop_path,
                    "release_date" => $release_date,
                ));
            }

            //Close prepare statement
            $statement->close();

            if(isset($dataMovies)){
                $data = array("title" => "get popular movies", "data" => $dataMovies, "status" => true);
                exit(json_encode($data));
            }else{
                $data = array("title" => "get popular movies", "status" => false);
                exit(json_encode($data));
            }
        }

        //[GET] xử lý lấy thông tin lấy 5 phim tiêu biểu
        public function getSpecialMovies(){
            require_once "./models/Movies/Movie.php";
            $movie = new Movie();
            $statement = $movie->getSpecialMovie();

            $statement->bind_result($id, $title, $overview, $poster_path, $backdrop_path, $tagline, $release_date, $country, $duration, $director_id, $imdb_id, $score);
            $dataMovies = array();
            while ($statement->fetch()) {
                $stmt = $movie->getMovieGenres($id);

                $stmt->bind_result($nameGenre, $idGenre);
                $listGenre = [];
                while($stmt->fetch()){
                    array_push($listGenre, array(
                        "id" => $idGenre,
                        "name" => $nameGenre
                    ));
                }

                $stmt = $movie->getTrailers($id);
                $stmt->bind_result($key_vd);
                $stmt->fetch();
                while($stmt->fetch());

                array_push($dataMovies, array(
                    "id" => $id,
                    "title" => $title,
                    "poster_path" => $poster_path,
                    "backdrop_path" => $backdrop_path,
                    "release_date" => $release_date,
                    'overview' => $overview,
                    'genres' => $listGenre,
                    'score' => $score,
                    'trailer_key' => $key_vd
                ));
            }

            //Close prepare statement
            $statement->close();

            if(isset($dataMovies)){
                $data = array("title" => "get special movie", "data" => $dataMovies, "status" => true);
                exit(json_encode($data));
            }else{
                $data = array("title" => "get special movie", "status" => false);
                exit(json_encode($data));
            }
        }

        //[GET] xử lý lấy danh sách các thể loại phim
        public function getGenres(){
            require_once "./models/Movies/Movie.php";
            $movie = new Movie();
            $statement = $movie->getGenres();

            $statement->bind_result($id, $name, $ofE);

            $dataGenres = array();
            while ($statement->fetch()) {
                array_push($dataGenres, array(
                    "id" => $id,
                    "name" => $name,
                ));
            }

            //Close prepare statement
            $statement->close();

            if(isset($dataGenres)){
                $data = array("title" => "get genres", "data" => $dataGenres, "status" => true);
                exit(json_encode($data));
            }else{
                $data = array("title" => "get genres", "status" => false);
                exit(json_encode($data));
            }

        }
    }
?>