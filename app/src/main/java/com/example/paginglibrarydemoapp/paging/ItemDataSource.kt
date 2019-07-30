package com.example.paginglibrarydemoapp.paging

import androidx.paging.PageKeyedDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemDataSource :PageKeyedDataSource<Int,Item>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Item>) {
        ApiClient.buildService().getRepositories(FIRST_PAGE, PAGE_SIZE, TOPIC)
            .enqueue(object :Callback<ItemResponse>{
                override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<ItemResponse>, response: Response<ItemResponse>) {
                   if(response.isSuccessful){
                       val apiResponse=response.body()!!
                       val responseItem=apiResponse.items

                       responseItem?.let {
                           callback.onResult(responseItem, null, FIRST_PAGE + 1)
                       }
                   }
                }
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        ApiClient.buildService().getRepositories(params.key, PAGE_SIZE, TOPIC)
            .enqueue(object :Callback<ItemResponse>{
                override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<ItemResponse>, response: Response<ItemResponse>) {
                    if (response.isSuccessful) {

                        val apiResponse = response.body()!!
                        val responseItems = apiResponse.items

                        val key = if (apiResponse.totalCount > params.key) params.key + 1 else apiResponse.totalCount

                        responseItems?.let {
                            callback.onResult(responseItems, key)
                        }
                    }
                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        ApiClient.buildService().getRepositories(params.key, PAGE_SIZE, TOPIC)
            .enqueue(object :Callback<ItemResponse>{
                override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<ItemResponse>, response: Response<ItemResponse>) {
                    if (response.isSuccessful) {

                        val apiResponse = response.body()!!
                        val responseItems = apiResponse.items

                        val key = if (params.key > 1) params.key - 1 else 0

                        responseItems?.let {
                            callback.onResult(responseItems, key)
                        }
                    }
                }
            })
    }
    companion object {
        const val PAGE_SIZE = 10
        const val FIRST_PAGE = 1
        const val TOPIC = "android"
    }
}