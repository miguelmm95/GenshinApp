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
        }, { error -> view.showError(error.toString()) })

        model.getArtifact({ artifacts ->
            view.showArtifacts(artifacts)
        }, { error -> view.showError(error.toString()) })
    }

    fun setChosenCharacter(character: GICharacter) {
        if (character != null) {
            view.enableCharacterSearchButton = true
        }
    }

    fun setChosenWeapon(weapon: Weapon) {
        if (weapon != null) {
            view.enableWeaponSearchButton = true
        }
    }

    fun setChosenArtifact(artifact: Artifact) {
        if (artifact != null) {
            view.enableArtifactSearchButton = true
        }
    }

    fun setChosenVision(vision: Vision) {
        if (vision != null) {
            view.enableCharacterVisionSearchButton = true
            if (view.enableCharacterTypeWeaponSearchButton) {
                view.enableVisionWeaponTypeSearch = true
            }
        }
    }

    fun setChosenWeaponType(weaponType: WeaponType) {
        if (weaponType != null) {
            view.enableCharacterTypeWeaponSearchButton = true
            view.enableWeaponTypeSearchButton = true

            if (view.enableCharacterVisionSearchButton) {
                view.enableVisionWeaponTypeSearch = true
            }
        }
    }

    fun makeCharacterVisible() {
        view.weaponVisible = false
        view.characterVisible = true
        view.artifactVisible = false
    }

    fun makeWeaponVisible() {
        view.characterVisible = false
        view.weaponVisible = true
        view.artifactVisible = false
    }

    fun makeArtifactVisible() {
        view.artifactVisible = true
        view.characterVisible = false
        view.weaponVisible = false
    }

}