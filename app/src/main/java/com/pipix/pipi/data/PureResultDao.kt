package com.pipix.pipi.data
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface PureResultDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPureResult(pr: PureResult)

    @Delete
    suspend fun deletePureResult(pr: PureResult)

    @Query("SELECT * FROM pure_result_table ORDER BY date ASC")
    fun readAllPureData(): LiveData<List<PureResult>>

    @Query("SELECT * FROM pure_result_table  WHERE oldID = oldID ORDER BY date ASC")
    fun readAllLOldData(oldID : Int): LiveData<List<PureResult>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSpeechResult(sr: SpeechResult)

    @Delete
    suspend fun deleteSpeechResult(sr: SpeechResult)

    @Query("SELECT * FROM speech_result_table ORDER BY id ASC")
    fun readAllSpeechData(): LiveData<List<SpeechResult>>
}