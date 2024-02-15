package com.example.proga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btStart;

    ImageView ivInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //Скрываем нижнюю панель навигации
        Window w = getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY );

        //Инициализируем кнопку Старт
        btStart = (Button) findViewById(R.id.bt_star);

        //Вешаем слушателей нажатий
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Перход на экран выбора уровня
                Intent maimActivityintent = new Intent(MainActivity.this, GameLevelsActivity.class);
                startActivity(maimActivityintent);

            }
        });





    }
}