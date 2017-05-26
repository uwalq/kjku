package com.labboy.zefta.gelumbang.kajianku.masjid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.labboy.zefta.gelumbang.kajianku.R;

import java.util.List;

/**
 * Created by acer on 18/04/2017.
 */


public class MasjidAdapter extends RecyclerView.Adapter<MasjidViewHolder> {

    private List<Masjid> agt;
    private Context mContext;

    public MasjidAdapter(Context context, List<Masjid> agt) {
        this.agt = agt;
        this.mContext = context;
    }

    @Override
    public MasjidViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rec_item, null);
        MasjidViewHolder mh = new MasjidViewHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(MasjidViewHolder feedListRowHolder, int i) {
        Masjid item = agt.get(i);

        feedListRowHolder.nama.setText(item.getNama());
        feedListRowHolder.alamat.setText(item.getAlamat());
        feedListRowHolder.latitude.setText(item.getLatitude());
        feedListRowHolder.longitude.setText(item.getLongitude());

    }

    @Override
    public int getItemCount() {
        return (null != agt ? agt.size() : 0);
    }


}