package monash.sydney.edu.au.typeracing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ContactUs extends AppCompatActivity {

    ContactUs context;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        textView = findViewById(R.id.feedbackText);
        Button submitFeedback = findViewById(R.id.submitFeedback);
        submitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView.getText() == null || textView.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Please enter feedback text", Toast.LENGTH_LONG).show();
                } else {
                    textView.setText("");
                    Toast.makeText(context, "Thank you for your feedback", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
