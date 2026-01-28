package com.example.myapplicationproject;

import android.graphics.Bitmap;

public class Data
{
        private int id;
        private String title;
        private String Adresse;
        private Bitmap image;

        public Data(int id, String title, String Adresse, Bitmap image) {
                this.id = id;
                this.title = title;
                this.Adresse = Adresse;
                this.image=image;

        }

        public int getId() {
                return id;
        }

        public String getTitle() {
                return title;
        }

        public Bitmap getImage() {
                return image;
        }

        public String getAdresse() {
                return Adresse;
        }
}
