package com.zikozee.skill;

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
public class SkillFragment extends Fragment {

    View v;
    private ProgressBar mLoadingProgress;
    private RecyclerView myRecyclerView;

    public SkillFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_skill, container, false);
        // Inflate the layout for this fragment
        mLoadingProgress = v.findViewById(R.id.pb_loading_skill);
        mLoadingProgress.setVisibility(View.VISIBLE);

        myRecyclerView = v.findViewById(R.id.skill_recyclerview);
        LinearLayoutManager learningLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        myRecyclerView.setLayoutManager(learningLayoutManager);

        try {
            URL skillUrl = ApiUtil.buildUrl("/api/skilliq");
            new SkillQueryTask().execute(skillUrl);

        }catch (Exception e){
            Log.d("error", e.getMessage());
        }

        return v;
    }

    public class SkillQueryTask extends AsyncTask<URL, Void, String> {

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
            TextView error = v.findViewById(R.id.error_skill);
            mLoadingProgress.setVisibility(View.GONE);
            if(error == null){
                myRecyclerView.setVisibility(View.INVISIBLE);
                error.setVisibility(View.VISIBLE);
            }else{
                myRecyclerView.setVisibility(View.VISIBLE);
                error.setVisibility(View.INVISIBLE);
            }
            ArrayList<Skill> skills = ApiUtil.getSkillFromJson(result);
            SkillAdapter adapter = new SkillAdapter(skills);
            myRecyclerView.setAdapter(adapter);
        }
    }

}