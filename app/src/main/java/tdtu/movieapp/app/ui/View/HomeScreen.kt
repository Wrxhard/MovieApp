package tdtu.movieapp.app.ui.View

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
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
    private  lateinit var mViewModel:MainActivityViewModel
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
        mViewModel=activity?.let { ViewModelProvider(it).get(MainActivityViewModel::class.java) }!!
        //Set up Section
        val parentList = mutableListOf<SectionModel>()
        binding.FilmSection.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        lifecycleScope.launchWhenStarted {
            mViewModel.treding.collectLatest{event ->
                when(event)
                {
                    is MainActivityViewModel.Event.Success ->
                    {
                        val detail=mutableListOf<String>()
                        detail.add("Action")
                        detail.add("Adventure")
                        parentList.add(SectionModel("Trending",event.result))
                        parentList.add(SectionModel("Favourite",event.result))
                        val parentAdapter = ParentAdapter(parentList) {
                            val action=HomeScreenDirections.actionHomescreenToFrag32(it.poster_path,detail.toTypedArray(),it.title)
                            findNavController().navigate(action)
                        }
                        binding.FilmSection.adapter = parentAdapter
                        parentAdapter.notifyDataSetChanged()
                    }
                    is MainActivityViewModel.Event.Failure ->
                    {
                    }
                    else -> Unit
                }
            }
        }
        binding.categoryList.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val categoryList = mutableListOf<Category>()
        categoryList.add(Category("Action"))
        categoryList.add(Category("Action"))
        categoryList.add(Category("Action"))
        categoryList.add(Category("Adventure"))
        categoryList.add(Category("Gameshow"))
        val categoryAdapter= CategoryAdapter(categoryList)
        binding.categoryList.adapter=categoryAdapter
        categoryAdapter.notifyDataSetChanged()

        return binding.root
    }
}