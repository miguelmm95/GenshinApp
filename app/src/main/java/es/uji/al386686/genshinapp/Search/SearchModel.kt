package es.uji.al386686.genshinapp.Search

import android.content.Context
import android.util.Log
import com.android.volley.Response
import es.uji.al386686.genshinapp.DataBase.DataBase
import es.uji.al386686.genshinapp.DataBase.GICharacter
import es.uji.al386686.genshinapp.Search.SearchActivity.Companion.info
import es.uji.al386686.genshinapp.SearchInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchModel(applicationContext: Context, info : SearchInfo) {

    private val dataBase = DataBase.getInstance(applicationContext)
    private val info = info

    fun getCharacter(listener: Response.Listener<List<GICharacter>>,
                     errorListener: Response.ErrorListener) =
            GlobalScope.launch(Dispatchers.Main) {
                val characters = withContext(Dispatchers.IO){
                    dataBase.dao.getCharactersByVision(info.vision)
                }
                Log.d("TEST", characters.toString())
                listener.onResponse(characters)
    }
}