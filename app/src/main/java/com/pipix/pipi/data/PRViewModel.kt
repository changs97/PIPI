package com.pipix.pipi.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PRViewModel(applications: Application): AndroidViewModel(applications) {


    val readAllPureData: LiveData<List<PureResult>>
    val readAllOld: LiveData<List<Old>>
    var currentOldID: Int = 0

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean>
        get() = _isSuccess

    private val repository: PRRepository

    init{
        val pureResultDao = PRDatabase.getDatabase(applications).pureResultDao()
        repository = PRRepository(pureResultDao)
        readAllPureData = repository.readAllPureData
        readAllOld = repository.readAllOld
        _isSuccess.value = false
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

    fun deleteAllPureResult(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllPureData()
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

    fun deleteAllOld(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllOld()
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