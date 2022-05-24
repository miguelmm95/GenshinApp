package es.uji.al386686.genshinapp.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "weapons", indices = [Index(value = ["id"], unique = true)])
data class Weapon(
        @PrimaryKey @ColumnInfo(name = "id") val id: Int,
        val name: String,
        val rarity: String,
        val atk: Int,
        val type: String) {

    override fun toString(): String {
        return name
    }
}