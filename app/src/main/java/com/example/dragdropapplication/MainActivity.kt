package com.example.dragdropapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dragdropapplication.data.ExampleViewModel
import com.example.dragdropapplication.dragDrop.DragDropItemTouchHelperCallback
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var exampleViewModel: ExampleViewModel
    private lateinit var integerList: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        exampleViewModel = ViewModelProviders.of(this).get(ExampleViewModel::class.java)
        exampleViewModel.integerListMutableLiveData.postValue(emptyList())

        initRecyclerView()

        fab.setOnClickListener { addTestData() }
    }

    private fun initRecyclerView() {
        // Attach adapter
        val adapter = ExampleAdapter(this, exampleViewModel)
        exampleRecyclerView.adapter = adapter
        exampleRecyclerView.layoutManager = LinearLayoutManager(this)

        // Attach Drag&Drop callback
        val dragDropCallback = DragDropItemTouchHelperCallback(adapter, ItemTouchHelper.UP or ItemTouchHelper.DOWN)
        val itemTouchHelper = ItemTouchHelper(dragDropCallback)
        adapter.itemTouchHelper = itemTouchHelper
        itemTouchHelper.attachToRecyclerView(exampleRecyclerView)

        // Attach observer
        exampleViewModel.integerListMutableLiveData.observe(this, Observer { integerList ->
            this.integerList = integerList
            adapter.dataList = integerList.toMutableList()
        })
    }

    private fun addTestData() {
        val random = Random(Date().time)
        val mutableIntegerList = integerList.toMutableList()
        for (i in 0..2) {
            mutableIntegerList.add(random.nextInt())
        }
        exampleViewModel.integerListMutableLiveData.postValue(mutableIntegerList)
    }
}
