package es.uji.al386686.genshinapp.DataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
    interface DAO {
        @Insert
        fun addCharacter(chracters : List<GICharacter>)

        @Query("SELECT * FROM characters ORDER BY 'name'")
        fun getCharacters() : List<GICharacter>
    }