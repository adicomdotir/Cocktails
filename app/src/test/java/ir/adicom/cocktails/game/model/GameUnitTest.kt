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

        verify(score).increment()
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

    @Test
    fun whenAnsweringIncorrectlyThreeTimes_shouldFinishTheGame() {
        val question1 = mock<Question>()
        whenever(question1.answer(anyString())).thenReturn(false)
        val question2 = mock<Question>()
        whenever(question2.answer(anyString())).thenReturn(false)
        val question3 = mock<Question>()
        whenever(question3.answer(anyString())).thenReturn(false)
        val questions = listOf(question1, question2, question3)
        val game = Game(questions)

        game.answer(question1, "INCORRECT")
        game.answer(question2, "INCORRECT")
        game.answer(question3, "INCORRECT")

        Assert.assertTrue(game.isOver)
    }

    @Test
    fun whenAnsweringCorrectlyThreeTimesSequentially_shouldStartGivingDoubleScore() {
        val question1 = mock<Question>()
        whenever(question1.answer(anyString())).thenReturn(true)
        val question2 = mock<Question>()
        whenever(question2.answer(anyString())).thenReturn(true)
        val question3 = mock<Question>()
        whenever(question3.answer(anyString())).thenReturn(true)
        val question4 = mock<Question>()
        whenever(question4.answer(anyString())).thenReturn(true)
        val questions = listOf(question1, question2, question3, question4)
        val score = mock<Score>()
        val game = Game(questions, score)

        game.answer(question1, "CORRECT")
        game.answer(question2, "CORRECT")
        game.answer(question3, "CORRECT")
        game.answer(question4, "CORRECT")

        verify(score, times(1 + 1 + 1 + 2)).increment()
    }
}

