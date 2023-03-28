package tdtu.movieapp.app.ui.View

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tdtu.movieapp.app.R
import tdtu.movieapp.app.databinding.DetailscreenBinding
import tdtu.movieapp.app.ui.Adapter.DetailCategoryAdapter
import tdtu.movieapp.app.ui.Model.Category
import tdtu.movieapp.app.ui.ViewModel.DetailScreenViewModel

class DetailScreen : Fragment() {
    private lateinit var detailScreenViewModel: DetailScreenViewModel
    private var _binding: DetailscreenBinding? = null
    private val binding: DetailscreenBinding
        get() = _binding!!
    //get data from main screen
    private val args: DetailScreenArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // bind view
        detailScreenViewModel= ViewModelProvider(this)[DetailScreenViewModel::class.java]
        _binding= DataBindingUtil.inflate(inflater,R.layout.detailscreen,container,false)
        //set poster and titless
        setPosterTitleOverview(binding.imageView,binding.detailTitle,binding.detaildesc)
        //set back btn behavior
        setBackBtn(binding.backtomain)
        //set up catefory of film
        setupDetailCategory(binding.MovieCategory)
        lifecycleScope.launchWhenStarted {
            detailScreenViewModel.showTxt.collect { ticket ->
                if (!ticket)
                {
                    binding.detaildesc.ellipsize=TextUtils.TruncateAt.END
                    binding.detaildesc.maxLines= 2
                }
                else
                {
                    binding.detaildesc.ellipsize=null
                    binding.detaildesc.maxLines= Int.MAX_VALUE
                }
            }
        }
        binding.detaildesc.setOnClickListener {
            detailScreenViewModel.setShowTxt()
        }
        return binding.root
    }

    private fun setPosterTitleOverview(poster:ImageView,title:TextView,overview:TextView)
    {
        title.text=args.title
        val value=args.poster
        overview.text=args.overview
        val picUrl ="https://image.tmdb.org/t/p/original${value}"
        Glide.with(this)
            .load(picUrl)
            .placeholder(R.drawable.placeholder)
            .fitCenter()
            .into(poster)

    }
    private fun setBackBtn(backBtn: ImageButton)
    {
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun setupDetailCategory(detailcategory:RecyclerView)
    {
        val categoryList = mutableListOf<Category>()
        args.detailCategory.forEach{
            categoryList.add(Category(it))
        }
        val DetailCategoryAdapter= DetailCategoryAdapter(categoryList)
        detailcategory.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        detailcategory.adapter=DetailCategoryAdapter
    }
}