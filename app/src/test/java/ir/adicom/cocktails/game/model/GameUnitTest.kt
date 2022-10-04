package ir.adicom.cocktails.game.model

import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class GameUnitTest {
    @Test
    fun whenIncrementingScore_shouldIncrementCurrentScore() {
        val game = Game()
        game.incrementScore()
        Assert.assertEquals(1, game.currentScore)
    }

    @Test
    fun whenIncrementingScore_aboveHighScore_shouldAlsoIncrementHighScore() {
        val game = Game()
        game.incrementScore()
        Assert.assertEquals(1, game.highestScore)
    }

    @Test
    fun whenIncrementingScore_belowHighScore_shouldNotIncrementHighScore() {
        val game = Game(highest = 10)
        game.incrementScore()
        Assert.assertEquals(10, game.highestScore)
    }

    @Test
    fun whenGettingNextQuestion_shouldReturnIt() {
        val question = Question("CORRECT", "INCORRECT")
        val questions = listOf(question)
        val game = Game(questions)

        val nextQuestion = game.nextQuestion()

        Assert.assertSame(question, nextQuestion)
    }

    @Test
    fun whenGettingNextQuestion_withoutMoreQuestions_shouldReturnNull() {
        val question = Question("CORRECT", "INCORRECT")
        val questions = listOf(question)
        val game = Game(questions)

        game.nextQuestion()
        val nullQuestion = game.nextQuestion()

        Assert.assertNull(nullQuestion)
    }



    @Test
    fun whenAnswering_shouldDelegateToQuestion() {
        val question = mock<Question>()
        val game = Game(listOf(question))

        game.answer(question, "OPTION")

        verify(question, times(1)).answer(eq("OPTION"))
    }
}

