package es.uji.al386686.genshinapp.DataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DAO {
    @Insert
    fun addCharacter(chracters: List<GICharacter>)

    @Insert
    fun addVisions(vision: List<Vision>)

    @Insert
    fun addWeapons(weapon: List<Weapon>)

    @Insert
    fun addWeaponsType(weaponsType: List<WeaponType>)

    @Insert
    fun addArtifacts(artifact: List<Artifact>)

    @Query("SELECT * FROM characters ORDER BY 'name'")
    fun getCharacters(): List<GICharacter>

    @Query("SELECT * FROM visions ORDER BY 'visionType'")
    fun getVisions(): List<Vision>

    @Query("SELECT DISTINCT visionType FROM characters ORDER BY 'visionType'")
    fun getVisionsFromCharacters(): List<Vision>

    @Query("SELECT * FROM weapons ORDER BY 'name'")
    fun getWeapons(): List<Weapon>

    @Query("SELECT * FROM weaponstype ORDER BY 'type'")
    fun getWeaponsType(): List<WeaponType>

    @Query("SELECT DISTINCT type FROM weapons ORDER BY 'type'")
    fun getTypeFromWeapons(): List<WeaponType>

    @Query("SELECT * FROM artifacts ORDER BY 'name'")
    fun getArtifacts(): List<Artifact>

    @Query("SELECT * FROM characters WHERE 'name' == :name")
    fun getCharacters(name : String) : GICharacter

    @Query("SELECT * FROM characters WHERE 'weapon' == :weaponType")
    fun getCharactersByWeaponType(weaponType: String): GICharacter

    @Query("SELECT * FROM characters WHERE 'visionType' == :vision")
    fun getCharactersByVision(vision: String?): List<GICharacter>

    @Query("SELECT * FROM characters WHERE 'weapon' == :weaponType AND 'visionType' == :vision")
    fun getCharactersByVisionAndWeaponType(weaponType: String, vision: String) : GICharacter
}