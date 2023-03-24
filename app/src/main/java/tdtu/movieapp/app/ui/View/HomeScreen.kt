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
import androidx.recyclerview.widget.RecyclerView
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
        mViewModel=activity?.let { ViewModelProvider(it)[MainActivityViewModel::class.java] }!!
        //Set up Section
        setupSection(binding.FilmSection)
        //Set up category
        setupCategory(binding.categoryList)
        return binding.root
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun setupSection(filmSection:RecyclerView)
    {
        val sectionlist = mutableListOf<SectionModel>()
        filmSection.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        lifecycleScope.launchWhenStarted {
            mViewModel.movies.collectLatest{event ->
                when(event)
                {
                    is MainActivityViewModel.Event.Success ->
                    {
                        val detail=mutableListOf<String>()
                        detail.add("Action")
                        detail.add("Adventure")
                        sectionlist.add(SectionModel("Popular",event.result))
                        sectionlist.add(SectionModel("Popular",event.result))
                        sectionlist.add(SectionModel("Popular",event.result))
                        val parentAdapter = ParentAdapter(sectionlist) {
                            val action=HomeScreenDirections.actionHomescreenToDetailScreen(it.poster_path,detail.toTypedArray(),it.title,it.overview)
                            findNavController().navigate(action)
                        }
                        filmSection.adapter = parentAdapter
                        parentAdapter.notifyDataSetChanged()
                    }
                    is MainActivityViewModel.Event.Failure ->
                    {
                    }
                    else -> Unit
                }
            }
        }

    }
    @SuppressLint("NotifyDataSetChanged")
    private fun setupCategory(category: RecyclerView)
    {
        category.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val categoryList = mutableListOf<Category>()
        categoryList.add(Category("Action"))
        categoryList.add(Category("Action"))
        categoryList.add(Category("Action"))
        categoryList.add(Category("Adventure"))
        categoryList.add(Category("Gameshow"))
        val categoryAdapter= CategoryAdapter(categoryList)
        category.adapter=categoryAdapter
        categoryAdapter.notifyDataSetChanged()

    }
}