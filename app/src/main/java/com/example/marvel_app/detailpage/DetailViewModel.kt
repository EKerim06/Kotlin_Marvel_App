package com.example.marvel_a

import ComicResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel_app.service.RetrofitInstance
import com.example.marvel_app.utils.ApiKey
import com.example.marvel_app.utils.getValue
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class DetailViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _detail = MutableLiveData<ComicResult>()
    val detailData: LiveData<ComicResult> get() = _detail

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> get() = _isError

    private val _filteredList = MutableLiveData<ArrayList<String>>()
    val filteredList: LiveData<ArrayList<String>> get() = _filteredList

    val _dummyList:ArrayList<String> = ArrayList()

    init {
        _filteredList.value = ArrayList()
    }

    fun fetchDetail(id: String) {
        viewModelScope.launch {
            _isLoading.value = true

            val response: Response<ComicResult> =
                RetrofitInstance.api.fetchComics(id, ApiKey.API_KEY.getValue())

            if (response.isSuccessful) {

                response.body()?.let { dtl ->


                    for (item in dtl.data?.results!!) {
                        val date = item.dates?.get(0)?.date

                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX")
                        val dateTime = OffsetDateTime.parse(date, formatter)

                        if (dateTime.year >= 2005 && dateTime != null && (_dummyList.size) < 10) {
                            item.title?.let { _dummyList.add(it) }
                        }
                    }

                    _filteredList.value = _dummyList

//                    _detail.value = dummyList
                    _detail.value = dtl
                    _isLoading.value = false
                    _isError.value = false
                } ?: run {
                    println("Response null error")
                    _isLoading.value = false
                    _isError.value = true
                }

            } else {
                _isLoading.value = false
                _isError.value = true
                val errorBodyString = response.errorBody()?.toString()
                println("errorBody kodu: $errorBodyString")
                println(response.body())
                println(response.code())
            }


        }
    }


}


