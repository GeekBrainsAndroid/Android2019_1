package ru.geekbrains.clicklistener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "button2 ClickListener event!", Toast.LENGTH_SHORT).show();
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:
                Toast.makeText(this, "button3 Activity ClickListener event!", Toast.LENGTH_SHORT).show();
                return;
            default:
                return;
        }
    }

    public void button1_onClick(View view) {
        Toast.makeText(this, "button1 onClick event!", Toast.LENGTH_SHORT).show();
    }
}
