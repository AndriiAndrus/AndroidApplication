package com.subliminals_central.subliminalscentral.subliminalscentral.UserActions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.subliminals_central.subliminalscentral.subliminalscentral.MainActivity;
import com.subliminals_central.subliminalscentral.subliminalscentral.R;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

public class UserAuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_auth);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // EditText email = (EditText) findViewById(R.id.editText2);
       // EditText passwd = (EditText) findViewById(R.id.editText3);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// Sign In (login)
                EditText email = (EditText) findViewById(R.id.editText2);
                EditText passwd = (EditText) findViewById(R.id.editText3);
                String usrEmail = email.getText().toString();
                String usrPass = passwd.getText().toString();
                try {
                    String[] isOk = UserAuth.userAuth(usrEmail, usrPass);
                    if(isOk[0].equalsIgnoreCase("100")){
                        SharedPreferences prefs = getApplicationContext().getSharedPreferences(
                                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

                        SharedPreferences.Editor editor = prefs.edit();

                        Snackbar.make(v, "You've Successfully Signed In", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();

                        int scores = Integer.parseInt(isOk[1]);

                        editor.putString("email", usrEmail);
                        editor.putString("pass", usrPass);
                        editor.putInt("points", scores);
                        editor.commit();

                        UserData.setUserEmail(usrEmail);
                        UserData.setUserPasswd(usrPass);
                        UserData.setUserPoints(scores);

                        UserData.isLogin = true;

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                    } else if(isOk[0].equalsIgnoreCase("500")){
                        Snackbar.make(v, "The e-mail address or password you entered was incorrect", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    } else {
                        Snackbar.make(v, isOk+"An error occurred, please try again later", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Snackbar.make(v, "Unknown error"+e.toString(), Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
            }
        });

        Button butto = (Button) findViewById(R.id.button2);
        butto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// Sign Up (register)
                EditText email = (EditText) findViewById(R.id.editText2);
                EditText passwd = (EditText) findViewById(R.id.editText3);
                String usrEmail = email.getText().toString();
                String usrPass = passwd.getText().toString();
                try {
                    String isOk = UserAuth.userRegistration(usrEmail, usrPass);
                    if(isOk.equalsIgnoreCase("200")){
                        Snackbar.make(v, "You've Successfully Signed up, Sign In now!", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    } else if(isOk.equalsIgnoreCase("300")){
                        Snackbar.make(v, "Email Address Already in Use", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    } else {
                        Snackbar.make(v, "An error occurred, please try again later", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Snackbar.make(v, "Error :"+e.toString(), Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
            }
        });
    }

}
