package team19.notes4u.model;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Body;
import retrofit2.http.Path;
import team19.notes4u.DB.Rating;

/**
 * Created by SUNNY on 2016-11-14.
 */

public interface ApiService {
    @POST("{TableName}/create")
    Call<String> create(@Path("TableName") String TableName, @Body Object input);
}
