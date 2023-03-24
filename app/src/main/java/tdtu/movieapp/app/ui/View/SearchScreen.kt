package tdtu.movieapp.app.ui.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tdtu.movieapp.app.databinding.SearchscreenBinding

class SearchScreen : Fragment() {
    private var _binding: SearchscreenBinding? = null
    private val binding: SearchscreenBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
         _binding= SearchscreenBinding.inflate(inflater,container,false)
        return binding.root
    }

}