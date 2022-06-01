package es.uji.al386686.genshinapp.Network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import es.uji.al386686.genshinapp.DataBase.Artifact
import es.uji.al386686.genshinapp.DataBase.DataBase
import es.uji.al386686.genshinapp.DataBase.GICharacter
import es.uji.al386686.genshinapp.DataBase.Weapon
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

private const val BASEURL = "https://genshinlist.com/api"
private const val NAME = "name"
private const val RARITY = "rarity"
private const val CHARACTER_DESCRIPTION = "description"
private const val CHARACTER_GENDER = "gender"
private const val CHARACTER_BIRTHDAY = "birthday"
private const val CHARACTER_VISION = "vision"
private const val CHARACTER_WEAPON = "weapon"
private const val WEAPON_ID = "id"
private const val WEAPON_ATK = "atk"
private const val WEAPON_TYPE = "type"
private const val ARTIFACT_ID = "id"
private const val ARTIFACT_2BONUS = "2_set_bonus"
private const val ARTIFACT_4BONUS = "4_set_bonus"

class Network private constructor(context: Context) {

    companion object : SingletonHolder<Network, Context>(::Network)

    private val database = DataBase.getInstance(context)

    val queue = Volley.newRequestQueue(context)


    fun getCharacters(listener: Response.Listener<List<GICharacter>>, errorListener: Response.ErrorListener) {
        val url = "$BASEURL/characters"

        val request = JsonArrayRequest(Request.Method.GET, url, null, { processCharacters(it, listener, errorListener) }, errorListener)
        queue.add(request)
    }

    fun getWeapons(listener: Response.Listener<List<Weapon>>, errorListener: Response.ErrorListener) {
        val url = "$BASEURL/weapons"

        val request = JsonArrayRequest(Request.Method.GET, url, null, { processWeapons(it, listener, errorListener) }, errorListener)
        queue.add(request)
    }

    fun getArtifacts(listener: Response.Listener<List<Artifact>>, errorListener: Response.ErrorListener) {
        val url = "$BASEURL/artifacts"

        val request = JsonArrayRequest(Request.Method.GET, url, null, { processArtifacts(it, listener, errorListener) }, errorListener)
        queue.add(request)
    }

    private fun processCharacters(it: JSONArray, listener: Response.Listener<List<GICharacter>>, errorListener: Response.ErrorListener) {
        val characters = ArrayList<GICharacter>()
        try {
            for (i in 0 until it.length()) {
                val characterObject = it[i] as JSONObject
                val name = characterObject.getString(NAME)
                val description = characterObject.getString(CHARACTER_DESCRIPTION)
                val gender = characterObject.getString(CHARACTER_GENDER)
                val birthday = characterObject.getString(CHARACTER_BIRTHDAY)
                val vision = characterObject.getString(CHARACTER_VISION)
                val weapon = characterObject.getString(CHARACTER_WEAPON)
                val rarity = characterObject.getString(RARITY)
                characters.add(GICharacter(name, description, gender, birthday, vision, weapon,rarity))
            }
        } catch (e: JSONException) {
            errorListener.onErrorResponse(VolleyError("BAD JSON FORMAT"))
            return
        }
        characters.sortBy { it.name }
        listener.onResponse(characters)
    }

    private fun processWeapons(it: JSONArray, listener: Response.Listener<List<Weapon>>, errorListener: Response.ErrorListener) {
        val weapons = ArrayList<Weapon>()
        try {
            for (i in 0 until it.length()) {
                val weaponObject = it[i] as JSONObject
                val id = weaponObject.getString(WEAPON_ID)
                val name = weaponObject.getString(NAME)
                val rarity = weaponObject.getString(RARITY)
                val atk = weaponObject.getString(WEAPON_ATK)
                val type = weaponObject.getString(WEAPON_TYPE)
                weapons.add(Weapon(id.toInt(), name, rarity, atk, type))
            }
        } catch (e: JSONException) {
            errorListener.onErrorResponse(VolleyError("BAD JSON FORMAT"))
            return
        }
        weapons.sortBy { it.name }
        listener.onResponse(weapons)
    }

    private fun processArtifacts(it: JSONArray, listener: Response.Listener<List<Artifact>>, errorListener: Response.ErrorListener) {
        val artifacts = ArrayList<Artifact>()
        try {
            for (i in 0 until it.length()) {
                val artifactObject = it[i] as JSONObject
                val id = artifactObject.getString(ARTIFACT_ID)
                val name = artifactObject.getString(NAME)
                val twoBonus = artifactObject.getString(ARTIFACT_2BONUS)
                val fourBonus = artifactObject.getString(ARTIFACT_4BONUS)
                artifacts.add(Artifact(id.toInt(), name, twoBonus, fourBonus))
            }
        } catch (e: JSONException) {
            errorListener.onErrorResponse(VolleyError("BAD JSON FORMAT"))
            return
        }
        artifacts.sortBy { it.name }
        listener.onResponse(artifacts)
    }
}