package es.uji.al386686.genshinapp.Search

import android.content.Context
import android.util.Log
import com.android.volley.Response
import es.uji.al386686.genshinapp.DataBase.DataBase
import es.uji.al386686.genshinapp.DataBase.GICharacter
import es.uji.al386686.genshinapp.DataBase.Weapon
import es.uji.al386686.genshinapp.Search.SearchActivity.Companion.info
import es.uji.al386686.genshinapp.SearchInfo
import es.uji.al386686.genshinapp.SpecifySearch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchModel(applicationContext: Context, info: SearchInfo) {

    private val dataBase = DataBase.getInstance(applicationContext)
    private val info = info


    var characterName : String = ""
    var weaponName: String = ""
    var isCharacter: Boolean = false
    var isWeapon: Boolean = false

    val specifySearch get() = SpecifySearch(characterName,weaponName, null, isCharacter, isWeapon)

    fun getCharacter(listener: Response.Listener<List<GICharacter>>,
                     errorListener: Response.ErrorListener) =
            GlobalScope.launch(Dispatchers.Main) {
                val characters = withContext(Dispatchers.IO) {
                    if (info.characterVision) {
                        dataBase.dao.getCharactersByVision(info.vision)
                    } else if (info.characterWeapon) {
                        dataBase.dao.getCharactersByWeaponType(info.weaponType)
                    } else {
                        dataBase.dao.getCharactersByVisionAndWeaponType(info.weaponType, info.vision)
                    }
                }
                listener.onResponse(characters)
            }

    fun getWeapon(listener: Response.Listener<List<Weapon>>,
                  errorListener: Response.ErrorListener) =
            GlobalScope.launch(Dispatchers.Main) {
                val weapons = withContext(Dispatchers.IO) {
                    dataBase.dao.getWeaponsByType(info.weaponType)
                }
                listener.onResponse(weapons)
            }
}