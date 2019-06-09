package ru.geekbrains.clicklistener;

import android.view.View;
import android.widget.Toast;

public class Button5Listener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "button5 ClickListener event!", Toast.LENGTH_SHORT).show();
    }
}
