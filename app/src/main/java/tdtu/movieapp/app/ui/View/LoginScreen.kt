package tdtu.movieapp.app.ui.View

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tdtu.movieapp.app.R
import tdtu.movieapp.app.databinding.LoginScreenBinding
import tdtu.movieapp.app.ui.ViewModel.MainActivityViewModel

@AndroidEntryPoint
class LoginScreen : AppCompatActivity() {
    private lateinit var binding: LoginScreenBinding
    private val mViewModel: MainActivityViewModel by viewModels()



    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.login_screen)
        //hide system ui
        hideSystem()
        //Set up on click listener for buttons
        setOnClickBtn()


    }
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
    private fun setOnClickBtn()
    {
        binding.btnLoginForm.setOnClickListener {
            val username=binding.edtEmail.text.toString()
            val password=binding.edtPassword.text.toString()
            mViewModel.login(username,password)
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED)
            {
                mViewModel.loginevent.collectLatest { event ->
                    when(event){
                        is MainActivityViewModel.Event.Success ->
                        {
                            binding.edtEmail.setText("")
                            binding.edtPassword.setText("")

                            val intent= Intent(this@LoginScreen,MainActivity::class.java)
                            intent.putExtra("id",event.result.id)
                            intent.putExtra("name",event.result.name)
                            startActivity(intent)
                        }
                        is MainActivityViewModel.Event.Failure ->
                        {
                            binding.edtEmail.setText("")
                            binding.edtPassword.setText("")

                            Toast.makeText(this@LoginScreen,event.error,Toast.LENGTH_SHORT).show()
                        }
                        else -> Unit
                    }
                }
            }
        }
        binding.notHaveAccount.setOnClickListener {
            val intent=Intent(this@LoginScreen,RegisterScreen::class.java)
            startActivity(intent)
        }
    }
}