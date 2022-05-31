package es.uji.al386686.genshinapp.Main

import android.content.Context
import android.provider.Settings
import android.text.BoringLayout
import android.util.Log
import com.android.volley.Response
import es.uji.al386686.genshinapp.DataBase.*
import es.uji.al386686.genshinapp.SearchInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GenshinModel(applicationContext: Context) {

    private val network = es.uji.al386686.genshinapp.Network.Network.getInstance(applicationContext)
    private val dataBase = DataBase.getInstance(applicationContext)

    var isCharacter: Boolean = false
    var characterVision: Boolean = false
    var characterWeapon: Boolean = false
    var isWeapon: Boolean = false
    var isArtifact: Boolean = false
    var vision: String = ""
    var weaponType: String = ""
    var characterName: String = ""
    var weaponName: String = ""
    var artifactName: String = ""

    val searchInfo get() = SearchInfo(isCharacter, characterVision, characterWeapon, isWeapon, isArtifact, vision, weaponType, characterName, weaponName, artifactName)

    fun getCharacter(listener: Response.Listener<List<GICharacter>>,
                     errorListener: Response.ErrorListener) =
            GlobalScope.launch(Dispatchers.Main) {
                val characters = withContext(Dispatchers.IO) {
                    dataBase.dao.getCharacters()
                }
                if (characters.isEmpty()) {
                    network.getCharacters({
                        GlobalScope.launch {
                            dataBase.dao.addCharacter(it)
                        }
                        listener.onResponse(it)
                    }, {
                        errorListener.onErrorResponse(it)
                    })
                } else {
                    listener.onResponse(characters)
                }
            }

    fun getVision(listener: Response.Listener<List<Vision>>,
                  errorListener: Response.ErrorListener) =
            GlobalScope.launch(Dispatchers.Main) {
                var visions = withContext(Dispatchers.IO) {
                    dataBase.dao.getVisions()
                }
                if (visions.isEmpty()) {
                    visions = withContext(Dispatchers.IO) {
                        dataBase.dao.getVisionsFromCharacters()
                    }
                    listener.onResponse(visions)
                } else {
                    listener.onResponse(visions)
                }
            }

    fun getWeapon(listener: Response.Listener<List<Weapon>>,
                  errorListener: Response.ErrorListener) =
            GlobalScope.launch(Dispatchers.Main) {
                val weapons = withContext(Dispatchers.IO) {
                    dataBase.dao.getWeapons()
                }
                if (weapons.isEmpty()) {
                    network.getWeapons({
                        GlobalScope.launch {
                            dataBase.dao.addWeapons(it)
                        }
                        listener.onResponse(it)
                    }, {
                        errorListener.onErrorResponse(it)
                    })
                } else {
                    listener.onResponse(weapons)
                }
            }

    fun getWeaponType(listener: Response.Listener<List<WeaponType>>,
                      errorListener: Response.ErrorListener) =
            GlobalScope.launch(Dispatchers.Main) {
                var weaponTypes = withContext(Dispatchers.IO) {
                    dataBase.dao.getWeaponsType()
                }
                if (weaponTypes.isEmpty()) {
                    weaponTypes = withContext(Dispatchers.IO) {
                        dataBase.dao.getTypeFromWeapons()
                    }
                    listener.onResponse(weaponTypes)
                } else {
                    listener.onResponse(weaponTypes)
                }

            }

    fun getArtifact(listener: Response.Listener<List<Artifact>>,
                    errorListener: Response.ErrorListener) =
            GlobalScope.launch(Dispatchers.Main) {
                val artifacts = withContext(Dispatchers.IO) {
                    dataBase.dao.getArtifacts()
                }
                if (artifacts.isEmpty()) {
                    network.getArtifacts({
                        GlobalScope.launch {
                            dataBase.dao.addArtifacts(it)
                        }
                        listener.onResponse(it)
                    }, {
                        errorListener.onErrorResponse(it)
                    })
                } else {
                    listener.onResponse(artifacts)
                }
            }
}

