package com.example.mvvm_compose_di.data.datasource.remote.movies

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mvvm_compose_di.data.datasource.remote.APIServices
import com.example.mvvm_compose_di.data.model.MovieItem
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PopularMoviesPagingDataSource @Inject constructor(val apiService: APIServices) :
    PagingSource<Int, MovieItem>() {

    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        return try {
            val nextPage = params.key ?: 1
            val data = apiService.getPopularMovies(page = nextPage)

            LoadResult.Page(
                data = data.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (data.results.isNotEmpty()) data.page + 1 else null
            )
        } catch (ex: HttpException) {
            print("exception ${ex.message}")
            LoadResult.Error(ex)
        } catch (exception: IOException) {
            print("exception ${exception.message}")
            LoadResult.Error(exception)
        } catch (exception: IllegalArgumentException) {
            print("exception ${exception.message}")
            LoadResult.Error(exception)
        }
    }
}