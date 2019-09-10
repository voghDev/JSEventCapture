# JavaScript button click capture example

This project is an example that shows a way to handle clicks in a JavaScript button placed inside a `WebView`, through our Android native App.
It will be done using a `@JavascriptInterface`

## Example 1: When you have access to the HTML code inside the `WebView`

    private val data = """
    <script language="javascript" type="text/javascript">
        function handleButtonClick() {
            alert('[JS] Button was clicked');
            androidButton.onCapturedButtonClicked(); // <-- This is the key line of code
        }
    </script>

    <button type='button' id='someButton' onclick='handleButtonClick();'>Click me</button>
    """

As you see, we are referring an object called `androidButton`. To make this object exist inside the `WebView`, we need to add a `@JavascriptInterface`

    webView.settings.javaScriptEnabled = true
    webView.webChromeClient = WebChromeClient()
    webView.addJavascriptInterface(CaptureClickJavascriptInterface(), "androidButton")
    webView.loadData(data, "text/html", "UTF-8")

    private inner class CaptureClickJavascriptInterface {
        @JavascriptInterface
        fun onCapturedButtonClicked() {
            showClickMessage()
        }
    }

Once this is executed, we can refer to `androidButton` and its `onCapturedButtonClick` method inside the JavaScript code