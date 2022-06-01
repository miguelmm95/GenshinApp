package es.uji.al386686.genshinapp.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "characters", indices = [Index(value = ["name"], unique = true)])
data class GICharacter(
        @PrimaryKey @ColumnInfo(name = "name") val name: String,
        val description: String,
        val gender: String,
        val birthday: String,
        @ColumnInfo(name = "visionType") val visionType: String,
        @ColumnInfo(name = "weapon") val weapon: String,
        val rarity: String
) {

    override fun toString(): String {
        return name
    }

}
