package team19.notes4u;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by tyler on 09/11/16.
 */

public class ViewSlackRequestsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewslackrequests);

        populateSlackRequests();

    }

    //        TODO: Find out how to get user id
    private void populateSlackRequests(int user_id){

//        http://notes4u.herokuapp.com/users/1/requests
//        format: 'users' + userid + '/requests'

        String connectionString("users/" + user_id + "/requests");
        Wrapper wrapper = new Wrapper(connectionString);

//        Object[] request_array = wrapper.getJsonObjects();

        String[] request_array = {"request1", "request2", "request3"};

        ListView listView =
                (ListView) this.findViewById(R.id.listviewslackrequests);

        //build adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.activity_viewslackrequests,
                android.R.id.text1,
                request_array
        );

        listView.setAdapter(adapter);
    }



}
