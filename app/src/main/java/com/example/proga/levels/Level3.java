package com.example.proga.levels;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.proga.GameLevelsActivity;
import com.example.proga.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.Slider;

import java.util.Random;

public class Level3 extends AppCompatActivity {

    Button btBack;
    TextView tvLevelNumber, tvLeftCard, tvRightCard, TvLeftCardText, TvRightCardText;
    Slider slider;

    int leftNumCard;
    int rightNumCard;

    private int LevelCounter;
    SharedPreferences getProgress;
    SharedPreferences.Editor saveProgress;

    Animation animation;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.univeral);

        //Вызов стартового диалога
        initStartDialog();

        btBack = (Button) findViewById(R.id.bt_back);
        tvLevelNumber = (TextView) findViewById(R.id.tv_level_number);
        tvRightCard = (TextView) findViewById(R.id.tv_right_card);
        tvLeftCard = (TextView) findViewById(R.id.tv_left_card);
        TvLeftCardText = (TextView) findViewById(R.id.tv_left_card_text);
        TvRightCardText = (TextView) findViewById(R.id.tv_right_card_text);

        slider = (Slider) findViewById(R.id.slider);

        //Обработка нажатия на кнопку "назад"
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Установка текста номера уровня
        tvLevelNumber.setText(R.string.level_3);

        //Метод инициализации чисел на карточках
        initCards();
        animation = AnimationUtils.loadAnimation(this, R.anim.my_anomation);

    }


    /**
     * Метод работы карточек
     */
    @SuppressLint(value = "ClickableViewAccessibility")
    private void initCards(){

        initCardView();              //метод, генерирует случайное значение



        //Обработка нажатия на левую карточку
        tvLeftCard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {           //OnTouch- знает какое действие совершено (касание, пользователь держит палец или он его отпустил, либо ведёт по экрану)


              ////Условие на касание карточки - начало
                if (event.getAction() == MotionEvent.ACTION_DOWN) {     ////если только коснулся экрана
                    tvRightCard.setEnabled(false);  //Отключаю правую карточку на время взаимодействия с левой

                    //Сравнение значений карточек - начало
                    if (leftNumCard > rightNumCard) {      //Если значение левой карточки больше (верный ответ)
                        tvLeftCard.setBackgroundResource(R.drawable.tv_style_green_40);   //Если пользователь ответил правильно, то задний фон карточки снанет зелёным
                    } else { //Если значение правой карточки больше (не верный ответ)
                        tvLeftCard.setBackgroundResource(R.drawable.tv_style_red_40);
                    }

                    //Сравнение значений карточек - конец
                }


                //Условие на касание карточки - конец



                /////Условие на отпускание карточки -начало

                else if (event.getAction() == MotionEvent.ACTION_UP) {           //// если пользователь палец убрал(отпустил карточку)

                    //Сравнение значений карточек - начало
                    if (leftNumCard > rightNumCard) {                            //Если левая карточка больше (верный ответ)

                        //Начисление очка(поинта) за пральный ответ - начало
                        if (slider.getValue() < slider.getValueTo()) {            //если  текущее значение слайдера меньше чем крайнее значение слайдера   то есть если сейчас уровень 2 (а он меньше 20-это всего уровней)
                            slider.setValue(slider.getValue() + 1);                //добавим к слайдеру одно очко
                        }

                        //Начисление очка(поинта) за пральный ответ - конец

                    }else { //правая правая карточка больше (не верный ответ)

                        //Вычитание поинта за неверный ответ - начало
                        if (slider.getValue() > 1) {
                            slider.setValue(slider.getValue() - 2);
                        }else  if (slider.getValue() == 1) {
                            slider.setValue(0);              //обнулим
                        }

                        //Вычитание поинта за неверный ответ - конец

                    }

                    //Сравнение значений карточек - конец

                    //Проверка строки прогресса уровня на заполненность - начало
                    if (slider.getValue() == slider.getValueTo()) {                       ////если строка прогресса заполнена

                        //Сохранение данных о пройденном уровне - начало
                        getProgress= getSharedPreferences(GameLevelsActivity.SAVE_FILE,MODE_PRIVATE);
                        LevelCounter = getProgress.getInt(GameLevelsActivity.LEVEL_KEY,1); //Получение значений пройденных уровней

                        if (LevelCounter <= 1) {//если кол-во уровней равно 1 или меньше
                            saveProgress = getProgress.edit();
                            saveProgress.putInt(GameLevelsActivity.LEVEL_KEY, 4); //Открываю доступ ко второму уровню(Сохраняем в файл значение2)
                            saveProgress.apply();
                        }





                        //Сохранение данных о пройденном уровне - конец

                        initEndDialog();    //Вызов диалога в конце уровня

                    } else {                                                             ////если строка прогресса не заполнена
                        tvLeftCard.startAnimation(animation);
                        initCardView();           //Присваиваем новые значения для карточек

                    }

                    //Проверка строки прогресса уровня на заполненность - конец

                    tvRightCard.setEnabled(true);  //Делаем правую карточку активной

                }
                //Условие на отпускание карточки - конец
                return true;
            }
        });


        //Обработка нажатия на правую карточку

        tvRightCard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {           //OnTouch- знает какое действие совершено (касание, пользователь держит палец или он его отпустил, либо ведёт по экрану)


                ////Условие на касание карточки - начало
                if (event.getAction() == MotionEvent.ACTION_DOWN) {     ////если только коснулся экрана
                    tvLeftCard.setEnabled(false);  //Отключаю левую карточку на время взаимодействия с левой

                    //Сравнение значений карточек - начало
                    if (rightNumCard > leftNumCard) {      //Если значение правой карточки больше (верный ответ)
                        tvRightCard.setBackgroundResource(R.drawable.tv_style_green_40);   //Если пользователь ответил правильно, то задний фон карточки снанет зелёным
                    } else { //Если значение левой карточки больше (не верный ответ)
                        tvRightCard.setBackgroundResource(R.drawable.tv_style_red_40);
                    }

                    //Сравнение значений карточек - конец
                }

                //Условие на касание карточки - конец



                /////Условие на отпускание карточки -начало

                else if (event.getAction() == MotionEvent.ACTION_UP) {           //// если пользователь палец убрал(отпустил карточку)

                    //Сравнение значений карточек - начало
                    if (rightNumCard > leftNumCard ) {                            //Если правая карточка больше (верный ответ)

                        //Начисление очка(поинта) за пральный ответ - начало
                        if (slider.getValue() < slider.getValueTo()) {            //если  текущее значение слайдера меньше чем крайнее значение слайдера   то есть если сейчас уровень 2 (а он меньше 20-это всего уровней)
                            slider.setValue(slider.getValue() + 1);                //добавим к слайдеру одно очко
                        }

                        //Начисление очка(поинта) за пральный ответ - конец

                    }else { // если левая карточка больше (не верный ответ)

                        //Вычитание поинта за неверный ответ - начало
                        if (slider.getValue() > 1) {
                            slider.setValue(slider.getValue() - 2);
                        }else  if (slider.getValue() == 1) {
                            slider.setValue(0);              //обнулим
                        }

                        //Вычитание поинта за неверный ответ - конец

                    }

                    //Сравнение значений карточек - конец

                    //Проверка строки прогресса уровня на заполненность - начало
                    if (slider.getValue() == slider.getValueTo()) {                       ////если строка прогресса заполнена

                        //Сохранение данных о пройденном уровне - начало
                        getProgress= getSharedPreferences(GameLevelsActivity.SAVE_FILE,MODE_PRIVATE);
                        LevelCounter = getProgress.getInt(GameLevelsActivity.LEVEL_KEY,1); //Получение значений пройденных уровней

                        if (LevelCounter <= 1) //если кол-во уровней равно 1 или меньше
                            saveProgress= getProgress.edit();
                        saveProgress.putInt(GameLevelsActivity.LEVEL_KEY,4); //Открываю доступ ко второму уровню(Сохраняем в файл значение2)
                        saveProgress.apply();



                        //Сохранение данных о пройденном уровне - конец

                        initEndDialog();    //Вызов диалога в конце уровня

                    } else {                                                             ////если строка прогресса не заполнена
                        tvRightCard.startAnimation(animation);
                        initCardView();           //Присваиваем новые значения для карточек

                    }

                    //Проверка строки прогресса уровня на заполненность - конец

                    tvLeftCard.setEnabled(true);  //Делаем правую карточку активной

                }
                //Условие на отпускание карточки - конец
                return true;
            }
        });
    }

    /**
     * Метод вызова диалога в конце уровня
     * Появляется после успешного прохождения уровня
     */
    private void initEndDialog(){

            Dialog dialogEnd = new Dialog(this);
            dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);  //отключаем название диалога
            dialogEnd.setContentView(R.layout.end_dialog);  //выбор макета для диалогового окна

            //Скрываем нижнюю панель навигации
            Window w = dialogEnd.getWindow();
            w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY );

            ConstraintLayout constraintLayout = dialogEnd.findViewById(R.id.my_end_dialog_constrain);
            constraintLayout.setBackgroundResource(R.drawable.im_back_dialog_level1); //выбор заднего фона для диалогового окна


            TextView tvDescription = dialogEnd.findViewById(R.id.tv_description);
            tvDescription.setText(getResources().getString(R.string.interesting_fast_level1)); //выбор текста интересного факта для уровня №1

            dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT); //растягиваем диалоговое окно
            dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // установка прозрачного фона для диалога
            dialogEnd.setCancelable(false);// отключение системной кнопки назад(в панели упровления внизу)

            dialogEnd.show(); //отображение диалога


            MaterialButton btClose = dialogEnd.findViewById(R.id.bt_close_dialog);    //btClose-это название кнопки
            //Обработка нажатия на кнопку "закрыть"
            btClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   finish();   //закрыть активность
                }
            });


            MaterialButton _continue = dialogEnd.findViewById(R.id.bt_continue);  //создание кнопки "продолжить"
            //Обработка нажатия на кнопку "продолжить"
            _continue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Level3.this, Level4.class);
                    startActivity(intent);
                    finish();
                    dialogEnd.cancel();
                }
            });

    }
    /**
     * Метод генерации рандомных значений для карточек и вывод значений на экран
     */
    private void initCardView(){
        String[] textArrayLevel1 = getResources().getStringArray(R.array.textArrayLevel1);  //Создаём массив -String,  название textArraqLevel1, в нём лежат числа от 0 до 10(строчки)

       Random random = new Random();         //Создаём экзэмпляр класса Random

        leftNumCard = random.nextInt(textArrayLevel1.length);   //в переменную leftNumCard прописываем рандомные значения с помощью метода .nextInt() в диапазоне длины нашего массива(textArrayLevel1.lenght)Будут присвоено значение от 0 до 9
        rightNumCard = random.nextInt(textArrayLevel1.length);

        while (leftNumCard == rightNumCard) {
            rightNumCard= random.nextInt(textArrayLevel1.length);  // Если значения карточек равны, генерируем новое значение для правой карточки
        }

        tvLeftCard.setText(String.valueOf(leftNumCard));                        //отображение информации
        tvRightCard.setText(String.valueOf(rightNumCard));

        TvLeftCardText.setText(textArrayLevel1[leftNumCard]);
        TvRightCardText.setText(textArrayLevel1[rightNumCard]);

        tvLeftCard.setBackgroundResource(R.drawable.tv_style_black_60);
        tvRightCard.setBackgroundResource(R.drawable.tv_style_black_60);




    }







    /**
     * Метод вызова стартового диалогового окна
     * Появляется перед началом уровня
     */
    public void initStartDialog(){
        Dialog dialogStart = new Dialog(this);
        dialogStart.requestWindowFeature(Window.FEATURE_NO_TITLE);  //отключаем название диалога
        dialogStart.setContentView(R.layout.preview_dialog);  //выбор макета для диалогового окна

        //Скрываем нижнюю панель навигации
        Window w = dialogStart.getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY );

        ConstraintLayout constraintLayout = dialogStart.findViewById(R.id.my_dialog_constrain);
        constraintLayout.setBackgroundResource(R.drawable.im_back_dialog_level1); //выбор заднего фона для диалогового окна

        ImageView ivDialog = dialogStart.findViewById(R.id.image_view);
        ivDialog.setImageResource(R.drawable.two_cards_level1);  //выбор картинки с карточками для уровня №1

        TextView tvDescription = dialogStart.findViewById(R.id.tv_description);
        tvDescription.setText(getResources().getString(R.string.exercise_level1)); //выбор текста с заданием для уровня №1

        dialogStart.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT); //растягиваем диалоговое окно
        dialogStart.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // установка прозрачного фона для диалога
        dialogStart.setCancelable(false);// отключение системной кнопки назад(в панели упровления внизу)

        dialogStart.show(); //отображение диалога


        MaterialButton btClose = dialogStart.findViewById(R.id.bt_close_dialog);    //btClose-это название кнопки
       //Обработка нажатия на кнопку "закрыть"
        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); //системный метод
            }
        });


        MaterialButton _continue = dialogStart.findViewById(R.id.bt_continue);  //сщздание кнопки "продолжить"

        //Обработка нажатия на кнопку "продолжить"
        _continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogStart.cancel();
            }
        });


    }
}


