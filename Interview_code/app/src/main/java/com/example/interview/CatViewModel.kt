package com.example.interview

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interview.models.Cat
import com.google.gson.Gson

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class CatViewModel : ViewModel() {


    val urlLiveData: MutableLiveData<Cat> by lazy {
        MutableLiveData<Cat>()
    }


    fun fetchUrl(url: String) {
        viewModelScope.launch {

            withContext(Dispatchers.IO) {
                fetchAsync(url)
            }
        }
    }

    private fun fetchAsync(url: String) {
        val jsonStr = URL(url).readText()

        val subStr = jsonStr.subSequence(1, jsonStr.length - 1)

        val cat = Gson().fromJson(subStr.toString(), Cat::class.java)

        urlLiveData.postValue(cat)

    }

}

