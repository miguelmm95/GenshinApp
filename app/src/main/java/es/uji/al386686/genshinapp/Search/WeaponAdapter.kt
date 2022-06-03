package es.uji.al386686.genshinapp.Search

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.uji.al386686.genshinapp.DataBase.Weapon
import es.uji.al386686.genshinapp.R

class WeaponAdapter(val weapons: List<Weapon>, val view: SearchActivity, val onClickListener: (Weapon) -> Unit) :
        RecyclerView.Adapter<WeaponAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.weaponName)
        var type: TextView = view.findViewById(R.id.weaponType)
        var rarity: TextView = view.findViewById(R.id.weaponRarity)

        var weaponName: TextView = view.findViewById(R.id.weaponName)
        var rarityRow: TableRow = view.findViewById(R.id.rarityRow)
        var weaponTypeRow: TableRow = view.findViewById(R.id.weaponTypeRow)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weapons_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(weapons[position]) {
            holder.name.text = name
            holder.rarity.text = rarity
            holder.type.text = type

            when (rarity) {
                "1" -> {
                    holder.weaponName.setBackgroundColor(Color.parseColor("#989595"))
                    holder.rarityRow.setBackgroundColor(Color.parseColor("#B5B1B1"))
                    holder.weaponTypeRow.setBackgroundColor(Color.parseColor("#B5B1B1"))
                }
                "2" -> {
                    holder.weaponName.setBackgroundColor(Color.parseColor("#4BC172"))
                    holder.rarityRow.setBackgroundColor(Color.parseColor("#59C17C"))
                    holder.weaponTypeRow.setBackgroundColor(Color.parseColor("#59C17C"))
                }
                "3" -> {
                    holder.weaponName.setBackgroundColor(Color.parseColor("#3581BD"))
                    holder.rarityRow.setBackgroundColor(Color.parseColor("#4A88BA"))
                    holder.weaponTypeRow.setBackgroundColor(Color.parseColor("#4A88BA"))
                }
                "4" -> {
                    holder.weaponName.setBackgroundColor(Color.parseColor("#AD4AE3"))
                    holder.rarityRow.setBackgroundColor(Color.parseColor("#B863E6"))
                    holder.weaponTypeRow.setBackgroundColor(Color.parseColor("#B863E6"))

                }
                "5" -> {
                    holder.weaponName.setBackgroundColor(Color.parseColor("#DD9222"))
                    holder.rarityRow.setBackgroundColor(Color.parseColor("#DA9939"))
                    holder.weaponTypeRow.setBackgroundColor(Color.parseColor("#DA9939"))
                }
            }

            holder.itemView.setOnClickListener {
                onClickListener(weapons[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return weapons.size
    }
}