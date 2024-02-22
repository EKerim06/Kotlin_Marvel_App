package com.example.marvel_app.charlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.marvel_app.models.CharacterResponse
import com.example.marvel_app.paging.CharacterPagingSource
import com.example.marvel_app.service.RetrofitInstance
import com.example.marvel_app.utils.ApiKey
import com.example.marvel_app.utils.getValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: RetrofitInstance) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _myPost = MutableLiveData<CharacterResponse>()
    val mypost: LiveData<CharacterResponse> get() = _myPost


    private val _isError = MutableLiveData<Boolean>()

    val isError: LiveData<Boolean> get() = _isError

  init {
      fetchChar()
  }


    fun fetchChar() {
        viewModelScope.launch {
            _isLoading.value = true

            val response: Response<CharacterResponse> =
                RetrofitInstance.api.fetchCharacters(ApiKey.API_KEY.getValue(), 30, 1)

//          val errorBodyString = response.errorBody()?.toString()
//          println("errorBody kodu: $errorBodyString")
//          println(response.body())
//          println(response.code())


            if (response.isSuccessful) {
//              print("Response is succes")
                response.body()?.let { pst ->
                    _myPost.value = pst
                    _isLoading.value = false
                    _isError.value = false
                } ?: run {
                    _isLoading.value = false
                    _isError.value = false
                }
            } else {
//              println("response is error")
                _isLoading.value = false
                _isError.value = true
            }


        }
    }


}