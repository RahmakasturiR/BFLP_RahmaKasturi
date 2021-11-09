package com.example.bflp_rahmakasturi.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bflp_rahmakasturi.Model.DataArtis;
import com.example.bflp_rahmakasturi.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class GridAdapter extends BaseAdapter {
    final private Context context;
    ArrayList<DataArtis> list;
    LayoutInflater inflater;

    private OnItemClick onItemClick;

    public void setOnItemClickCallback(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public GridAdapter(Context context, ArrayList<DataArtis> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.grid_item, null);

        try {

            ImageView img = (ImageView) convertView.findViewById(R.id.imgbg);
            TextView artis = (TextView) convertView.findViewById(R.id.txtartist);
            String nama = list.get(position).getProfilband();
            DataArtis dataArtis = list.get(position);
//            Log.i("TAG", "getView: " + nama);
            artis.setText(String.valueOf(nama));

            String mURL = list.get(position).getImage();

            Picasso.get()
                    .load(mURL)
                    .into(img, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {
                            Toast.makeText(context, "error : " + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    });

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  onItemClick.onItemClick(dataArtis);
                }
            });
        } catch (Exception e) {
            Log.e("TAG", String.valueOf(e));
        }
        return convertView;
    }
}
