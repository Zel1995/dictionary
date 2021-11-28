package com.example.dictionary.presentation.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.R
import com.example.dictionary.domain.Model.DataModel

class SearchAdapter(private val onItemClick: (text: String) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = mutableListOf<DataModel>()

    fun setData(dataToSet: List<DataModel>) {
        data.apply {
            clear()
            addAll(dataToSet)
            notifyDataSetChanged()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_main_recyclerview_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SearchViewHolder).bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val header = itemView.findViewById<TextView>(R.id.header_textview_recycler_item)
        private val description =
            itemView.findViewById<TextView>(R.id.description_textview_recycler_item)

        init {
            itemView.setOnClickListener {
                onItemClick.invoke(data[adapterPosition].text.toString())
            }
        }

        fun bind(dataModel: DataModel) {
            header.text = dataModel.text
            description.text = dataModel.meanings?.get(0)?.translation?.text
        }

    }
}