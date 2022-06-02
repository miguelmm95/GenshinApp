package es.uji.al386686.genshinapp.Search

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.uji.al386686.genshinapp.DataBase.GICharacter
import es.uji.al386686.genshinapp.R

class CharacterAdapter(val characters: List<GICharacter>, val view: SearchActivity, val onClickListener: (GICharacter) -> Unit) :
        RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView
        var vision: TextView
        var weaponUsed: TextView

        var charcterName: TextView
        var visionRow: TableRow
        var weaponRow: TableRow

        init {
            name = view.findViewById(R.id.weaponName)
            vision = view.findViewById(R.id.weaponRarity)
            weaponUsed = view.findViewById(R.id.weaponType)
            charcterName = view.findViewById(R.id.weaponName)
            visionRow = view.findViewById(R.id.visionRow)
            weaponRow = view.findViewById(R.id.weaponRow)
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

            when (visionType) {
                "pyro" -> {
                    holder.charcterName.setBackgroundColor(Color.parseColor("#BD2929"))
                    holder.visionRow.setBackgroundColor(Color.parseColor("#BD4949"))
                    holder.weaponRow.setBackgroundColor(Color.parseColor("#BD4949"))
                }
                "hydro" -> {
                    holder.charcterName.setBackgroundColor(Color.parseColor("#2538E1"))
                    holder.visionRow.setBackgroundColor(Color.parseColor("#3A4BE1"))
                    holder.weaponRow.setBackgroundColor(Color.parseColor("#3A4BE1"))
                }
                "electro" -> {
                    holder.charcterName.setBackgroundColor(Color.parseColor("#6108DC"))
                    holder.visionRow.setBackgroundColor(Color.parseColor("#7224DD"))
                    holder.weaponRow.setBackgroundColor(Color.parseColor("#7224DD"))
                }
                "cryo" -> {
                    holder.charcterName.setBackgroundColor(Color.parseColor("#27B2DD"))
                    holder.visionRow.setBackgroundColor(Color.parseColor("#44B6DA"))
                    holder.weaponRow.setBackgroundColor(Color.parseColor("#44B6DA"))
                }
                "anemo" -> {
                    holder.charcterName.setBackgroundColor(Color.parseColor("#44CDB9"))
                    holder.visionRow.setBackgroundColor(Color.parseColor("#26D8B5"))
                    holder.weaponRow.setBackgroundColor(Color.parseColor("#26D8B5"))
                }
                "geo" -> {
                    holder.charcterName.setBackgroundColor(Color.parseColor("#E19F4D"))
                    holder.visionRow.setBackgroundColor(Color.parseColor("#DFAB6A"))
                    holder.weaponRow.setBackgroundColor(Color.parseColor("#DFAB6A"))
                }
            }

            holder.itemView.setOnClickListener {
                onClickListener(characters[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return characters.size
    }
}