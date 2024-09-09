package com.bcit.ghiblifilm.data

class WishListRepository(private val filmDAO: FilmDAO) {

    fun insertEntity(film: WishListFilm){
        filmDAO.add(film)
    }

    fun getAll():List<WishListFilm>{
        return filmDAO.getAll()
    }

    fun delete(film: WishListFilm){
        filmDAO.delete(film)
    }
}