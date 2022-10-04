package ir.adicom.cocktails.game.model

class Game(questions: List<Question> = listOf(), highest: Int = 0) {
    var currentScore = 0
        private set
    var highestScore = highest
        private set
    var questions = questions
        private set
    private var idx = 0

    fun incrementScore() {
        currentScore++
        if (currentScore > highestScore) {
            highestScore = currentScore
        }
    }

    fun answer(question: Question, option: String) {
        question.answer(option)
    }

    fun nextQuestion(): Question? {
        if (idx < questions.size) {
            return questions[idx++]
        }
        return null
    }
}