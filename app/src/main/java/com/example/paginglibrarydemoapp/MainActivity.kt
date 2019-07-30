package com.example.paginglibrarydemoapp


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paginglibrarydemoapp.paging.ItemViewModel
import com.example.paginglibrarydemoapp.paging.RecyclerItemAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter=RecyclerItemAdapter()
        recyclerView.layoutManager=LinearLayoutManager(this)

        val itemViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)

        itemViewModel.itemPagedList.observe(this,Observer{
            adapter.submitList(it)
        })
        recyclerView.adapter=adapter

    }
}
