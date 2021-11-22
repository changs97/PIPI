package com.pipix.pipi.data
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface PureResultDao {
    // Pure Result
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPureResult(pr: PureResult)

    @Delete
    suspend fun deletePureResult(pr: PureResult)

    @Query("SELECT * FROM pure_result_table ORDER BY date ASC")
    fun readAllPureData(): LiveData<List<PureResult>>

    @Query("SELECT * FROM pure_result_table ORDER BY date ASC")
    fun getAllPureData(): MutableList<PureResult>


    // Old
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOld(old: Old)

    @Delete
    suspend fun deleteOld(old: Old)

    @Query("SELECT * FROM old_table ORDER BY oldID ASC")
    fun readAllOld(): LiveData<List<Old>>

    @Query("SELECT * FROM old_table ORDER BY oldID ASC")
    fun getAllOld(): MutableList<Old>



    // 미사용
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSpeechResult(sr: SpeechResult)

    @Delete
    suspend fun deleteSpeechResult(sr: SpeechResult)

    @Query("SELECT * FROM speech_result_table ORDER BY id ASC")
    fun readAllSpeechData(): LiveData<List<SpeechResult>>
}