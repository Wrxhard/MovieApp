<link rel="stylesheet" href="/views/UserDetail/index.css">
<script src=" https://cdn.jsdelivr.net/npm/@splidejs/splide@4.1.4/dist/js/splide.min.js "></script>
<link href=" https://cdn.jsdelivr.net/npm/@splidejs/splide@4.1.4/dist/css/splide.min.css " rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<br><br><br><br><br><br>
<main>
  <div class="container">
    <div class="row">
      <div class="col-md-3">
        <div class="tab">
                <a href="#" class="tablinks" onclick="openEvent(event,'Profile')" id="defaultOpen"><i class="fa-regular fa-user"></i> &ensp;  Profile Settings</a>
                <a href="#" class="tablinks" onclick="openEvent(event,'Lan&cur')"><i class="fa-solid fa-globe"></i> &ensp;   Languages and Currency</a> 
                <a href="#" class="tablinks" onclick="openEvent(event,'Movie_set')"><i class="fa-solid fa-film"></i> &ensp;  Movie Setting</a>
                <a href="#" class="tablinks" onclick="openEvent(event,'fb_acc')"><i class="fa-brands fa-facebook"></i> &ensp; Facebook Account</a>
                <a href="#" class="tablinks" onclick="openEvent(event,'tw_acc')"><i class="fa-brands fa-square-twitter"></i> &ensp;  Twitter Account</a>
        </div>
      </div>
      <div class="col-md-7">
        <div class="card">
          <div id="Profile" class="card-header tabcontent">
            <h2>Profile Settings<h2>
                <br>
            <p>&emsp;&emsp;Profile image</p>
            <img class="left_size" src="assets/img/gamer (1).png" alt="Gamer" width="20%" height="20%">
            <p class="right_size">We recommend an image of at least 300x300.<br> 
                Gifs work too. Max 5 MB 
                <br><br><br>
                <button type="open" class="btn color-button">Save Changes</button></p>
                <br><br><br><br>
            <div class="card-body">
              <form>
                <div class="form-group">
                  <label for="ID">Your ID</label>
                  <input type="text" class="form-control" id="ID" placeholder="Nhap ID cua ban">
                </div><br>
                <div class="form-group">
                  <label for="UserName">Username</label>
                  <input type="text" class="form-control" id="UserName" placeholder="Nhap ten dang nhap">
                </div><br>
                <div class="form-group">
                  <label for="Birth">Birthday</label>
                  <input type="text" class="form-control" id="Birth" placeholder="Nhap ngay thang nam sinh">
                </div><br>
                <div class="form-group">
                  <label for="Role">Role</label>
                  <input type="text" class="form-control" id="Role" placeholder="Nhap role">
                </div><br>
                <div class="form-group">
                  <label for="img_path">img_path</label>
                  <input type="text" class="form-control" id="img_path" placeholder="Nhap img_path">
                </div><br>
                <div class="form-group">
                  <label for="firstName">First Name</label>
                  <input type="text" class="form-control" id="firstName" placeholder="Nhap Ten cua ban">
                </div><br>
                <div class="form-group">
                  <label for="lastName">Last Name</label>
                  <input type="text" class="form-control" id="lastName" placeholder="Nhap ho cua ban">
                </div><br>
                <div class="form-group">
                  <label for="email">Email address</label>
                  <input type="email" class="form-control" id="email" placeholder="Nhap Email cua ban">
                </div><br>
                <div class="form-group">
                  <label for="phoneNumber">Phone Number</label>
                  <input type="text" class="form-control" id="phoneNumber" placeholder="Nhap so dien thoai cua ban">
                </div>
                <br>
                <button type="submit" class="btn color-button">Save Changes</button>
              </form>
            </div>
          </div>

          <div id="Lan&cur" class="tabcontent card-header">
            <h2>Change language</h2>
            <button id="switch-lang">Switch Language</button>
            <br><br>
            <h2>Change currency</h2>
            <h4>You can switch the currency here</h4>
            <button id="switch-lang">Switch Currency</button>
          </div>

          <div id="Movie_set" class="tabcontent card-header">
            <h2>Setting movie</h2>
            <button id="switch-lang">Resolution</button>
            <br>
            <h4 lang="en"><a href="#">Change link 1</a></h4>
            <h4 lang="es"><a href="#">Change link 2</a></h4>
          </div>

          <div id="fb_acc" class="tabcontent card-header">
            <h2>Facebook account</h2>
            <a href="#" class="fb btn">
            <i class="fa fa-facebook fa-fw"></i> Login with Facebook</a>
            <br><br>
            <div class="bottom-container">
            <div class="row">
              <div class="col">
                <a href="#" style="color:white" class="btn">Sign up</a>
              </div>
            </div>
          </div>
            <div class="col">
              <div class="hide-md-lg">
                <p>Or sign in manually:</p>
              </div>
              <input type="text" name="username" placeholder="Username" required>
              <br><br>
              <input type="password" name="password" placeholder="Password" required>
              <br><br>
              <input type="submit" value="Login">
              <br><br>
              <div class="col">
                  <a href="#" style="color:white" class="btn">Forgot password?</a>
              </div>
            </div>
          </div>

          <div id="tw_acc" class="tabcontent card-header">
            <h2>Twitter account</h2>
            <a href="#" class="twitter btn">
            <i class="fa fa-twitter fa-fw"></i> Login with Twitter</a>
            <br><br>
            <div class="bottom-container">
            <div class="row">
                <div class="col">
                  <a href="#" style="color:white" class="btn">Sign up</a>
                </div>
              </div>
            </div>
            <div class="col">
              <div class="hide-md-lg">
                <p>Or sign in manually:</p>
              </div>
              <input type="text" name="username" placeholder="Username" required>
              <br><br>
              <input type="password" name="password" placeholder="Password" required>
              <br><br>
              <input type="submit" value="Login">
              <br><br>
              <div class="col">
                  <a href="#" style="color:white" class="btn">Forgot password?</a>
              </div>
            </div>

          </div>
        </div>
      </div>
    </div>
  <script>
    function openEvent(evt, evtName) {
      var i, tabcontent, tablinks;
      tabcontent = document.getElementsByClassName("tabcontent");
      for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
      }
      tablinks = document.getElementsByClassName("tablinks");
      for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
      }
      document.getElementById(evtName).style.display = "block";
      evt.currentTarget.className += " active";
    }

    // Get the element with id="defaultOpen" and click on it
    document.getElementById("defaultOpen").click();
</script>
</main>

