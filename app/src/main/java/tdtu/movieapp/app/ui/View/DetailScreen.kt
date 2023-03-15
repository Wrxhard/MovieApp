package tdtu.movieapp.app.ui.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import tdtu.movieapp.app.R
import tdtu.movieapp.app.databinding.DetailscreenBinding

class DetailScreen : Fragment() {
    private var _binding: DetailscreenBinding? = null
    private val binding: DetailscreenBinding
        get() = _binding!!
    val args: DetailScreenArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val value=args.poster
        _binding= DataBindingUtil.inflate(inflater,R.layout.detailscreen,container,false)
        val picUrl ="https://image.tmdb.org/t/p/original${value}"
        Glide.with(this)
            .load(picUrl)
            .placeholder(R.drawable.placeholder)
            .fitCenter()
            .into(binding.imageView)
        binding.backtomain.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }
}