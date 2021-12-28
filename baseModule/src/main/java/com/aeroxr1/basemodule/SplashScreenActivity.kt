package com.aeroxr1.basemodule

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.aeroxr1.basemodule.databinding.ActivitySplashScreenBinding
import com.aeroxr1.notificationmodule.NotificationUtility
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.net.ssl.HandshakeCompletedEvent

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.splash.setImageResource(R.drawable.home_base)

        lifecycleScope.launch{
            delay(3000)
            NotificationUtility.showNotification(application)
            val intent = Intent(this@SplashScreenActivity,
                Class.forName("com.aeroxr1.searchpoc.MainActivity"))
            startActivity(intent)
        }

    }



}