package com.himanshurawat.githubdeveloperapi.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.himanshurawat.githubdeveloperapi.R
import com.himanshurawat.githubdeveloperapi.databinding.ActivitySplashBinding
import com.himanshurawat.githubdeveloperapi.ui.main.MainActivity

class SplashActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@SplashActivity,
            R.layout.activity_splash
        )

        var progressStatus = 0
        val handler = Handler()


        Thread(Runnable {
            while (progressStatus < 100) {
                progressStatus += 1
                // Update the progress bar and display the
                //current value in the text view
                handler.post(Runnable {binding.activitySplashProgressBar.progress= progressStatus })
                try {
                    // Sleep for 50 milliseconds.
                    Thread.sleep(20)
                    if (progressStatus == 100) {
                        startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                        finish()
                    }
                } catch (e: Throwable) {
                    e.printStackTrace()
                }

            }
        }).start()

    }
}