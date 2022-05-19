package es.uji.al386686.genshinapp.Network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import es.uji.al386686.genshinapp.DataBase.DataBase
import es.uji.al386686.genshinapp.DataBase.GICharacter
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

private const val BASEURL = "https://genshinlist.com/api"
private const val CHARACTER_NAME = "name"

class Network private constructor(context: Context){

    companion object : SingletonHolder<Network, Context>(::Network)

    private val database = DataBase.getInstance(context)

    val queue = Volley.newRequestQueue(context)


    fun getCharacters(listener: Response.Listener<List<GICharacter>>, errorListener: Response.ErrorListener) {
        val url = "$BASEURL/characters"

        val request = JsonArrayRequest(Request.Method.GET,url,null,{processCharacters(it,listener,errorListener)}, errorListener)
        queue.add(request)
    }

    private fun processCharacters(it: JSONArray, listener: Response.Listener<List<GICharacter>>, errorListener: Response.ErrorListener) {
        val characters = ArrayList<GICharacter>()
        try {
            for (i in 0 until it.length()){
                val characterObject = it[i] as JSONObject
                val name = characterObject.getString(CHARACTER_NAME)
                characters.add(GICharacter(name))
            }
        }catch (e:JSONException){
            errorListener.onErrorResponse(VolleyError("BAD JSON FORMAT"))
            return
        }
        characters.sortBy { it.name }
        listener.onResponse(characters)
    }
}