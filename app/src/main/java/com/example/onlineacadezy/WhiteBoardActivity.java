package com.example.onlineacadezy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WhiteBoardActivity extends AppCompatActivity {
    String galleryImage;
    RelativeLayout rl_whiteboard;
    List<ModelCanvas> canvasViewList;
    int currentCanvas=0;
    Bitmap bit;
    ImageView iv_share;
    public  static WhiteBoardActivity whiteBoardActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_white_board);
        whiteBoardActivity=this;
        iv_share=findViewById(R.id.iv_share);
        TextView tv_clear = findViewById(R.id.tv_clear);
        TextView tv_upload = findViewById(R.id.tv_upload);
        TextView tv_newWhiteBoard=findViewById(R.id.tv_newWhiteBoard);
        final TextView tv_prev=findViewById(R.id.tv_prev);
        final TextView tv_next=findViewById(R.id.tv_next);
        canvasViewList =new ArrayList<>();
        rl_whiteboard = findViewById(R.id.activity_my_view_whiteboard);
        tv_prev.setVisibility(View.INVISIBLE);
        tv_next.setVisibility(View.INVISIBLE);
        PaintView   paintView = new PaintView(WhiteBoardActivity.this);
        ModelCanvas modelCanvas=new ModelCanvas();
        List<ImageDataModel> imageDataModelList=new ArrayList<>();
        modelCanvas.setPaintView(paintView);
        modelCanvas.setImageDataModelList(imageDataModelList);
        canvasViewList.add(modelCanvas);

        rl_whiteboard.addView(canvasViewList.get(0).getPaintView());
        checkPermission();
        tv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasViewList.get(currentCanvas).getPaintView().clearCanvas();
            }
        });
        tv_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WhiteBoardActivity.this, PhoneGalleryActivity.class);
                startActivityForResult(intent, 6);

//                ImageDataModel imageDataModel = new ImageDataModel();
//                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.asssessment);
//                imageDataModel.setBitmap(bmp);
//                canvasViewList.get(currentCanvas).getImageDataModelList().add(imageDataModel);
//                canvasViewList.get(currentCanvas).getPaintView().putImage(canvasViewList.get(currentCanvas).getImageDataModelList());

            }
        });

        iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_whiteboard.setDrawingCacheEnabled(true);
               bit = rl_whiteboard.getDrawingCache();

//                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.assessment_1);
//                Uri image_uri = getImageUri(bit);
                Uri image_uri = getImageUri(bit);
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("image/*");
                sharingIntent.putExtra(Intent.EXTRA_STREAM, image_uri);
                startActivity(Intent.createChooser(sharingIntent, "Share image using"));
                rl_whiteboard.setDrawingCacheEnabled(false);
            }
        });

        tv_newWhiteBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_whiteboard.removeAllViews();
                tv_prev.setVisibility(View.VISIBLE);
                currentCanvas=canvasViewList.size();
                tv_prev.setVisibility(View.VISIBLE);
                tv_next.setVisibility(View.INVISIBLE);
                PaintView   paintView = new PaintView(WhiteBoardActivity.this);
                List<ImageDataModel> imageDataModelList=new ArrayList<>();
                ModelCanvas modelCanvas=new ModelCanvas();
                modelCanvas.setPaintView(paintView);
                modelCanvas.setImageDataModelList(imageDataModelList);
                canvasViewList.add(modelCanvas);
                rl_whiteboard.addView(canvasViewList.get(currentCanvas).getPaintView());

            }
        });

        tv_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCanvas=currentCanvas-1;
                rl_whiteboard.removeAllViews();
                rl_whiteboard.addView(canvasViewList.get(currentCanvas).getPaintView());
//                && currentCanvas<=(canvasViewList.size()-1)
                if(canvasViewList.size()>1 && currentCanvas>=1)
                {
                    tv_prev.setVisibility(View.VISIBLE);
                }
                else
                {
                    tv_prev.setVisibility(View.INVISIBLE);
                }

                if(canvasViewList.size()>1 && currentCanvas<(canvasViewList.size()-1))
                {
                    tv_next.setVisibility(View.VISIBLE);
                }
                else
                {
                    tv_next.setVisibility(View.INVISIBLE);
                }
            }
        });

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCanvas=currentCanvas+1;
                rl_whiteboard.removeAllViews();
                rl_whiteboard.addView(canvasViewList.get(currentCanvas).getPaintView());

                if(canvasViewList.size()>1 && currentCanvas>=1)
                {
                    tv_prev.setVisibility(View.VISIBLE);
                }
                else
                {
                    tv_prev.setVisibility(View.INVISIBLE);
                }

                if(canvasViewList.size()>1 && currentCanvas<(canvasViewList.size()-1))
                {
                    tv_next.setVisibility(View.VISIBLE);
                }
                else
                {
                    tv_next.setVisibility(View.INVISIBLE);
                }

            }
        });

//        tv_share.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                rl_whiteboard.setDrawingCacheEnabled(true);
//                 bit = rl_whiteboard.getDrawingCache();
//                image.setImageBitmap(bit);
//                Uri image_uri = getImageUri(bit);
//                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//                StrictMode.setVmPolicy(builder.build());
//                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
//                sharingIntent.setType("image/*");
//                sharingIntent.putExtra(Intent.EXTRA_STREAM, image_uri);
//                startActivity(Intent.createChooser(sharingIntent, "Share image using"));
//            }
//        });

    }

    public void checkPermission() {

        if (ActivityCompat.checkSelfPermission(WhiteBoardActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        2000);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 2000 && grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        }
    }

    public Uri getImageUri(Bitmap bmp) {
        Uri bmpUri = null;
        try {

            File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "IMG_" + System.currentTimeMillis() + ".jpg");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();

            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

    public void getLocalBitmapUri() {
        Bitmap bmp = null;
        File filenew = new File(galleryImage);
        File reSizedImageFile = ReduceFileSize.reduce(filenew);
//        int file_size_resized = Integer.parseInt(String.valueOf(reSizedImageFile.length()/1024));
        Uri mUri = FileProvider.getUriForFile(WhiteBoardActivity.this, "com.example.onlineacadezy.fileprovider", reSizedImageFile);
        try {
            bmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".jpeg");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageDataModel imageDataModel = new ImageDataModel();
        imageDataModel.setBitmap(bmp);
        canvasViewList.get(currentCanvas).getImageDataModelList().add(imageDataModel);
//        paintViewList.get(currentCanvas).setImageDataModelList(imageDataModelList);
        canvasViewList.get(currentCanvas).getPaintView().putImage(canvasViewList.get(currentCanvas).getImageDataModelList());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 6) {
            if (resultCode == 6) {
                galleryImage = data.getExtras().getString("galleryImage");
                getLocalBitmapUri();
            }
        }
    }
}