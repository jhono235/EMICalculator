package com.example.emicalculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class MainActivity extends AppCompatActivity {

    ImageButton button;
    ImageView imageView;
    String pathToFile;
    EditText nameField;
    Button submit,goToList;

    ArrayList<String> userNames = new ArrayList<String>();

    Uri image_uri;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.imageButton);
        if(Build.VERSION.SDK_INT >=23){
            requestPermissions(new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });



        //binding views
        imageView = findViewById(R.id.imageView);
        nameField = findViewById(R.id.nameField);
        submit = findViewById(R.id.submit);
        goToList = findViewById(R.id.goToUserForm);








}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode ==1){
                Bitmap bitmap = BitmapFactory.decodeFile(pathToFile);
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    //camera button intent to launch camera
    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;

                photoFile = createPhotoFile();
                if(photoFile !=null) {
                    pathToFile = photoFile.getAbsolutePath();
                    Uri photoURI = FileProvider.getUriForFile(MainActivity.this,"com.example.emicalculator.provider",photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
                    startActivityForResult(takePictureIntent, 1);
                }



            }
        }




    private File createPhotoFile(){
        String name =new  SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(name, ".jpg", storageDir);
        }catch (IOException e){
            e.printStackTrace();
            Log.d("mylog","Excep : " + e.toString());
        }
        return image;
    }

    public void goToEMI(View view) {
       Intent intent = new Intent(this,Calculator.class);
       startActivity(intent);
    }

    public void submitName(View view) {
        String names = nameField.getText().toString();
        userNames.add(names);

        Toast.makeText(this,"User Added!",Toast.LENGTH_SHORT).show();

        nameField.setText(null);
    }

    public void goToUser(View view) {
        Intent intent = new Intent(MainActivity.this, UserList.class);
        intent.putExtra("FILES_TO_SEND", userNames);
        startActivity(intent);
    }
}

