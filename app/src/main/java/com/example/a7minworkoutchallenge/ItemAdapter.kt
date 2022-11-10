package com.example.a7minworkoutchallenge

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_bmiactivity.view.*
import kotlinx.android.synthetic.main.item_row.view.*

class ItemAdapter(val context:Context,val arr:ArrayList<String>):RecyclerView.Adapter<ItemAdapter.ViewHolder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val content = arr[position]

        holder.item2.text = (position+1).toString()
        holder.item3.text = content

        if(position%2==0){
            holder.item1.setBackgroundColor(Color.parseColor("#DBE3E3"))
        }else{
            holder.item1.setBackgroundColor(ContextCompat.getColor(context,R.color.highlighted))
        }
    }

    override fun getItemCount(): Int {
       return arr.size
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
     val item1 = view.llrow
     val item2 = view.tvPosition
     val item3 = view.tvItem
    }
}