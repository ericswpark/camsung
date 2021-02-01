package android.com.ericswpark.camsung.FAQActivity

import android.com.ericswpark.camsung.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FAQAdapter(val faqList: ArrayList<FAQ>) : RecyclerView.Adapter<FAQAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FAQAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.faq_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(faqList[position])
    }

    override fun getItemCount(): Int {
        return faqList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(faq: FAQ) {
            val titleTextView = itemView.findViewById<TextView>(R.id.faq_item_title)
            val descriptionTextView = itemView.findViewById<TextView>(R.id.faq_item_description)

            titleTextView.text = faq.title
            descriptionTextView.text = faq.description
        }
    }
}