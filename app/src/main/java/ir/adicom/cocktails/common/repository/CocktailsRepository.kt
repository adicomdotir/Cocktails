package ir.adicom.cocktails.common.repository

interface CocktailsRepository {
    fun saveHighScore(score: Int)

    fun getHighScore(): Int

    fun getAlcoholic(any: Any)
}

interface RepositoryCallback<T, E> {
    fun onSuccess(t: T)
    fun onError(e: E)
}