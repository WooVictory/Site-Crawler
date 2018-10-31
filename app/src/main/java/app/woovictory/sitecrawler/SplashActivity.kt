package app.woovictory.sitecrawler

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), Animation.AnimationListener {
    override fun onAnimationRepeat(p0: Animation?) {
    }

    override fun onAnimationEnd(p0: Animation?) {
    }

    override fun onAnimationStart(p0: Animation?) {
    }

    lateinit var fadeOutAnim: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        fadeOut()

    }

    fun fadeOut() {
        fadeOutAnim = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        fadeOutAnim.setAnimationListener(this)
        splashText.startAnimation(fadeOutAnim)
        var handler = Handler()
        handler.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        }, 2000)

        //startActivity(Intent(this, MainActivity::class.java))
        //finish()
    }
}
