package ir.adicom.cocktails.game.factory

import ir.adicom.cocktails.common.network.Cocktail
import ir.adicom.cocktails.common.repository.CocktailsRepository
import ir.adicom.cocktails.game.model.Game
import ir.adicom.cocktails.common.repository.RepositoryCallback

interface CocktailsGameFactory {
    fun buildGame(callback: Callback)

    interface Callback {
        fun onSuccess(game: Game)
        fun onError()
    }
}

class CocktailsGameFactoryImpl(private val repository: CocktailsRepository) : CocktailsGameFactory {
    override fun buildGame(callback: CocktailsGameFactory.Callback) {
        repository.getAlcoholic(
            object : RepositoryCallback<List<Cocktail>, String> {
                override fun onSuccess(cocktailList: List<Cocktail>) {
                    callback.onSuccess(Game(emptyList()))
                }

                override fun onError(e: String) {
                    callback.onError()
                }
            }
        )
    }
}