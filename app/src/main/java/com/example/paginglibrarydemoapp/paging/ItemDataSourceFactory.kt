package com.example.paginglibrarydemoapp.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

class ItemDataSourceFactory:DataSource.Factory<Int,Item>() {
    val itemRepoLiveDataSource=MutableLiveData<ItemDataSource>()

    override fun create(): DataSource<Int, Item> {
        val itemDataSource = ItemDataSource()
        itemRepoLiveDataSource.postValue(itemDataSource)
        return itemDataSource
    }
}