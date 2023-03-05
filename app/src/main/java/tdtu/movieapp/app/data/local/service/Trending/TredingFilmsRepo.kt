package tdtu.movieapp.app.data.local.service.Trending


import tdtu.movieapp.app.data.model.Treding.TredingMovie

interface TredingFilmsRepo {

    suspend fun getAllTreding():List<TredingMovie>
    suspend fun saveTredingMovie():List<TredingMovie>

}