package com.labboy.zefta.gelumbang.kajianku.Kajian;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.labboy.zefta.gelumbang.kajianku.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

import Utilities.NetworkUtil;

public class KajianActivity extends AppCompatActivity implements KajianAdapter.KajianAdapterOnClickHandler {

    private static final String TAG = "Recycler Kajianku";
    private static final String URL = "http://kajianku.herokuapp.com/kajianku.json";
    private RecyclerView mRecyclerView;
    private KajianAdapter mKajianAapter;
    public LinearLayout ll;
    Toolbar toolbar;
    ProgressBar mLoadingIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kajian);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_kajian);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        toolbar.setLogo(R.mipmap.ic_kajianku);
        toolbar.setTitle("Kajianku");
        setSupportActionBar(toolbar);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mKajianAapter = new KajianAdapter (this);
        mRecyclerView.setAdapter(mKajianAapter);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        mLoadingIndicator.setVisibility(View.VISIBLE);

        new asyncTask().execute(URL);
    }

    @Override
    public void onClick(String kajianAll) {
        Context context = this;
        Toast.makeText(context, kajianAll, Toast.LENGTH_SHORT).show();
    }

    public class asyncTask extends AsyncTask <String, Void, String[]>
    {
        @Override
        protected String[] doInBackground(String... params) {
            String dataUrl = params[0];
            URL requestUrl = NetworkUtil.buildUrl(dataUrl);

            try {
                String jsonResponse = NetworkUtil.getResponseFromHttpUrl(requestUrl);

                String[] resultJson = parseResult(jsonResponse);
                return resultJson;

            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[] kajianData) {
            if(kajianData !=null) {
                mLoadingIndicator.setVisibility(View.INVISIBLE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mKajianAapter.setKajianData(kajianData);
            }
            else{
                mLoadingIndicator.setVisibility(View.INVISIBLE);
                Toast.makeText(KajianActivity.this, "Data Gagal di muat", Toast.LENGTH_SHORT).show();
            }



        }
    }

    public String[] parseResult(String result) throws JSONException{
        final String KAJIAN = "kajian";

        JSONObject parseObject = new JSONObject(result);

        JSONArray jsonArray = parseObject.getJSONArray(KAJIAN);

        String parsed[] = null;


        for (int i = 0; i<jsonArray.length();i++)
        {
            JSONObject post = jsonArray.getJSONObject(i);
            String judul = post.getString("jdl_kajian");
            parsed[i] = judul;
        }
        return parsed;


    }


}
