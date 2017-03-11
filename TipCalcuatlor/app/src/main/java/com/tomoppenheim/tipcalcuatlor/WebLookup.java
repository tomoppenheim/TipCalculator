package com.tomoppenheim.tipcalcuatlor;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class WebLookup  extends Activity{

    private EditText urlEditText;
    private Button urlGoButton;
    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weblookup);

        urlEditText = (EditText) findViewById(R.id.urlEditText);
        urlGoButton = (Button) findViewById(R.id.urlGoButton);
        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);


        //Allows urls to be open within the webview
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });


        urlGoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                webView.loadUrl(urlEditText.getText().toString());
            }
        });
        urlEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    webView.loadUrl(urlEditText.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    //Set back button to go navigate back one website.
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //When web look up is finished, throw message indicating it is finished.
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Web Look Up Finished", Toast.LENGTH_LONG)
                .show();
    }

}

