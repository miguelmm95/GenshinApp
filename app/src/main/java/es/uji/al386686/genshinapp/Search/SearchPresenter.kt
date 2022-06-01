package es.uji.al386686.genshinapp.Search

import es.uji.al386686.genshinapp.DataBase.GICharacter
import es.uji.al386686.genshinapp.DataBase.Weapon
import es.uji.al386686.genshinapp.SearchInfo
import es.uji.al386686.genshinapp.SpecifySearch

class SearchPresenter(val view: SearchActivity, info: SearchInfo, val model: SearchModel) {

    init {
        if (info.isCharacter) {
            model.getCharacter({ characters ->
                view.showCharacter(characters)
            }, { error -> view.showError(error.toString()) })
        } else if (info.isWeapon) {
            model.getWeapon({ weapons ->
                view.showWeapon(weapons)
            }, { error -> view.showError(error.toString()) })
        }
    }

    fun getCharacterName(character: GICharacter) {
        model.characterName = character.name
        model.isCharacter = true
        startSearch()
    }

    fun getWeaponName(weapon: Weapon) {
        model.weaponName = weapon.name
        model.isWeapon = true
        startSearch()
    }

    private fun startSearch() {
        view.onAdapterPressed(model.specifySearch)
    }
}