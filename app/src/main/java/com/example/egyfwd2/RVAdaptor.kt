package com.example.egyfwd2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class RVAdaptor(private val onClickEvent:OnClickListener,var items: List<AstroidHolder>) :RecyclerView.Adapter<RVAdaptor.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textVieww:TextView = itemView.findViewById(R.id.textOut)
        val date:TextView = itemView.findViewById(R.id.date)
        val hazard:ImageView = itemView.findViewById(R.id.hazard)
    }

    interface OnClickListener{
        fun onClick(item:AstroidHolder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.textVieww.text= item.name
        holder.date.text = item.date
        if(item.hazardous){
            holder.hazard.setImageResource(R.drawable.sad)
            holder.hazard.contentDescription ="@string/hazard"
        }
        else{
            holder.hazard.setImageResource(R.drawable.smile)
            holder.hazard.contentDescription ="@string/safe"
        }
        holder.textVieww.setOnClickListener {
            onClickEvent.onClick(item)
        }
    }
    override fun getItemCount(): Int {
       return items.size
    }
}
