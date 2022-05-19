package es.uji.al386686.genshinapp.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "characters", indices = [Index(value = ["name"], unique = true)])
data class GICharacter(@PrimaryKey @ColumnInfo(name = "name") val name : String) {

    override fun toString(): String {
        return name
    }

}
