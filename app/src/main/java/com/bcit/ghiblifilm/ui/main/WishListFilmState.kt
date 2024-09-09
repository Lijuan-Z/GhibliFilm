package com.bcit.ghiblifilm.ui.main

import androidx.compose.runtime.toMutableStateList
import com.bcit.ghiblifilm.data.WishListFilm
import com.bcit.ghiblifilm.data.WishListRepository

class WishListFilmState (private  val repository: WishListRepository){
    val films = repository.getAll().toMutableStateList()

    fun add(film: WishListFilm){
        films.add(film)
        repository.insertEntity(film)
    }

    fun delete(film: WishListFilm){
        films.remove(film)
        repository.delete(film)
    }

//    fun refresh(){
//        films.apply{// one recomposition
//            films.clear()
//            films.addAll(repository.getAll())
//        }
//    }
}