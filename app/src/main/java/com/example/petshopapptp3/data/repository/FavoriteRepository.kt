package com.example.petshopapptp3.data.repository

import com.example.petshopapptp3.data.local.FavoriteProductDao
import com.example.petshopapptp3.data.local.FavoriteProductEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoriteRepository(private val dao: FavoriteProductDao) {

    suspend fun addFavorite(favorite: FavoriteProductEntity) {
        withContext(Dispatchers.IO) {
            dao.insert(favorite)
        }
    }

    suspend fun removeFavorite(favorite: FavoriteProductEntity) {
        withContext(Dispatchers.IO) {
            dao.delete(favorite)
        }
    }

    suspend fun isFavorite(id: Int): Boolean {
        return withContext(Dispatchers.IO) {
            dao.exists(id)
        }
    }

    suspend fun getAllFavorites(): List<FavoriteProductEntity> {
        return withContext(Dispatchers.IO) {
            dao.getAll()
        }
    }
}
