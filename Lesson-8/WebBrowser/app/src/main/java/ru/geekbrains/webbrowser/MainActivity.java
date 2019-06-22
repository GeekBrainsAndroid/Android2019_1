package ru.geekbrains.webbrowser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "WebBrowser";

    private TextView url;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.browse);
        url = findViewById(R.id.url);
        Button ok = findViewById(R.id.ok);
        ok.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            HttpsURLConnection urlConnection = null;
            try {
                URL uri = new URL(url.getText().toString());
                urlConnection = (HttpsURLConnection) uri.openConnection();
                urlConnection.setRequestMethod("GET"); // установка метода получения данных -GET
                urlConnection.setReadTimeout(10000); // установка таймаута - 10 000 миллисекунд
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); // читаем  данные в поток
                String result = getLines(in);
                webView.loadData(result, "text/html; charset=utf-8", "utf-8");
            } catch (Exception e) {
                Log.e(TAG, "Fail connection", e);
                e.printStackTrace();
            } finally {
                if (null != urlConnection) {
                    urlConnection.disconnect();
                }
            }
        }

        private String getLines(BufferedReader in) {
            return in.lines().collect(Collectors.joining("\n"));
        }
    };
}
