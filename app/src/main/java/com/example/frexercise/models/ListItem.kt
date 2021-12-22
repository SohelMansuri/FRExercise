package com.example.frexercise.models

import com.google.gson.annotations.SerializedName

/**
 * Data class which is a matching blueprint of
 * data coming from the server.
 */
data class ListItem(
    @SerializedName("id")
    var itemId: Int? = null,
    var listId: Int? = null,
    var name: String? = null
) : Comparable<ListItem> {

    /**
     * Comparable is added to allow sorting a list
     * of objects based on listId and then name
     * in ascending order.
     *
     * @param other     the ListItem to compare to
     */
    override fun compareTo(other: ListItem): Int {
        if(this.listId == other.listId) {
            return this.name?.compareTo(other.name ?: "") ?: -1
        }
        return this.listId?.compareTo(other.listId ?: 0) ?: -1
    }

    companion object {
        /**
         * Filtering logic is included as a static method to filter out
         * null or empty values for name field.
         *
         * @param unfilteredList        the unfiltered list to filter
         *
         * @return          a filtered list of ListItem objects
         * where the name property is non-null and non-empty
         */
        fun filterListItems(unfilteredList: List<ListItem>): List<ListItem> {
            return unfilteredList.filter { listItem ->
                !listItem.name.isNullOrEmpty()
            }
        }
    }
}
