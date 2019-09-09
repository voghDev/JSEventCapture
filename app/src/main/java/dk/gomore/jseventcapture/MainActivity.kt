package dk.gomore.jseventcapture

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val data = """
    <script language="javascript" type="text/javascript">
        function handleButtonClick() {
            alert('[JS] Button was clicked');
            androidButton.onCapturedButtonClicked();
        }
    </script>
        
    <button type='button' id='someButton' onclick='handleButtonClick();'>Click me</button>
    """

    @SuppressLint("JavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()
        webView.addJavascriptInterface(CaptureClickJavascriptInterface(), "androidButton")
        webView.loadData(data, "text/html", "UTF-8")
    }

    private fun showClickMessage() {
        Toast.makeText(
            this@MainActivity,
            "[Android] JavaScript button was clicked",
            Toast.LENGTH_LONG
        ).show()
    }

    private inner class CaptureClickJavascriptInterface {
        @JavascriptInterface
        fun onCapturedButtonClicked() {
            showClickMessage()
        }
    }
}
