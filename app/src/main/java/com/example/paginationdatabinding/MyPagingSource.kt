package com.example.paginationdatabinding

import androidx.paging.PagingSource
import androidx.paging.PagingState

class MyPagingSource : PagingSource<Int, MyDataModel>() {

//    private val myDataList = java.util.List(100) {
//        MyDataModel("Item $it")
//
//    }

    private val myDataList = List(100) { MyDataModel("Item #$it", "Password $it") }


    override fun getRefreshKey(state: PagingState<Int, MyDataModel>): Int? {
        return state.anchorPosition?.let {
            anchorPsotion ->
            val anchorPage = state.closestPageToPosition(anchorPsotion)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)

        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MyDataModel> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val startIndex = (page - 1) * pageSize
            val endIndex = minOf(startIndex + pageSize, myDataList.size)
            val sublist = myDataList.subList(startIndex, endIndex)
            LoadResult.Page(
                data = sublist,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (endIndex >= myDataList.size) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}