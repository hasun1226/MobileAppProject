package team19.notes4u;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SlackerMenuActivity extends AppCompatActivity {
    private TextView userName;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_slacker);
        userName = (TextView) findViewById(R.id.userName);
        Button makeButton = (Button) findViewById(R.id.makeRequest);
        Button viewButton = (Button) findViewById(R.id.viewRequest);
        setTitle("NoteTaker Profile");
        userName.setText(LoginActivity.user);

        makeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SlackerMenuActivity.this, PostActivity.class);
                intent.putExtra("user", LoginActivity.user);
                startActivity(intent);
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SlackerMenuActivity.this, ViewSlackRequestsActivity.class);
                intent.putExtra("user", LoginActivity.user);
                startActivity(intent);
            }
        });
    }
}
