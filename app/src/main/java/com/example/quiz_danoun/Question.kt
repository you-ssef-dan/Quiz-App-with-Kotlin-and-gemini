package com.example.quiz_danoun

class Question {
    var question: String? = null
        private set
    var reponses: MutableList<String?>? = null
        private set
    var repCorrect: String? = null
        private set

    constructor()

    constructor(question: String?, reponses: MutableList<String?>?, repCorrect: String?) {
        this.question = question
        this.reponses = reponses
        this.repCorrect = repCorrect
    }
}