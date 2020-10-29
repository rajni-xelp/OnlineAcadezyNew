package com.example.onlineacadezy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewPhoneGalleryImageAdapter extends RecyclerView.Adapter<NewPhoneGalleryImageAdapter.Viewholder> {

    private int source;
    private ArrayList<GalleryModel> images;
    Context context;
    public int counter = 0;
    public ArrayList<String> galleryImagesList;
    private OnCameraClickListner listner;
    ShowGalleryNextButtonInterface showGalleryNextButtonInterface;
    int maxNoOfImages;
    int maxNoOfVideos = 0;

    public NewPhoneGalleryImageAdapter(int maxNoOfImages, ArrayList<GalleryModel> images, Context context, int src, OnCameraClickListner listner) {
        this.images = images;
        this.context = context;
        source = src;
        galleryImagesList = new ArrayList<>();
        this.listner = listner;
        this.maxNoOfImages = maxNoOfImages;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_gallery_image, parent, false);

        return new Viewholder(view);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull final Viewholder holder, final int position) {
        holder.camera_image.setVisibility(View.GONE);
        holder.bg_img.setVisibility(View.VISIBLE);
        String hero = images.get(position).getImage();
        Glide.with(context).load(hero).into(holder.img_galley);

        holder.bg_img.setSelected(false);
        holder.circle_img.setVisibility(View.GONE);
        holder.img_galley_border.setVisibility(View.GONE);

        if (images.get(position).isSelected()) {
            holder.img_galley_border.setVisibility(View.VISIBLE);
            holder.circle_img.setVisibility(View.VISIBLE);
        } else {
            holder.img_galley_border.setVisibility(View.GONE);
            holder.circle_img.setVisibility(View.GONE);
        }
        holder.bg_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // uploading images.
                if (images.get(position).isSelected()) {
                    images.get(position).setSelected(false);
                    holder.img_galley_border.setVisibility(View.GONE);
                    holder.circle_img.setVisibility(View.GONE);
                    maxNoOfImages -= 1;
                    counter -= 1;
                    galleryImagesList.remove(images.get(position).getImage());
                    // MainApplication.galleryImages.remove(images.get(position).getImage());
                } else {
                    if (maxNoOfImages < 1) {
                        images.get(position).setSelected(true);
                        holder.img_galley_border.setVisibility(View.VISIBLE);
                        holder.circle_img.setVisibility(View.VISIBLE);
                        counter += 1;
                        maxNoOfImages += 1;
                        galleryImagesList.add(images.get(position).getImage());
                        //  MainApplication.galleryImages.add(images.get(position).getImage());
                    } else {
                        Toast.makeText(context, "You can only select 1 image", Toast.LENGTH_SHORT).show();
                    }
                }
                // showing next button based on condition
                if (showGalleryNextButtonInterface != null) {
                    if(!galleryImagesList.isEmpty())
                    showGalleryNextButtonInterface.showGalleryNextButton(maxNoOfImages, galleryImagesList.get(0));
                    else
                        showGalleryNextButtonInterface.showGalleryNextButton(maxNoOfImages, null);

                } else {
                    showGalleryNextButtonInterface = (PhoneGalleryActivity) context;
                    if(!galleryImagesList.isEmpty())
                    showGalleryNextButtonInterface.showGalleryNextButton(maxNoOfImages, galleryImagesList.get(0));
                    else
                        showGalleryNextButtonInterface.showGalleryNextButton(maxNoOfImages, null);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public interface OnCameraClickListner {
        void cameraClickNew();
    }

    public interface ShowGalleryNextButtonInterface {
        void showGalleryNextButton(int gallerySelectedContentSize, String galleryImage);
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        ImageView img_galley, circle_img, img_galley_border;
        FrameLayout bg_img, camera_image;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            img_galley = (ImageView) itemView.findViewById(R.id.img_galley);
            img_galley_border = (ImageView) itemView.findViewById(R.id.img_galley_border);
            circle_img = (ImageView) itemView.findViewById(R.id.circle_img);
            bg_img = (FrameLayout) itemView.findViewById(R.id.bg_img);
            camera_image = (FrameLayout) itemView.findViewById(R.id.camera_image);

        }

        public void setCamera_Click() {
            camera_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listner.cameraClickNew();

                }
            });
        }

    }


}


