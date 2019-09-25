package com.hyb.rxpicker;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.caimuhao.rxpicker.RxPicker;
import com.caimuhao.rxpicker.bean.ImageItem;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    boolean hasNoPermission = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    hasNoPermission = false;
                    RxPermissions rxPermissions = new RxPermissions(MainActivity.this);
                    rxPermissions.requestEach(permissions).subscribe(new Observer<Permission>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Permission permission) {
                            if (!permission.granted) {
                                hasNoPermission = true;
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {
                            if (!hasNoPermission) {
                                showPicker();
                            } else {
                                Toast.makeText(MainActivity.this, "请允许相关权限后再操作", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    showPicker();
                }
            }
        });
    }

    @SuppressLint("CheckResult")
    private void showPicker(){
        RxPicker.of()
                .single(false)
                .camera(true)
                .limit(1,9)
                .backgroundColor(android.R.color.holo_green_light)
                .checkBoxColor(android.R.color.holo_blue_bright)
                .start(this)
                .subscribe(new Consumer<List<ImageItem>>() {
                    @Override
                    public void accept(List<ImageItem> imageItems) throws Exception {
                        for(ImageItem item:imageItems){
                            Log.i("tag" , "image path:" + item.getPath());
                        }
                    }
                });
    }

}
