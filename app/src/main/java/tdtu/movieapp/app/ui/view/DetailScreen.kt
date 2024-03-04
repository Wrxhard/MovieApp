package tdtu.movieapp.app.ui.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import tdtu.movieapp.app.R
import tdtu.movieapp.app.data.model.Movies.Category
import tdtu.movieapp.app.databinding.DetailscreenBinding
import tdtu.movieapp.app.ui.adapter.CategoryAdapter
import tdtu.movieapp.app.ui.viewModel.DetailScreenViewModel
import tdtu.movieapp.app.ui.viewModel.MainActivityViewModel

class DetailScreen : Fragment() {
    private lateinit var detailScreenViewModel: DetailScreenViewModel
    private var _binding: DetailscreenBinding? = null
    private val binding: DetailscreenBinding
        get() = _binding!!
    private  lateinit var mViewModel: MainActivityViewModel
    //get data from main screen
    private val args: DetailScreenArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // bind view
        detailScreenViewModel= ViewModelProvider(this)[DetailScreenViewModel::class.java]
        _binding= DataBindingUtil.inflate(inflater,R.layout.detailscreen,container,false)
        mViewModel=activity?.let { ViewModelProvider(it)[MainActivityViewModel::class.java] }!!
        //set poster and titless
        setInfo(binding.imageView,binding.detailTitle,binding.detaildesc,binding.imdbscore,binding.playbtn,binding.detaildesc)
        showtxt()
        //set back btn behavior
        setBackBtn(binding.backtomain)
        //set up catefory of film
        setupDetailCategory(binding.MovieCategory)

        return binding.root
    }

    private fun showtxt(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED)
            {
                detailScreenViewModel.showTxt.collect { ticket ->
                    if (!ticket)
                    {
                        binding.detaildesc.ellipsize=TextUtils.TruncateAt.END
                        binding.detaildesc.maxLines= 2
                    }
                    else
                    {
                        binding.detaildesc.ellipsize=null
                        binding.detaildesc.maxLines= Int.MAX_VALUE
                    }
                }
            }
        }
    }
    private fun setInfo(poster:ImageView,title:TextView,overview:TextView,IMDBScore: TextView,playbtn:ImageButton, detaildesc:TextView)
    {
        title.text=args.title
        overview.text=args.overview
        IMDBScore.text="IMDB "+args.score.toString()
        val value=args.poster
        val picUrl ="https://image.tmdb.org/t/p/original${value}"
        Glide.with(this)
            .load(picUrl)
            .placeholder(R.drawable.placeholder)
            .fitCenter()
            .into(poster)
        playbtn.setOnClickListener {
            val intent=Intent(requireActivity(),PlayMovieScreen::class.java).apply {
                putExtra("id",args.id)
                putExtra("title",args.title)
                putExtra("poster",args.poster)
                putExtra("video_url",args.videoUrl)
            }
            startActivity(intent)
        }
        poster.setOnClickListener {
            val intent=Intent(requireActivity(),PlayMovieScreen::class.java).apply {
                putExtra("id",args.id)
                putExtra("title",args.title)
                putExtra("poster",args.poster)
                putExtra("video_url",args.videoUrl)
            }
            startActivity(intent)
        }
        //show full desc
        detaildesc.setOnClickListener {
            detailScreenViewModel.setShowTxt()
        }

    }
    private fun setBackBtn(backBtn: ImageButton)
    {
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun setupDetailCategory(detailcategory:RecyclerView)
    {
        val categoryList = mutableListOf<Category>()
        args.detailCategory.forEach{
            categoryList.add(Category(it))
        }
        val CategoryAdapter= CategoryAdapter(categoryList){
            mViewModel.clearSearch()
            mViewModel.filterSearch(it.genre)
            val action=DetailScreenDirections.actionDetailscreenToSearchScreen(it.genre,"category")
            findNavController().navigate(action)
        }
        detailcategory.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        detailcategory.adapter=CategoryAdapter
    }
}