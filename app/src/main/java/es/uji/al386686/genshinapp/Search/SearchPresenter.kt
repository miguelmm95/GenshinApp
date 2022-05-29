package es.uji.al386686.genshinapp.Search

import es.uji.al386686.genshinapp.SearchInfo

class SearchPresenter(val view: SearchActivity, info: SearchInfo, val model: SearchModel) {

    init {
        if (info.isCharacter){
            model.getCharacter({ characters ->
                view.showCharacter(characters)
            }, { error -> view.showError(error.toString())})
        }
    }
}