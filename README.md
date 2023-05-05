# MovieApp
### This is an Android application that allows users to view and search for movies. Users can also save their favorite movies and view details about them.

## Table of contents
* [General info](#General-info)
* [Technologies](#Technologies)
* [Prerequisites](#Prerequisites)
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

## Setup
- Launch XAMPP and start Apache and MySQL servers
- In PHPMyAdmin, create a new database called moonvie.
- Import the moonvie.sql file from Server folder.
- Open the project in Android Studio.
- Clone the repository:

```
git clone https://github.com/Wrxhard/MovieApp.git
```

- Copy all the files in Server folder into C:\xampp\htdocs
- Change the base URL to the IP address of your device in the AppModule.kt file located in app/src/main/java/tdtu/movieapp/app/di 
using one of these two:

```
const val BASE_URL="http://your_device_ip_address:80/" #Laptop
```

```
const val BASE_URL="http://your_device_ip_address:8080/" #PC
```
    
- Build and run the app on an emulator or physical device

## Author
- NguyenTrongPhuc - Wrxhard

## Acknowledgments
- API used : [https://www.themoviedb.org](#Tmdb)
