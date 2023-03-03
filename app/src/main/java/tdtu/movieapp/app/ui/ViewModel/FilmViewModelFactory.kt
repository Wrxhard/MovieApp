package tdtu.movieapp.app.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tdtu.movieapp.app.data.DataRepo

class FilmViewModelFactory(private val respository: tdtu.movieapp.app.data.DataRepo): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FilmViewModel::class.java)) {
                return FilmViewModel(respository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }

}
