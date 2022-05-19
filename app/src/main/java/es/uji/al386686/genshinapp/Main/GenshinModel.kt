package es.uji.al386686.genshinapp.Main

import android.content.Context
import com.android.volley.Response
import es.uji.al386686.genshinapp.DataBase.DataBase
import es.uji.al386686.genshinapp.DataBase.GICharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GenshinModel(applicationContext: Context) {

    private val network = es.uji.al386686.genshinapp.Network.Network.getInstance(applicationContext)
    private val dataBase = DataBase.getInstance(applicationContext)

    fun getCharacter(listener: Response.Listener<List<GICharacter>>,
                    errorListener: Response.ErrorListener) =
        GlobalScope.launch(Dispatchers.Main) {
            val characters = withContext(Dispatchers.IO){
                dataBase.dao.getCharacters()
            }
            if(characters.isEmpty()){
                network.getCharacters({
                    GlobalScope.launch {
                        dataBase.dao.addCharacter(it)
                    }
                    listener.onResponse(it)
                },{
                    errorListener.onErrorResponse(it)
                })
            }else{
                listener.onResponse(characters)
            }
    }



}
