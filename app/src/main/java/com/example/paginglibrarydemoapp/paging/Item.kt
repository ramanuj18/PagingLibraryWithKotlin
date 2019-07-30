package com.example.paginglibrarydemoapp.paging

import com.google.gson.annotations.SerializedName

class Item {

    @field:SerializedName("full_name")
    var fullName: String? = null

    val description: String? = null

    @field:SerializedName("stargazers_count")
    val stars: Int = 0

    @field:SerializedName("forks_count")
    val forks: Int = 0

    @field:SerializedName("language")
    val language: String? = null
}

class ItemResponse {

    @field:SerializedName("total_count")
    var totalCount: Int = 0

    var items: List<Item>? = null
}