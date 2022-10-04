package ir.adicom.cocktails.game.factory

import ir.adicom.cocktails.common.network.Cocktail
import ir.adicom.cocktails.common.repository.CocktailsRepository
import ir.adicom.cocktails.game.model.Game
import ir.adicom.cocktails.common.repository.RepositoryCallback
import ir.adicom.cocktails.game.model.Question
import ir.adicom.cocktails.game.model.Score

interface CocktailsGameFactory {
    fun buildGame(callback: Callback)

    interface Callback {
        fun onSuccess(game: Game)
        fun onError()
    }
}

class CocktailsGameFactoryImpl(private val repository: CocktailsRepository) : CocktailsGameFactory {
    private fun buildQuestions(cocktailList: List<Cocktail>) = cocktailList.map { cocktail ->
        val otherCocktail = cocktailList.shuffled().first { it != cocktail }
        Question(
            cocktail.strDrink,
            otherCocktail.strDrink,
            cocktail.strDrinkThumb
        )
    }

    override fun buildGame(callback: CocktailsGameFactory.Callback) {
        repository.getAlcoholic(
            object : RepositoryCallback<List<Cocktail>, String> {
                override fun onSuccess(cocktailList: List<Cocktail>) {
                    val questions = buildQuestions(cocktailList)
                    var score = Score(repository.getHighScore())
                    var game = Game(questions, score)
                    callback.onSuccess(game)
                }

                override fun onError(e: String) {
                    callback.onError()
                }
            }
        )
    }
}