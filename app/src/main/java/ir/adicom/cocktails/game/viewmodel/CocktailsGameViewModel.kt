package ir.adicom.cocktails.game.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.adicom.cocktails.common.repository.CocktailsRepository
import ir.adicom.cocktails.game.factory.CocktailsGameFactory
import ir.adicom.cocktails.game.model.Game
import ir.adicom.cocktails.game.model.Question
import ir.adicom.cocktails.game.model.Score

class CocktailsGameViewModel(
    private val repository: CocktailsRepository,
    private val factory: CocktailsGameFactory
): ViewModel() {
    private var game: Game? = null
    private val loadingLiveData = MutableLiveData<Boolean>()
    private val errorLiveData = MutableLiveData<Boolean>()
    private val questionLiveData = MutableLiveData<Question>()
    private val scoreLiveData = MutableLiveData<Score>()

    fun getLoading(): LiveData<Boolean> = loadingLiveData
    fun getError(): LiveData<Boolean> = errorLiveData
    fun getQuestion(): LiveData<Question> = questionLiveData
    fun getScore(): LiveData<Score> = scoreLiveData

    fun initGame() {
        loadingLiveData.value = true
        errorLiveData.value = false
        factory.buildGame(object : CocktailsGameFactory.Callback {
            override fun onSuccess(game: Game) {
                loadingLiveData.value = false
                errorLiveData.value = false
                scoreLiveData.value = game.score
                this@CocktailsGameViewModel.game = game
                nextQuestion()
            }

            override fun onError() {
                loadingLiveData.value = false
                errorLiveData.value = true
            }
        })
    }

    fun nextQuestion() {
        game?.let {
            questionLiveData.value = it.nextQuestion()
        }
    }

    fun answerQuestion(question: Question, option: String) {
        game?.let {
            it.answer(question, option)
            repository.saveHighScore(it.score.highest)
            scoreLiveData.value = it.score
            questionLiveData.value = question
        }
    }
}