package com.example.a7minworkoutchallenge

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.BoringLayout
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_bmiactivity.*
import kotlin.math.pow

class BMIActivity : AppCompatActivity() {
    private val metric = "METRIC"
    private val us = "US"
    private  var selection:String =  metric
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmiactivity)


        setSupportActionBar(bmi_toolbar)
        val actionBar = supportActionBar

        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "BMI calculator"
        }

        bmi_toolbar.setNavigationOnClickListener {

            onBackPressed()

        }

        visibility_Metricview()

        radio_grp.setOnCheckedChangeListener { group, checkedId ->

            if(checkedId == R.id.rb_metricUnits){
                visibility_Metricview()
                selection = metric
                rb_metricUnits.setTextColor(ContextCompat.getColor(this,R.color.white))
                rb_usUnits.setTextColor(ContextCompat.getColor(this,R.color.highlighted))
            }else{
                visibility_usView()
                selection = us
                rb_usUnits.setTextColor(ContextCompat.getColor(this,R.color.white))
                rb_metricUnits.setTextColor(ContextCompat.getColor(this,R.color.highlighted))
            }

        }

        btn_calculate.setOnClickListener {
          if (isValid() && selection == metric) {
              var res: Float =
                  (weight_in_kg.text.toString().toFloat() / height_in_m.text.toString().toFloat()
                      .toDouble().pow(2)).toFloat()

              tv_bmi.text = res.toString()

              if (res < 24.9 && res > 18.5) {
                  suggestions.text = "your weight is Normal"
              } else if (res < 18.5) {
                  suggestions.text = "Your weight is underweight"
              } else if (res > 25.0 && res < 30.0) {
                  suggestions.text = "Your weight is OverWeight"
              } else {
                  suggestions.text = "Your weight is obese"
              }
          }
          else{
              Toast.makeText(this,"Please Enter data correctly",Toast.LENGTH_SHORT).show()
          }

        }
        btn_calculate2.setOnClickListener {
            if (isValid() && selection == us){
                val res: Float =
                    (weight_in_lb.text.toString().toFloat()*703 / (height_in_inch.text.toString().toFloat()+height_in_feet.text.toString().toFloat()*12)
                        .toDouble().pow(2)).toFloat()

                tv_bmi2.text = res.toString()

                if (res < 24.9 && res > 18.5) {
                    suggestions2.text = "your weight is Normal"
                } else if (res < 18.5) {
                    suggestions2.text = "Your weight is underweight"
                } else if (res > 25.0 && res < 30.0) {
                    suggestions2.text = "Your weight is OverWeight"
                } else {
                    suggestions2.text = "Your weight is obese"
                }
            }
            else{
                Toast.makeText(this,"Please Enter data correctly",Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun isValid():Boolean{
      var isvalid : Boolean = false
      if(selection == metric)
        isvalid = !(height_in_m.text.toString().isEmpty() || weight_in_kg.text.toString().isEmpty())
      else if(selection == us) {
          isvalid = !( weight_in_lb.text.toString().isEmpty() ||
                  height_in_inch.text.toString().isEmpty() || height_in_feet.text.toString().isEmpty())
      }
          return isvalid
    }
    private fun visibility_Metricview(){
         ll1.visibility = View.VISIBLE
        ll2.visibility = View.GONE

        tv_bmi.text= ""
        suggestions.text = ""
        weight_in_kg.text!!.clear()
        height_in_m.text!!.clear()

    }
    private fun visibility_usView(){
        ll1.visibility = View.GONE
        ll2.visibility = View.VISIBLE

        tv_bmi2.text = ""
        suggestions2.text = ""
        weight_in_lb.text!!.clear()
        height_in_inch.text!!.clear()
        height_in_feet.text!!.clear()

    }
}