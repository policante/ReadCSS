package com.crmall.readcss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.crmall.styleparse.CSS;
import com.crmall.styleparse.Parser.CSSHandler;
import com.crmall.styleparse.Parser.Declaration;
import com.crmall.styleparse.Parser.DefaultHandler;
import com.crmall.styleparse.Parser.FontFace;
import com.crmall.styleparse.Parser.KeyFrame;
import com.crmall.styleparse.Parser.Parser;
import com.crmall.styleparse.Parser.RuleSet;
import com.crmall.styleparse.Parser.TokenSequence;
import com.crmall.styleparse.StyleCSS;
import com.crmall.styleparse.StyleParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            StyleParser.init(getAssets().open("styles.css"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_main);
    }
}
