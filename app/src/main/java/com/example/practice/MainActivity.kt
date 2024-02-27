package com.example.practice

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private val viewModel = BGViewModel()
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = MyAdapter()
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    count+=20
                    viewModel.getData(count)
//                    Toast.makeText(this@MainActivity, "滑到底部了", Toast.LENGTH_SHORT).show()
                }
                if (!recyclerView.canScrollVertically(-1)) {
//                    Toast.makeText(this@MainActivity, "滑到頂部了", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.listLiveData.observe(this
        ) { list ->
            runOnUiThread {
                adapter.setMyData(list)
                adapter.notifyDataSetChanged()
            }
        }
    }
}