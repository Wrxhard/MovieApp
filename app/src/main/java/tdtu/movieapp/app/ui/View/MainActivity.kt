package tdtu.movieapp.app.ui.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import tdtu.movieapp.app.R
import tdtu.movieapp.app.databinding.ActivityMainBinding
import tdtu.movieapp.app.utils.MainActivityHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        CoroutineScope(Dispatchers.IO).launch {
                val navHostFragment=supportFragmentManager.findFragmentById(R.id.Screen) as NavHostFragment?
                val navController=navHostFragment?.navController
                if (navController != null) {
                    binding.bottomNav.selectedItemId=R.id.first_nav_graph
                    MainActivityHelper().setupBottomNav(navController,  binding.bottomNav)
                }
        }
    }

}