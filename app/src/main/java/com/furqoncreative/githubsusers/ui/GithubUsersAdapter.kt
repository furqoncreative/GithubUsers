package com.furqoncreative.githubsusers.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.furqoncreative.githubsusers.databinding.ItemRowGithubUserBinding
import com.furqoncreative.githubsusers.model.GithubUser
import java.lang.StringBuilder

class GithubUsersAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<GithubUsersAdapter.ViewHolder>() {
    private var itemList = ArrayList<GithubUser>()

    interface OnItemClickListener {
        fun onCLick(item: GithubUser)
    }

    fun setItems(items: ArrayList<GithubUser>) {
        itemList.clear()
        itemList.addAll(items)
        notifyDataSetChanged()
    }

    fun setFilter(newList: ArrayList<GithubUser>) {
        itemList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemRowGithubUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindView(itemList[position])

    override fun getItemCount() = itemList.size
    inner class ViewHolder(
        private val itemBinding: ItemRowGithubUserBinding,
        private val listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {
        private lateinit var item: GithubUser

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bindView(item: GithubUser) {
            this.item = item
            itemBinding.txtUsername.text = StringBuilder("@${item.username}")
            itemBinding.txtName.text = item.name
            itemBinding.txtCompany.text = item.company
            itemBinding.txtLocation.text = item.location
            Glide.with(itemBinding.root).load(item.avatar).circleCrop().into(itemBinding.imgAvatar)
        }

        override fun onClick(v: View?) {
            listener.onCLick(item)
        }

    }
}