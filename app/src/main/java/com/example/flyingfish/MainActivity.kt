package com.example.flyingfish

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import java.util.*

class MainActivity : AppCompatActivity() {


    private lateinit var gameView: FlyingFishView
    private val handlerThread = Handler()
    private val interval=30L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        gameView= FlyingFishView(this)
        setContentView(gameView)
        requestedOrientation= ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)


        Timer().schedule(object:TimerTask()
        {
            override fun run() {
                gameView.invalidate()
            }

        },0,interval)
    }
}
