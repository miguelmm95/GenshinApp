package es.uji.al386686.genshinapp.Info

import es.uji.al386686.genshinapp.DataBase.Artifact
import es.uji.al386686.genshinapp.DataBase.GICharacter
import es.uji.al386686.genshinapp.DataBase.Weapon

interface IInfoActivity {
    fun showCharacterInfo(character: GICharacter)
    fun showWeaponInfo(weapon: Weapon)
    fun showArtifact(artifact: Artifact)

    var artifactVisible: Boolean
    var characterVisible: Boolean
    var weaponVisible: Boolean
}