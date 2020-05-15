package com.example.eduvite;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import java.io.IOException;

public class Profile extends AppCompatActivity {
    public Bitmap bitmap;
    ImageView img;
    Uri uri;
    public static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Window win = getWindow();
        win.setStatusBarColor(getResources().getColor(R.color.colorPrimary, getTheme()));

        setImage();

        //Configure your toolbar
//        Toolbar tb = (Toolbar) findViewById(R.id.profile_toolbar);
//        setSupportActionBar(tb);
    }

    public void setImage() {
        img = (ImageView) findViewById(R.id.profile_img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "insert picture"), PICK_IMAGE);
            }
        });

        Glide.with(this)
                .load(R.drawable.girl)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .transform(new CircleCrop())
                .into(img);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) ;

        try {
            uri = data.getData();
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
/**
 *        ImageDecoder.decodeBitmap(ImageDecoder.createSource(this.getContentResolver(), uri));
 */
            img.setImageBitmap(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
