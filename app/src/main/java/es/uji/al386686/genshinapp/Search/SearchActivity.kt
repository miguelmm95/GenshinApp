package es.uji.al386686.genshinapp.Search

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.uji.al386686.genshinapp.CharacterAdapter
import es.uji.al386686.genshinapp.DataBase.GICharacter
import es.uji.al386686.genshinapp.R
import es.uji.al386686.genshinapp.SearchInfo

class SearchActivity : AppCompatActivity(), ISearchActivity {

    lateinit var genshinView: RecyclerView

    lateinit var presenter: SearchPresenter
    lateinit var model: SearchModel

    companion object {
        const val SEARCH_INFO = "SearchInfo"
        lateinit var info: SearchInfo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val info: SearchInfo = intent.getParcelableExtra(SEARCH_INFO)!!

        val model: SearchModel = SearchModel(applicationContext, info)
        presenter = SearchPresenter(this, info ,model)

        if (info.isCharacter){
            title = "Character Search"
        }else if (info.isWeapon){
            title = "Weapon Search"
        }else{
            title = "Artifact Search"
        }
    }

    fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showCharacter(characters: List<GICharacter>) {
        genshinView = findViewById(R.id.genshinView)
        genshinView.layoutManager = LinearLayoutManager(this)
        genshinView.adapter = CharacterAdapter(characters, this)
    }


}