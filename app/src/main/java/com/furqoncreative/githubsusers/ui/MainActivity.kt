package com.furqoncreative.githubsusers.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.furqoncreative.githubsusers.R
import com.furqoncreative.githubsusers.databinding.ActivityMainBinding
import com.furqoncreative.githubsusers.utils.loadJSONFromAsset
import com.furqoncreative.githubsusers.model.GithubUser
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), GithubUsersAdapter.OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var githubUsersAdapter: GithubUsersAdapter
    private lateinit var userList: ArrayList<GithubUser>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecycleview()
        setData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)
        val searchView: SearchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                startActivity(Intent(applicationContext, AboutActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun filter(text: String) {
        val newList = ArrayList<GithubUser>()
        for (item in userList) {
            if (item.name?.toLowerCase(Locale.ROOT)?.contains(text) == true) {
                newList.add(item)
            }
        }
        githubUsersAdapter.setFilter(newList)
    }

    private fun setRecycleview() {
        githubUsersAdapter = GithubUsersAdapter(this)
        binding.rvUsers.apply {
            adapter = githubUsersAdapter
            binding.rvUsers.layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setData() {
        userList = ArrayList()
        try {
            val obj = JSONObject(loadJSONFromAsset(applicationContext, "githubuser.json"))
            val userArray = obj.getJSONArray("users")
            for (i in 0 until userArray.length()) {
                val userDetail = userArray.getJSONObject(i)
                val username = userDetail.getString("username")
                val name = userDetail.getString("name")
                val company = userDetail.getString("company")
                val location = userDetail.getString("location")
                val repository = userDetail.getString("repository").toInt()
                val follower = userDetail.getString("follower").toInt()
                val following = userDetail.getString("following").toInt()
                val avatar = userDetail.getString("avatar")
                val avatarImage = resources.getIdentifier(avatar, null, packageName)

                userList.add(
                    GithubUser(
                        follower,
                        following,
                        name,
                        company,
                        location,
                        avatarImage,
                        repository,
                        username
                    )
                )

            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        githubUsersAdapter.setItems(userList)
    }

    override fun onCLick(item: GithubUser) {
        val intent = Intent(this, UserDetailActivity::class.java)
        intent.putExtra(UserDetailActivity.ITEM, item)
        startActivity(intent)
    }

}