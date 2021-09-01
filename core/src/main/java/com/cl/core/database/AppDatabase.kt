package com.cl.core.database

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Database(entities = arrayOf(RepositoryModel::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun repoDao(): RepoDao
}


@Entity(tableName = "Repository")
class RepositoryModel(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String,

    @SerializedName("full_name")
    @ColumnInfo(name = "full_name")
    val fullName: String
) {
    @SerializedName("node_id")
    @ColumnInfo(name = "node_id")
    var nodeId: String? = null

    @SerializedName("private")
    @ColumnInfo(name = "private")
    var private: Boolean? = null

    @SerializedName("forks_count")
    @ColumnInfo(name = "forks_count")
    var forks: Int? = null

    var isFooter: Boolean? = null
}