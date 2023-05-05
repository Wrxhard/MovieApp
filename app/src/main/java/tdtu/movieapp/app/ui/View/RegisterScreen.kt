package tdtu.movieapp.app.ui.View

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
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
import tdtu.movieapp.app.databinding.RegisterScreenBinding
import tdtu.movieapp.app.ui.ViewModel.MainActivityViewModel

@AndroidEntryPoint
class RegisterScreen : AppCompatActivity() {
    private lateinit var binding:RegisterScreenBinding
    private val mViewModel: MainActivityViewModel by viewModels()



    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.register_screen)
        hideSystem()
        setOnClick()
    }

    private fun setOnClick()
    {
        binding.haveAccount.setOnClickListener {
            finish()
        }

        binding.btnRegister.setOnClickListener {
            val firstname=binding.edtRegisterFirstname.text.toString()
            val lastname=binding.edtRegisterLastname.text.toString()
            val username=binding.edtRegisterAccount.text.toString()
            val email=binding.edtRegisterEmail.text.toString()
            val password=binding.edtRegisterPassword.text.toString()
            val confirmpassword=binding.edtRegisterConfirmPassword.text.toString()

            if (password!=confirmpassword)
            {
                Toast.makeText(this@RegisterScreen,"Password don't matched",Toast.LENGTH_SHORT).show()
            }
            else if (password.length<8)
            {
                Toast.makeText(this@RegisterScreen,"Password is too short",Toast.LENGTH_SHORT).show()
            }
            else {
                mViewModel.register(firstname,lastname,username,email,password)
            }

        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED)
            {
                mViewModel.registerevent.collectLatest { event ->
                    when(event)
                    {
                        is MainActivityViewModel.Event.Success -> {
                            Toast.makeText(this@RegisterScreen,"Register Successfully",Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        is MainActivityViewModel.Event.Failure -> {
                            Toast.makeText(this@RegisterScreen,event.error,Toast.LENGTH_SHORT).show()
                        }
                        else -> Unit
                    }
                }
            }
        }
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
}