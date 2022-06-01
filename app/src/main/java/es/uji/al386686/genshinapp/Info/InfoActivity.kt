package es.uji.al386686.genshinapp.Info

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.uji.al386686.genshinapp.DataBase.Artifact
import es.uji.al386686.genshinapp.DataBase.GICharacter
import es.uji.al386686.genshinapp.DataBase.Weapon
import es.uji.al386686.genshinapp.R
import es.uji.al386686.genshinapp.SpecifySearch

class InfoActivity : AppCompatActivity(), IInfoActivity {

    lateinit var descriptionTextView: TextView
    lateinit var weaponRarityTextView: TextView
    lateinit var characterDescription: TextView
    lateinit var weaponInfoRarity: TextView
    lateinit var genderTextView: TextView
    lateinit var atkTextView: TextView
    lateinit var characterGender: TextView
    lateinit var weaponAtk: TextView
    lateinit var birthdayTextView: TextView
    lateinit var weaponTypeTextView: TextView
    lateinit var characterBirthday: TextView
    lateinit var weaponInfoType: TextView
    lateinit var rarityTextView: TextView
    lateinit var characterRarity: TextView
    lateinit var visionTextView: TextView
    lateinit var characterVision: TextView
    lateinit var weaponTextView: TextView
    lateinit var characterWeapon: TextView
    lateinit var twoBonusTextView: TextView
    lateinit var artifactTwoBonus: TextView
    lateinit var fourBonusTextView: TextView
    lateinit var artifactFourBonus: TextView

    lateinit var presenter: InfoPresenter
    lateinit var model: InfoModel

    companion object {
        const val SPECIFY_INFO = "SpecifySearch"
        lateinit var info: SpecifySearch
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        descriptionTextView = findViewById(R.id.descriptionTextView)
        weaponRarityTextView = findViewById(R.id.weaponRarityTextView)
        characterDescription = findViewById(R.id.characterDescription)
        weaponInfoRarity = findViewById(R.id.weaponInfoRarity)
        genderTextView = findViewById(R.id.genderTextView)
        atkTextView = findViewById(R.id.atkTextView)
        characterGender = findViewById(R.id.characterGender)
        weaponAtk = findViewById(R.id.weaponAtk)
        birthdayTextView = findViewById(R.id.birthdayTextView)
        weaponTypeTextView = findViewById(R.id.weaponTypeTextView)
        characterBirthday = findViewById(R.id.characterBirthday)
        weaponInfoType = findViewById(R.id.weaponInfoType)
        rarityTextView = findViewById(R.id.rarityTextView)
        characterRarity = findViewById(R.id.characterRarity)
        visionTextView = findViewById(R.id.visionTextView)
        characterVision = findViewById(R.id.characterVision)
        weaponTextView = findViewById(R.id.weaponTextView)
        characterWeapon = findViewById(R.id.characterWeapon)
        twoBonusTextView = findViewById(R.id.twoBonusTextView)
        artifactTwoBonus = findViewById(R.id.artifactTwoBonus)
        fourBonusTextView = findViewById(R.id.fourBonusTextView)
        artifactFourBonus = findViewById(R.id.artifactFourBonus)

        val info: SpecifySearch = intent.getParcelableExtra(SPECIFY_INFO)!!

        val model: InfoModel = InfoModel(applicationContext, info)
        presenter = InfoPresenter(this, model, info)

        if (info.isCharacter) {
            presenter.makeCharacterVisible()
            title = info.characterName
        } else if (info.isWeapon) {
            presenter.makeWeaponVisible()
            title = info.weaponName
        } else {
            presenter.makeArtifactVisible()
            title = info.artifactName
        }
    }

    fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showCharacterInfo(character: GICharacter) {
        characterDescription.text = character.description
        characterWeapon.text = character.weapon
        characterVision.text = character.visionType
        characterRarity.text = character.rarity
        characterGender.text = character.gender
        characterBirthday.text = character.birthday
    }

    override fun showWeaponInfo(weapon: Weapon) {
        weaponInfoRarity.text = weapon.rarity
        weaponAtk.text = weapon.atk
        weaponInfoType.text = weapon.type
    }

    override fun showArtifact(artifact: Artifact) {
        artifactTwoBonus.text = artifact.setBonus2
        artifactFourBonus.text = artifact.setBonus4

    }

    override var characterVisible: Boolean
        get() = descriptionTextView.visibility == View.VISIBLE
        set(value) {
            val v = if (value) View.VISIBLE else View.GONE
            descriptionTextView.visibility = v
            characterDescription.visibility = v
            genderTextView.visibility = v
            characterGender.visibility = v
            birthdayTextView.visibility = v
            characterBirthday.visibility = v
            rarityTextView.visibility = v
            characterRarity.visibility = v
            visionTextView.visibility = v
            characterVision.visibility = v
            weaponTextView.visibility = v
            characterWeapon.visibility = v
        }
    override var weaponVisible: Boolean
        get() = weaponRarityTextView.visibility == View.VISIBLE
        set(value) {
            val v = if (value) View.VISIBLE else View.GONE
            weaponRarityTextView.visibility = v
            weaponInfoRarity.visibility = v
            atkTextView.visibility = v
            weaponTypeTextView.visibility = v
            weaponInfoType.visibility = v
        }

    override var artifactVisible: Boolean
        get() = twoBonusTextView.visibility == View.VISIBLE
        set(value) {
            val v = if (value) View.VISIBLE else View.GONE
            twoBonusTextView.visibility = v
            artifactTwoBonus.visibility = v
            fourBonusTextView.visibility = v
            artifactFourBonus.visibility = v
        }
}