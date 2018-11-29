package monash.sydney.edu.au.typeracing;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class PlayGameActivity extends AppCompatActivity {

    public long duration;
    IconRoundCornerProgressBar progress2;
    Integer wordCount;
    Integer charCount = 0;
    String[] wordArray;
    String[] wordAndImage;
    Integer currentWord;
    Map<String, Integer> errorInWords;
    long startTime = 0;
    boolean timerOn = true;
    TextView userInput;
    boolean onAllCaps = false;
    TextView raceText;
    GameStats gameStats;
    TextItem textItem;
    Map<String, Integer> imageMap = new HashMap<>();
    String flag;

    public void createImageMap() {
        imageMap.put("cat", R.drawable.cat);
        imageMap.put("dog", R.drawable.dog);
        imageMap.put("fox", R.drawable.fox);
        imageMap.put("hen", R.drawable.hen);
        imageMap.put("fire", R.drawable.fire);
        imageMap.put("dragon", R.drawable.dragon);
        imageMap.put("hippopotamus", R.drawable.hippopotamus);
        imageMap.put("tune", R.drawable.tune);
        imageMap.put("giraffe", R.drawable.giraffe);
        imageMap.put("fish", R.drawable.fish);
        imageMap.put("donkey", R.drawable.donkey);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        errorInWords = new HashMap<>();
        textItem= ParagraphFactory.getInstance().getRandomTextItem();
        String strRaceText = textItem.getText();
        charCount = 0;
        wordArray = strRaceText.split("\\s+");
        wordAndImage = textItem.getTextWithImage().split("\\s+");
        wordCount = wordArray.length;
        currentWord = 0;
       userInput = (EditText) findViewById(R.id.userInput);

        //set race text view
        raceText = (TextView) findViewById(R.id.racetext);
        setRaceTextView(strRaceText);

        //set progress bar with image from user
        setProgressBar(R.drawable.mblue);
        findViewById(R.id.back).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                userInput.setText("");
                return true;
            }
        });

        //set progress by total view
        setProgressByTotalView(0);

        //fill the map
        createImageMap();

        //update display
        updateTextView(true);
        //start Timer
        startTimer();

    }




    public void addInErrorMap(String word) {
        Integer errorCount = 0;
        if (errorInWords.containsKey(word)) {
            errorCount = errorInWords.get(word) + 1;
        } else {
            errorCount = 1;
        }
        errorInWords.put(word, errorCount);
    }

    public void onSpaceClick(View v) {

        if (userInput.getText().toString().equals(wordArray[currentWord])) {
            charCount+=wordArray[currentWord].length();
            currentWord = currentWord + 1;
            setProgressByTotalView(currentWord);
            userInput.setText("");
            updateTextView(true);
        } else {
            Toast toast = Toast.makeText(this, "Try Again : It's \"" + wordArray[currentWord] + "\"", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            addInErrorMap(wordArray[currentWord]);
            updateTextView(false);
        }

        if (currentWord == wordCount) {
            timerOn = false;
            flag = "SUCCESS";
            findViewById(R.id.next).setVisibility(View.VISIBLE);
            findViewById(R.id.space).setEnabled(false);
            ((TextView) findViewById(R.id.raceStatus)).setText("Race Finished");
            ((TextView) findViewById(R.id.raceStatus)).setTextColor(Color.parseColor("#247d23"));
            gameStats = new GameStats(charCount, duration, currentWord, new ArrayList<String>(errorInWords.keySet()),new ArrayList<Integer>(errorInWords.values()));
        }

    }

    public void updateTextView(boolean flag) {

        String[] textArray = textItem.getTextWithImage().split(" ");
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        int count = 0;
        for (String text : textArray) {
            if (isImage(text)) {
                String imageName = text.replace("IMG[", "").replace("]", "");
                int drawableID = imageMap.get(imageName);
                Drawable drawable = getResources().getDrawable(drawableID);
                if (count == currentWord) {
                    drawable.setBounds(0, 0, 100, 100);
                } else {
                    drawable.setBounds(0, 0, 80, 80);
                }

                String newStr = drawable.toString();
                ssb.append("  " + newStr + " ");
                ssb.setSpan(
                        new ImageSpan(drawable),
                        ssb.length() - newStr.length() - 1,
                        ssb.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                if (count < currentWord) {
                    ssb.append(" " + text);
                    ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#247d23")), ssb.length() - text.length(), ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else if (count > currentWord) {
                    ssb.append(" " + text);
                    ssb.setSpan(new ForegroundColorSpan(Color.BLACK), ssb.length() - text.length(), ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else {

                    if (flag == true) {
                        //blue
                        ssb.append(" " + text);
                        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#015b97")), ssb.length() - text.length(), ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ssb.setSpan(new StyleSpan(Typeface.BOLD), ssb.length() - text.length(), ssb.length(), 0);
                    } else {
                        //red
                        ssb.append(" " + text);
                        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#bf0000")), ssb.length() - text.length(), ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ssb.setSpan(new StyleSpan(Typeface.BOLD), ssb.length() - text.length(), ssb.length(), 0);
                    }
                }

            }
            count++;
        }
        raceText.setText(ssb);
    }

    private boolean isImage(String text) {
        return text.startsWith("IMG[") && text.endsWith("]");
    }

   /* private void updateTextView(boolean flag) {

        SpannableStringBuilder builder = new SpannableStringBuilder();
        if (wordCount == currentWord) {
            SpannableString str1 = new SpannableString(getText());
            str1.setSpan(new ForegroundColorSpan(Color.parseColor("#247d23")), 0, str1.length(), 0);
            builder.append(str1);
            raceText.setText(builder, TextView.BufferType.SPANNABLE);
            return;
        }


        SpannableString str1 = new SpannableString(getSucessText());
        SpannableString str2 = new SpannableString(wordArray[currentWord] + " ");

        //green
        str1.setSpan(new ForegroundColorSpan(Color.parseColor("#247d23")), 0, str1.length(), 0);

        if(flag==true){
            //blue
            str2.setSpan(new ForegroundColorSpan(Color.parseColor("#015b97")), 0, str2.length(), 0);
        }
        else {
            //red
            str2.setSpan(new ForegroundColorSpan(Color.parseColor("#bf0000")), 0, str2.length(), 0);
        }
        str2.setSpan(new StyleSpan(Typeface.BOLD), 0, str2.length(), 0);

        builder.append(str1);
        builder.append(str2);
        SpannableString str3 = new SpannableString(getYetToCompleText());
        str3.setSpan(new ForegroundColorSpan(Color.BLACK), 0, str3.length(), 0);
        builder.append(str3);

        raceText.setText(builder, TextView.BufferType.SPANNABLE);
    }
    */

   /*private CharSequence getYetToCompleText() {
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = currentWord + 1; i < wordCount; i++) {
            stringBuilder.append(wordArray[i] + " ");
        }
        return stringBuilder.toString();
    }

    private String getSucessText() {
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < currentWord; i++) {
            stringBuilder.append(wordArray[i] + " ");
        }
        return stringBuilder.toString();
    }*/

    private void setRaceTextView(String strRaceText) {
        ((TextView) findViewById(R.id.racetext)).setText(strRaceText);
    }

    private void setProgressBar(int resID) {

        progress2 = (IconRoundCornerProgressBar) findViewById(R.id.progress_2);
        progress2.setProgressColor(Color.parseColor("#56d2c2"));
        progress2.setProgressBackgroundColor(Color.parseColor("#E7E7E9"));
        progress2.setIconBackgroundColor(Color.parseColor("#0D7075"));
        progress2.setIconImageResource(resID);
        progress2.setMax(wordCount);
    }

    private void setProgressByTotalView(int progressValue) {
        progress2.setProgress(progressValue);
        ((TextView) findViewById(R.id.progressbytotal)).setText(progressValue + "/" + wordCount);
    }


    public void startTimer() {
        Timer stopwatchTimer = new Timer();
        startTime = System.currentTimeMillis();
        stopwatchTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (timerOn == true) {
                            TextView timerTextView = (TextView) findViewById(R.id.timer);
                            timerTextView.setText(stopwatch()+"/05:00");

                            if (duration > 300000) {
                                timerOn = false;
                                flag = "FAIL";
                                findViewById(R.id.next).setVisibility(View.VISIBLE);
                                findViewById(R.id.next).setBackgroundColor(Color.parseColor("#bf0000"));
                                findViewById(R.id.space).setEnabled(false);
                                ((TextView) findViewById(R.id.raceStatus)).setText("Time Up");
                                ((TextView) findViewById(R.id.raceStatus)).setTextColor(Color.parseColor("#bf0000"));
                                gameStats = new GameStats(charCount, duration, currentWord, new ArrayList<String>(errorInWords.keySet()),new ArrayList<Integer>(errorInWords.values()));
                            }
                        }
                    }
                });

            }
        }, 0, 10);


    }

    // Returns the combined string for the stopwatch, counting in tenths of seconds.
    public String stopwatch() {
        long nowTime = System.currentTimeMillis();
        duration = nowTime - startTime;
        Date date = new Date(duration);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        return simpleDateFormat.format(date);
    }

    public void backSpace(View view) {
        Button b = (Button) view;
        String text = b.getText().toString();
        if (b.getId() != R.id.back) {
            userInput.append(text);
            undoCaps();
        } else {
            CharSequence original = userInput.getText();
            if (original == null || original.toString().isEmpty()) {
                userInput.setText(original);
            } else {
                userInput.setText(original.subSequence(0, original.length() - 1));
            }
        }
    }

    public void changeCaps(View view) {
        makeCaps();
    }

    public void undoCaps() {

        ((Button) findViewById(R.id.a)).setText("a");
        ((Button) findViewById(R.id.b)).setText("b");
        ((Button) findViewById(R.id.c)).setText("c");
        ((Button) findViewById(R.id.d)).setText("d");
        ((Button) findViewById(R.id.e)).setText("e");
        ((Button) findViewById(R.id.f)).setText("f");
        ((Button) findViewById(R.id.g)).setText("g");
        ((Button) findViewById(R.id.h)).setText("h");
        ((Button) findViewById(R.id.i)).setText("i");
        ((Button) findViewById(R.id.j)).setText("j");
        ((Button) findViewById(R.id.k)).setText("k");
        ((Button) findViewById(R.id.l)).setText("l");
        ((Button) findViewById(R.id.m)).setText("m");
        ((Button) findViewById(R.id.n)).setText("n");
        ((Button) findViewById(R.id.o)).setText("o");
        ((Button) findViewById(R.id.p)).setText("p");
        ((Button) findViewById(R.id.q)).setText("q");
        ((Button) findViewById(R.id.r)).setText("r");
        ((Button) findViewById(R.id.s)).setText("s");
        ((Button) findViewById(R.id.t)).setText("t");
        ((Button) findViewById(R.id.u)).setText("u");
        ((Button) findViewById(R.id.v)).setText("v");
        ((Button) findViewById(R.id.w)).setText("w");
        ((Button) findViewById(R.id.x)).setText("x");
        ((Button) findViewById(R.id.y)).setText("y");
        ((Button) findViewById(R.id.z)).setText("z");
    }

    public void makeCaps() {

        ((Button) findViewById(R.id.a)).setText("A");
        ((Button) findViewById(R.id.b)).setText("B");
        ((Button) findViewById(R.id.c)).setText("C");
        ((Button) findViewById(R.id.d)).setText("D");
        ((Button) findViewById(R.id.e)).setText("E");
        ((Button) findViewById(R.id.f)).setText("F");
        ((Button) findViewById(R.id.g)).setText("G");
        ((Button) findViewById(R.id.h)).setText("H");
        ((Button) findViewById(R.id.i)).setText("I");
        ((Button) findViewById(R.id.j)).setText("J");
        ((Button) findViewById(R.id.k)).setText("K");
        ((Button) findViewById(R.id.l)).setText("L");
        ((Button) findViewById(R.id.m)).setText("M");
        ((Button) findViewById(R.id.n)).setText("N");
        ((Button) findViewById(R.id.o)).setText("O");
        ((Button) findViewById(R.id.p)).setText("P");
        ((Button) findViewById(R.id.q)).setText("Q");
        ((Button) findViewById(R.id.r)).setText("R");
        ((Button) findViewById(R.id.s)).setText("S");
        ((Button) findViewById(R.id.t)).setText("T");
        ((Button) findViewById(R.id.u)).setText("U");
        ((Button) findViewById(R.id.v)).setText("V");
        ((Button) findViewById(R.id.w)).setText("W");
        ((Button) findViewById(R.id.x)).setText("X");
        ((Button) findViewById(R.id.y)).setText("Y");
        ((Button) findViewById(R.id.z)).setText("Z");

    }

    public void showStat(View view) {
        Intent intent = new Intent(this, ShowStat.class);
        intent.putExtra("GAME_STATS", gameStats);
        intent.putExtra("TEXT_ITEM",textItem);
        intent.putExtra("FLAG",flag);
        startActivity(intent);
    }
}
