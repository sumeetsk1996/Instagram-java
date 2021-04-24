package com.example.instagram;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    ImageView cameraToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
     //   AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
       //         R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
         //       .build();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
     //   NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
     //   NavigationUI.setupWithNavController(navView, navController);

        Toolbar toolbar=findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        ImageView cameraToolbar=toolbar.findViewById(R.id.camera);

        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    }
                    ,15);
        }

        cameraToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,15);
                Toast.makeText(MainActivity.this, "Camera is clicked", Toast.LENGTH_SHORT).show();
            }
        });


        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                if (menuItem.getItemId()==R.id.navigation_home){

                }
                return true;
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==15){
            Bitmap captureimage=(Bitmap)data.getExtras().get("data");
            cameraToolbar.setImageBitmap(captureimage);
        }

    }
}