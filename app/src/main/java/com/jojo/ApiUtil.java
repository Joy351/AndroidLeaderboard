package com.zikozee;

import android.util.Log;

import com.zikozee.learning.Learning;
import com.zikozee.skill.Skill;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class ApiUtil {
    private ApiUtil(){}

    public static final String BASE_API_URI = "https://gadsapi.herokuapp.com";

    public static URL buildUrl(String title){
        String fullUrl = BASE_API_URI  + title;

        URL url = null;
        try{
            url = new URL(fullUrl);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getJson(URL url) throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();

        try{
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");
            boolean hasData = scanner.hasNext();
            if(hasData){
                return  scanner.next();
            }else{
                return null;
            }
        }catch (Exception e){
            Log.d("Error", e.toString());
            return  null;
        }finally {
            connection.disconnect();
        }

    }

    public static ArrayList<Skill> getSkillFromJson(String json){
        final String NAME = "name";
        final String SCORE = "score";
        final String COUNTRY = "country";
        final String URL = "badgeUrl";

        ArrayList<Skill> skills = new ArrayList<>();

        try{
//            JSONObject jsonSkills = new JSONObject(json);
            JSONArray arraySKills = new JSONArray(json);
            int numberOfSkills = arraySKills.length();

            for(int i=0; i< numberOfSkills; i++){
                JSONObject skillJSON = arraySKills.getJSONObject(i);
                Skill skill = new Skill(skillJSON.getString(NAME), skillJSON.getInt(SCORE),
                        skillJSON.getString(COUNTRY), skillJSON.getString(URL));

                skills.add(skill);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
        return skills;
    }

    public static ArrayList<Learning> getLearningFromJson(String json){
        final String NAME = "name";
        final String HOURS = "hours";
        final String COUNTRY = "country";
        final String URL = "badgeUrl";

        ArrayList<Learning> learnings = new ArrayList<>();

        try{
//            JSONObject jsonSkills = new JSONObject(json);
            JSONArray arrayLearning = new JSONArray(json);
            int numberOfSkills = arrayLearning.length();

            for(int i=0; i< numberOfSkills; i++){
                JSONObject LearningJSON = arrayLearning.getJSONObject(i);
                Learning learning = new Learning(LearningJSON.getString(NAME), LearningJSON.getInt(HOURS),
                        LearningJSON.getString(COUNTRY), LearningJSON.getString(URL));

                learnings.add(learning);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
        return learnings;
    }
}
