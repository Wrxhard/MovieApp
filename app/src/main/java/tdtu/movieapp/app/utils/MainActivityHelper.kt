package tdtu.movieapp.app.utils

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivityHelper() {
    suspend fun setupBottomNav(navController: NavController, bottomNavigationView: BottomNavigationView)
    {
        withContext(Dispatchers.Main){
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
}