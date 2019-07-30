package com.example.paginglibrarydemoapp.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class ItemViewModel:ViewModel() {
    var itemPagedList:LiveData<PagedList<Item>>
    private var liveDataSource:LiveData<ItemDataSource>
    init {
        val itemDataSourceFactory=ItemDataSourceFactory()
        liveDataSource=itemDataSourceFactory.itemRepoLiveDataSource

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(ItemDataSource.PAGE_SIZE)
            .build()

        itemPagedList=LivePagedListBuilder(itemDataSourceFactory,config).build()
    }
}