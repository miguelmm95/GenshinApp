package es.uji.al386686.genshinapp.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "artifacts", indices = [Index(value = ["id"], unique = true)])
data class Artifact(
        @PrimaryKey @ColumnInfo(name = "id") val id: Int,
        val name: String,
        val setBonus2: String,
        val setBonus4: String) {

    override fun toString(): String {
        return name
    }
}