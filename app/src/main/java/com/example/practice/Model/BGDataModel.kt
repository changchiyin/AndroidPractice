package com.example.practice.Model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class BGDataModel {
    private var client = OkHttpClient().newBuilder().build()
    interface OnDataReadyCallback {
        fun onDataReady(result: List<BGDataClass>)
    }

    fun getData(offset: Int, callback: OnDataReadyCallback){
        val urlBuilder = "https://data.taipei/api/v1/dataset".toHttpUrl().newBuilder()
            .addPathSegment("f18de02f-b6c9-47c0-8cda-50efad621c14")
            .addQueryParameter("scope", "resourceAquire")
            .addQueryParameter("offset", offset.toString())
        val request: Request = Request.Builder()
            .url(urlBuilder.build().toString())
            .get()
            .build()
        val call: Call = client.newCall(request)

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body!!.string().replace("\uFEFF", "")
                val jObject = JSONObject(result)
                val jArray = jObject.getJSONObject("result").getJSONArray("results")
                val listType = object : TypeToken<ArrayList<BGDataClass?>?>() {}.type
                callback.onDataReady(Gson().fromJson<MutableList<BGDataClass>>(
                    jArray.toString(),
                    listType
                ))
            }
        })
    }
}