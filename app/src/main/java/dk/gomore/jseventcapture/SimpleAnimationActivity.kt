package dk.gomore.jseventcapture

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.widget.Toast
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.model.LottieCompositionCache
import kotlinx.android.synthetic.main.activity_main.*

class SimpleAnimationActivity : AppCompatActivity() {
    @SuppressLint("JavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val assetName = "LottieLogo1.json"

        Thread().run {
            var composition =
                LottieCompositionFactory.fromAssetSync(this@SimpleAnimationActivity, assetName).value
                    ?: throw IllegalArgumentException("Invalid composition $assetName")
//            val drawable = LottieDrawable()
//            drawable.setComposition(composition)
            composition?.let { animationView.setComposition(it) }
            animationView.post {
                animationView.playAnimation()
            }
        }
    }

    private fun showClickMessage() {
        Toast.makeText(
            this@SimpleAnimationActivity,
            "[Android] JavaScript button was clicked",
            Toast.LENGTH_LONG
        ).show()
    }
}
