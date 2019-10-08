package com.example.qrreader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class ImageDownloaderTask extends AsyncTask<String,Void, Bitmap> {
    private final WeakReference<ImageView> imageViewWeakReference;
    public ImageDownloaderTask(ImageView imageView){
        imageViewWeakReference=new WeakReference<>(imageView);
    }
    @Override
    protected Bitmap doInBackground(String... strings) {
        return downLoadBitmap(strings[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(isCancelled()){
            bitmap=null;
        }
        ImageView imgView=imageViewWeakReference.get();
        if(imgView!=null){
            if(bitmap!=null){
                imgView.setImageBitmap(bitmap);
            }
        }
    }

    private Bitmap downLoadBitmap(String url){
        HttpURLConnection httpURLConnection=null;
        try {
            URL uri=new URL(url);
            httpURLConnection=(HttpURLConnection) uri.openConnection();
            int statusCode=httpURLConnection.getResponseCode();
            if(statusCode!=httpURLConnection.HTTP_OK){
                return null;
            }
            InputStream inputStream=httpURLConnection.getInputStream();
            if(inputStream!=null){
                return BitmapFactory.decodeStream(inputStream);
            }
        }catch (Exception e){
            httpURLConnection.disconnect();

        }finally {
            if(httpURLConnection!=null){
                httpURLConnection.disconnect();

            }
        }
        return null;

    }
}
