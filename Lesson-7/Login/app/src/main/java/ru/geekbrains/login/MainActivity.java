package ru.geekbrains.login;

import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    TextInputEditText login;
    TextInputEditText password;

    // Регулярные выражения позволяют проверить на соответствие шаблону
    // Это имя. Первая буква большая латинская, остальные маленькие латинские
    Pattern checkLogin = Pattern.compile("^[A-Z][a-z]{2,}$");
    // Это пароль, минимум 6 символов, обязательны маленькая буква, большая буква, цифра
    Pattern checkPassword = Pattern.compile("^(?=^.{6,}$)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.inputLoginName);
        password = findViewById(R.id.inputPassword);

        // Чтобы не докучать пользователю при вводе каждой буквы, сделаем проверку при потере фокуса
        login.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            // Как только фокус потерян, сразу проверяем на валидность данные
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) return;
                TextView tv = (TextView) v;
                // Это сама валидация, она вынесена в отдельный метод, чтобы не дублировать код
                // см вызов ниже
                validate(tv, checkLogin, "Это не имя!");
            }
        });

        // Пароль тоже проверим при потере фокуса
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) return;
                TextView tv = (TextView) v;
                // Валидация, почти точно такая же, как и в поле логина
                validate(tv, checkPassword, "Пароль слишком простой!");
            }
        });
    }

    // Валидация
    private void validate(TextView tv, Pattern check, String message){
        String value = tv.getText().toString();
        if (check.matcher(value).matches()){    // Проверим на основе регулярных выражений
            hideError(tv);
        }
        else{
            showError(tv, message);
        }
    }

    // Показать ошибку
    private void showError(TextView view, String message) {
        view.setError(message);
    }

    // спрятать ошибку
    private void hideError(TextView view) {
        view.setError(null);
    }
}
