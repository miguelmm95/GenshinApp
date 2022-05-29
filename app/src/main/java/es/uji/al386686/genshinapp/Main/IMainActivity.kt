package es.uji.al386686.genshinapp.Main

import es.uji.al386686.genshinapp.DataBase.*
import es.uji.al386686.genshinapp.SearchInfo

interface IMainActivity {
    var characterVisible: Boolean
    var weaponVisible: Boolean
    var artifactVisible: Boolean

    var enableCharacterSearchButton: Boolean
    var enableCharacterTypeWeaponSearchButton: Boolean
    var enableCharacterVisionSearchButton: Boolean
    var enableWeaponSearchButton: Boolean
    var enableWeaponTypeSearchButton: Boolean
    var enableArtifactSearchButton: Boolean
    var enableVisionWeaponTypeSearch: Boolean

    fun showCharacters(characters: List<GICharacter>)
    fun showWeapons(weapons: List<Weapon>)
    fun showArtifacts(artifacts: List<Artifact>)
    fun showVisions(visions: List<Vision>)
    fun showWeaponsType(weaponType: List<WeaponType>)
    fun onSearchPressed(info: SearchInfo)
}