package com.example.practice.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practice.Model.BGDataClass
import com.example.practice.Model.BGDataModel

class BGViewModel : ViewModel() {
    private val dataModel = BGDataModel()
    val listLiveData: MutableLiveData<List<BGDataClass>> = MutableLiveData()

    init {
        getData(0)
    }

    fun getData(offset: Int = 0) {
        dataModel.getData(offset, object : BGDataModel.OnDataReadyCallback{
            override fun onDataReady(result: List<BGDataClass>) {
                val listTemp = listLiveData.value?.toMutableList() ?: mutableListOf()
                listTemp.addAll(result)
                listLiveData.postValue(listTemp.toList())
            }
        })
    }

    fun filterData(keyWorlds: String): List<BGDataClass> {
        return listLiveData.value?.filter {
            it.feature.contains(keyWorlds) || it.name.contains(keyWorlds) || it.location.contains(
                keyWorlds
            )
        } ?: emptyList()
    }
}