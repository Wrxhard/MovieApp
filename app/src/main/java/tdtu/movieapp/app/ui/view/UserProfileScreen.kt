package tdtu.movieapp.app.ui.view

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tdtu.movieapp.app.R
import tdtu.movieapp.app.data.model.Movies.Movie
import tdtu.movieapp.app.data.model.Movies.RecentlyMovie
import tdtu.movieapp.app.databinding.UserProfileScreenBinding
import tdtu.movieapp.app.ui.adapter.ParentAdapter
import tdtu.movieapp.app.ui.viewModel.MainActivityViewModel
import tdtu.movieapp.app.ui.viewModel.SectionModel

class UserProfileScreen : Fragment() {
    private var _binding: UserProfileScreenBinding? = null
    private val binding: UserProfileScreenBinding
        get() = _binding!!
    private lateinit var mViewModel: MainActivityViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().requestedOrientation= ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= DataBindingUtil.inflate(inflater,R.layout.user_profile_screen,container,false)
        //bind view model
        mViewModel=activity?.let { ViewModelProvider(it)[MainActivityViewModel::class.java] }!!

        //Set up section
        setUpSection()

        return binding.root
    }
    private fun setUpSection()
    {
        var recentList= mutableListOf<RecentlyMovie>()
        val sectionlist = mutableListOf<SectionModel>()
        binding.profileusername.text= requireActivity().intent.getStringExtra("name")
        binding.profileid.text="Id: #"+requireActivity().intent.getStringExtra("id")
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED)
            {
                mViewModel.recentlyWatch.collectLatest { event ->
                    when(event){
                        is MainActivityViewModel.Event.Success -> {
                            recentList= event.result as MutableList<RecentlyMovie>
                            val list= mutableListOf<Movie>()
                            event.result.forEach {
                                list.add(
                                    Movie(it.id,it.poster_path,it.title,"","","","","",0f,it.trailer,
                                        emptyList()
                                    )
                                )
                            }
                            sectionlist.add(SectionModel("Recently Watch",list))
                        }
                        else -> Unit
                    }
                }
            }
        }
        val adapter=ParentAdapter(sectionlist){ movie->
            val recent=recentList.find {
                (it.id==movie.id)
            }
            val intent= Intent(requireActivity(),PlayMovieScreen::class.java).apply {
                putExtra("view_time",recent!!.view_time)
                putExtra("id",movie.id)
                putExtra("title",movie.title)
                putExtra("poster",movie.poster_path)
                putExtra("video_url",movie.trailer)
            }
            startActivity(intent)
        }
        binding.FilmSection.adapter=adapter
        binding.FilmSection.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
    }
}