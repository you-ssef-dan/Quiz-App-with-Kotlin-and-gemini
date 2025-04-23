package com.example.quiz_danoun

class Quiz {
    var name: String? = null
    var questions: MutableList<Question?>? = null

    constructor()

    constructor(name: String?, questions: MutableList<Question?>?) {
        this.name = name
        this.questions = questions
    }
}