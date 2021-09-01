package com.cl.core.database

import androidx.room.*


@Dao
interface RepoDao {
    @Query("SELECT * FROM repository")
    fun getAll(): List<RepositoryModel>

    @Query("SELECT * FROM repository WHERE id IN (:repoIds)")
    fun loadAllByIds(repoIds: IntArray): List<RepositoryModel>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(repos: List<RepositoryModel>)

    @Delete
    fun delete(repo: RepositoryModel)

    @Query("SELECT * FROM repository WHERE name Like :searchKeyword")
    fun findByKeyword(searchKeyword: String): List<RepositoryModel>
}

