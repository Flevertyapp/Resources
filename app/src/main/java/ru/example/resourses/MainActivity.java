package ru.example.resourses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //читаем шрифт из ассета
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/segoesc.ttf");

        //вывод ресурсов в строки программно
        TextView descriptionLanguage = findViewById(R.id.textVLang);
        descriptionLanguage.setText(getString(R.string.descriptionLanguage));
        //установка шрифта
        descriptionLanguage.setTypeface(typeFace);
        TextView textLanguage = findViewById(R.id.textLang);
        textLanguage.setText(getResources().getString(R.string.language));

        //устанавливаем картинку
        AppCompatImageView image = findViewById(R.id.imageView);
        loadImageFromAsset(image, "nasa_icon.png");

        initList();
    }

    //достаем картинку из ассетов
    private void loadImageFromAsset(ImageView image, String fileName) {
        try {
            InputStream ims = getAssets().open(fileName);
            //loading like a drawable
            Drawable d = Drawable.createFromStream(ims, null);
            //выводим картинку в imageView
            image.setImageDrawable(d);
        } catch (IOException e) {
            return;
        }
    }

    private void initList() {
        LinearLayout layoutList = findViewById(R.id.layoutList);
        String[] versions = getResources().getStringArray(R.array.version_names);
        LayoutInflater inflater = getLayoutInflater();

        for (int i = 0; i < versions.length; i++) {
            String version = versions[i];
            //достаем элемент из android_item.xml
            View item = inflater.inflate(R.layout.android_item, layoutList, false);
            //находим в этом элементе текст вью
            TextView textView = item.findViewById(R.id.textAndroid);
            textView.setText(version);
            //получение массива указателей на изображения
            TypedArray imgs = getResources().obtainTypedArray(R.array.version_logos);
            //выбор подходящего изобр по индексу
            AppCompatImageView imgLogo = item.findViewById(R.id.imageAndroid);
            imgLogo.setImageResource(imgs.getResourceId(i, -1));
            layoutList.addView(item);
        }
    }
}