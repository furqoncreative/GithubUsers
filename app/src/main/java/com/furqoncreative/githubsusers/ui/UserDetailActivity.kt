package com.furqoncreative.githubsusers.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.furqoncreative.githubsusers.databinding.ActivityUserDetailBinding
import com.furqoncreative.githubsusers.model.GithubUser
import java.lang.StringBuilder

class UserDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailBinding

    companion object {
        const val ITEM = "ITEM"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val item = intent.getParcelableExtra<GithubUser>(ITEM)
        item?.let { setData(it) }
    }

    @SuppressLint("SetTextI18n")
    private fun setData(item: GithubUser) {
        binding.txtUsername.text = StringBuilder("@${item.username}")
        binding.txtName.text = item.name
        binding.txtCompany.text = item.company
        binding.txtLocation.text = item.location
        binding.txtFollowerValue.text = item.follower.toString()
        binding.txtFollowimgValue.text = item.following.toString()
        binding.txtRepositoryValue.text = item.repository.toString()
        Glide.with(this).load(item.avatar).circleCrop().into(binding.imgAvatar)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}