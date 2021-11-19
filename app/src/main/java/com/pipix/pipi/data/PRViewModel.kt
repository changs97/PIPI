package com.pipix.pipi.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PRViewModel(applications: Application): AndroidViewModel(applications) {

    //not private
    val readAllPureData: LiveData<List<PureResult>>
    val readAllOld: LiveData<List<Old>>
    lateinit var currentOld: Old

    private val repository: PRRepository

    init{
        val pureResultDao = PRDatabase.getDatabase(applications).pureResultDao()
        repository = PRRepository(pureResultDao)
        readAllPureData = repository.readAllPureData
        readAllOld = repository.readAllOld
    }

    // PureResult
    fun addPureResult(pr: PureResult){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPureResult(pr)
        }
    }

    fun deletePureResult(pr: PureResult){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePureResult(pr)
        }
    }

    fun getAllPureData(): MutableList<PureResult>{
        return repository.getAllPureData()
    }

    // Old
    fun addOld(old: Old){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addOld(old)
        }
    }

    fun deleteOld(old: Old){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteOld(old)
        }
    }

    fun getAllOld(): MutableList<Old>{
        return repository.getAllOld()
    }






    // 미사용
    fun deleteSpeechResult(sr: SpeechResult){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteSpeechResult(sr)
        }
    }

    fun addSpeechResult(sr: SpeechResult){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSpeechResult(sr)
        }
    }
}