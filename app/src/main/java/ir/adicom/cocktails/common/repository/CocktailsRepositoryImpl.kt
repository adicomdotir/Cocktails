package ir.adicom.cocktails.common.repository

import android.content.SharedPreferences
import ir.adicom.cocktails.common.network.CocktailsApi

private const val HIGH_SCORE_KEY = "HIGH_SCORE_KEY"

class CocktailsRepositoryImpl(
    private val api: CocktailsApi,
    private val sharedPreferences: SharedPreferences
): CocktailsRepository {
    override fun saveHighScore(score: Int) {
        val highScore = getHighScore()
        if (score > highScore) {
            val editor = sharedPreferences.edit()
            editor.putInt(HIGH_SCORE_KEY, score)
            editor.apply()
        }
    }

    override fun getHighScore(): Int {
        return sharedPreferences.getInt(HIGH_SCORE_KEY, 0)
    }

    override fun getAlcoholic(any: Any) {
    }
}