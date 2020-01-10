package com.example.flyingfish

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlin.math.floor

class FlyingFishView(context: Context):View(context) {

    private var  fish= ArrayList<Bitmap>()

    private var backgroundImage=BitmapFactory.decodeResource(resources,R.drawable.buw)

    private var scorePaint: Paint =Paint()

    private var life = ArrayList<Bitmap>()
    private var fishX=10
    private var fishY=0

    private var fishSpeed=0
    private var canvasWidth=0
    private var canvasHeight=0


    private var yellowX=0
    private var yellowY=0
    private var yellowSpeed=13
    private val yellowPaint=Paint()

    private var greenX=0
    private var greeny=0
    private var greenSpeed=17
    private val greenPaint=Paint()

    private var redX=0
    private var redY=0
    private var redSpeed=20
    private val redPaint=Paint()

    private var score:Int=0
    private var lifeCounter:Int=3

    private var touch=false
    init {
        fish.add(BitmapFactory.decodeResource(resources,R.drawable.fish1))
        fish.add(BitmapFactory.decodeResource(resources,R.drawable.fish2))
        yellowPaint.color=Color.YELLOW
        yellowPaint.isAntiAlias=false

        greenPaint.color=Color.GREEN
        greenPaint.isAntiAlias=false

        redPaint.color=Color.RED
        redPaint.isAntiAlias=false

        scorePaint.color=Color.WHITE
        scorePaint.textSize=70f
        scorePaint.typeface= Typeface.DEFAULT_BOLD
        scorePaint.isAntiAlias=true

        life.add(BitmapFactory.decodeResource(resources,R.drawable.hearts))
        life.add(BitmapFactory.decodeResource(resources,R.drawable.heart_grey))
        fishY=55
    }
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvasWidth=width
        canvasHeight=height

        canvas.drawBitmap(backgroundImage,0f,0f,null)

        val minFishY=fish[0].height
        val maxFishY=canvasHeight-fish[0].height*3
        fishY+=fishSpeed
        if(fishY<minFishY)
        {
            fishY=minFishY
        }
        if(fishY>maxFishY)
        {
            fishY=maxFishY
        }
        fishSpeed += 2

        if(touch)
        {
            canvas.drawBitmap(fish[1],fishX.toFloat(),fishY.toFloat(),null)
            touch=false
        }
        else
        {
            canvas.drawBitmap(fish[0],fishX.toFloat(),fishY.toFloat(),null)
        }



        yellowX -= yellowSpeed

        if(hitBallChecker(yellowX,yellowY))
        {
            score+=10
            yellowX = -100
        }
        if(yellowX<0)
        {
            yellowX=canvasWidth+21
            yellowY=(floor(Math.random()*(maxFishY-minFishY)) +minFishY).toInt()
        }
        canvas.drawCircle(yellowX.toFloat(),yellowY.toFloat(),25f,yellowPaint)




        greenX -= greenSpeed

        if(hitBallChecker(greenX,greeny))
        {
            score+=20
            greenX = -100
        }
        if(greenX<0)
        {
            greenX=canvasWidth+21
            greeny=(floor(Math.random()*(maxFishY-minFishY)) +minFishY).toInt()
        }
        canvas.drawCircle(greenX.toFloat(),greeny.toFloat(),25f,greenPaint)



        redX -= redSpeed

        if(hitBallChecker(redX,redY))
        {
            redX = -100
            lifeCounter--
            if(lifeCounter==0)
            {
                Toast.makeText(context,"Game Over",Toast.LENGTH_SHORT).show()
                val intent= Intent(context,GameOverActivity::class.java)
                intent.putExtra("Score",score)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK )
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                context.startActivity(intent)
            }
        }
        if(redX<0)
        {
            redX=canvasWidth+21
            redY=(floor(Math.random()*(maxFishY-minFishY)) +minFishY).toInt()
        }
        canvas.drawCircle(redX.toFloat(),redY.toFloat(),30f,redPaint)

        canvas.drawText("Score : $score",20f,60f,scorePaint)


        for( i in 0..3)
        {
            val x=430+life[0].width*1.5*i
            val y=30
            if(i<lifeCounter)
            {
                canvas.drawBitmap(life[0],x.toFloat(),y.toFloat(),null)
            }
            else
            {
                canvas.drawBitmap(life[1],x.toFloat(),y.toFloat(),null)
            }
        }


        //canvas.drawBitmap(fish,0f,0f,null)
    }

    fun hitBallChecker(x:Int,y:Int):Boolean
    {
        if(fishX<x && x<(fishX+fish[0].width) && fishY<y && y<(fishY+fish[0].height))
        {
            return true
        }
        return false
    }




    override fun onTouchEvent(event: MotionEvent): Boolean {
        if(event.action==MotionEvent.ACTION_DOWN)
        {
            touch=true
            fishSpeed=-22
        }
        return true
    }




}