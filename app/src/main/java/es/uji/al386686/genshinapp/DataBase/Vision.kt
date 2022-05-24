package es.uji.al386686.genshinapp.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "visions", indices = [Index(value = ["visionType"], unique = true)])
data class Vision(
        @PrimaryKey @ColumnInfo(name = "visionType") val visionType: String) {

    override fun toString(): String {
        return visionType
    }
}