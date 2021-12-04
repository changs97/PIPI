package com.pipix.pipi.data

import androidx.lifecycle.LiveData

class PRRepository(private val pureResultDao: PureResultDao) {

    // PureResult
    val readAllPureData: LiveData<List<PureResult>> = pureResultDao.readAllPureData()

    suspend fun addPureResult(pr: PureResult){
        pureResultDao.addPureResult(pr)
    }

    suspend fun deletePureResult(pr: PureResult){
        pureResultDao.deletePureResult(pr)
    }

    suspend fun deleteAllPureData(){
        pureResultDao.deleteAllPureResult()
    }

    fun getAllPureData(): MutableList<PureResult>{
        return pureResultDao.getAllPureData()
    }


    // Old
    val readAllOld: LiveData<List<Old>> = pureResultDao.readAllOld()

    suspend fun addOld(old: Old){
        pureResultDao.addOld(old)
    }

    suspend fun deleteOld(old: Old){
        pureResultDao.deleteOld(old)
    }

    suspend fun deleteAllOld(){
        pureResultDao.deleteAllOld()
    }

    fun getAllOld(): MutableList<Old>{
        return pureResultDao.getAllOld()
    }

    // 미사용
    val readAllSpeechData: LiveData<List<SpeechResult>> = pureResultDao.readAllSpeechData()
    suspend fun deleteSpeechResult(sr: SpeechResult){
        pureResultDao.deleteSpeechResult(sr)
    }

    suspend fun addSpeechResult(sr: SpeechResult){
        pureResultDao.addSpeechResult(sr)
    }
}