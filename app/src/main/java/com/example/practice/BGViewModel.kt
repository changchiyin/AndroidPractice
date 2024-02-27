package com.example.practice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class BGViewModel:ViewModel() {
    private var client = OkHttpClient().newBuilder().build()
    private var listTemp = mutableListOf<BGDataClass>()
    var listLiveData : MutableLiveData<List<BGDataClass>> = MutableLiveData()

    init {
        getData(0)
    }

    fun getData(offset:Int = 0){
        val request: Request = Request.Builder()
            .url("https://data.taipei/api/v1/dataset/f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire&offset=${offset}")
            .get()
            .build()
        val call: Call = client.newCall(request)

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body!!.string().replace("\uFEFF", "")
                val Jobject = JSONObject(result)
                val Jarray = Jobject.getJSONObject("result").getJSONArray("results")
                val listType = object : TypeToken<ArrayList<BGDataClass?>?>() {}.type
                listTemp = listLiveData.value?.toMutableList() ?: mutableListOf()
                listTemp.addAll(Gson().fromJson<MutableList<BGDataClass>>(Jarray.toString(), listType))
                listLiveData.postValue(listTemp.toList())
            }
        })
    }
}