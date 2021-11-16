package com.pipix.pipi.data

import androidx.lifecycle.LiveData

class PRRepository(private val pureResultDao: PureResultDao) {

    val readAllPureData: LiveData<List<PureResult>> = pureResultDao.readAllPureData()
    val readAllSpeechData: LiveData<List<SpeechResult>> = pureResultDao.readAllSpeechData()

    suspend fun deletePureResult(pr: PureResult){
        pureResultDao.deletePureResult(pr)
    }

    suspend fun addPureResult(pr: PureResult){
        pureResultDao.addPureResult(pr)
    }

    suspend fun deleteSpeechResult(sr: SpeechResult){
        pureResultDao.deleteSpeechResult(sr)
    }

    suspend fun addSpeechResult(sr: SpeechResult){
        pureResultDao.addSpeechResult(sr)
    }
}