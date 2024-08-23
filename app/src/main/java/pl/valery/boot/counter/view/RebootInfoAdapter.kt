package pl.valery.boot.counter.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.valery.boot.counter.R
import pl.valery.boot.counter.dao.model.RebootDayCount

// Adapter class
class RebootInfoAdapter(
    private val dataList: List<RebootDayCount>
) : RecyclerView.Adapter<RebootInfoAdapter.RebootInfoViewHolder>() {

    // ViewHolder class
    inner class RebootInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val dateView: TextView = view.findViewById(R.id.reboot_info_text)

        // Bind data to the views
        fun bind(rebootDayCount: RebootDayCount) {
            dateView.text =
                dateView.resources.getString(
                    R.string.reboot_info_item_text,
                    rebootDayCount.date,
                    rebootDayCount.count
                )
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RebootInfoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reboot_info, parent, false)
        return RebootInfoViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: RebootInfoViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataList.size
    }
}