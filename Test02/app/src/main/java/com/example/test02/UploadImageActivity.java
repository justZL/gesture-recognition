package com.example.test02;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.test02.databinding.ActivityUploadImageBinding;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class UploadImageActivity extends Activity {
    private static final String TAG = UploadImageActivity.class.getSimpleName();
    private static final String SERVER_URL = "http://39.101.74.210:8000/save_profile/";
    private ActivityUploadImageBinding binding;
    public static int ALBUM_RESULT_CODE = 0x999 ;
    private static String impath="";
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        handleImageOnKitKat(data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        setContentView(R.layout.activity_upload_image);
        //这一两行代码主要是向用户请求权限
        if (ActivityCompat.checkSelfPermission(UploadImageActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
          {
            ActivityCompat.requestPermissions(UploadImageActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE
            },1);
            }



        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 替换为您的图像文件路径

                String imagePath = impath;
                Intent intent=openGallery(1);
                // 创建一个新的OkHttpClient实例
//                OkHttpClient.Builder builder
//                SSLContext sslContext = SSLContextUtil.getDefaultSLLContext();
//                if (sslContext != null) {
//                    SSLSocketFactory socketFactory = sslContext.getSocketFactory();
//                    builder.sslSocketFactory(socketFactory);
//                }
//                builder.hostnameVerifier(SSLContextUtil.HOSTNAME_VERIFIER);

                OkHttpClient client = new OkHttpClient();

                // 创建Multipart请求构建器
                MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM);

                // 添加图像文件到请求体
                File imageFile = new File(imagePath);
                requestBodyBuilder.addFormDataPart("image", imageFile.getName(),
                        RequestBody.create(MediaType.parse("image/jpeg"), imageFile));

                // 构建请求体
                RequestBody requestBody = requestBodyBuilder.build();

                // 创建POST请求
                Request request = new Request.Builder()
                        .url(SERVER_URL)
                        .post(requestBody)
                        .build();

                // 发送请求并处理响应
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e(TAG, "请求失败：" + e.getMessage());
                        // 处理请求失败的情况
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String responseBody = response.body().string();
                            Log.i(TAG, "请求成功，服务器响应：" + responseBody);
                            // 处理请求成功的情况
                        } else {
                            Log.e(TAG, "请求失败，错误码：" + response.code());
                            // 处理请求失败的情况
                        }
                    }
                });
            }
        });
        binding.btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Looper.prepare();
                Toast.makeText(UploadImageActivity.this, "传输失败，请检查网络连接-……-", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        });
    }
    private OkHttpClient getOkHttpClient() {
        try {
            // 加载SSL证书文件
            InputStream inputStream = getResources().openRawResource(R.raw.justzlxyz);
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(inputStream);
            inputStream.close();

            // 创建Keystore，并将SSL证书添加到Keystore中
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            keyStore.setCertificateEntry("certificate", certificate);

            // 创建TrustManagerFactory，用于验证SSL证书
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            // 创建SSLContext，并配置OkHttpClient以使用它
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

            // 创建X509TrustManager，用于自定义SSL验证
            X509TrustManager trustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };

            // 配置OkHttpClient以使用自定义SSL验证
            return new OkHttpClient.Builder()
                    .sslSocketFactory(sslContext.getSocketFactory(), trustManager)
                    .hostnameVerifier((hostname, session) -> true)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return new OkHttpClient();
        }
    }
    private Intent openGallery(int type) {
        Intent gallery = new Intent(Intent.ACTION_PICK);
        gallery.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(gallery, type);
        return gallery;
    }
    @SuppressLint("Range")
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if(cursor != null){

            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void openSysAlbum() {
        Intent albumIntent = new Intent(Intent.ACTION_PICK);
        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(albumIntent, ALBUM_RESULT_CODE);
    }
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content: //downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        // 根据图片路径显示图片
        impath=imagePath;
        displayImage(imagePath);
        System.out.println(imagePath);
    }

    /**展示图片*/
    private void displayImage(String imagePath) {
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        binding.displayImage.setImageBitmap(bitmap);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        // 这里没有判断是否匹配，data为空
//        Glide.with(this).load(data.getData()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(helpBinding.ivHelpImageFirst);
//    }

    public void performNetworkRequest() {
        // 创建一个新的线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitm=null;
                try {
                    // 在此处执行网络请求
                    URL url = new URL("http://39.101.74.210:8000/static/round.png");
                    bitm = BitmapFactory.decodeStream(url.openStream());
                    // 设置请求方法、请求头、请求体等
                    // 执行请求并处理响应
                    // 处理输入流
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(2000); // 休眠2秒钟
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 网络请求完成后，在这里执行任何后续操作
                // 请注意，您不能直接更新UI，因为这是在主线程之外执行的

                // 如果需要更新UI，可以使用Handler将操作发送到主线程
                Handler handler = new Handler(Looper.getMainLooper());
                Bitmap finalBitm = bitm;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // 在主线程中执行UI更新操作
                        if (finalBitm !=null){
                            binding.imageView2.setImageBitmap(finalBitm);

                        }else{

                        }
                    }
                });
            }
        });

        // 启动线程
        thread.start();
    }

}

