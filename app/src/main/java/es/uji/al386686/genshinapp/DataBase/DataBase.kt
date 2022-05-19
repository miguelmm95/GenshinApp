package es.uji.al386686.genshinapp.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.uji.al386686.genshinapp.Network.SingletonHolder

class DataBase private constructor(context: Context){

    companion object: SingletonHolder<DataBase, Context>(::DataBase)
    val dao : DAO

    init{
        val db = Room.databaseBuilder(context, AbstractDatabase::class.java,"database").build()
        dao = db.getDAO()
    }
}

@Database(
    entities = [
        GICharacter::class
    ],
    version = 1
)
abstract class AbstractDatabase: RoomDatabase() {
    abstract fun getDAO(): DAO
}