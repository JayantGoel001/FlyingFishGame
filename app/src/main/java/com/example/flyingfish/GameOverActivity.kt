package com.example.flyingfish

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_game_over.*

class GameOverActivity : AppCompatActivity() {
    var highScore=100
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)



        val displayScore=intent.extras!!.get("Score").toString()
        score.text="Score : $displayScore"

        val sharedPreferences=getSharedPreferences("Reference", Context.MODE_PRIVATE)

        if(displayScore.toInt()>highScore)
        {
            highScore=displayScore.toInt()
            sharedPreferences.edit{
                putString("highScore",highScore.toString())
            }

            val highScore=sharedPreferences.getString("highScore","0")!!

            high_Score.text= "New HighScore : $highScore"
        }
        else
        {
            val highScore=sharedPreferences.getString("highScore","0")!!
            high_Score.text= "HighScore : $highScore"
        }



        playAgain.setOnClickListener {
            val intent= Intent(this,SplashActivity::class.java)
            startActivity(intent)
        }




    }
}
