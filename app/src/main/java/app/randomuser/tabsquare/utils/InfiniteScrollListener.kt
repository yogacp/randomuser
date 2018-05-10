package app.randomuser.tabsquare.utils

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

abstract class InfiniteScrollListener(val mLayoutManager : RecyclerView.LayoutManager) : RecyclerView.OnScrollListener() {

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    var visibleThreshold = 10
    // The current offset index of data you have loaded
    var currentPage = 0
    // The total number of items in the dataset after the last load
    var previousTotalItemCount = 0
    // True if we are still waiting for the last set of data to load.
    var loading = true
    // Sets the starting page index
    var startingPageIndex = 0

    init {
        when(mLayoutManager) {
            is GridLayoutManager -> {
                visibleThreshold = visibleThreshold * mLayoutManager.spanCount
            }
            is StaggeredGridLayoutManager -> {
                visibleThreshold = visibleThreshold * mLayoutManager.spanCount
            }
        }
    }

    fun getLastVisibleItem(lastVisibleItemPositions: IntArray) : Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            maxSize = lastVisibleItemPositions.get(i)
        }
        return maxSize
    }

    // Call this method whenever performing new searches
    fun resetState() {
        currentPage = startingPageIndex
        previousTotalItemCount = 0
        loading = true
    }

    // Defines the process for actually loading more data based on page
    abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView)


    // This happens many times a second during a scroll, so be wary of the code you place here.
    // We are given a few useful parameters to help us work out if we need to load some more data,
    // but first we check if we are waiting for the previous load to finish.
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        var lastVisibleItemPosition = 0
        val totalItemCount = mLayoutManager.itemCount

        when(mLayoutManager) {
            is StaggeredGridLayoutManager -> {
                // get maximum element within the list
                val lastVisibleItemPositions = mLayoutManager.findLastVisibleItemPositions(null)
                lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
            }
            is GridLayoutManager -> {
                lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition()
            }
            is LinearLayoutManager -> {
                lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition()
            }
        }

        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        if(totalItemCount < previousTotalItemCount) {
            currentPage = startingPageIndex
            previousTotalItemCount = totalItemCount

            if(totalItemCount == 0) {
                loading = true
            }
        }

        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        if(loading && (totalItemCount > previousTotalItemCount)) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        if(!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            currentPage++
            onLoadMore(currentPage, totalItemCount, recyclerView)
            loading = true
        }
    }
}