package com.example.peppersong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ipsec.ike.IkeDerAsn1DnIdentification;
import android.os.Bundle;

import com.aldebaran.qi.Future;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.AnimateBuilder;
import com.aldebaran.qi.sdk.builder.AnimationBuilder;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.object.actuation.Animate;
import com.aldebaran.qi.sdk.object.actuation.Animation;

public class MainActivity4 extends RobotActivity implements RobotLifecycleCallbacks {

    MediaPlayer mediaPlayer;
    com.example.peppersong.databinding.ActivityMain4Binding design;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        design= com.example.peppersong.databinding.ActivityMain4Binding.inflate(getLayoutInflater());
        // Register the RobotLifecycleCallbacks to this Activity
        setContentView(design.getRoot());
        QiSDK.register(this, this);
        mediaPlayer=MediaPlayer.create(MainActivity4.this,R.raw.kedi);


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
        mediaPlayer.start();
        Animation myAnimation = AnimationBuilder.with(qiContext)
                .withResources(R.raw.feline_a001)
                .build();

// Build the action.
        Animate animate = AnimateBuilder.with(qiContext)
                .withAnimation(myAnimation)
                .build();

// Run the action asynchronously.
        Future<Void> future=animate.async().run();

        future.andThenConsume(kedi->{
            Future<Void> future1=animate.async().run();

            future1.andThenConsume(kedi2->{
               Future<Void> future2=animate.async().run();
               future2.andThenConsume(kedi3->{
                   Intent intent=new Intent(MainActivity4.this,MainActivity.class);
                   startActivity(intent);
               });


            });


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