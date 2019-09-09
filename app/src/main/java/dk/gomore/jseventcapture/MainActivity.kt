package dk.gomore.jseventcapture

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val data = """
        <button type='button' id='someButton'>Click me</button>
    """

    val injectedFunction = """
        javascript:(function() {
            window.someButton.onclick = function() {
                alert('Button Clicked');
                androidButton.onCapturedButtonClicked();
            }
        })()
    """

    private val webViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)

            view?.let {
                it.loadUrl(injectedFunction)
            }
        }
    }

    @SuppressLint("JavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = webViewClient
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
