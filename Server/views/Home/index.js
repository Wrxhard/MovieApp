
// lấy dữ liệu 5 phim đặc biệt cho vào carousel
$(async function () {

  let carousel = $("#carouselCaptions .carousel-inner")

  function renderGenres(listGenre) {
    let html = ""
    listGenre.forEach(function (genre) {
      genreName = ""
      if (genre.name.includes("Phim")) {
        genreName = genre.name.replace("Phim ", "")
      }
      html += `<div class="rounded-pill text-dark genre-block me-2 mb-1 d-flex align-items-center justify-content-center">${genreName}</div>`
    })
    return html
  }

  $.get(`/`, { getSpecialMovie: true }, function (data) {
    data = JSON.parse(data);
    if (data.status) {
      films = data.data
      films.forEach(function (film) {

        let backdrop = "https://image.tmdb.org/t/p/original" + film.backdrop_path
        let thumbnail = `https://img.youtube.com/vi/${film.trailer_key}/maxresdefault.jpg`

        html = `
                <div class="carousel-item position-relative align-items-center align-items-center">
                  <img src="${backdrop}" class="slideBackdrop d-1lock w-100 position-absolute" alt="...">
                  <div class="informationFilm flex-row d-flex align-items-end justify-content-around carousel-caption position-absolute">
                    <div class="blockLeft col-lg-5">
                      <div class="mainInfo d-flex flex-column w-100">
                        <div class="infor d-flex w-100 justify-content-between">
                          <h5 class="filmName text-start">
                            ${film.title.toUpperCase()}
                          </h5>
                        </div>
                        <div class="mb-4 genreGroup d-flex flex-ow align-items-end flex-wrap">
                          ${renderGenres(film.genres)}
                        </div>
                        <div class="mb-3 buttonGroup d-flex flex-row">
                          <button type="button" class="me-2 rounded-pill btn btn-watch">
                            <i class="fa-solid fa-play text-light"></i>
                            Xem ngay
                          </button>
                          <button type="button" class="me-2 btn rounded-pill btn-inf">
                            <i class="fa-solid fa-circle-info"></i>
                            Chi tiết
                          </button>
                        </div>
                      </div>
                      <div class="overview">
                        <p class="">${film.overview}</p>
                      </div>
                    </div>
                    <div class="blockRight col-lg-5 d-flex flex-column justify-content-end align-items-end">
                      <div class="markFilm w-100 g-0 h-100 mb-2 d-flex flex-row align-items-center justify-content-start">
                        <div class="scoreBlock d-flex flex-row me-3">
                          <h5 class="score h-100">${film.score}/10 </h5>
                          <label class="h-100 labelMark rounded ms-2">IMDb</label>
                        </div>
                        <div class="scoreBlock d-flex flex-row me-3">
                          <h5 class="score h-100">4.3/5</h5>
                          <label class="h-100 labelMark_user rounded ms-2">User score</label>
                        </div>
                      </div>
                      <div class="trailer rounded-pill d-flex flex-column align-items-start">
                        <h5 class="titleTrailer">Trailer:</h5>
                        <div class="trailerImg w-100 d-flex position-relative">
                          <div class="overlay visually-hidden position-absolute w-100 h-100">
                            <iframe id="trailerVideo" class="w-100 h-100" src="https://www.youtube.com/embed/${film.trailer_key}" title="YouTube vide text-secondaryo Xem ngayer" frameborder="0" allow="accelerometer; au text-secondarytoXem ngay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
                          </div>
                          <img src="${thumbnail}" alt="" class="thumbnail w-100 rounded">
                          <button class="btn position-absolute top-50 start-50 translate-middle trailerBtn rounded-pill">
                            <i class="fa-solid fa-play text-light"></i>
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>
              </div>
                `

        carousel.append(html);
        carousel.children(".carousel-item:first-child").addClass("active");

        $(".trailerBtn").click(function (e) {
          let trailer = $(".overlay")
          trailer.removeClass("visually-hidden")
          $(".trailerBtn").addClass("visually-hidden")
        })
      })
    }
  })


})

$(function () {

  $.get(`/`, { getNewestMovie: true }, function (data) {
    console.log(JSON.parse(data))
    data = JSON.parse(data)
    if (data.status) {
      let listFimls = $(".listFilm")
      data.data.forEach(function (film, index) {
        let poster = "https://image.tmdb.org/t/p/w500" + film.poster_path

        let release_date = new Date(film.release_date)
        
        let html = `
          <div class="col-lg-2 text-light">
            <div class="bg-transparent card me-1 g-0 w-100" style="width: 18rem;">
              <div class="watchLink position-relative">
                <img src="${poster}" class="card-img-top rounded watchThumbnail" alt="...">
                <h3 class="position-absolute top-50 start-50 translate-middle text-light">Xem ngay</h3>
              </div>
              <div class="card-body">
                <h5 class="card-title linkToDetail">${film.title}</h5>
                <div class="card-subInf d-flex justify-content-between">
                  <p class="card-text text-secondary">${release_date.getFullYear()}</p>
                  <div class="favouriteTotal d-flex">
                    <i class="fa-solid fa-heart me-2"></i>
                    <p>lượt thích:</p>
                    <b>50</b>
                  </div>
                </div>
              </div>
            </div>
          </div>
        `
        listFimls.append(html)

      })
    }
  })
})
