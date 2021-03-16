package com.furqoncreative.githubsusers.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.furqoncreative.githubsusers.R
import com.furqoncreative.githubsusers.databinding.ActivitySplashBinding
import com.furqoncreative.githubsusers.utils.hideSystemUi

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.root.hideSystemUi()
        Glide.with(this).load(R.drawable.github_icon).into(binding.imageView)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }

}
