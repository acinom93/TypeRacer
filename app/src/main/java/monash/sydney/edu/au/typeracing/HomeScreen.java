package monash.sydney.edu.au.typeracing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawableLayout;
    GoogleSignInAccount signedInAccount = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signedInAccount = getIntent().getParcelableExtra("ACCOUNT");
        if(signedInAccount==null)
        {
            signedInAccount= GoogleSignIn.getLastSignedInAccount(this);
        }

        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.menu);
        drawableLayout = findViewById(R.id.drawer_layout);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView userImg = headerView.findViewById(R.id.userImage);
        TextView userName = (TextView) headerView.findViewById(R.id.userName);
        TextView userAdreess = (TextView) headerView.findViewById(R.id.emailAdress);
        if (signedInAccount != null) {
            userAdreess.setText(signedInAccount.getEmail());
            userName.setText(signedInAccount.getDisplayName());

            if(signedInAccount.getPhotoUrl()!=null) {
                String imgurl = signedInAccount.getPhotoUrl().toString();
                if (imgurl != null) {
                    Glide.with(this).load(imgurl).into(userImg);
                }
            }
        }

        ArrayList<LItem> leaderList = getLeaders();
        LeaderBoardAdapter leaderBoardAdapter = new LeaderBoardAdapter(this, leaderList);
        ListView listView = findViewById(R.id.listLeaderBoard);
        listView.setAdapter(leaderBoardAdapter);
        leaderBoardAdapter.notifyDataSetChanged();
    }

    private ArrayList<LItem> getLeaders() {
        ArrayList<LItem> leaderList = new ArrayList<>();
        leaderList.add(new LItem(-1, (long) 560000, "Saurabh", 30));
        leaderList.add(new LItem(1, (long) 560000, "Saurabh", 30));
        leaderList.add(new LItem(2, (long) 486000, "Amol", 27));
        leaderList.add(new LItem(3, (long) 455000, "Abhilasha", 26));
        leaderList.add(new LItem(4, (long) 435000, "Vinod", 25));
        leaderList.add(new LItem(5, (long) 395000, "Sandhya", 23));
        return leaderList;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        switch (menuItem.getItemId()) {

            case R.id.nav_about:
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_edit:
                intent = new Intent(this, SelectInterestActivity.class);
                startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_process:
                intent = new Intent(this, ShowStat.class);
                startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_help:
                intent = new Intent(this, ContactUs.class);
                startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_credit:
                intent = new Intent(this, CreaditActivity.class);
                startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_invite:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "I Type and Race to win on the TRacer App. Can you defeat me ? Download the TRacer App now !");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_logout:
                signOut();
                drawer.closeDrawer(GravityCompat.START);
                break;
            default:
                Toast.makeText(this, "Menu is clicked : " + menuItem.getTitle(), Toast.LENGTH_LONG).show();

                drawer.closeDrawer(GravityCompat.START);
        }

        return true;
    }

    private void signOut() {
        GoogleSignInClient signInClient = GoogleSignIn.getClient(this,
                GoogleSignInOptions.DEFAULT_SIGN_IN);
        final HomeScreen object = this;
        signInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(object, MainActivity.class);
                        startActivity(intent);
                    }
                });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        }
    }

    public void startNewGame(View view) {
        Intent intent = new Intent(this, PlayGameActivity.class);
        startActivity(intent);
    }



}
