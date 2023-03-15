package tdtu.movieapp.app.ui.View

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import tdtu.movieapp.app.R
import tdtu.movieapp.app.databinding.ActivityMainBinding
import tdtu.movieapp.app.ui.Model.ListFilmModel
import tdtu.movieapp.app.ui.ViewModel.MainActivityViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel:MainActivityViewModel
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystembar()
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel= ViewModelProvider(this)[MainActivityViewModel::class.java]
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.Screen) as NavHostFragment?
        val navController=navHostFragment?.navController
        if (navController != null) {
            binding.bottomNav.selectedItemId=R.id.first_nav_graph
            setupBottomNav(navController,  binding.bottomNav)
        }
        viewModel.getTrending(1).observe(this) {
            val movieList = it.body()?.tredingMovies?.listIterator()
            if (movieList != null) {
                val trending = mutableListOf<ListFilmModel>()
                while (movieList.hasNext()) {
                    val movieItem = movieList.next()
                    trending.add(
                        ListFilmModel(
                            "/original${movieItem.poster_path}",
                            movieItem.title
                        )
                    )
                }
                viewModel.setListTrending(trending)
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.R)
    fun hideSystembar(){
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode = WindowManager
                .LayoutParams
                .LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)

        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())

    }
    //Set up bottom navigation
    fun setupBottomNav(navController: NavController, bottomNavigationView: BottomNavigationView)
    {

        bottomNavigationView.apply {
            navController.let {
                    navController ->
                NavigationUI.setupWithNavController(this,navController)
                setOnItemSelectedListener {
                        item->
                    NavigationUI.onNavDestinationSelected(item, navController)
                    true

                }
                setOnItemReselectedListener {
                    val selectedItemNavGraph=navController.graph.findNode(it.itemId) as NavGraph
                    selectedItemNavGraph.let {
                            menuGraph->
                        navController.popBackStack(menuGraph.startDestinationId,false)
                    }
                }

            }
        }

    }

}