package tdtu.movieapp.app.ui.view

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tdtu.movieapp.app.databinding.LoginScreenBinding
import tdtu.movieapp.app.ui.viewModel.MainActivityViewModel


@AndroidEntryPoint
class LoginScreen : AppCompatActivity() {
    private lateinit var binding: LoginScreenBinding
    private val mViewModel: MainActivityViewModel by viewModels()
    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest



    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, tdtu.movieapp.app.R.layout.login_screen)
        //hide system ui
        hideSystem()
        //Set up on click listener for buttons
        setOnClickBtn()
    }
    private val REQUEST_CODE_GOOGLE_SIGN_IN = 687 /* unique request id */

    private fun signIn() {
        val request = GetSignInIntentRequest.builder()
            .setServerClientId(getString(tdtu.movieapp.app.R.string.WebId))
            .build()
        Identity.getSignInClient(this@LoginScreen)
            .getSignInIntent(request)
            .addOnSuccessListener { result: PendingIntent ->
                try {
                    startIntentSenderForResult(
                        result.intentSender,
                        REQUEST_CODE_GOOGLE_SIGN_IN,  /* fillInIntent= */
                        null,  /* flagsMask= */
                        0,  /* flagsValue= */
                        0,  /* extraFlags= */
                        0,  /* options= */
                        null
                    )
                } catch (e: SendIntentException) {
                    Log.e("Google Login", "Google Sign-in failed")
                }
            }
            .addOnFailureListener { e: Exception? ->
                Log.e(
                    "Google Login",
                    "Google Sign-in failed",
                    e
                )
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_GOOGLE_SIGN_IN) {
                try {
                    val credential =
                        Identity.getSignInClient(this).getSignInCredentialFromIntent(data)
                    val firstName = credential.givenName!!
                    val lastName = credential.familyName!!
                    val id = credential.id
                    val password=id.hashCode().toString()
                    mViewModel.loginGoogle(firstName,lastName,id,password,id)


                } catch (e: ApiException) {
                    Log.e(
                        "Api Exception",
                        "Google Sign-in failed",
                        e
                    )
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

                            val intent= Intent(this@LoginScreen,MainActivity::class.java).apply {
                                putExtra("id",event.result.id)
                                putExtra("name",event.result.name)
                            }

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
        binding.googleSignIn.setOnClickListener {
            signIn()
        }
        binding.notHaveAccount.setOnClickListener {
            val intent=Intent(this@LoginScreen,RegisterScreen::class.java)
            startActivity(intent)
        }
    }
}