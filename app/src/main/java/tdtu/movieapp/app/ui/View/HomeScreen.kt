package tdtu.movieapp.app.ui.View

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
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import tdtu.movieapp.app.R
import tdtu.movieapp.app.databinding.HomescreenBinding
import tdtu.movieapp.app.ui.Adapter.CategoryAdapter
import tdtu.movieapp.app.ui.Adapter.ParentAdapter
import tdtu.movieapp.app.ui.Model.Category
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Bind view
        _binding=DataBindingUtil.inflate(inflater,R.layout.homescreen,container,false)
        //Bind ViewModel
        mViewModel=activity?.let { ViewModelProvider(it)[MainActivityViewModel::class.java] }!!
        getData(mViewModel)
        //Set up Section
        setupSection(mViewModel,binding.FilmSection,binding.Shimmer)
        //Set up category
        setupCategory(binding.categoryList)
        /*binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(txt: String?): Boolean {
                if (txt!=null)
                {
                    val action=HomeScreenDirections.actionHomescreenToSearchScreen(txt)
                    findNavController().navigate(action)
                }
                return false
            }

            override fun onQueryTextChange(txt: String?): Boolean {
                return false
            }

        })*/
        return binding.root
    }

    private fun getData(mViewModel: MainActivityViewModel){
        lifecycleScope.launchWhenStarted {
            val jobs= listOf(
                async {
                    mViewModel.getPopular(1)
                },
                async {
                    mViewModel.getTrending(2)
                },
            )
            jobs.awaitAll()
            mViewModel.cancel()
            this.cancel()
        }
    }
    private fun setupSection(mViewModel: MainActivityViewModel,filmSection:RecyclerView,shimmerFrameLayout: ShimmerFrameLayout)
    {
        val detail=mutableListOf<String>()
        detail.add("Action")
        detail.add("Adventure")

        val sectionlist = mutableListOf<SectionModel>()
        filmSection.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        val parentAdapter = ParentAdapter(sectionlist) {
            val action=HomeScreenDirections.actionHomescreenToDetailScreen(it.poster_path,detail.toTypedArray(),it.title,it.overview)
            findNavController().navigate(action)
        }
        filmSection.adapter = parentAdapter
        lifecycleScope.launchWhenStarted {
            val jobs= listOf(
                async {
                    mViewModel.movies.collect{ event ->
                    when(event)
                    {
                        is MainActivityViewModel.Event.Success ->
                        {
                            shimmerFrameLayout.stopShimmer()
                            shimmerFrameLayout.hideShimmer()
                            shimmerFrameLayout.visibility=View.GONE
                            sectionlist.add(SectionModel("Popular",event.result))
                            if (sectionlist.size>=1)
                            {
                                parentAdapter.notifyItemInserted(sectionlist.size-1)
                            }
                            else{
                                parentAdapter.notifyItemInserted(0)
                            }
                        }
                        is MainActivityViewModel.Event.Failure ->
                        {
                            async {
                                mViewModel.getPopular(1)
                            }.await()

                        }
                        else -> Unit
                    }
                }},
                async {
                    mViewModel.movies2.collect{ event ->
                    when(event)
                    {
                        is MainActivityViewModel.Event.Success ->
                        {
                            /*Because two function run and also update the list at the same time so the order of item
                              may not be correct so we need to delay it */
                            delay(10)
                            sectionlist.add(SectionModel("Trending",event.result))
                            if (sectionlist.size>=1)
                            {
                                parentAdapter.notifyItemInserted(sectionlist.size-1)
                            }
                            else{
                                parentAdapter.notifyItemInserted(0)
                            }
                        }
                        is MainActivityViewModel.Event.Failure ->
                        {
                            async {
                                    mViewModel.getTrending(2)
                            }.await()

                            mViewModel.cancel()
                        }
                        else -> Unit
                    }
                }}
            )
            jobs.awaitAll()
        }


    }
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

    }
}