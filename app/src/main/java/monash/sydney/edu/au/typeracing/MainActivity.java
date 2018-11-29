package monash.sydney.edu.au.typeracing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int RC_SIGN_IN = 9001;
    GoogleSignInAccount signedInAccount = null;
    boolean firstlogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.btnLogin).setEnabled(false);
    }

    public void loginSuccess(View view) {
        Intent intent = new Intent(this, SelectInterestActivity.class);
        startActivity(intent);
    }

    private void signInSilently() {
        GoogleSignInClient signInClient = GoogleSignIn.getClient(this,
                new GoogleSignInOptions
                        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        // .requestScopes(Games.SCOPE_GAMES_LITE)
                        .requestEmail()
                        .build());
        final MainActivity object = this;
        signInClient.silentSignIn().addOnCompleteListener(this,
                new OnCompleteListener<GoogleSignInAccount>() {
                    @Override
                    public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                        if (task.isSuccessful()) {
                            // The signed in account is stored in the task's result.
                            if (!firstlogin) {
                                signedInAccount = task.getResult();
                                Intent intent = new Intent(object, HomeScreen.class);
                                intent.putExtra("ACCOUNT", signedInAccount);
                                startActivity(intent);
                            }

                        } else {
                            // Player will need to sign-in explicitly using via UI
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();


        signInSilently();
    }

    private void startSignInIntent() {
        GoogleSignInClient signInClient = GoogleSignIn.getClient(this,
                GoogleSignInOptions.DEFAULT_SIGN_IN);
        Intent intent = signInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // The signed in account is stored in the result.
                signedInAccount = result.getSignInAccount();
                Intent intent = new Intent(this, SelectInterestActivity.class);
                intent.putExtra("ACCOUNT", signedInAccount);
                startActivity(intent);
                firstlogin = true;

            } else {
                String message = result.getStatus().getStatusMessage();
                if (message == null || message.isEmpty()) {
                    message = "Error while sign in";
                }
                new AlertDialog.Builder(this).setMessage(message)
                        .setNeutralButton(android.R.string.ok, null).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sign_in_button) {
            // start the asynchronous sign in flow
            startSignInIntent();
        }
    }
}
