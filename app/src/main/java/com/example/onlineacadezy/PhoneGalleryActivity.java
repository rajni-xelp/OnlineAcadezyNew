package com.example.onlineacadezy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class PhoneGalleryActivity extends AppCompatActivity implements NewPhoneGalleryImageAdapter.ShowGalleryNextButtonInterface {

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int MY_VIDEO_CAMERA_PERMISSION_CODE = 200;
    private static final int VIDEO_CAPTURED = 2800;
    private static final String TAG = PhoneGalleryActivity.class.getSimpleName();
    public static int imageReload = 0, imageReloadAfterDelete = 0;
    public static TextView textView_roboto_boldNext;
    public static int cameraResultReload = 0;
    public static Integer source_change = 0;
    int source = 1;
    private static Context context;
    private static int media = 0;
    private static int a = 2000;
    private NewPhoneGalleryImageAdapter adapter;
    int maxNoOfImages = 0;
    private static RecyclerView recyclerView;
    ArrayList<GalleryModel> listOfAllImages;
    ArrayList<String> bkeys;
    StringBuffer sb;
    ProgressBar progress_bar;
    String imageFilePath;
    LinearLayout main_layout;
    RelativeLayout main_lini_layout;
    int spinner_counter = 0;
    int temp_source = 0;
    private int counter = 0;
    private int mCurrentFolder;
    private Integer fromPermission = 0;
    Bundle bundle;
    Intent getIntent;
    String galleryImageNew;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_gallery);
        context = PhoneGalleryActivity.this;
        source = 1;
        getIntent = getIntent();
        bundle = getIntent.getExtras();
        if (bundle != null && bundle.getInt("maxNoOfImages") != 0) {
            maxNoOfImages = bundle.getInt("maxNoOfImages");
        }
        main_layout = findViewById(R.id.main_layout);
        main_lini_layout = findViewById(R.id.main_lini_layout);
        progress_bar = findViewById(R.id.progress_bar);
        textView_roboto_boldNext = findViewById(R.id.next_text);
        progress_bar.setVisibility(View.GONE);
        textView_roboto_boldNext.setEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("All Images");
        if (source == 1 || source == 4) {
            arrayList.add("All Videos");
        }

        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(PhoneGalleryActivity.this, 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView        images=new ArrayList<>();
        listOfAllImages = new ArrayList<>();

        checkPermission();
        source = 1;
        adapter = new NewPhoneGalleryImageAdapter(maxNoOfImages, listOfAllImages, PhoneGalleryActivity.this, source, new NewPhoneGalleryImageAdapter.OnCameraClickListner() {

            @Override
            public void cameraClickNew() {
//                            MainApplication.galleryImages = new ArrayList<>();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                            || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                            || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                                MY_CAMERA_PERMISSION_CODE);
                    } else {
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                            startActivityForResult(takePictureIntent, CAMERA_REQUEST);
                        }
                    }
                } else {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, CAMERA_REQUEST);
                    }
                }
            }
        });
        recyclerView.setAdapter(adapter);

        textView_roboto_boldNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view12) {
                Intent intent = new Intent(PhoneGalleryActivity.this, WhiteBoardActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("galleryImage", galleryImageNew);
                intent.putExtras(bundle);
                setResult(6, intent);
                finish();
            }
        });
    }

    @SuppressLint("InlinedApi")
    public void getGalleryData() {
        try {
            Uri uri;
            Cursor cursor;
            int column_index_data, column_index_folder_name;
            String absolutePathOfImage = null;
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

            String[] projection = {MediaStore.MediaColumns.DATA,
                    MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

            cursor = getContentResolver().query(uri, projection, null,
                    null, MediaStore.Images.Media.DATE_TAKEN + " DESC");

            column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            listOfAllImages = new ArrayList<>();

            while (cursor.moveToNext()) {
                GalleryModel galleryModel = new GalleryModel();
                absolutePathOfImage = cursor.getString(column_index_data);

                String[] ab = absolutePathOfImage.split("/");
                int length = ab.length;

                galleryModel.setImage(absolutePathOfImage);
                galleryModel.setImageName(ab[length - 1]);
                listOfAllImages.add(galleryModel);
            }
            if (fromPermission == 1) {
                adapter = new NewPhoneGalleryImageAdapter(maxNoOfImages, listOfAllImages, PhoneGalleryActivity.this, source, new NewPhoneGalleryImageAdapter.OnCameraClickListner() {
                    @SuppressLint("NewApi")
                    @Override
                    public void cameraClickNew() {
//                        MainApplication.galleryImages = new ArrayList<>();
                        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                                || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                                || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                                    MY_CAMERA_PERMISSION_CODE);
                        } else {
                            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                                startActivityForResult(takePictureIntent, CAMERA_REQUEST);
                            }
                        }

                    }
                });
                recyclerView.setAdapter(adapter);
            }

            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


    }

    public void checkPermission() {

        if (ActivityCompat.checkSelfPermission(PhoneGalleryActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        2000);
            }
        } else {
            getGalleryData();
        }

    }


    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.MediaColumns.DATA};
        @SuppressWarnings("deprecation")
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();

        String mString = cursor.getString(column_index);

        return mString;

    }

    @Override
    protected void onStop() {
        super.onStop();
        imageReload = 0;
        imageReloadAfterDelete = 0;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 2000 && grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            fromPermission = 1;
            getGalleryData();
        }

        if (requestCode == MY_CAMERA_PERMISSION_CODE && grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        } else if (requestCode == MY_VIDEO_CAMERA_PERMISSION_CODE && grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(cameraIntent, VIDEO_CAPTURED);
        }

    }
    @Override
    public void showGalleryNextButton(int gallerySelectedContentSize,String galleryImage) {
        if (gallerySelectedContentSize > 0)
            textView_roboto_boldNext.setVisibility(View.VISIBLE);
        else {
            textView_roboto_boldNext.setVisibility(View.GONE);
        }
        if(galleryImage !=null)
        this.galleryImageNew=galleryImage;
    }
}
