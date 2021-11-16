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
    val readAllSpeechData: LiveData<List<SpeechResult>>
    private val repository: PRRepository

    init{
        val pureResultDao = PRDatabase.getDatabase(applications).pureResultDao()
        repository = PRRepository(pureResultDao)
        readAllPureData = repository.readAllPureData
        readAllSpeechData = repository.readAllSpeechData
    }

    fun deletePureResult(pr: PureResult){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePureResult(pr)
        }
    }

    fun addPureResult(pr: PureResult){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPureResult(pr)
        }
    }

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