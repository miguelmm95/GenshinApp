package es.uji.al386686.genshinapp.Main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import es.uji.al386686.genshinapp.DataBase.*
import es.uji.al386686.genshinapp.R
import es.uji.al386686.genshinapp.Search.SearchActivity
import es.uji.al386686.genshinapp.SearchInfo

class MainActivity : AppCompatActivity(), IMainActivity {

    lateinit var charactersTextView: TextView
    lateinit var weaponsTextView: TextView
    lateinit var artifactsTextView: TextView
    lateinit var visionTextView: TextView
    lateinit var weaponTypeTextView: TextView
    lateinit var visionAndWeaponTypeTextView: TextView
    lateinit var characterAutoTextView: AutoCompleteTextView
    lateinit var characterVisionSpinner: Spinner
    lateinit var weaponsAutoTextView: AutoCompleteTextView
    lateinit var weaponsTypeSpinner: Spinner
    lateinit var artifactsAutoTextView: AutoCompleteTextView
    lateinit var characterSearchButton: Button
    lateinit var characterVisionSearch: Button
    lateinit var characterWeaponTypeSearch: Button
    lateinit var weaponSearchButton: Button
    lateinit var weaponTypeSearchButton: Button
    lateinit var artifactButtonSearch: Button
    lateinit var visionAndWeaponTypeSearchButton: Button
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
        weaponTypeTextView = findViewById(R.id.weaponUsedTextView)
        characterAutoTextView = findViewById(R.id.characterAutoTextView)
        visionAndWeaponTypeTextView = findViewById(R.id.visionAndWeaponTypeTextView)
        characterVisionSpinner = findViewById(R.id.characterVisionSpinner)
        weaponsAutoTextView = findViewById(R.id.weaponsAutoTextView)
        weaponsTypeSpinner = findViewById(R.id.weaponsTypeSpinner)
        artifactsAutoTextView = findViewById(R.id.artifactsAutoTextView)
        characterSearchButton = findViewById(R.id.characterSearchButton)
        characterVisionSearch = findViewById(R.id.characterVisionSearch)
        characterWeaponTypeSearch = findViewById(R.id.characterWeaponUsedSearch)
        weaponSearchButton = findViewById(R.id.weaponSearchButton)
        weaponTypeSearchButton = findViewById(R.id.weaponTypeSearchButton)
        artifactButtonSearch = findViewById(R.id.artifactButtonSearch)
        visionAndWeaponTypeSearchButton = findViewById(R.id.visionAndWeaponTypeSearchButton)
        radioSearchGroup = findViewById(R.id.radioSearchGroup)
        charactersRadioButton = findViewById(R.id.charactersRadioButton)
        weaponsRadioButton = findViewById(R.id.weaponsRadioButton)
        artifactsRadioButton = findViewById(R.id.artifactsRadioButton)

        charactersRadioButton.setOnClickListener { presenter.makeCharacterVisible() }
        weaponsRadioButton.setOnClickListener { presenter.makeWeaponVisible() }
        artifactsRadioButton.setOnClickListener { presenter.makeArtifactVisible() }
        characterVisionSearch.setOnClickListener { presenter.startSearch() }

        val model = GenshinModel(applicationContext)
        presenter = GenshinPresenter(this, model)
    }

    fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override var characterVisible: Boolean
        get() = charactersTextView.visibility == View.VISIBLE
        set(value) {
            val v = if (value) View.VISIBLE else View.GONE
            charactersTextView.visibility = v
            characterAutoTextView.visibility = v
            visionAndWeaponTypeTextView.visibility = v
            characterSearchButton.visibility = v
            visionAndWeaponTypeSearchButton.visibility = v
            visionTextView.visibility = v
            characterVisionSpinner.visibility = v
            characterVisionSearch.visibility = v
            weaponTypeTextView.visibility = v
            weaponTypeTextView.text = "Type of Weapon Used"
            characterWeaponTypeSearch.visibility = v
            weaponsTypeSpinner.visibility = v
        }
    override var weaponVisible: Boolean
        get() = weaponsTextView.visibility == View.VISIBLE
        set(value) {
            val v = if (value) View.VISIBLE else View.GONE
            weaponsTextView.visibility = v
            weaponsAutoTextView.visibility = v
            weaponsTextView.visibility = v
            weaponTypeTextView.visibility = v
            weaponTypeTextView.text = "Weapon Type"
            weaponsTypeSpinner.visibility = v
            weaponSearchButton.visibility = v
            weaponTypeSearchButton.visibility = v
        }

    override var artifactVisible: Boolean
        get() = artifactsTextView.visibility == View.VISIBLE
        set(value) {
            val v = if (value) View.VISIBLE else View.GONE
            artifactsTextView.visibility = v
            artifactsAutoTextView.visibility = v
            artifactButtonSearch.visibility = v
        }

    override var enableCharacterSearchButton: Boolean
        get() = characterSearchButton.isEnabled
        set(value) {
            characterSearchButton.isEnabled = value
        }

    override var enableCharacterTypeWeaponSearchButton: Boolean
        get() = characterWeaponTypeSearch.isEnabled
        set(value) {
            characterWeaponTypeSearch.isEnabled = value
        }

    override var enableCharacterVisionSearchButton: Boolean
        get() = characterVisionSearch.isEnabled
        set(value) {
            characterVisionSearch.isEnabled = value
        }

    override var enableWeaponSearchButton: Boolean
        get() = weaponSearchButton.isEnabled
        set(value) {
            weaponSearchButton.isEnabled = value
        }

    override var enableWeaponTypeSearchButton: Boolean
        get() = weaponTypeSearchButton.isEnabled
        set(value) {
            weaponTypeSearchButton.isEnabled = value
        }

    override var enableArtifactSearchButton: Boolean
        get() = artifactButtonSearch.isEnabled
        set(value) {
            artifactButtonSearch.isEnabled = value
        }

    override var enableVisionWeaponTypeSearch: Boolean
        get() = characterVisionSearch.isEnabled && characterWeaponTypeSearch.isEnabled
        set(value) {
            visionAndWeaponTypeSearchButton.isEnabled = value
        }

    override fun showCharacters(characters: List<GICharacter>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, characters)
        characterAutoTextView.apply {
            setAdapter(adapter)
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(p0: Editable?) {
                    val character = p0.toString()
                    characters.binarySearch { it.name.compareTo(character) }.let {
                        if (it > 0) {
                            presenter.setChosenCharacter(characters[it])
                        }
                    }
                }
            })
        }
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
        weaponsAutoTextView.apply {
            setAdapter(adapter)
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(p0: Editable?) {
                    val weapon = p0.toString()
                    weapons.binarySearch { it.name.compareTo(weapon) }.let {
                        if (it > 0) {
                            presenter.setChosenWeapon(weapons[it])
                        }
                    }
                }
            })
        }
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
        artifactsAutoTextView.apply {
            setAdapter(adapter)
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(p0: Editable?) {
                    val artifact = p0.toString()
                    artifacts.binarySearch { it.name.compareTo(artifact) }.let {
                        if (it > 0) {
                            presenter.setChosenArtifact(artifacts[it])
                        }
                    }
                }
            })
        }
    }

    override fun onSearchPressed(info: SearchInfo) {
        val intent = Intent(this,SearchActivity::class.java).apply { 
            putExtra(SearchActivity.SEARCH_INFO,info)
        }
        startActivity(intent)
    }
}