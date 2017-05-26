package com.labboy.zefta.gelumbang.kajianku.Kajian;

/**
 * Created by acer on 26/04/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.labboy.zefta.gelumbang.kajianku.R;


public class KajianAdapter extends RecyclerView.Adapter<KajianAdapter.KajianAdapterViewHolder> {
    private String[] mKajian;


    private final KajianAdapterOnClickHandler mClickHandler;

    @Override
    public KajianAdapter.KajianAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.rec_kajian;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutIdForListItem, viewGroup, false);
        return new KajianAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KajianAdapter.KajianAdapterViewHolder kajianAdapterViewHolder, int position) {
      String kajianAll = mKajian[position];
        kajianAdapterViewHolder.mKajianTextView.setText(kajianAll);
    }

    @Override
    public int getItemCount() {
       if (null == mKajian) return 0;
        return mKajian.length;
    }

    public void setKajianData(String[] kajianData)
    {
        mKajian = kajianData;
        notifyDataSetChanged();
    }


    public interface KajianAdapterOnClickHandler {
        void onClick(String kajianAll);
    }

//
//    public KajiandAdapter(Context context, List<Kajian> agt) {
//        this.agt = agt;
//        this.mContext = context;
//    }
    public KajianAdapter(KajianAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public class KajianAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView mKajianTextView;

        public KajianAdapterViewHolder(View itemView) {
            super(itemView);
            mKajianTextView = (TextView) itemView.findViewById(R.id.tv_kajian);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String kajianAll = mKajian[adapterPosition];
            mClickHandler.onClick(kajianAll);
        }
    }


//    @Override
//    public KajianViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rec_kajian, null);
//        KajianViewHolder mh = new KajianViewHolder(v);
//        return mh;
//    }
//
//    @Override
//    public void onBindViewHolder(KajianViewHolder feedListRowHolder, int i) {
//        Kajian item = agt.get(i);
//
//        feedListRowHolder.nama.setText(item.getNama());
//        feedListRowHolder.alamat.setText(item.getAlamat());
//        feedListRowHolder.latitude.setText(item.getLatitude());
//        feedListRowHolder.longitude.setText(item.getLongitude());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return (null != agt ? agt.size() : 0);
//    }
//

}