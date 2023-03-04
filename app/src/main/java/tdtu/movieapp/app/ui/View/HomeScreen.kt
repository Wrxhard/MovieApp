package tdtu.movieapp.app.ui.View

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Response
import tdtu.movieapp.app.R
import tdtu.movieapp.app.data.remote.MovieList
import tdtu.movieapp.app.databinding.HomescreenBinding
import tdtu.movieapp.app.ui.Adapter.CategoryAdapter
import tdtu.movieapp.app.ui.Adapter.ParentAdapter
import tdtu.movieapp.app.ui.Model.Category
import tdtu.movieapp.app.ui.Model.ListFilmModel
import tdtu.movieapp.app.ui.Model.*
import tdtu.movieapp.app.ui.ViewModel.HomeScreenViewModel
import tdtu.movieapp.app.ui.ViewModel.SectionModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeScreen : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: HomescreenBinding? = null
    private lateinit var viewModel:HomeScreenViewModel
    private lateinit var responeData: LiveData<Response<MovieList>>
    private val binding: HomescreenBinding
        get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Bind view
        _binding=DataBindingUtil.inflate(inflater,R.layout.homescreen,container,false)
        //Bind ViewModel
        viewModel= ViewModelProvider(this)[HomeScreenViewModel::class.java]
        responeData=viewModel.getTrending()
        responeData.observe(viewLifecycleOwner) {
            val movieList = it.body()?.results?.listIterator()

            if (movieList != null) {
                val categoryList= mutableListOf<Category>()
                val trending= mutableListOf<ListFilmModel>()
                val parentList= mutableListOf<SectionModel>()
                while (movieList.hasNext()) {
                    val movieItem = movieList.next()
                    trending.add(ListFilmModel("/original${movieItem.poster_path}",movieItem.title))

                }
                //set up recycle view
                parentList.add(SectionModel("Treding",trending))
                binding.FilmSection.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                binding.categoryList.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                val parentAdapter=ParentAdapter(parentList) {
                    findNavController().navigate(R.id.action_homescreen_to_frag32)
                }
                val categoryAdapter=CategoryAdapter(categoryList)
                binding.categoryList.adapter=categoryAdapter
                categoryAdapter.notifyDataSetChanged()
                binding.FilmSection.adapter=parentAdapter
                parentAdapter.notifyDataSetChanged()
            }
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeScreen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}