package com.caimuhao.rxpicker.ui.preview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caimuhao.rxpicker.R;
import com.caimuhao.rxpicker.bean.ImageItem;
import com.caimuhao.rxpicker.ui.adapter.PreviewAdapter;
import com.caimuhao.rxpicker.utils.RxPickerManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Smile
 * @time 2017/4/20  上午11:37
 * @desc ${TODD}
 */
public class PreviewActivity extends AppCompatActivity {

  private ViewPager vpPreview;
  private PreviewAdapter vpAdapter;
  private LinearLayout llTop;
  private ImageView ivBack;
  private TextView title;

  private List<ImageItem> data;

  public static void start(Context context, ArrayList<ImageItem> data) {
    Intent intent = new Intent(context, PreviewActivity.class);
    intent.putExtra("preview_list", data);
    context.startActivity(intent);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_preview);
    handleData();
    initTopView();
    vpPreview =  findViewById(R.id.vp_preview);
    vpAdapter = new PreviewAdapter(data);
    vpPreview.setAdapter(vpAdapter);
    vpPreview.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
      @Override public void onPageSelected(int position) {
        title.setText(position + 1 + "/" + data.size());
      }
    });
  }

  private void handleData() {
    data = (List<ImageItem>) getIntent().getSerializableExtra("preview_list");
  }

  private void initTopView(){
    llTop = findViewById(R.id.ll_top);
    ivBack = findViewById(R.id.iv_back);
    title = findViewById(R.id.tv_title);
    llTop.setBackgroundResource(RxPickerManager.getInstance().getConfig().getBackgroundColor());
    title.setText("1/"+data.size());
    ivBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }

}
