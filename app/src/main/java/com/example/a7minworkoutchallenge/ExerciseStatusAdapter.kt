package com.example.a7minworkoutchallenge

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_exercise_status.view.*

class ExerciseStatusAdapter(val items:ArrayList<ExerciseModel>,val context :Context) : RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_exercise_status,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model : ExerciseModel = items[position]
        var modelNumber = model.getId()
        holder.tvItem.text = modelNumber.toString()

        if(model.getIsSelected()){
            holder.tvItem.background = ContextCompat.getDrawable(context,R.drawable.current_exercise_status)
        }
        else if (model.getIsCompleted()){
            holder.tvItem.background = ContextCompat.getDrawable(context,R.drawable.done_exercise_status)
            holder.tvItem.setTextColor(Color.parseColor("#FFFFFFFF"))
        }
        else{
            holder.tvItem.background = ContextCompat.getDrawable(context,R.drawable.circular_item_gray)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
       val tvItem = view.tvItemStatus
    }

}