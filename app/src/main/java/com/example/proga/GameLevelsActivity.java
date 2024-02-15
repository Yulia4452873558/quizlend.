package com.example.proga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.proga.levels.Level1;
import com.example.proga.levels.Level2;


public class GameLevelsActivity extends AppCompatActivity {
    Button btBack;

    TextView tvLevel1, tvLevel2;
    int[] openedLevels;

    public static final String SAVE_FILE = "SAVE_FILE";
    public static final String LEVEL_KEY = "LEVEL_KEY";


    private int levelCounter;   //для хранения текущего уровня
    SharedPreferences sharedPreferences; //для доступа в данным,сохранённым файле

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_levels);



        //Скрываем нижнюю панель навигации
        Window w = getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        //Инициализируем кнопку "назад"
        btBack = (Button) findViewById(R.id.bt_back);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        sharedPreferences = getSharedPreferences(SAVE_FILE, MODE_PRIVATE);
        levelCounter = sharedPreferences.getInt(LEVEL_KEY, 1); //Получаем доступ к данным о пройденных уровнях

        openedLevels =  new int[]{R.id.tv_level_1, R.id.tv_level_2,R.id.tv_level_3,R.id.tv_level_4,
                R.id.tv_level_5,R.id.tv_level_6,R.id.tv_level_7,R.id.tv_level_8,R.id.tv_level_9,
                R.id.tv_level_10, R.id.tv_level_11,R.id.tv_level_12,R.id.tv_level_13,R.id.tv_level_14,
                R.id.tv_level_15,R.id.tv_level_17,R.id.tv_level_18,R.id.tv_level_19,R.id.tv_level_20 };

        //Проходим по массиву - убираем изображения замка и открываем доступ
        for (int i = 1; i < levelCounter; i++) {
            TextView tv = findViewById(openedLevels[i]);
            tv.setText(String.valueOf(i = 1));
            tv.setEnabled(true);

        }


        /////Инициализируем кнопку "level_1"
        tvLevel1 = (TextView) findViewById(R.id.tv_level_1);

        //вешаем слушателей нажатий
        tvLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Перход на экран уровня №1
                Intent intentLevel1 = new Intent(GameLevelsActivity.this, Level1.class);
                startActivity(intentLevel1);
            }
        });

       //Инициализируем кнопку "level_2"
        tvLevel2 = (TextView) findViewById(R.id.tv_level_2);

        tvLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLevel2 = new Intent(GameLevelsActivity.this, Level2.class);
                startActivity(intentLevel2);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        levelCounter = sharedPreferences.getInt(LEVEL_KEY, 1); //Получаем доступ к данным о пройденных уровнях

        for (int i = 1; i < levelCounter; i++) {
            TextView tv = findViewById(openedLevels[i]);
            tv.setText(String.valueOf(i = 1));
            tv.setEnabled(true);
        }

    }
}



























       /* /////Инициализируем кнопку "level_2"
        tvLevel2 = (TextView) findViewById(R.id.tv_level_2);

        tvLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLevel2 = new Intent(GameLevelsActivity.this, Level2.class);
                startActivity(intentLevel2);
            }
        });

        /////Инициализируем кнопку "level_3"
        tvLevel3 = (TextView) findViewById(R.id.tv_level_3);

        tvLevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLevel3 = new Intent(GameLevelsActivity.this, Level3.class);
                startActivity(intentLevel3);
            }
        });

        //////Инициализируем кнопку "level4"

        tvLevel4 = (TextView) findViewById(R.id.tv_level_4);

        tvLevel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLevel4 =new Intent(GameLevelsActivity.this, Level4.class);
                startActivity(intentLevel4);
            }
        });

        //Инициализируем кнопку "level_5"
        tvLevel5 = (TextView) findViewById(R.id.tv_level_5);

        tvLevel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLevel5 =new Intent(GameLevelsActivity.this, Level5.class);
            }
        });

        //Инициализируем кнопку "level_6"
        tvLevel6 = (TextView) findViewById(R.id.tv_level_6);

        tvLevel6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLevel6 = new Intent(GameLevelsActivity.this, Level6.class);
            }
        });

        //Инициализируем кнопку "level_7"
        tvLevel7 = (TextView) findViewById(R.id.tv_level_7);

        tvLevel7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLevel7 = new Intent(GameLevelsActivity.this, Level7.class);
            }
        });

        //Инициализируем кнопку "level_8"
        tvLevel8 =(TextView) findViewById(R.id.tv_level_8);

        tvLevel8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLevel8 = new Intent(GameLevelsActivity.this, Level8.class);
            }
        });

        //Инициализируем кнопку "level_9"
        tvLevel9 = (TextView) findViewById(R.id.tv_level_9);

        tvLevel9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLevel9 = new Intent(GameLevelsActivity.this, Level9.class);
            }
        });

        //Инициализируем кнопку "level_10"
        tvLevel10 = (TextView) findViewById(R.id.tv_level_10);

        tvLevel10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLevel10 = new Intent(GameLevelsActivity.this, Level10.class);
            }
        });

        //Инициализируем кнопку "level_11"
        tvLevel11 =(TextView) findViewById(R.id.tv_level_11);

        tvLevel11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLevel11 = new Intent(GameLevelsActivity.this, Level11.class);
            }
        });

        //Инициализируем кнопку "level_12"
        tvLevel12 =(TextView)  findViewById(R.id.tv_level_12);

        tvLevel12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLevel12 = new Intent(GameLevelsActivity.this, Level12.class);
            }
        });

        //Инициализируем кнопку "level_13"
        tvLevel13 = (TextView) findViewById(R.id.tv_level_13);

        tvLevel13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLevel13 = new Intent(GameLevelsActivity.this, Level13.class);
            }
        });

        //Инициализируем кнопку "level_14"
        tvLevel14 = (TextView) findViewById(R.id.tv_level_14);

        tvLevel14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLevel14 = new Intent(GameLevelsActivity.this, Level14.class);
            }
        });
    }


    /*
    TextView[] tvLevels = new TextView[20];
    private void initButtonsLevels() {
        initButtonLevel(0, R.id.tv_level_1, Level1.class);
        initButtonLevel(1, R.id.tv_level_2, Level2.class);
        initButtonLevel(2, R.id.tv_level_3, Level3.class);
        initButtonLevel(3, R.id.tv_level_4, Level4.class);
        // не прописаны
        initButtonLevel(4, R.id.tv_level_5, Level5.class);
        initButtonLevel(5, R.id.tv_level_6, Level6.class);
        initButtonLevel(6, R.id.tv_level_7, Level7.class);
        initButtonLevel(7, R.id.tv_level_8, Level8.class);
        initButtonLevel(8, R.id.tv_level_9, Level9.class);
        initButtonLevel(9, R.id.tv_level_10, Level10.class);
        initButtonLevel(10, R.id.tv_level_11, Level11.class);
        initButtonLevel(11, R.id.tv_level_12, Level12.class);
        initButtonLevel(12, R.id.tv_level_13, Level13.class);
        initButtonLevel(13, R.id.tv_level_14, Level14.class);
    }

    private <A extends AppCompatActivity> void initButtonLevel(int idx, int levelId, Class<A> levelClass) {
        // инициализируем кнопку
        tvLevels[idx] = (TextView) findViewById(levelId);
        // вешаем слушателя
        tvLevels[idx].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) //Перход на экран уровня
            {
                startActivity(new Intent(GameLevelsActivity.this, levelClass));
            }
        });
    }
}
*/