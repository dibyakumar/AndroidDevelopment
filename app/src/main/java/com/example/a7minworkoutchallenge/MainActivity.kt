package com.example.a7minworkoutchallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_bmiactivity.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        ll_start.setOnClickListener{
        val intent = Intent(this,Exercise::class.java)
            startActivity(intent)
        }

        bmi.setOnClickListener {
            intent = Intent(this,BMIActivity::class.java)
            startActivity(intent)
        }

        history.setOnClickListener {
            intent = Intent(this,HistoryActivity::class.java)
            startActivity(intent)
        }


    }
}