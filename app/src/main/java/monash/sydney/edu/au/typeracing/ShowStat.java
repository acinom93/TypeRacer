package monash.sydney.edu.au.typeracing;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ShowStat extends AppCompatActivity {
    TextItem textItem;
    StataticDao stataticDao;
    GameStats dbgameStats;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stat);
        GameStats game_stats = (GameStats) getIntent().getParcelableExtra("GAME_STATS");

        String stats;
        StatisicDB database = StatisicDB.getDatabase(this.getApplication().getApplicationContext());
        stataticDao = database.toDoItemDao();

       if(game_stats==null)
       {
           readFromDatabase();
           stats = "\n" + "Your Statistics" + "\n" ;
           ((TextView)findViewById(R.id.status)).setText("Level "+ this.dbgameStats.getLevel());
           ((TextView)findViewById(R.id.bookname)).setVisibility(View.GONE);
           ((Button)findViewById(R.id.amazonb)).setVisibility(View.GONE);

       }
       else{
           this.dbgameStats =game_stats;
           insertToDB();

           textItem= (TextItem) getIntent().getParcelableExtra("TEXT_ITEM");
           ((TextView)findViewById(R.id.bookname)).setText("Race Text was from \""+textItem.getBookname()+"\". Buy Now !");

           stats = "\n" + "Race Statistics" + "\n" + "Rank : " + 1 ;

           String flag = getIntent().getStringExtra("FLAG");

           if("FAIL".equals(flag)){
               ((TextView)findViewById(R.id.status)).setText("Sorry, Time is Up !");
           }
       }

        stats +=  "     Points : " + dbgameStats.getPoints() + "\n";
        stats += "Time : " + getStringValue(dbgameStats.getDurationInMilis()) + "     Words : " + dbgameStats.getWordsCount() + "\n";
        stats += "Words Per Minute : " + Math.abs(dbgameStats.getWordsPerMinute()) + "\n";
        stats += "Accuracy : " + dbgameStats.getAccuracy() + "%\n";
        ((TextView) findViewById(R.id.stats)).setText(stats);

        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        ArrayList<Integer> pieColors = new ArrayList<>();
        Random rnd = new Random();
        for(int i=0; i<dbgameStats.getErrorInWords().size();i++)
        {
            pieEntries.add(new PieEntry(dbgameStats.getValues()[i], dbgameStats.getErrorInWords().get(i)));
            pieColors.add(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));

        }

        PieChart pieChart = (PieChart) findViewById(R.id.accurecy_graph);
        Description d = new Description();
        d.setText("");
        pieChart.setDescription(d);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(20f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Error Count");
        pieChart.setCenterTextSize(10);

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        pieDataSet.setColors(pieColors);

        //add legend to chart
        /*Legend  legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);*/
        pieChart.getLegend().setEnabled(false);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }



    private String getStringValue(long currentTime) {
        Date date = new Date(currentTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        return simpleDateFormat.format(date);
    }

    public void onAmazonClick(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(textItem.getAmazonlink()));
        startActivity(browserIntent);
    }

    public void onRaceClick(View view) {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

    private void readFromDatabase() {
        try {
            //Use asynchronous task run in backgroud
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    //read toDoListItems from database using room
                    List<GameStats> itemsFromDB = stataticDao.listAll();
                    if (itemsFromDB != null) {
                        generateOneGameStatus(itemsFromDB);
                    }
                    return null;
                }
            }.execute().get();
        } catch (Exception ex) {
            Log.e("readFromDB", ex.getStackTrace().toString());
        }
    }

    private void generateOneGameStatus(List<GameStats> itemsFromDB) {
        List<Integer> values = new ArrayList<>();
        long durationInMilis = 0;
        Integer totalWords = 0;
        List<String> errorInWords = new ArrayList<>();
        Integer charCount = 0;

        for(GameStats game : itemsFromDB) {
           addAllToList(errorInWords,values,game.getValues(),game.getErrorInWords());
           durationInMilis+=game.getDurationInMilis();
            totalWords+=game.getWordsCount();

            charCount+=game.getCharCount();
        }

        dbgameStats = new GameStats(charCount, durationInMilis, totalWords,  errorInWords, values);
    }

    private void addAllToList(List<String> errorInWords, List<Integer> values, int[] values1, List<String> inWords) {
        for(int i=0;i<inWords.size();i++)
        {
            if(errorInWords.contains(inWords.get(i)))
            {
                int index= errorInWords.indexOf(inWords.get(i));
                int value=values.get(index)+values1[i];
                values.add(index,value);
            }
            else
            {
                errorInWords.add(inWords.get(i));
                values.add(values1[i]);
            }
        }

    }

    /***
     * Add data to database
     */
    private void insertToDB() {
        //Use asynchronous task run in backgroud
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                stataticDao.insert(dbgameStats);
                    Log.i("SaveToDb", dbgameStats.toString());

                return null;
            }
        }.execute();
    }
    @Override
    public void onBackPressed()
    {
        Intent intent= new Intent(this,HomeScreen.class);
        startActivity(intent);
    }

}
