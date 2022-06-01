package es.uji.al386686.genshinapp.Info

import android.content.Context
import com.android.volley.Response
import es.uji.al386686.genshinapp.DataBase.Artifact
import es.uji.al386686.genshinapp.DataBase.DataBase
import es.uji.al386686.genshinapp.DataBase.GICharacter
import es.uji.al386686.genshinapp.DataBase.Weapon
import es.uji.al386686.genshinapp.SpecifySearch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InfoModel(applicationContext: Context, info: SpecifySearch) {

    private val dataBase = DataBase.getInstance(applicationContext)
    private val info = info

    fun getCharacterInfo(listener: Response.Listener<GICharacter>,
                         errorListener: Response.ErrorListener) =
            GlobalScope.launch(Dispatchers.Main) {
                val character = withContext(Dispatchers.IO) {
                    dataBase.dao.getCharacterInfo(info.characterName)
                }
                listener.onResponse(character)
            }

    fun getWeaponInfo(listener: Response.Listener<Weapon>,
                      errorListener: Response.ErrorListener) =
            GlobalScope.launch(Dispatchers.Main) {
                val weapon = withContext(Dispatchers.IO) {
                    dataBase.dao.getWeaponInfo(info.weaponName)
                }
                listener.onResponse(weapon)
            }

    fun getArtifactInfo(listener: Response.Listener<Artifact>,
                        errorListener: Response.ErrorListener) =
            GlobalScope.launch(Dispatchers.Main){
                val artifact = withContext(Dispatchers.IO){
                    dataBase.dao.getArtifactInfo(info.artifactName)
                }
                listener.onResponse(artifact)
    }
}