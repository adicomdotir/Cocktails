package ir.adicom.cocktails.game.model

class Game(private val questions: List<Question>, val score: Score = Score(0)) {

    private var questionIndex = -1

    fun incrementScore() {
        score.increment()
    }

    fun answer(question: Question, option: String) {
        if (question.answer(option)) {
            incrementScore()
        }
    }

    fun nextQuestion(): Question? {
        if (questionIndex + 1 < questions.size) {
            questionIndex += 1
            return questions[questionIndex]
        }
        return null
    }
}