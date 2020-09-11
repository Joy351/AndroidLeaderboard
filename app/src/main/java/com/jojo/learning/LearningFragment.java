package com.zikozee.learning;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.zikozee.ApiUtil;
import com.zikozee.R;

import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LearningFragment extends Fragment {

    private ProgressBar mLoadingProgress;
    private RecyclerView myRecyclerView;
    View v;

    public LearningFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_learning, container, false);
        // Inflate the layout for this fragment
        Context context = container.getContext();
        mLoadingProgress = v.findViewById(R.id.pb_loading_learning);
        mLoadingProgress.setVisibility(View.VISIBLE);

        myRecyclerView = v.findViewById(R.id.learning_recyclerview);
        LinearLayoutManager learningLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        myRecyclerView.setLayoutManager(learningLayoutManager);

        try {
            URL learningUrl = ApiUtil.buildUrl("/api/hours");
            new LearningQueryTask().execute(learningUrl);
        }catch (Exception e){
            Log.d("error", e.getMessage());
        }

        return v;
    }



    public class LearningQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL searchURL = urls[0];
            String result = null;

            try{
                result = ApiUtil.getJson(searchURL);
            }catch (Exception e){
                Log.e("error", e.toString());
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
//            TextView tvResult = v.findViewById(R.id.dummyText);
            TextView error = v.findViewById(R.id.error_learning);
            mLoadingProgress.setVisibility(View.GONE);
            if(error == null){
                myRecyclerView.setVisibility(View.INVISIBLE);
                error.setVisibility(View.VISIBLE);
            }else{
                myRecyclerView.setVisibility(View.VISIBLE);
                error.setVisibility(View.INVISIBLE);
            }
            ArrayList<Learning> learnings = ApiUtil.getLearningFromJson(result);
//            String resultString ="";
//            for(Learning learning: learnings){
//                resultString = resultString + learning.getName() + "\n"+ learning.getCountry()
//                        + "\n"+ learning.getHours() + "\n"+ learning.getUrl() + "\n\n";
//            }
           LearningAdapter adapter = new LearningAdapter(learnings);
            myRecyclerView.setAdapter(adapter);
        }
    }
}