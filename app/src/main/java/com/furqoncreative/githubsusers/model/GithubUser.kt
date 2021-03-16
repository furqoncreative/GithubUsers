package com.furqoncreative.githubsusers.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(
	val follower: Int? = null,
	val following: Int? = null,
	val name: String? = null,
	val company: String? = null,
	val location: String? = null,
	val avatar: Int? = null,
	val repository: Int? = null,
	val username: String? = null
) : Parcelable
