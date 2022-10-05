package ir.adicom.cocktails.game.model

class Game(private val questions: List<Question>, val score: Score = Score(0)) {

    private var questionIndex = -1

    val isOver: Boolean
        get() = questionsAnsweredIncorrectly >= 3

    private var questionsAnsweredIncorrectly = 0
    private var questionsAnsweredCorrectSequentially = 0

    fun incrementScore() {
        score.increment()
    }

    fun answer(question: Question, option: String) {
        val result = question.answer(option)
        if (result) {
            questionsAnsweredCorrectSequentially++
            incrementScore()
            if (shouldGiveExtraPoint()) {
                score.increment()
            }
        } else {
            questionsAnsweredCorrectSequentially = 0
            questionsAnsweredIncorrectly++
        }
    }

    private fun shouldGiveExtraPoint() = questionsAnsweredCorrectSequentially > 3

    fun nextQuestion(): Question? {
        if (questionIndex + 1 < questions.size) {
            questionIndex += 1
            return questions[questionIndex]
        }
        return null
    }
}