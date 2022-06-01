package es.uji.al386686.genshinapp.Search

import es.uji.al386686.genshinapp.DataBase.GICharacter
import es.uji.al386686.genshinapp.DataBase.Weapon
import es.uji.al386686.genshinapp.SpecifySearch

interface ISearchActivity {
    fun showCharacter(characters: List<GICharacter>)
    fun showWeapon(weapons: List<Weapon>)
    fun onAdapterPressed(specifySearch: SpecifySearch)
}