package tdtu.movieapp.app.ui.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tdtu.movieapp.app.R
import tdtu.movieapp.app.databinding.SearchscreenBinding
import tdtu.movieapp.app.ui.Adapter.SearchAdapter
import tdtu.movieapp.app.ui.ViewModel.MainActivityViewModel

class SearchScreen : Fragment() {
    private var _binding: SearchscreenBinding? = null
    private val binding: SearchscreenBinding
        get() = _binding!!
    private  lateinit var mViewModel:MainActivityViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
         _binding= SearchscreenBinding.inflate(inflater,container,false)
        mViewModel=activity?.let { ViewModelProvider(it)[MainActivityViewModel::class.java] }!!
        setUpRecyclerView()
        setOnClick()

        return binding.root
    }
    private fun setOnClick()
    {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(txt: String?): Boolean {
                txt?.let {
                    mViewModel.clearSearch()
                    mViewModel.filterSearch(it)
                }
                binding.searchView.setQuery("", false)
                binding.searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(txt: String?): Boolean {
                return false
            }

        })
        binding.backtomain.setOnClickListener {
            findNavController().popBackStack(R.id.homescreen,false)
        }
    }
    private fun setUpRecyclerView()
    {
        val detail=mutableListOf<String>()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                mViewModel.searchList.collectLatest { list ->
                    val adapter=SearchAdapter(list){ movie ->
                        movie.movie_genres.forEach {
                            detail.add(it.genre)
                        }
                        val action=SearchScreenDirections.actionSearchScreenToDetailscreen(movie.id,movie.poster_path,detail.toTypedArray(),movie.title,movie.overview,movie.score,movie.trailer)
                        findNavController().navigate(action)
                    }
                    binding.searchList.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
                    binding.searchList.adapter=adapter
                }
            }
        }

    }

}