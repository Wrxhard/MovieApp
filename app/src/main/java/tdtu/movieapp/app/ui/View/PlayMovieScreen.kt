package tdtu.movieapp.app.ui.View

import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import tdtu.movieapp.app.R
import tdtu.movieapp.app.databinding.PlayscreenBinding

class PlayMovieScreen : AppCompatActivity() {
    private lateinit var binding: PlayscreenBinding
    private lateinit var youTubePlayerView: YouTubePlayerView
    private lateinit var youTubePlayer: YouTubePlayer

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.playscreen)
        requestedOrientation= ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE
        youTubePlayerView=binding.youtubePlayerView
        val url = intent.getStringExtra("video_url")
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                Toast.makeText(this@PlayMovieScreen,"Press Back Button Or Swipe Right To Exit",Toast.LENGTH_LONG).show()
                val videoId = url
                if (videoId != null) {
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            }
        })
        //hide system bar
        hideSystem()
        //observe lifecycle
        lifecycle.addObserver(youTubePlayerView);

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