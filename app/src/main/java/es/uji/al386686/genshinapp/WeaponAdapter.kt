package es.uji.al386686.genshinapp

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.uji.al386686.genshinapp.DataBase.GICharacter
import es.uji.al386686.genshinapp.DataBase.Weapon
import es.uji.al386686.genshinapp.Search.SearchActivity

class WeaponAdapter(val weapons: List<Weapon>, val view: SearchActivity) :
        RecyclerView.Adapter<WeaponAdapter.ViewHolder>() {
    class ViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.weaponName)
        var type : TextView = view.findViewById(R.id.weaponType)
        var rarity: TextView = view.findViewById(R.id.weaponRarity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weapons_layout, parent,false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(weapons[position]){
            holder.name.text = name
            holder.rarity.text = rarity
            holder.type.text = type
        }
    }

    override fun getItemCount(): Int {
        return weapons.size
    }
}