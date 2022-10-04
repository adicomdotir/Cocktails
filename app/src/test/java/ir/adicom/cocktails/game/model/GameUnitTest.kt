package ir.adicom.cocktails.game.model

import org.junit.Assert
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.*

class GameUnitTest {
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

    @Test
    fun whenAnsweringCorrectly_shouldIncrementCurrentScore() {
        var question = mock<Question>()
        whenever(question.answer(anyString())).thenReturn(true)
        val score = mock<Score>()
        val game = Game(listOf(question), score)

        game.answer(question, "OPTION")

        Assert.assertEquals(1, score.current)
    }

    @Test
    fun whenAnsweringIncorrectly_shouldNotIncrementCurrentScore() {
        val question = mock<Question>()
        whenever(question.answer(anyString())).thenReturn(false)
        val score = mock<Score>()
        val game = Game(listOf(question))
        game.answer(question, "OPTION")

        Assert.assertEquals(0, score.current)
    }
}

