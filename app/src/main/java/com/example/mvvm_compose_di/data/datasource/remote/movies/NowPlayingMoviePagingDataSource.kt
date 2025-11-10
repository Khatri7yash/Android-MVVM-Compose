package com.example.mvvm_compose_di.data.datasource.remote.movies

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mvvm_compose_di.data.datasource.remote.APIServices
import com.piashcse.hilt_mvvm_compose_movie.data.model.MovieItem
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NowPlayingMoviePagingDataSource @Inject constructor(val apiService: APIServices) :
    PagingSource<Int, MovieItem>() {
    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        return try {
            val nextPage = params.key ?: 1
            val movies = apiService.getMovies(nextPage)
            LoadResult.Page(
                data = movies.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (movies.results.isNotEmpty()) movies.page + 1 else null
            )
        } catch (ex: HttpException) {
            print("exception ${ex.message}")
            LoadResult.Error(ex)
        } catch (exception: IOException) {
            print("exception ${exception.message}")
            return LoadResult.Error(exception)
        }
    }


}