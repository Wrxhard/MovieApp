package tdtu.movieapp.app.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import tdtu.movieapp.app.data.DataRepo
import tdtu.movieapp.app.data.local.Film
import kotlinx.coroutines.launch

class FilmViewModel(private val repository: tdtu.movieapp.app.data.DataRepo): ViewModel() {
    val films=repository.films
    fun insert(film: tdtu.movieapp.app.data.local.Film){
        viewModelScope.launch {
            repository.insertFilm(film)
        }
    }
    fun delete(film: tdtu.movieapp.app.data.local.Film){
        viewModelScope.launch {
            repository.deleteFilm(film)
        }
    }
    fun update(film: tdtu.movieapp.app.data.local.Film){
        viewModelScope.launch {
            repository.updateFilm(film)
        }
    }
    fun clearAll(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}