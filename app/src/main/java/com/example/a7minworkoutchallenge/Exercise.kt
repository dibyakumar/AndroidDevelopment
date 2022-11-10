package com.example.a7minworkoutchallenge

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.item_exercise_status.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class Exercise : AppCompatActivity() , TextToSpeech.OnInitListener{

    private var restTimer : CountDownTimer? = null

    private var restProgress = 0

    private var exerciseCountdown:Long = 30

    private var exerciseLists :ArrayList<ExerciseModel>? = null

    private var currentPosition : Int  = -1

    private var tts : TextToSpeech? = null

    private var player : MediaPlayer? = null

    private var exerciseStatusAdapter : ExerciseStatusAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        setSupportActionBar(toolbar)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        val actionBar = supportActionBar
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        toolbar.setNavigationOnClickListener{
            alertFunction()
        }


        tts = TextToSpeech(this,this)

        exerciseLists = ExercisesList.exerciseList()

        setUpRestView()

        exerciseNumberRecyclerView()




    }

    override fun onBackPressed() {
        alertFunction()
    }

    override fun onDestroy() {
        if(restTimer!=null){
            restTimer!!.cancel()
            restProgress= 0
        }

        if(tts!=null){
            tts!!.stop()
            tts!!.shutdown()
        }

        if(player!=null){
            player!!.stop()
        }
        super.onDestroy()
    }
    private fun setRestProgress(){
        progressbar.progress = restProgress
            restTimer = object : CountDownTimer(10000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    restProgress++
                    progressbar.progress = (10 - restProgress)
                    Timer.text = ((10 - restProgress)).toString()

                    Upcoming_exercise.text = exerciseLists!![currentPosition+1].getName()


                }
                override fun onFinish() {
                    currentPosition++
                    exerciseLists!!.get(currentPosition).setIsSelected(true)

                    exerciseStatusAdapter!!.notifyDataSetChanged()

                   setUpExerciseRestView()



                }
            }.start()
        }

    private fun setExerciseRestProgress(){
        ExerciseProgressbar.max=exerciseCountdown.toInt()
        restTimer = object : CountDownTimer(exerciseCountdown*1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                ExerciseProgressbar.progress = (exerciseCountdown.toInt() - restProgress)
                ExerciseTimer.text = ((exerciseCountdown.toInt() - restProgress)).toString()
            }

            override fun onFinish() {
                if (currentPosition < 1) {
                    setUpRestView()
                    exerciseLists!!.get(currentPosition).setIsCompleted(true)
                    exerciseLists!!.get(currentPosition).setIsSelected(false)
                    exerciseStatusAdapter!!.notifyDataSetChanged()
                } else {
                    finish()
                    intent = Intent(this@Exercise,Activity_finish::class.java)
                    startActivity(intent)
                }
            }

        }.start()
    }
    private fun setUpExerciseRestView(){
        llRestView.visibility = View.GONE
        llExerciseView.visibility = View.VISIBLE
        if(restTimer!=null){
            restTimer!!.cancel()
            restProgress = 0
        }

        ImageView.setImageResource(exerciseLists!![currentPosition].getImage())

        exerciseName.text = exerciseLists!![currentPosition].getName()

        onSpeak(exerciseLists!![currentPosition].getName())

        setExerciseRestProgress()
    }
    private fun setUpRestView(){

        try{
            player = MediaPlayer.create(applicationContext,R.raw.press_start)
            player!!.isLooping=false
            player!!.start()
        }catch (e:Exception){
            e.stackTrace
        }


        llRestView.visibility = View.VISIBLE
        llExerciseView.visibility = View.GONE
        if(restTimer!=null){
            restTimer!!.cancel()
            restProgress = 0
        }
        setRestProgress()

    }

    private fun pause(){
        if(restTimer!=null){
            restTimer!!.cancel()
        }
    }

    override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS){
            val res = tts!!.setLanguage(Locale.ENGLISH)

            if(res == TextToSpeech.LANG_MISSING_DATA || res == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","Language not Supported")
            }
        }else{
            Log.e("TTS","Initialization failed")
        }
    }

    private fun onSpeak(text:String){
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    private fun exerciseNumberRecyclerView(){

        rvExerciseView.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)

        exerciseStatusAdapter = ExerciseStatusAdapter(exerciseLists!!,this)

        rvExerciseView.adapter = exerciseStatusAdapter

    }

    private fun alertFunction(){

        val builder = AlertDialog.Builder(this)

        builder.setTitle("Do You Want To Exit ?")
        builder.setIcon(R.drawable.ic_sharp_add_alert_24)

        builder.setPositiveButton("exit"){
           dialogInterface,_->
            finish()
            dialogInterface.dismiss()
        }

        builder.setNegativeButton("No"){
            dialogInterface,_->
            dialogInterface.dismiss()
        }


        builder.setNeutralButton("cancel"){
            dialogInterface,_->
            dialogInterface.dismiss()

        }

        val dialog = builder.create()
        dialog.setCancelable(false)

        dialog.show()
    }
}