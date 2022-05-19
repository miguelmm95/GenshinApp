package es.uji.al386686.genshinapp.Main

import es.uji.al386686.genshinapp.DataBase.GICharacter

class GenshinPresenter(view: MainActivity, model: GenshinModel) {

    init {
        model.getCharacter({characters ->
            view.showCharacters(characters)
        }, { error -> view.showError(error.toString())})
    }

    fun setChosenCharacter(character: GICharacter) {
    }

}
