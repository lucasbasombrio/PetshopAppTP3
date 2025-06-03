package com.example.petshopapptp3.data.local

import androidx.room.*

@Dao
interface FavoriteProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorite: FavoriteProductEntity)

    @Delete
    fun delete(favorite: FavoriteProductEntity)

    @Query("SELECT * FROM favorites")
    fun getAll(): List<FavoriteProductEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id = :id)")
    fun exists(id: Int): Boolean

}




