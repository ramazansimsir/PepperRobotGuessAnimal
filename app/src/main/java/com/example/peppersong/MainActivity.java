package com.example.peppersong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.aldebaran.qi.Future;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.ListenBuilder;
import com.aldebaran.qi.sdk.builder.PhraseSetBuilder;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.object.conversation.Listen;
import com.aldebaran.qi.sdk.object.conversation.ListenResult;
import com.aldebaran.qi.sdk.object.conversation.PhraseSet;
import com.aldebaran.qi.sdk.object.locale.Language;
import com.aldebaran.qi.sdk.object.locale.Locale;
import com.aldebaran.qi.sdk.object.locale.Region;
import com.example.peppersong.databinding.ActivityMainBinding;

public class MainActivity extends RobotActivity implements RobotLifecycleCallbacks {

    MediaPlayer mediaPlayer;
    ActivityMainBinding design;
    String userCh="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        design=ActivityMainBinding.inflate(getLayoutInflater());
        // Register the RobotLifecycleCallbacks to this Activity
        setContentView(design.getRoot());
        QiSDK.register(this, this);

        design.fare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        design.fil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });
        design.kedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, MainActivity4.class);
                startActivity(intent);
            }
        });
        design.kopek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, MainActivity5.class);
                startActivity(intent);
            }
        });
        design.goril.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, MainActivity6.class);
                startActivity(intent);
            }
        });
        design.kurt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, MainActivity7.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onDestroy() {
        // Unregister the RobotLifecycleCallbacks for this Activity.
        QiSDK.unregister(this, this);
        super.onDestroy();
    }

    @Override
    public void onRobotFocusGained(QiContext qiContext) {
        // The robot focus is gained.
        PhraseSet phraseSet1=PhraseSetBuilder.with(qiContext)
                .withTexts("köpek","kedi","goril","fil","kurt","fare")
                .build();

        Locale locale = new Locale(Language.TURKISH, Region.TURKEY);
        Listen listenCh=ListenBuilder.with(qiContext)
                .withPhraseSet(phraseSet1)
                .withLocale(locale)
                .build();


        Future<ListenResult> listenFuture=listenCh.async().run();

        userCh=listenFuture.getValue().getHeardPhrase().getText().toString();

        listenFuture.andThenConsume(first->{
            switch (userCh){
                case "fare":
                    Intent intent=new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                    break;
                case "goril":
                    Intent intent2=new Intent(MainActivity.this, MainActivity6.class);
                    startActivity(intent2);
                    break;
                case "köpek":
                    Intent intent3=new Intent(MainActivity.this, MainActivity5.class);
                    startActivity(intent3);

                    break;
                case "kedi":
                    Intent intent4=new Intent(MainActivity.this, MainActivity4.class);
                    startActivity(intent4);
                    break;
                case "kurt":
                    Intent intent5=new Intent(MainActivity.this, MainActivity7.class);
                    startActivity(intent5);
                    break;
                case "fil":
                    Intent intent6=new Intent(MainActivity.this, MainActivity3.class);
                    startActivity(intent6);
                    break;
                default:
                    break;
            }
        });

    }

    @Override
    public void onRobotFocusLost() {
        // The robot focus is lost.
    }

    @Override
    public void onRobotFocusRefused(String reason) {
        // The robot focus is refused.
    }
}