package com.example.marvel_app.paging

import android.net.http.HttpException
import androidx.annotation.RequiresApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvel_app.models.CharacterResponse
import com.example.marvel_app.service.RetrofitInstance
import com.example.marvel_app.utils.ApiKey
import com.example.marvel_app.utils.LimitValue
import com.example.marvel_app.utils.getValue

class CharacterPagingSource(private val repository: RetrofitInstance) :
    PagingSource<Int, CharacterResponse>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


    @RequiresApi(34)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponse> {
        return try {
            val currentPage = params.key ?: 1
            //burda offset mantigi oldugu icin sonraki sayfa icin 30*1, 30*2 gibi islem yapmak lazim.
            val response = repository.api.fetchCharacters(
                ApiKey.API_KEY.getValue(),
                LimitValue.NORMAL.getValue(),
                currentPage * 30
            )
            val data = response.body()
            val responseData = mutableListOf<CharacterResponse>()
            if (data != null) responseData.add(data)


            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (httpE: HttpException) {
            LoadResult.Error(httpE)

        }
    }


}