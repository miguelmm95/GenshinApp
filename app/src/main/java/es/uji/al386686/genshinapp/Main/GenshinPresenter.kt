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
            model.characterName = character.name
        }
    }

    fun setChosenWeapon(weapon: Weapon) {
        if (weapon != null) {
            view.enableWeaponSearchButton = true
            model.weaponName = weapon.name
        }
    }

    fun setChosenArtifact(artifact: Artifact) {
        if (artifact != null) {
            view.enableArtifactSearchButton = true
            model.artifactName = artifact.name
        }
    }

    fun setChosenVision(vision: Vision) {
        if (vision != null) {
            view.enableCharacterVisionSearchButton = true
            model.vision = vision.visionType

            if (view.enableCharacterTypeWeaponSearchButton) {
                view.enableVisionWeaponTypeSearch = true
            }
        }
    }

    fun setChosenWeaponType(weaponType: WeaponType) {
        if (weaponType != null) {
            view.enableCharacterTypeWeaponSearchButton = true
            view.enableWeaponTypeSearchButton = true
            model.weaponType = weaponType.type

            if (view.enableCharacterVisionSearchButton) {
                view.enableVisionWeaponTypeSearch = true
            }
        }
    }

    fun startSearch() {
        view.onSearchPressed(model.searchInfo)
    }

    fun makeCharacterVisible() {
        view.weaponVisible = false
        view.characterVisible = true
        view.artifactVisible = false

        model.isCharacter = true
        model.isWeapon = false
        model.isArtifact = false
    }

    fun makeWeaponVisible() {
        view.characterVisible = false
        view.weaponVisible = true
        view.artifactVisible = false

        model.isWeapon = true
        model.isCharacter = false
        model.isArtifact = false
    }

    fun makeArtifactVisible() {
        view.artifactVisible = true
        view.characterVisible = false
        view.weaponVisible = false

        model.isArtifact = true
        model.isCharacter = false
        model.isWeapon = false
    }
}