package tdtu.movieapp.app.ui.ViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailScreenViewModel: ViewModel() {
    private val _showTxt = MutableStateFlow<Boolean>(false)
    val showTxt = _showTxt.asStateFlow()
    fun setShowTxt()
    {
        _showTxt.value=!_showTxt.value
    }
}