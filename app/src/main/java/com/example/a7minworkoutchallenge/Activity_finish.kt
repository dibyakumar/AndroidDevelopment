package com.example.a7minworkoutchallenge

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.activity_finish.*
import java.util.*

class Activity_finish : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        setSupportActionBar(finish_toolbar)
        val actionbar = supportActionBar
        if(actionbar!=null){
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        finish_toolbar.setNavigationOnClickListener{
            onBackPressed()
        }

        finish_button.setOnClickListener {
            finish()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            addToDataBase()
        }

    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun addToDataBase(){
        val calendar = Calendar.getInstance()

        val dateTime = calendar.time

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())

        val date = sdf.format(dateTime)

        val db = DatabaseHandler(this,null)
        db.addDate(date)
    }


}