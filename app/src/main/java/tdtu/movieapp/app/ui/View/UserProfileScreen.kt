package tdtu.movieapp.app.ui.View

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import tdtu.movieapp.app.R
import tdtu.movieapp.app.databinding.UserProfileScreenBinding
import tdtu.movieapp.app.ui.Adapter.ParentAdapter
import tdtu.movieapp.app.ui.ViewModel.MainActivityViewModel
import tdtu.movieapp.app.ui.ViewModel.SectionModel

class UserProfileScreen : Fragment() {
    private var _binding: UserProfileScreenBinding? = null
    private val binding: UserProfileScreenBinding
        get() = _binding!!
    private  lateinit var mViewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().requestedOrientation= ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= DataBindingUtil.inflate(inflater,R.layout.user_profile_screen,container,false)
        mViewModel=activity?.let { ViewModelProvider(it)[MainActivityViewModel::class.java] }!!

        val sectionlist = mutableListOf<SectionModel>()
        val detail=mutableListOf<String>()
        detail.add("Action")
        detail.add("Adventure")

        binding.profileusername.text= requireActivity().intent.getStringExtra("name")
        binding.profileid.text="Id: #"+requireActivity().intent.getStringExtra("id")
        if (mViewModel.getRecentlyWatch().isNotEmpty())
        {
            sectionlist.add(SectionModel("Recently Watch",mViewModel.getRecentlyWatch()))
        }
        if (mViewModel.getFavourite().isNotEmpty())
        {
            sectionlist.add(SectionModel("Favourites",mViewModel.getFavourite()))
        }
        val adapter=ParentAdapter(sectionlist){
            val intent= Intent(requireActivity(),PlayMovieScreen::class.java)
            intent.putExtra("video_url",it.trailer)
            startActivity(intent)
        }
        binding.FilmSection.adapter=adapter
        binding.FilmSection.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        return binding.root
    }
}