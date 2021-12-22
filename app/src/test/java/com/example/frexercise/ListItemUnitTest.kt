package com.example.frexercise

import com.example.frexercise.models.ListItem
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * A few unit tests designed to test sorting & filtering
 * logic of ListItem model class.
 */
class ListItemUnitTest {
    @Test
    fun `test sorting when one list id value is null`() {
        val list: List<ListItem> = listOf(
            ListItem(1, 1, "some value"),
            ListItem(0, null, "some value")
        )

        val sortedList = list.sorted()
        assertEquals(0, sortedList[0].itemId)
    }

    @Test
    fun `test sorting when both list id values are null`() {
        val list: List<ListItem> = listOf(
            ListItem(1, null, "b"),
            ListItem(0, null, "a")
        )

        val sortedList = list.sorted()
        assertEquals(0, sortedList[0].itemId)
    }

    @Test
    fun `test sorting when list id values are the same but one name value is null`() {
        val list: List<ListItem> = listOf(
            ListItem(1, 1, "a"),
            ListItem(0, 1, null)
        )

        val sortedList = list.sorted()
        assertEquals(0, sortedList[0].itemId)
    }

    @Test
    fun `test sorting when list id values are the same and name values are both null`() {
        val list: List<ListItem> = listOf(
            ListItem(0, 1, null),
            ListItem(1, 1, null)
        )

        val sortedList = list.sorted()
        assertEquals(1, sortedList[0].itemId)
    }

    @Test
    fun `test sorting when both list id values are the same but not name values`() {
        val list: List<ListItem> = listOf(
            ListItem(1, 1, "b"),
            ListItem(0, 1, "a")
        )

        val sortedList = list.sorted()
        assertEquals(0, sortedList[0].itemId)
    }

    @Test
    fun `test sorting when both list id values and name values are the same`() {
        val list: List<ListItem> = listOf(
            ListItem(1, 1, "a"),
            ListItem(0, 1, "a")
        )

        val sortedList = list.sorted()
        assertEquals(1, sortedList[0].itemId)
    }

    @Test
    fun `test filter when list contains a null name value`() {
        val list: List<ListItem> = listOf(
            ListItem(1, 1, null),
            ListItem(0, 1, "a")
        )

        val filteredList = ListItem.filterListItems(list)
        assertEquals(1, filteredList.size)
    }

    @Test
    fun `test filter when list contains an empty name value`() {
        val list: List<ListItem> = listOf(
            ListItem(1, 1, ""),
            ListItem(0, 1, "a")
        )

        val filteredList = ListItem.filterListItems(list)
        assertEquals(1, filteredList.size)
    }

    @Test
    fun `test filter when list contains values where no filters are needed`() {
        val list: List<ListItem> = listOf(
            ListItem(1, 1, "b"),
            ListItem(0, 1, "a")
        )

        val filteredList = ListItem.filterListItems(list)
        assertEquals(2, filteredList.size)
    }
}