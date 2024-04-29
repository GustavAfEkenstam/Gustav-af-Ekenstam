package com.example.labb_4_g;

import org.json.JSONException;
import org.json.JSONObject;
    public interface VolleyCallback <T>{
        public void onSuccess(JSONObject object);
        public void onFailure(Exception e);
    }

