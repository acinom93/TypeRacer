package monash.sydney.edu.au.typeracing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void openFeedbackPage(View view) {
        Intent intent = new Intent(this, ContactUs.class);
        startActivity(intent);
    }
}