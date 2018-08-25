package com.teo.behaviourlogger.service;

/**
 * Created by teo on 19.04.2018.
 */

import com.teo.behaviourlogger.domain.BehaviourInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @POST("/logNewBehaviour")
    Call<BehaviourInstance> logNewBehaviour(@Body BehaviourInstance behaviourInstance);
}
