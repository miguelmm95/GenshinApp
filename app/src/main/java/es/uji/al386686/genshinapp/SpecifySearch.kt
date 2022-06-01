package es.uji.al386686.genshinapp

import android.os.Parcel
import android.os.Parcelable

data class SpecifySearch(val characterName: String?, val weaponName: String?, val artifactName: String?, val isCharacter: Boolean, val isWeapon: Boolean) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(characterName)
        parcel.writeString(weaponName)
        parcel.writeString(artifactName)
        parcel.writeByte(if (isCharacter) 1 else 0)
        parcel.writeByte(if (isWeapon) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SpecifySearch> {
        override fun createFromParcel(parcel: Parcel): SpecifySearch {
            return SpecifySearch(parcel)
        }

        override fun newArray(size: Int): Array<SpecifySearch?> {
            return arrayOfNulls(size)
        }
    }

}