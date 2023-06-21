# MovieApp

## Table of contents
* [General info](#General-info)
* [Technologies](#Technologies)
* [Prerequisites](#Prerequisites)
* [Library](#Library)
* [Setup](#Setup)
* [Demo](#Demo)
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

## Library
- Dagger Hilt
- Coroutine
- Glide
- Retrofit
- Okhttp
- Room

## Setup
- Open the project in Android Studio.
- Clone the repository:

    ```
    git clone https://github.com/Wrxhard/MovieApp.git
    ```

- Copy all the files in Server folder into C:\xampp\htdocs
- Launch XAMPP and start Apache and MySQL servers
- In PHPMyAdmin, import the database.sql file from Server folder.
- Change the BASE_URL in MooviApp\app\src\main\java\tdtu\movieapp\app\di\AppModule.kt 
to match the ip address of the host device (using ipconfig)

    ```
    const val BASE_URL="http://your_device_ip_address:80/" #Laptop
    ```
    
    ```
    const val BASE_URL="http://your_device_ip_address:8080/" #PC
    ```

    
- Build and run the app on an emulator or physical device

## Demo


https://github.com/Wrxhard/MovieApp/assets/91369021/51442b85-607b-463f-8873-55bcca2cf4a9



## Author
- NguyenTrongPhuc

## Acknowledgments
- API used : [https://www.themoviedb.org](#Tmdb)


