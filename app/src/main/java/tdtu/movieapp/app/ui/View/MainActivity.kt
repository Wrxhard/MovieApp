package tdtu.movieapp.app.ui.View

import android.Manifest.permission.*
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import tdtu.movieapp.app.R
import tdtu.movieapp.app.databinding.ActivityMainBinding
import tdtu.movieapp.app.ui.ViewModel.MainActivityViewModel


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mViewModel: MainActivityViewModel


    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        mViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        //bindingview
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //bind viewmodel
        mViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        //hideSystemBar
        hideSystem()
        //Find and set Navigation controller
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.Screen) as NavHostFragment?
        val navController = navHostFragment?.navController
        navController?.let {
            binding.bottomNav.selectedItemId = R.id.homescreen_nav_graph
            //setup bottom nav
            setupBottomNav(it, binding.bottomNav)
        }
        //getData
        getData()


    }

    //hide systembar
    @RequiresApi(Build.VERSION_CODES.R)
    private fun hideSystem(){
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
    private fun setupBottomNav(navController: NavController, bottomNavigationView: BottomNavigationView)
    {

        bottomNavigationView.apply {
            navController.let {
                    navController ->
                NavigationUI.setupWithNavController(this,navController)
                setOnItemSelectedListener {
                        item->
                    if (item.itemId == R.id.exitapp)
                    {
                        MaterialAlertDialogBuilder(context,R.style.AlertDialogTheme)
                            .setTitle("Confirm")
                            .setMessage("Are you sure you want to exit?")
                            .setNegativeButton("No") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .setPositiveButton("Yes") { _, _ ->
                                val intent = Intent(Intent.ACTION_MAIN).apply {
                                    addCategory(Intent.CATEGORY_HOME)
                                    flags=Intent.FLAG_ACTIVITY_NEW_TASK
                                }
                                startActivity(intent)
                                finishAffinity()

                            }
                            .show()
                    }
                    else{
                        NavigationUI.onNavDestinationSelected(item, navController)
                    }
                    true
                }
                setOnItemReselectedListener {
                    if (it.itemId == R.id.exitapp)
                    {
                        MaterialAlertDialogBuilder(context,R.style.AlertDialogTheme)
                            .setTitle("Confirm")
                            .setMessage("Are you sure you want to exit?")
                            .setNegativeButton("No") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .setPositiveButton("Yes") { _, _ ->
                                val intent = Intent(Intent.ACTION_MAIN).apply {
                                    addCategory(Intent.CATEGORY_HOME)
                                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                }
                                startActivity(intent)
                                finishAffinity()
                            }
                            .show()
                    }
                    else{
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
    private fun getData(){
        //getData
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                val jobs = listOf(
                    async {
                        mViewModel.getPopular(1)
                    },
                    async {
                        mViewModel.getTrending(2)
                    },
                )
                jobs.awaitAll()
                mViewModel.cancel()
            }
        }
        mViewModel.getRecentlyWatch()
    }


}