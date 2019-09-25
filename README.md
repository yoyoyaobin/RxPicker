# RxPicker
超方便的拍照相册选择器

![image](https://github.com/yoyoyaobin/RxPicker/blob/master/app/src/main/assets/1.jpg)

## 配置
1.在project的build.gradle里配置
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
2.在app的build.gradle里配置
```
implementation 'com.github.yoyoyaobin:ReboundLayout:1.0.0'
```

## 使用方式
获取存储以及相机权限后调用
```
RxPicker.of()
                .single(false) //是否单张选择
                .camera(true)//是否拥有相机功能
                .limit(1,9)//最少-最多选择数量
                .backgroundColor(android.R.color.black)//整体背景颜色
                .checkBoxColor(android.R.color.holo_red_dark)//图片选中框颜色
                .start(this)//activity or fragment
                .subscribe(new Consumer<List<ImageItem>>() {
                    @Override
                    public void accept(List<ImageItem> imageItems) throws Exception {
                        for(ImageItem item:imageItems){
                            Log.i("tag" , "image path:" + item.getPath());
                        }
                    }
                });
```

## 感谢
感谢原作者
