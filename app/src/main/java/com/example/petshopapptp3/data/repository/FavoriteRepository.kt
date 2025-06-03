package com.example.petshopapptp3.data.repository

import android.content.Context
import com.example.petshopapptp3.data.local.AppDatabase
import com.example.petshopapptp3.data.local.FavoriteProductEntity

class FavoriteRepository(context: Context) {
    private val dao = AppDatabase.getDatabase(context).favoriteDao()

    suspend fun addFavorite(product: FavoriteProductEntity) {
        dao.insert(product)
    }

    suspend fun removeFavorite(product: FavoriteProductEntity) {
        dao.delete(product)
    }

    suspend fun isFavorite(productId: Int): Boolean {
        return dao.exists(productId)
    }

    suspend fun getAllFavorites(): List<FavoriteProductEntity> {
        return dao.getAll()
    }
}
