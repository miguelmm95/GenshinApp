package es.uji.al386686.genshinapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.uji.al386686.genshinapp.DataBase.GICharacter
import es.uji.al386686.genshinapp.Search.SearchActivity

class CharacterAdapter(val characters: List<GICharacter>, val view: SearchActivity) :
        RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView
        var vision: TextView
        var weaponUsed: TextView

        init {
            name = view.findViewById(R.id.characterName)
            vision = view.findViewById(R.id.characterVision)
            weaponUsed = view.findViewById(R.id.characterWeaponUsed)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.characters_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(characters[position]) {
            holder.name.text = name
            holder.vision.text = visionType
            holder.weaponUsed.text = weapon
        }
    }

    override fun getItemCount(): Int {
        return characters.size
    }
}