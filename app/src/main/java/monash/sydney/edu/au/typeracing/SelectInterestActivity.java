package monash.sydney.edu.au.typeracing;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;
import java.util.List;

public class SelectInterestActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    GoogleSignInAccount signedInAccount = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_interest);

        signedInAccount = getIntent().getParcelableExtra("ACCOUNT");
        TableLayout my_layout = (TableLayout) findViewById(R.id.linearLayout);
        List<CheckBoxItem> checkBoxItems = getAllInterestTopics();

        int index = 0;
        int startIndex = 100;
        int size = 3;
        for (index = 0; index < checkBoxItems.size(); index = index + size) {

            TableRow tableRow = new TableRow(this);

            for (int i = 0; i < size; i++) {
                if (i + index < checkBoxItems.size()) {
                    CheckBox checkBox = new CheckBox(this);
                    checkBox.setOnCheckedChangeListener(this);
                    checkBox.setId(startIndex++);

                    CheckBoxItem checkBoxItem = checkBoxItems.get(i + index);
                    checkBox.setText(checkBoxItem.getItemName());
                    checkBox.setChecked(checkBoxItem.isChecked());
                    checkBox.setTextSize(16);
                    checkBox.setTextColor(Color.WHITE);
                    checkBox.setButtonTintList(new ColorStateList(new int[][]{{Color.WHITE}}, new int[]{Color.WHITE}));
                    tableRow.addView(checkBox);
                }
            }
            my_layout.addView(tableRow);

        }
    }


    private List<CheckBoxItem> getAllInterestTopics() {
        List<CheckBoxItem> checkBoxItems = new ArrayList<>();
        checkBoxItems.add(new CheckBoxItem("Sports", true));
        checkBoxItems.add(new CheckBoxItem("Art", false));
        checkBoxItems.add(new CheckBoxItem("Physics", false));

        checkBoxItems.add(new CheckBoxItem("Business", false));
        checkBoxItems.add(new CheckBoxItem("Chemistry", false));
        checkBoxItems.add(new CheckBoxItem("Britain", false));

        checkBoxItems.add(new CheckBoxItem("Food", false));
        checkBoxItems.add(new CheckBoxItem("Law", false));
        checkBoxItems.add(new CheckBoxItem("Travel", true));

        checkBoxItems.add(new CheckBoxItem("Big Data", false));
        checkBoxItems.add(new CheckBoxItem("Technology", false));
        checkBoxItems.add(new CheckBoxItem("Machine Learning", false));

        checkBoxItems.add(new CheckBoxItem("Medieval", false));
        checkBoxItems.add(new CheckBoxItem("Geology", false));
        checkBoxItems.add(new CheckBoxItem("Psychology", false));

        checkBoxItems.add(new CheckBoxItem("Fiction", false));
        checkBoxItems.add(new CheckBoxItem("SciFi", false));
        checkBoxItems.add(new CheckBoxItem("India", false));

        checkBoxItems.add(new CheckBoxItem("Love Story", false));
        checkBoxItems.add(new CheckBoxItem("Friendship", false));
        checkBoxItems.add(new CheckBoxItem("Physiology", false));

        checkBoxItems.add(new CheckBoxItem("Magical", false));
        checkBoxItems.add(new CheckBoxItem("Biology", false));
        checkBoxItems.add(new CheckBoxItem("Australia", true));

        checkBoxItems.add(new CheckBoxItem("Baking", false));
        checkBoxItems.add(new CheckBoxItem("Geology", false));
        checkBoxItems.add(new CheckBoxItem("Paleontology", false));

        checkBoxItems.add(new CheckBoxItem("Java", false));
        checkBoxItems.add(new CheckBoxItem("Trekking", false));
        checkBoxItems.add(new CheckBoxItem("Europe", false));

        checkBoxItems.add(new CheckBoxItem("Carbon Fibres", false));
        checkBoxItems.add(new CheckBoxItem("Politics", false));
        checkBoxItems.add(new CheckBoxItem("Environment", false));

        checkBoxItems.add(new CheckBoxItem("History", false));
        checkBoxItems.add(new CheckBoxItem("Economics", false));
        checkBoxItems.add(new CheckBoxItem("Web Design", false));

        checkBoxItems.add(new CheckBoxItem("Culture", false));
        checkBoxItems.add(new CheckBoxItem("Americas", false));
        checkBoxItems.add(new CheckBoxItem("Aboriginal", false));
        return checkBoxItems;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    public void openHomeage(View view) {
        Intent intent = new Intent(this, HomeScreen.class);
        intent.putExtra("ACCOUNT", signedInAccount);
        startActivity(intent);
    }
}
