<?php
    class Movie
    {
        function __construct(){
        }

        public function getMovieByID($id){
        }

        public function getMovieByType($type){
        }

        //[GET]lấy các phim mới nhất
        public function getNewestMovie(){
            $query = 'SELECT id, title, poster_path, backdrop_path, release_date FROM `movie` WHERE release_date < now() ORDER by release_date desc limit 0, 20';
            return $this->getDataByQuery($query);
        }

        //[GET]lấy các phim phổ biến
        public function getPopularMonthMovie(){
            $query = 'SELECT movie.id, movie.title, movie.poster_path, movie.backdrop_path, movie.release_date, imdb_rating.score from movie LEFT JOIN imdb_rating on movie.imdb_id = imdb_rating.id where year(release_date) BETWEEN 1990 and year(CURDATE()) and month(release_date) <= month(curdate()) order by imdb_rating.score desc limit 20';
            return $this->getDataByQuery($query);
        }
        
        //[GET]lấy danh sách các thể loại phim
        public function getGenres(){
            $query = "SELECT * FROM genre";
            return $this->getDataByQuery($query);
        }

        //[GET]lấy 5 phim mới, có điểm imdb cao nhất trong tháng
        public function getSpecialMovie(){
            $query = 'select movie.*, imdb_rating.score from movie LEFT JOIN imdb_rating on movie.imdb_id = imdb_rating.id where movie.overview != "" and movie.id IN (select id from movie where (MONTH(release_date) BETWEEN MONTH(CURDATE()) - 3 and MONTH(CURDATE())) AND YEAR(release_date) = YEAR(CURDATE())) ORDER by imdb_rating.score desc limit 5';
            return $this->getDataByQuery($query);
        }

        //[GET]lấy các thể loại của phim
        public function getMovieGenres($id){
            $query = 'SELECT genre.name, genre.id FROM `movie_genre` left join genre on genre.id = movie_genre.id_type WHERE movie_genre.id_movid = ?';
            return $this->getDataByQueryAndID($query, $id);
        }

        //[GET] lấy các trailer của phim
        public function getTrailers($id){
            $query = 'SELECT key_vd FROM `trailer` WHERE id_movid = ? and official = 1';
            return $this->getDataByQueryAndID($query, $id);
        }

        //[GET]hàm tổng lấy data theo query và id
        private function getDataByQueryAndID($query, $id){

            require_once "./models/Database/Database.php";
            $databaseIns = new Database();
            $db = $databaseIns->connect(true);

            if ($statement = $db->prepare($query)) {
                // execute query to get newest movies
                $statement->bind_param("s", $id);
                $statement->execute();
                $statement->store_result();
                return $statement;
            }
        }

        //[GET]hàm tổng lấy data theo query
        private function getDataByQuery($query){

            require_once "./models/Database/Database.php";
            $databaseIns = new Database();
            $db = $databaseIns->connect(true);

            if ($statement = $db->prepare($query)) {
                // execute query to get newest movies
                $statement->execute();
                $statement->store_result();
                return $statement;
            }
        }

        public function __getMovies($page)
        {
            require_once "./models/Database/Database.php";
            $databaseIns = new Database();
            $database = $databaseIns->connect(true);
            $result = $database->prepare("SELECT movie.id, movie.title, movie.overview, movie.poster_path, movie.backdrop_path, movie.tagline,movie.release_date,movie.country, movie.duration, movie.director_id, imdb_rating.id, imdb_rating.score, imdb_rating.vote_count FROM movie INNER JOIN imdb_rating ON movie.imdb_id = imdb_rating.id LIMIT ?,?");
            $quantity = $page * 20;
            $limit = 20;
            $result->bind_param('ii', $quantity, $limit);
            $result->execute();
            $result->store_result();
            $data = array();
            $movie = array();
            if ($result->num_rows() > 0) {
                $result->bind_result($id, $title, $overview, $poster_path, $backdrop_path, $tagline, $release_date, $country, $duration, $director_id,$imdb_id,$score,$vote_count);
                while ($result->fetch()) {
                    $movie["id"] = $id;
                    $movie["title"] = $title;
                    $movie["overview"] = $overview;
                    $movie["poster_path"] = $poster_path; #them duong dan den thu muc chua anh
                    $movie["backdrop_path"] = $backdrop_path; #them duong dan den thu muc chua anh
                    $movie["tagline"] = $tagline;
                    $movie["release_date"] = $release_date;
                    $movie["country"] = $country;
                    $movie["duration"] = $duration;
                    $movie["director_id"] = $director_id;
                    $movie["imdb_id"] = $imdb_id;
                    $movie["score"]=$score;
                    $movie["vote_count"]=$vote_count;
                    array_push($data, $movie);
                }
                header('Content-Type: application/json');
                exit(json_encode($data));
            } else {
                exit("Failed to get data");
            }
            $result->close();
        }
    }

?>