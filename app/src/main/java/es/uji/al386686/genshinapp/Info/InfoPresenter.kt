package es.uji.al386686.genshinapp.Info

import es.uji.al386686.genshinapp.SpecifySearch

class InfoPresenter(val view: InfoActivity, val model: InfoModel, info: SpecifySearch) {

    init {
        if (info.isCharacter) {
            model.getCharacterInfo({ character ->
                view.showCharacterInfo(character)
            }, { error -> view.showError(error.toString()) })
        } else if (info.isWeapon) {
            model.getWeaponInfo({ weapon ->
                view.showWeaponInfo(weapon)
            }, { error -> view.showError(error.toString()) })
        } else {
            model.getArtifactInfo({artifact ->
                view.showArtifact(artifact)
            }, { error -> view.showError(error.toString())})
        }
    }

    fun makeCharacterVisible() {
        view.characterVisible = true
    }

    fun makeWeaponVisible() {
        view.weaponVisible = true
    }

    fun makeArtifactVisible() {
        view.artifactVisible = true
    }

}