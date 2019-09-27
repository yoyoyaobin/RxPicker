# RxPicker
超方便的拍照相册选择器,拥有单选、多选、拍照、预览图片缩放等功能。

![image](https://jitpack.io/v/yoyoyaobin/RxPicker.svg)

![image](https://github.com/yoyoyaobin/RxPicker/blob/master/app/src/main/assets/1.png)

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
implementation 'com.github.yoyoyaobin:RxPicker:1.0.1'
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
本库基于https://github.com/imuhao/RxPicker 修改，新增部分特性以及减少流程，感谢原作者。
