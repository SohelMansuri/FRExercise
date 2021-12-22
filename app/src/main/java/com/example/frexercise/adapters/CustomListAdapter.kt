package com.example.frexercise.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.frexercise.R
import com.example.frexercise.models.DataItem
import com.example.frexercise.models.Header
import com.example.frexercise.models.ListItem
import java.lang.ClassCastException

/**
 * CustomListAdapter which utilizes ViewHolder to efficiently display
 * its items.
 */

private val ITEM_VIEW_TYPE_HEADER = 0
private val ITEM_VIEW_TYPE_ITEM = 1

class CustomListAdapter(
    private var items: ArrayList<DataItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class CustomItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val holderId: TextView = itemView.findViewById(R.id.list_item_id)
        private val holderListId: TextView = itemView.findViewById(R.id.list_item_list_id)
        private val holderName: TextView = itemView.findViewById(R.id.list_item_name)

        fun bind(listItem: ListItem) {
            val id = listItem.itemId
            val listId = listItem.listId
            val name = listItem.name

            holderId.visibility = if(id == null) View.GONE else View.VISIBLE
            holderListId.visibility = if(listId == null) View.GONE else View.VISIBLE
            holderName.visibility = if(name == null) View.GONE else View.VISIBLE

            val context = itemView.context
            holderId.text = context.getString(R.string.list_item_id, id?.toString())
            holderListId.text = context.getString(R.string.list_item_list_id, listId?.toString())
            holderName.text = context.getString(R.string.list_item_name, name)
        }

        companion object {
            fun from(parent: ViewGroup): CustomItemViewHolder {
                return CustomItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
                )
            }
        }
    }

    class CustomHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val id: TextView = itemView.findViewById(R.id.list_header_tv)

        fun bind(listItem: Header) {
            id.text = itemView.context.getString(R.string.list_item_list_id, listItem.listId?.toString())
        }

        companion object {
            fun from(parent: ViewGroup): CustomHeaderViewHolder {
                return CustomHeaderViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.list_header, parent, false)
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position]) {
            is Header -> ITEM_VIEW_TYPE_HEADER
            is ListItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ITEM_VIEW_TYPE_HEADER -> CustomHeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> CustomItemViewHolder.from(parent)
            else -> throw ClassCastException("Unknown view type!")
        }
    }

    /**
     * Method used to bind data to the view.  Each view will be
     * hidden or visible based on if there is corresponding data.
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is CustomHeaderViewHolder -> {
                val listHeader = items[position] as Header
                holder.bind(listHeader)
            }
            is CustomItemViewHolder -> {
                val listItem = items[position] as ListItem
                holder.bind(listItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun replaceData(newItems: ArrayList<DataItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}