# MovieApp
### This is an Android application that allows users to view and search for movies. Users can also save their favorite movies and view details about them.

## Table of contents
* [General info](#General-info)
* [Technologies](#Technologies)
* [Prerequisites](#Prerequisites)
* [Support Library](#Support Library)
* [Setup](#Setup)
* [Author](#Author)
* [Acknowledgments](#Acknowledgments)

## General info
### These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
## Technologies
- PHP
- Kotlin
- Android Studio
- Xampp
- PhpMyAdmin

## Prerequisites
- Android Studio
- XAMPP (Apache server and MySQL)
- PHPMyAdmin

## Support Library
- Dagger Hilt
- Coroutine
- Glide
- Retrofit

## Setup
- Launch XAMPP and start Apache and MySQL servers
- In PHPMyAdmin, import the database.sql file from Server folder.
- Open the project in Android Studio.
- Clone the repository:

```
git clone https://github.com/Wrxhard/MovieApp.git
```

- Copy all the files in Server folder into C:\xampp\htdocs
- Change the base URL

```
const val BASE_URL="http://10.0.2.2/"
```

    
- Build and run the app on an emulator or physical device

## Author
- NguyenTrongPhuc - Wrxhard

## Acknowledgments
- API used : [https://www.themoviedb.org](#Tmdb)
