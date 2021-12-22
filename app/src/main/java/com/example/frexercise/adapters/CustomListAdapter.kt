package com.example.frexercise.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.frexercise.R
import com.example.frexercise.models.ListItem

/**
 * CustomListAdapter which utilizes ViewHolder to efficiently display
 * its items.
 */
class CustomListAdapter(
    private var items: ArrayList<ListItem>
) : RecyclerView.Adapter<CustomListAdapter.CustomViewHolder>() {
    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.list_item_id)
        val listId: TextView = itemView.findViewById(R.id.list_item_list_id)
        val name: TextView = itemView.findViewById(R.id.list_item_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    /**
     * Method used to bind data to the view.  Each view will be
     * hidden or visible based on if there is corresponding data.
     */
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val listItem = items[position]
        val id = listItem.itemId
        val listId = listItem.listId
        val name = listItem.name

        holder.id.visibility = if(id == null) View.GONE else View.VISIBLE
        holder.listId.visibility = if(listId == null) View.GONE else View.VISIBLE
        holder.name.visibility = if(name == null) View.GONE else View.VISIBLE

        val context = holder.itemView.context
        holder.id.text = context.getString(R.string.list_item_id, id?.toString())
        holder.listId.text = context.getString(R.string.list_item_list_id, listId?.toString())
        holder.name.text = context.getString(R.string.list_item_name, name)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun replaceData(newItems: ArrayList<ListItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}