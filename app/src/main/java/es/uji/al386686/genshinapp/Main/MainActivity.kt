package es.uji.al386686.genshinapp.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import es.uji.al386686.genshinapp.DataBase.GICharacter
import es.uji.al386686.genshinapp.R

class MainActivity : AppCompatActivity(), IMainActivity {

    lateinit var charactersSpinner: Spinner

    lateinit var presenter : GenshinPresenter
    lateinit var model : GenshinModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        charactersSpinner = findViewById(R.id.charactersSpinner)

        val model = GenshinModel(applicationContext)
        presenter = GenshinPresenter(this,model)
    }

    fun showError(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()
    }

    override fun showCharacters(characters: List<GICharacter>) {
        val adapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,characters)
        charactersSpinner.adapter = adapter
        object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val character : GICharacter = charactersSpinner.getItemAtPosition(p2) as GICharacter
                presenter.setChosenCharacter(character)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }.also { charactersSpinner.onItemSelectedListener = it }
    }


}