package es.uji.al386686.genshinapp.Main

import android.view.View
import android.widget.RadioButton
import es.uji.al386686.genshinapp.DataBase.*

class GenshinPresenter(val view: MainActivity, val model: GenshinModel) {

    init {
        model.getCharacter({ characters ->
            view.showCharacters(characters)
        }, { error -> view.showError(error.toString()) })

        model.getVision({ visions ->
            view.showVisions(visions)
        }, { error -> view.showError(error.toString()) })

        model.getWeapon({ weapons ->
            view.showWeapons(weapons)
        }, { error -> view.showError(error.toString()) })

        model.getWeaponType({ weaponsType ->
            view.showWeaponsType(weaponsType)
        }, { error -> view.showError(error.toString())})

        model.getArtifact({ artifacts ->
            view.showArtifacts(artifacts)
        }, { error -> view.showError(error.toString()) })
    }

    fun setChosenCharacter(character: GICharacter) {
    }

    fun setChosenWeapon(weapon: Weapon) {
    }

    fun setChosenArtifact(artifact: Artifact) {
    }

    fun setChosenVision(vision: Vision) {
    }

    fun setChosenWeaponType(weaponType: WeaponType) {
    }

}
