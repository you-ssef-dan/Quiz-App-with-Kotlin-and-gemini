package com.example.quiz_danoun

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import java.io.IOException

class Score : FragmentActivity() {
    var tvScore: TextView? = null
    var tvCorrect: TextView? = null
    var tvWrong: TextView? = null
    var tvGreating: TextView? = null
    var score: Int = 0
    var totalQuestions: Int = 0

    var btn_playAgain: Button? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)

        tvScore = findViewById<TextView?>(R.id.tv_score)
        tvCorrect = findViewById<TextView?>(R.id.tv_correct)
        tvWrong = findViewById<TextView?>(R.id.tv_wrong)
        tvGreating = findViewById<TextView?>(R.id.tv_greating)
        btn_playAgain = findViewById<Button?>(R.id.playAgain)

        this.intentData
        displayScore()

        btn_playAgain!!.setOnClickListener(View.OnClickListener { v: View? ->
            startActivity(Intent(this@Score, MainActivity::class.java))
            finish()
        })
    }

    private val intentData: Unit
        get() {
            val intent = getIntent()
            score = intent.getIntExtra("score", 0)
            totalQuestions = intent.getIntExtra("totalQuestions", 0)
        }

    private fun displayScore() {
        val percentage = (score * 100) / totalQuestions
        tvScore!!.setText(percentage.toString() + "%")
        tvCorrect!!.setText(score.toString())
        tvWrong!!.setText((totalQuestions - score).toString())

        if (percentage >= 80) {
            tvGreating!!.setText("Great work!")
        } else if (percentage >= 60) {
            tvGreating!!.setText("Good job!")
        } else {
            tvGreating!!.setText("Keep practicing!")
        }
    }

}