package es.uji.al386686.genshinapp.Main

import es.uji.al386686.genshinapp.DataBase.GICharacter

interface IMainActivity {
    fun showCharacters(characters : List<GICharacter>)
}