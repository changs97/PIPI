package com.pipix.pipi.data
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface PureResultDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPureResult(pr: PureResult)

    @Delete
    suspend fun deletePureResult(pr: PureResult)

    @Query("SELECT * FROM result_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<PureResult>>
}