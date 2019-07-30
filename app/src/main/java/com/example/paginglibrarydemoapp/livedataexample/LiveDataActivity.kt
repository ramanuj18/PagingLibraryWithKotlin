package com.example.paginglibrarydemoapp.livedataexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.paginglibrarydemoapp.R
import kotlinx.android.synthetic.main.activity_live_data.*

class LiveDataActivity : AppCompatActivity() {
        var userList:ArrayList<String> = arrayListOf("Abc","Xyz")
    var livedata= MutableLiveData<ArrayList<String>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        livedata.value=userList

        livedata.observe(this, Observer {
            Toast.makeText(this,"${it.size}",Toast.LENGTH_SHORT).show()
        })

        btnAdd.setOnClickListener {
            userList.add("hello")
            //One thing to keep in mind about setvalue() is that it cannot be done in background thread it must be done in main thread only.
            //As setvalue cannot be called from background thread so postvalue must be used to set value from background thread.
            livedata.value=userList
        }
    }
}
