package tdtu.movieapp.app.ui.View

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import tdtu.movieapp.app.R
import tdtu.movieapp.app.data.model.Movies.RecentlyMovie
import tdtu.movieapp.app.databinding.PlayscreenBinding
import tdtu.movieapp.app.ui.ViewModel.MainActivityViewModel

@AndroidEntryPoint
class PlayMovieScreen : AppCompatActivity() {
    private lateinit var binding: PlayscreenBinding
    private lateinit var youTubePlayerView: YouTubePlayerView
    private lateinit var youTubePlayer: YouTubePlayer
    private val tracker: YouTubePlayerTracker = YouTubePlayerTracker()
    private lateinit var mViewModel: MainActivityViewModel
    private var currentSecond:Float=0f


    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        //Bind view
        binding= DataBindingUtil.setContentView(this,R.layout.playscreen)
        //Force Landscape Mode
        requestedOrientation= ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE
        youTubePlayerView=binding.youtubePlayerView
        //Get video from youtube url
        val url = intent.getStringExtra("video_url")
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                Toast.makeText(this@PlayMovieScreen,"Press Back Button Or Swipe Right To Exit",Toast.LENGTH_LONG).show()
                val videoId = url
                val startSecond=intent.getFloatExtra("view_time",0f)
                if (videoId != null) {
                    youTubePlayer.loadVideo(videoId, startSecond)
                    youTubePlayer.addListener(tracker)

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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onStop() {
        super.onStop()
        currentSecond = tracker.currentSecond
        val id=intent.getStringExtra("id")
        val poster=intent.getStringExtra("poster")
        val title=intent.getStringExtra("title")
        val trailer=intent.getStringExtra("video_url")
        if (id!=null && poster!=null && title!=null && trailer!=null )
        {
            mViewModel.addRecentlyWatch(
                RecentlyMovie(id,poster,title,trailer,currentSecond)
            )
        }else
        {
            Log.d("recentlywatch","$id,$poster,$title,$trailer,$currentSecond")
        }
    }


}