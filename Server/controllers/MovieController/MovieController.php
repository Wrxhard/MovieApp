<?php
    require_once "./config.php";
    class MovieController{
        public function showMovieS()
        {  
            include_once('views/Movie/index.php');
        }
        public function showmovie($quantity){
            require_once "./models/Movies/Movie.php";
            $movies=new Movie();
            $movies ->__getMovies($quantity);

        }

    }
?>