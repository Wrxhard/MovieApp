package tdtu.movieapp.app.ui.View

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import tdtu.movieapp.app.R
import tdtu.movieapp.app.data.model.Movies.Category
import tdtu.movieapp.app.databinding.HomescreenBinding
import tdtu.movieapp.app.ui.Adapter.CategoryAdapter
import tdtu.movieapp.app.ui.Adapter.ParentAdapter
import tdtu.movieapp.app.ui.ViewModel.MainActivityViewModel
import tdtu.movieapp.app.ui.ViewModel.SectionModel

class HomeScreen : Fragment() {
    private var _binding: HomescreenBinding? = null
    private val binding: HomescreenBinding
        get() = _binding!!
    private  lateinit var mViewModel:MainActivityViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().requestedOrientation= ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Bind view
        _binding=DataBindingUtil.inflate(inflater,R.layout.homescreen,container,false)
        //Bind ViewModel
        mViewModel=activity?.let { ViewModelProvider(it)[MainActivityViewModel::class.java] }!!
        //Set up Section
        setupSection(mViewModel,binding.FilmSection,binding.Shimmer)
        //Set up category
        setupCategory(binding.categoryList)
        //Search Function
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(txt: String?): Boolean {
                if (txt!=null)
                {
                       mViewModel.clearSearch()
                       mViewModel.filterSearch(txt)
                       val action=HomeScreenDirections.actionHomescreenToSearchScreen(txt,"userinput")
                       findNavController().navigate(action)
                }
                binding.searchView.setQuery("", false)
                binding.searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(txt: String?): Boolean {
                return false
            }

        })
        return binding.root
    }

    private fun setupSection(mViewModel: MainActivityViewModel,filmSection:RecyclerView,shimmerFrameLayout: ShimmerFrameLayout)
    {
        val detail=mutableListOf<String>()
        val sectionlist = mutableListOf<SectionModel>()
        filmSection.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        val parentAdapter = ParentAdapter(sectionlist) { movie ->
            movie.movie_genres.forEach {
                detail.add(it.genre)
            }
            val action=HomeScreenDirections.actionHomescreenToDetailScreen(movie.poster_path,detail.toTypedArray(),movie.title,movie.overview,movie.score,movie.trailer)
            findNavController().navigate(action)
        }
        filmSection.adapter = parentAdapter
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                val jobs = listOf(
                    async {
                        mViewModel.movies.collectLatest { event ->
                            when (event) {
                                is MainActivityViewModel.Event.Success -> {
                                    shimmerFrameLayout.stopShimmer()
                                    shimmerFrameLayout.hideShimmer()
                                    shimmerFrameLayout.visibility = View.GONE
                                    sectionlist.add(SectionModel("Popular", event.result))
                                    mViewModel.clearFullList()
                                    mViewModel.addFullList(event.result)
                                    if (sectionlist.size >= 1) {
                                        parentAdapter.notifyItemInserted(sectionlist.size - 1)
                                    } else {
                                        parentAdapter.notifyItemInserted(0)
                                    }
                                }
                                is MainActivityViewModel.Event.Failure -> {
                                    async {
                                        mViewModel.getPopular(1)
                                    }.await()

                                }
                                else -> Unit
                            }
                        }
                    },
                    async {
                        mViewModel.movies2.collectLatest { event ->
                            when (event) {
                                is MainActivityViewModel.Event.Success -> {
                                    /*Because two function run and also update the list at the same time so the order of item
                                      may not be correct so we need to delay it */
                                    delay(10)
                                    sectionlist.add(SectionModel("Trending", event.result))
                                    mViewModel.addFullList(event.result)
                                    if (sectionlist.size >= 1) {
                                        parentAdapter.notifyItemInserted(sectionlist.size - 1)
                                    } else {
                                        parentAdapter.notifyItemInserted(0)
                                    }
                                }
                                is MainActivityViewModel.Event.Failure -> {
                                    async {
                                        mViewModel.getTrending(2)
                                    }.await()
                                    mViewModel.cancel()

                                }
                                else -> Unit
                            }
                        }
                    }
                )
                jobs.awaitAll()
            }
        }


    }
    private fun setupCategory(category: RecyclerView)
    {
        category.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val categoryList = listOf(
            Category("Phim Nhạc"),
            Category("Phim Lãng Mạn"),
            Category("Phim Gia Đình"),
            Category("Phim Chiến Tranh"),
            Category("phiêu lưu"),
            Category("Kids"),
            Category("News"),
            Category("Reality"),
            Category("Sci-Fi & Fantasy"),
            Category("Soap"),
            Category("Talk"),
            Category("War & Politics"),
            Category("Truyền Hình"),
            Category("Phim Phiêu Lưu"),
            Category("Phim Giả Tưởng"),
            Category("Phim Hoạt Hình"),
            Category("Phim Chính Kịch"),
            Category("Phim Kinh Dị"),
            Category("Phim Hành Động"),
            Category("Phim Hài"),
            Category("Phim Lịch Sử"),
            Category("Phim Miền Tây"),
            Category("Phim Gây Cấn"),
            Category("Phim Hình Sự"),
            Category("Phim Viễn Tưởng"),
        )
        val categoryAdapter= CategoryAdapter(categoryList){
            mViewModel.clearSearch()
            mViewModel.filterSearch(it.genre)
            val action=HomeScreenDirections.actionHomescreenToSearchScreen(it.genre,"category")
            findNavController().navigate(action)
        }
        category.adapter=categoryAdapter

    }
}