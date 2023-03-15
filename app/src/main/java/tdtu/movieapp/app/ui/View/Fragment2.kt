package tdtu.movieapp.app.ui.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tdtu.movieapp.app.databinding.ProfilescreenBinding

class Fragment2 : Fragment() {
    private var _binding: ProfilescreenBinding? = null
    private val binding: ProfilescreenBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
         _binding= ProfilescreenBinding.inflate(inflater,container,false)
        return binding.root
    }

}