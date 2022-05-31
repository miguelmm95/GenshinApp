package es.uji.al386686.genshinapp

import android.os.Parcel
import android.os.Parcelable

data class SearchInfo(val isCharacter: Boolean, val characterVision: Boolean, val characterWeapon: Boolean, val isWeapon: Boolean, val isArtifact: Boolean,
                      val vision: String?,
                      val weaponType: String?,
                      val characterName: String?,
                      val weaponName: String?,
                      val artifactName: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (isCharacter) 1 else 0)
        parcel.writeByte(if (characterVision) 1 else 0)
        parcel.writeByte(if (characterWeapon) 1 else 0)
        parcel.writeByte(if (isWeapon) 1 else 0)
        parcel.writeByte(if (isArtifact) 1 else 0)
        parcel.writeString(vision)
        parcel.writeString(weaponType)
        parcel.writeString(characterName)
        parcel.writeString(weaponName)
        parcel.writeString(artifactName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchInfo> {
        override fun createFromParcel(parcel: Parcel): SearchInfo {
            return SearchInfo(parcel)
        }

        override fun newArray(size: Int): Array<SearchInfo?> {
            return arrayOfNulls(size)
        }
    }

}