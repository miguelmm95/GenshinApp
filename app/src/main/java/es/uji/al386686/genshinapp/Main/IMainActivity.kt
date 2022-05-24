package es.uji.al386686.genshinapp.Main

import es.uji.al386686.genshinapp.DataBase.*

interface IMainActivity {
    fun showCharacters(characters : List<GICharacter>)
    fun showWeapons(weapons : List<Weapon>)
    fun showArtifacts(artifacts : List<Artifact>)
    fun showVisions(visions : List<Vision>)
    fun showWeaponsType(weaponType : List<WeaponType>)
}