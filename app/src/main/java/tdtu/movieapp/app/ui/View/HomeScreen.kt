package tdtu.movieapp.app.ui.View

import tdtu.movieapp.app.ui.ViewModel.FilmViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import tdtu.movieapp.app.R
import tdtu.movieapp.app.databinding.HomescreenBinding
import tdtu.movieapp.app.ui.Adapter.CategoryAdapter
import tdtu.movieapp.app.ui.Adapter.ParentAdapter
import tdtu.movieapp.app.ui.ViewModel.*
import tdtu.movieapp.app.ui.ViewModel.ChildModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeScreen : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var filmViewModel: FilmViewModel
    private var _binding: HomescreenBinding? = null
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
    ): View? {
        // Inflate the layout for this fragment
        val categoryList= mutableListOf<Category>()
        val favoriteList= mutableListOf<ChildModel>()
        val recentlyWatchList= mutableListOf<ChildModel>()
        val parentList= mutableListOf<ParentModel>()
        val recentlyWatchList2= mutableListOf<ChildModel>()
        categoryList.add(Category("ACTION"))
        categoryList.add(Category("DRAMA"))
        categoryList.add(Category("GAMESHOW"))
        categoryList.add(Category("ROMANCE"))
        categoryList.add(Category("COMEDY"))
        favoriteList.add(ChildModel(R.drawable.movieholder,"Thor: Love and thunder"))
        favoriteList.add(ChildModel(R.drawable.movieholder,"Thor: Love and thunder"))
        favoriteList.add(ChildModel(R.drawable.movieholder,"Thor: Love and thunder"))
        favoriteList.add(ChildModel(R.drawable.movieholder,"Thor: Love and thunder"))
        recentlyWatchList.add(ChildModel(R.drawable.movieholder,"Thor: Love and thunder"))
        recentlyWatchList.add(ChildModel(R.drawable.movieholder,"Thor: Love and thunder"))
        recentlyWatchList.add(ChildModel(R.drawable.movieholder,"Thor: Love and thunder"))
        recentlyWatchList.add(ChildModel(R.drawable.movieholder,"Thor: Love and thunder"))
        recentlyWatchList2.add(ChildModel(R.drawable.movieholder,"Thor: Love and thunder"))
        recentlyWatchList2.add(ChildModel(R.drawable.movieholder,"Thor: Love and thunder"))
        recentlyWatchList2.add(ChildModel(R.drawable.movieholder,"Thor: Love and thunder"))
        recentlyWatchList2.add(ChildModel(R.drawable.movieholder,"Thor: Love and thunder"))
        parentList.add(ParentModel("Lastest movie",favoriteList))
        parentList.add(ParentModel("Favourite movie",recentlyWatchList))
        parentList.add(ParentModel("Most rated",recentlyWatchList2))

        _binding=DataBindingUtil.inflate(inflater,R.layout.homescreen,container,false)
        binding.FilmSection.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.categoryList.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val parentAdapter=ParentAdapter(parentList,{
            findNavController().navigate(R.id.action_homescreen_to_frag32)
        })
        val categoryAdapter=CategoryAdapter(categoryList)
        binding.categoryList.adapter=categoryAdapter
        binding.FilmSection.adapter=parentAdapter
        categoryAdapter.notifyDataSetChanged()
        parentAdapter.notifyDataSetChanged()

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