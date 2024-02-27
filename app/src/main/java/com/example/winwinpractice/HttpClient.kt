package com.example.winwinpractice

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class HttpClient() {
    private val client = OkHttpClient().newBuilder().build()

    private val _totalCount = mutableIntStateOf(0)
    val totalCount: State<Int>
        get() = _totalCount

    private val _items = mutableStateOf<List<ItemClass>>(emptyList())
    val items: State<List<ItemClass>>
        get() = _items

    init {
        val request: Request = Request.Builder()
            .url("https://raw.githubusercontent.com/winwiniosapp/interview/main/interview.json")
            .get()
            .build()
        val call: Call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body!!.string()
                val Jobject = JSONObject(result)
                _totalCount.intValue = Jobject.getJSONObject("data").getInt("totalCount")
                val Jarray = Jobject.getJSONObject("data").getJSONArray("items")
                val listType = object : TypeToken<ArrayList<ItemClass?>?>() {}.type
                _items.value = Gson().fromJson(Jarray.toString(), listType)
            }
        })
    }
}