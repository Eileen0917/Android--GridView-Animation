package com.example.eileen.hw3_4;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory{

    GridView gvPhotos;
    ImageSwitcher isPhoto;


    int [] imgId={R.drawable.img01,R.drawable.img02,R.drawable.img03,R.drawable.img04,R.drawable.img05,R.drawable.img06};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gvPhotos = (GridView) this.findViewById(R.id.gvPhoto);
        isPhoto = (ImageSwitcher) this.findViewById(R.id.isPhoto);


        isPhoto.setFactory(this);
        gvPhotos.setOnItemClickListener(new GridView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                switch ((int) (Math.random() * 4 + 1)) {
                    case 1:
                        isPhoto.setInAnimation(getApplicationContext(), R.anim.ani_rotate_in);
                        isPhoto.setOutAnimation(getApplicationContext(), R.anim.ani_rotate_out);
                        break;
                    case 2:
                        isPhoto.setInAnimation(getApplicationContext(), R.anim.ani_scale_in);
                        isPhoto.setOutAnimation(getApplicationContext(), R.anim.ani_scale_out);
                        break;
                    case 3:
                        isPhoto.setInAnimation(getApplicationContext(), R.anim.ani_alpha_in);
                        isPhoto.setOutAnimation(getApplicationContext(), R.anim.ani_alpha_out);
                        break;
                    case 4:
                        isPhoto.setInAnimation(getApplicationContext(), R.anim.ani_trans_in);
                        isPhoto.setOutAnimation(getApplicationContext(), R.anim.ani_trans_out);
                        break;
                }

                isPhoto.setImageResource(imgId[arg2]);
            }});
        GvAdapter myAdapter = new GvAdapter(this);
        gvPhotos.setAdapter(myAdapter);
    }

    class GvAdapter extends BaseAdapter{
        Activity mContext;
        public GvAdapter(MainActivity gvCxt){ mContext=gvCxt;}

        public int getCount(){return imgId.length;}

        public Object getItem(int arg0) {return null;}

        public long getItemId(int pos) {return pos;}

        public View getView(int arg0,View arg1,ViewGroup arg2) {
            ImageView iv = new ImageView(mContext);
            iv.setImageResource(imgId[arg0]);
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            int size = (int) getResources().getDimension(R.dimen.grid_image_size);
            iv.setLayoutParams(new GridView.LayoutParams(size, size));
            return iv;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public View makeView(){
        ImageView vi=new ImageView(this);
        vi.setScaleType(ImageView.ScaleType.FIT_CENTER);
        vi.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return vi;
    }
}
