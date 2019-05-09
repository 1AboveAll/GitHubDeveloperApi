package com.himanshurawat.githubdeveloperapi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.himanshurawat.githubdeveloperapi.databinding.ActivitySplashBinding

class SplashActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@SplashActivity,R.layout.activity_splash)

        startActivity(Intent(this@SplashActivity,MainActivity::class.java))

    }
}