package es.uji.al386686.genshinapp.Search

import es.uji.al386686.genshinapp.DataBase.GICharacter

interface ISearchActivity {
    fun showCharacter(characters: List<GICharacter>)
}