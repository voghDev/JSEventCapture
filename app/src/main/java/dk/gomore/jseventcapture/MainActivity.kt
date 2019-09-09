package dk.gomore.jseventcapture

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val data = """
        <script language="javascript">
       function handleButtonClick() {
          alert('[JS] Button was clicked');
          androidButton.performClick();
       }
        </script>
        
        <button type='button' id='btn' onclick='handleButtonClick();'>Click me</button>
    """

    private val webViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)

            view?.let {
                it.loadUrl(
                    "javascript:(function(){ " +
                            "alert('Hey'); " +
                            "})()"
                )
            }
        }
    }

    @SuppressLint("JavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val invisibleButton = Button(this)
        invisibleButton.setOnClickListener {
            Toast.makeText(this, "JavaScript event was captured", Toast.LENGTH_LONG).show()
        }

        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = webViewClient
        webView.addJavascriptInterface(invisibleButton, "androidButton")
        webView.loadData(data, "text/html", "UTF-8")
    }
}
