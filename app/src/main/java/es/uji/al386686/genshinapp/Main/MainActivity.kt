package es.uji.al386686.genshinapp.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import es.uji.al386686.genshinapp.DataBase.*
import es.uji.al386686.genshinapp.R

class MainActivity : AppCompatActivity(), IMainActivity {

    lateinit var charactersTextView: TextView
    lateinit var weaponsTextView: TextView
    lateinit var artifactsTextView: TextView
    lateinit var visionTextView: TextView
    lateinit var charactersSpinner: Spinner
    lateinit var characterVisionSpinner: Spinner
    lateinit var weaponsSpinner: Spinner
    lateinit var weaponsTypeSpinner: Spinner
    lateinit var artifactsSpinner: Spinner
    lateinit var radioSearchGroup: RadioGroup
    lateinit var charactersRadioButton: RadioButton
    lateinit var weaponsRadioButton: RadioButton
    lateinit var artifactsRadioButton: RadioButton

    lateinit var presenter: GenshinPresenter
    lateinit var model: GenshinModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        charactersTextView = findViewById(R.id.charactersTextView)
        weaponsTextView = findViewById(R.id.weaponsTextView)
        artifactsTextView = findViewById(R.id.artifactsTextView)
        visionTextView = findViewById(R.id.characterVisionTextView)
        charactersSpinner = findViewById(R.id.charactersSpinner)
        characterVisionSpinner = findViewById(R.id.characterVisionSpinner)
        weaponsSpinner = findViewById(R.id.weaponsSpinner)
        weaponsTypeSpinner = findViewById(R.id.weaponsTypeSpinner)
        artifactsSpinner = findViewById(R.id.artifactsSpinner)
        radioSearchGroup = findViewById(R.id.radioSearchGroup)
        charactersRadioButton = findViewById(R.id.charactersRadioButton)
        weaponsRadioButton = findViewById(R.id.weaponsRadioButton)
        artifactsRadioButton = findViewById(R.id.artifactsRadioButton)

        val model = GenshinModel(applicationContext)
        presenter = GenshinPresenter(this, model)
    }

    fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showCharacters(characters: List<GICharacter>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, characters)
        charactersSpinner.adapter = adapter
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val character: GICharacter = charactersSpinner.getItemAtPosition(p2) as GICharacter
                presenter.setChosenCharacter(character)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }.also { charactersSpinner.onItemSelectedListener = it }
    }


    override fun showVisions(visions: List<Vision>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, visions)
        characterVisionSpinner.adapter = adapter
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val vision: Vision = characterVisionSpinner.getItemAtPosition(p2) as Vision
                presenter.setChosenVision(vision)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }.also { characterVisionSpinner.onItemSelectedListener = it }
    }

    override fun showWeapons(weapons: List<Weapon>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, weapons)
        weaponsSpinner.adapter = adapter
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val weapon: Weapon = weaponsSpinner.getItemAtPosition(p2) as Weapon
                presenter.setChosenWeapon(weapon)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }.also { weaponsSpinner.onItemSelectedListener = it }

    }

    override fun showWeaponsType(weaponTypes: List<WeaponType>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, weaponTypes)
        weaponsTypeSpinner.adapter = adapter
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val weaponType: WeaponType = weaponsTypeSpinner.getItemAtPosition(p2) as WeaponType
                presenter.setChosenWeaponType(weaponType)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }.also { weaponsTypeSpinner.onItemSelectedListener = it }
    }

    override fun showArtifacts(artifacts: List<Artifact>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, artifacts)
        artifactsSpinner.adapter = adapter
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val artifact: Artifact = artifactsSpinner.getItemAtPosition(p2) as Artifact
                presenter.setChosenArtifact(artifact)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }.also { artifactsSpinner.onItemSelectedListener = it }
    }
}