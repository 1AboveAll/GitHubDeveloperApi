package com.himanshurawat.githubdeveloperapi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.himanshurawat.githubdeveloperapi.R
import com.himanshurawat.githubdeveloperapi.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )



    }
}