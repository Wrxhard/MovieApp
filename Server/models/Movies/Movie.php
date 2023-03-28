<?php
class Movie
{
    public function __getMovieByID($id)
    {
    }

    public function __getMovieByType($type)
    {
    }
    public function __getMovies($page){
        require_once "./models/Database/Database.php";
            $databaseIns=new Database();
            $database=$databaseIns->connect(true);
            $result = $database->prepare("SELECT * FROM movie LIMIT ?,?");
            $quantity=($page-1)*20;
            $limit=20;
            $result->bind_param('ii',$quantity,$limit);
            $result->execute();
            $result->store_result();
            $data=array();
            $movie=array();
            if ($result->num_rows() > 0) {
                $result->bind_result($id,$title,$overview,$poster_path,$backdrop_path,$tagline,$yearProduce,$country,$duration,$director_id);
                while ($result->fetch()) {
                    $movie["id"]=$id;
                    $movie["title"]=$title;
                    $movie["overview"]=$overview;
                    $movie["poster_path"]=$poster_path; #them duong dan den thu muc chua anh
                    $movie["backdrop_path"]=$backdrop_path; #them duong dan den thu muc chua anh
                    $movie["tagline"]=$tagline;
                    $movie["yearProduce"]=$yearProduce;
                    $movie["country"]=$country;
                    $movie["duration"]=$duration;
                    $movie["director_id"]=$director_id;
                    array_push($data,$movie);
                }
                header('Content-Type: application/json');
                exit(json_encode($data));
            }
            else{
                exit("Failed to get data");
            }
            $result->close();
    }
}
?>