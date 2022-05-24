package es.uji.al386686.genshinapp.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "weaponstype", indices = [Index(value = ["type"], unique = true)])
data class WeaponType(
        @PrimaryKey @ColumnInfo(name = "type") val type: String) {

    override fun toString(): String {
        return type
    }
}