package ru.geekbrains.coordinator;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView content = findViewById(R.id.content);
        content.setText(R.string.text_content);

        FloatingActionButton fab = findViewById(R.id.fab);
        // Обработка нажатия на плавающую кнопку
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Здесь вылетит Snackbar
                Snackbar.make(view, "Вы нажали на плавающую кнопку", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // разместить меню в action bar (по версии перевода Google "строка действий")
        // если он присутствует. По мне так строка действий звучит еще запутанней, чем Action bar
        // Фактически это небольшой участок экрана со всякими кнопочками и функциями, типа поиска,
        // меню и т.д. Кстати, Option Menu переводится как меню параметров, что тоже не ахти как
        // понятно.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Управление касаниями на action bar.
        // Action bar будет автоматически управлять нажатиями на Home/Up кнопку
        // Вы это можете указать в родительском активити в файле манифеста.
        int id = item.getItemId();

        switch (id){
            case R.id.action_settings:
                Snackbar.make(toolbar, "Вы выбрали пункт меню установки", Snackbar.LENGTH_LONG)
                        .setAction("Кнопка", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this.getApplicationContext(), "Кнопка в Snackbar нажата", Toast.LENGTH_LONG).show();
                            }
                        }).show();
                return true;
            case R.id.action_preferences:
                Snackbar.make(toolbar, "Вы выбрали пункт меню настройки", Snackbar.LENGTH_LONG).show();
                return true;
            case R.id.action_params:
                Snackbar.make(toolbar, "Вы выбрали пункт меню параметры", Snackbar.LENGTH_LONG).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
