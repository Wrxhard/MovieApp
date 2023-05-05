package tdtu.movieapp.app.ui.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.util.query
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tdtu.movieapp.app.data.model.Movies.Movie
import tdtu.movieapp.app.databinding.SearchscreenBinding
import tdtu.movieapp.app.ui.Adapter.SearchAdapter
import tdtu.movieapp.app.ui.ViewModel.MainActivityViewModel

class SearchScreen : Fragment() {
    private var _binding: SearchscreenBinding? = null
    private val binding: SearchscreenBinding
        get() = _binding!!
    private  lateinit var mViewModel:MainActivityViewModel
    private val args: SearchScreenArgs by navArgs()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
    fun setOnClick()
    {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(txt: String?): Boolean {
                if (txt!=null)
                {
                    mViewModel.clearSearch()
                    mViewModel.filterSearch(txt)
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
            findNavController().popBackStack()
        }
    }
    fun setUpRecyclerView()
    {
        val detail=mutableListOf<String>()
        detail.add("Action")
        detail.add("Adventure")
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                mViewModel.searchList.collectLatest { list ->
                    val adapter=SearchAdapter(list){
                        val action=SearchScreenDirections.actionSearchScreenToDetailscreen(it.poster_path,detail.toTypedArray(),it.title,it.overview,it.score,it.trailer)
                        findNavController().navigate(action)
                    }
                    binding.searchList.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
                    binding.searchList.adapter=adapter
                }
            }
        }

    }

}