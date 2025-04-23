package com.example.quiz_danoun

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.json.JSONArray

class Questions : AppCompatActivity() {

    private lateinit var question: TextView
    private lateinit var rep1: TextView
    private lateinit var rep2: TextView
    private lateinit var rep3: TextView
    private lateinit var tvTime: TextView
    private lateinit var btnNext: Button
    private lateinit var btnQuit: Button
    private lateinit var progressBarTime: ProgressBar

    private var totalQuestions = 0
    private var currentQuestionIndex = 0
    private var score = 0
    private val time: Long = 10000
    private var countDownTimer: CountDownTimer? = null

    private val quizzes = mutableListOf<Quiz>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_questions)

        rep1 = findViewById(R.id.rep1)
        rep2 = findViewById(R.id.rep2)
        rep3 = findViewById(R.id.rep3)
        question = findViewById(R.id.question)
        btnNext = findViewById(R.id.btnNext)
        progressBarTime = findViewById(R.id.progressBarTime)
        tvTime = findViewById(R.id.tv_time)
        btnQuit = findViewById(R.id.btn_quit)

        btnNext.setOnClickListener { nextQuestion() }
        btnQuit.setOnClickListener { quitQuiz() }

        rep1.setOnClickListener { checkAnswer(rep1.text.toString()) }
        rep2.setOnClickListener { checkAnswer(rep2.text.toString()) }
        rep3.setOnClickListener { checkAnswer(rep3.text.toString()) }

        fetchQuizzesFromGemini()
    }

    private fun fetchQuizzesFromGemini() {
        val generativeModel = GenerativeModel(
            modelName = "gemini-2.0-flash",
            apiKey = "AIzaSyBjCq_BzwhlaoV3O7mhaF4JC1jwIgHzNXE"
        )
        val prompt = """
            Generate a quiz with 5 short multiple-choice questions focused on [calculate]. 
            Each question should include 3 short answer options labeled A, B, and C. 
            Clearly indicate the correct answer for each question. 
            Return the result as a JSON array, where each item includes:
            - "question"
            - "options" (with keys "A", "B", and "C")
            - "answer" (e.g., "A", "B", or "C")
            Do not add any explanation or extra text — only output valid JSON.
        """.trimIndent()

        MainScope().launch {
            try {
                val response = generativeModel.generateContent(prompt)
                var jsonString = response.text ?: "[]"

                jsonString = jsonString.replace("```json", "").replace("```", "").trim()
                val jsonArray = JSONArray(jsonString)
                val questionList = mutableListOf<Question?>()

                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    val qText = obj.getString("question")
                    val options = obj.getJSONObject("options")
                    val correct = obj.getString("answer")

                    val responses = mutableListOf<String?>(
                        options.getString("A"),
                        options.getString("B"),
                        options.getString("C")
                    )

                    val correctAnswer = when (correct) {
                        "A" -> options.getString("A")
                        "B" -> options.getString("B")
                        "C" -> options.getString("C")
                        else -> ""
                    }

                    questionList.add(Question(qText, responses, correctAnswer))
                }

                quizzes.add(Quiz("AI Quiz", questionList))
                totalQuestions = questionList.size
                displayQuizzes(quizzes)

            } catch (e: Exception) {
                Toast.makeText(this@Questions, "Error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun displayQuizzes(quizzes: List<Quiz>) {
        if (quizzes.isEmpty()) {
            Toast.makeText(this, "No quizzes found", Toast.LENGTH_SHORT).show()
            return
        }

        startCountdown()
        val questionList = quizzes[0].questions!!

        if (currentQuestionIndex < questionList.size) {
            val currentQuestion = questionList[currentQuestionIndex]!!
            question.text = currentQuestion.question
            rep1.text = currentQuestion.reponses!![0]
            rep2.text = currentQuestion.reponses!![1]
            rep3.text = currentQuestion.reponses!![2]
        } else {
            Toast.makeText(this, "You've reached the end of the quiz!", Toast.LENGTH_SHORT).show()
            btnNext.isEnabled = false
            Toast.makeText(this, "Your score is: $score", Toast.LENGTH_SHORT).show()
            navigateToScore()
        }
    }

    private fun nextQuestion() {
        currentQuestionIndex++
        displayQuizzes(quizzes)
    }

    private fun checkAnswer(selectedAnswer: String) {
        val currentQuestion = quizzes[0].questions!![currentQuestionIndex]!!
        if (selectedAnswer == currentQuestion.repCorrect) {
            score++
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show()
        }
        nextQuestion()
    }

    private fun startCountdown() {
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(time, 1000) {
            @SuppressLint("DefaultLocale")
            override fun onTick(millisUntilFinished: Long) {
                val progress = ((time - millisUntilFinished) * 100 / time).toInt()
                progressBarTime.progress = progress

                val secondsRemaining = (millisUntilFinished / 1000).toInt()
                val minutes = secondsRemaining / 60
                val seconds = secondsRemaining % 60
                tvTime.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                nextQuestion()
            }
        }.start()
    }

    private fun quitQuiz() {
        navigateToScore()
    }

    private fun navigateToScore() {
        val intent = Intent(this, Score::class.java)
        intent.putExtra("score", score)
        intent.putExtra("totalQuestions", totalQuestions)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        countDownTimer?.cancel()
        super.onDestroy()
    }
}
