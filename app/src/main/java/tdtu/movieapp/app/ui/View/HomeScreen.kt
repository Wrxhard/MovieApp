package tdtu.movieapp.app.ui.View

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import tdtu.movieapp.app.R
import tdtu.movieapp.app.databinding.HomescreenBinding
import tdtu.movieapp.app.ui.Adapter.CategoryAdapter
import tdtu.movieapp.app.ui.Adapter.ParentAdapter
import tdtu.movieapp.app.ui.Model.Category
import tdtu.movieapp.app.ui.Model.*
import tdtu.movieapp.app.ui.ViewModel.MainActivityViewModel
import tdtu.movieapp.app.ui.ViewModel.SectionModel

class HomeScreen : Fragment() {
    private var _binding: HomescreenBinding? = null
    private lateinit var viewModel:MainActivityViewModel
    private val binding: HomescreenBinding
        get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Bind view
        _binding=DataBindingUtil.inflate(inflater,R.layout.homescreen,container,false)
        //Bind ViewModel
        viewModel= activity?.let { ViewModelProvider(it).get(MainActivityViewModel::class.java) }!!
        //Set up Section
        val parentList = mutableListOf<SectionModel>()
        binding.FilmSection.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        viewModel.listTrending.observe(viewLifecycleOwner) {
            val temp=it
            if (!parentList.contains(SectionModel("Trending",temp))) {
                parentList.add(SectionModel("Trending", temp))
                parentList.add(SectionModel("Trending", temp))
                parentList.add(SectionModel("Trending", temp))
                parentList.add(SectionModel("Trending", temp))

                val parentAdapter = ParentAdapter(parentList) {
                    val action=HomeScreenDirections.actionHomescreenToFrag32(it.image)
                    findNavController().navigate(action)
                }
                binding.FilmSection.adapter = parentAdapter
                parentAdapter.notifyDataSetChanged()
            }
            else{
                val temp2=parentList.find {
                    (it.title=="Trending")
                }
                if (temp2 != null) {
                    temp2.childlist=temp
                }
                val parentAdapter = ParentAdapter(parentList) {
                    val action=HomeScreenDirections.actionHomescreenToFrag32(it.image)
                    findNavController().navigate(action)
                }
                binding.FilmSection.adapter = parentAdapter
                parentAdapter.notifyDataSetChanged()
            }

        }
        binding.categoryList.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val categoryList = mutableListOf<Category>()
        val categoryAdapter= categoryList.let { CategoryAdapter(it) }
        binding.categoryList.adapter=categoryAdapter
        categoryAdapter.notifyDataSetChanged()

        return binding.root
    }
}