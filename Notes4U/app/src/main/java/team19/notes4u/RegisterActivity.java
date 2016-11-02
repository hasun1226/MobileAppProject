package team19.notes4u;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button register_btn = (Button) findViewById((R.id.signup_button));
        register_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                EditText email = (EditText) findViewById(R.id.email);
                EditText password = (EditText) findViewById(R.id.password);
                EditText confirmation = (EditText) findViewById(R.id.confirmation);

                if (email.getText().toString().equals("")){
                    Toast pieceToast= Toast.makeText(getApplicationContext(), "Please Enter Email.", Toast.LENGTH_SHORT);
                    pieceToast.show();
                }

                else if (password.getText().toString().equals("")){
                    Toast pieceToast= Toast.makeText(getApplicationContext(), "Please Enter password.", Toast.LENGTH_SHORT);
                    pieceToast.show();
                }

                else if (confirmation.getText().toString().equals("")){
                    Toast pieceToast= Toast.makeText(getApplicationContext(), "Please Enter Confirmation.", Toast.LENGTH_SHORT);
                    pieceToast.show();
                }

                else if (!confirmation.getText().toString().equals(password)){
                    Toast pieceToast= Toast.makeText(getApplicationContext(), "Password does not match.", Toast.LENGTH_SHORT);
                    pieceToast.show();
                }

                else{
                    register();
                }

            }
        });
    }

    private void register(){
        // TODO: register the user.

        // Go to Login page
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
