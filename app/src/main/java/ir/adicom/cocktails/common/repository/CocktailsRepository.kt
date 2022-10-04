package ir.adicom.cocktails.common.repository

import ir.adicom.cocktails.common.network.Cocktail

interface CocktailsRepository {
    fun saveHighScore(score: Int)

    fun getHighScore(): Int

    fun getAlcoholic(callback: RepositoryCallback<List<Cocktail>, String>)
}

interface RepositoryCallback<T, E> {
    fun onSuccess(t: T)
    fun onError(e: E)
}