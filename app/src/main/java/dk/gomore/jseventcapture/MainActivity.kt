package dk.gomore.jseventcapture

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("JavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val drawable = R.drawable.gilbert_animated

        val assetName = "LottieLogo1.json"
    }

    private fun showClickMessage() {
        Toast.makeText(
            this@MainActivity,
            "[Android] JavaScript button was clicked",
            Toast.LENGTH_LONG
        ).show()
    }
}
