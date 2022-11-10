package com.example.a7minworkoutchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setSupportActionBar(history_toolbar)
        val actionbar = supportActionBar
        if(actionbar!= null){
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.title = "History"
        }
        history_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        getAllCompletedDates()
    }

    private fun getAllCompletedDates(){
   val db = DatabaseHandler(this,null)
        val list = db.getAllData()
        if(list.size>0){
            tv_heading.visibility = View.VISIBLE
            rv_items.visibility = View.VISIBLE
            tv_default.visibility = View.GONE

            rv_items.layoutManager = LinearLayoutManager(this)
            val itemAdapter = ItemAdapter(this,list)
            rv_items.adapter = itemAdapter
        }else{
            tv_heading.visibility = View.GONE
            rv_items.visibility = View.GONE
            tv_default.visibility = View.VISIBLE
        }
    }
}