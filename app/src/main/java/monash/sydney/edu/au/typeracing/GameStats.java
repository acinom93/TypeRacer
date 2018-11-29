package monash.sydney.edu.au.typeracing;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;


import java.util.List;

@Entity(tableName = "Statistics")
public class GameStats  implements Parcelable{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ID")
    private int ID;
    @ColumnInfo(name = "values")

    int[] values;

public GameStats()
{

}

    @NonNull
    public int getID() {
        return ID;
    }

    public void setID(@NonNull int ID) {
        this.ID = ID;
    }

    public void setValues(int[] values) {
        this.values = values;
    }

    public Integer getTotalWords() {
        return totalWords;
    }

    public void setTotalWords(Integer totalWords) {
        this.totalWords = totalWords;
    }

    public void setErrorInWords(List<String> errorInWords) {
        this.errorInWords = errorInWords;
    }

    public void setCharCount(Integer charCount) {
        this.charCount = charCount;
    }

    @ColumnInfo(name = "durationInMill")
    long durationInMilis;

    @ColumnInfo(name = "totalWord")
    Integer totalWords;
    @ColumnInfo(name = "errorWords")
    List<String> errorInWords;

    @ColumnInfo(name = "charCount")
    Integer charCount;

    public GameStats(Integer charCount, long durationInMilis, Integer totalWords, List<String> errorInWords, List<Integer> values) {
        this.durationInMilis = durationInMilis;
        this.totalWords = totalWords;
        this.errorInWords = errorInWords;
        this.values = convertIntegers(values);
        this.charCount = charCount;
    }

    protected GameStats(Parcel in) {
        ID = in.readInt();
        values = in.createIntArray();
        durationInMilis = in.readLong();
        if (in.readByte() == 0) {
            totalWords = null;
        } else {
            totalWords = in.readInt();
        }
        errorInWords = in.createStringArrayList();
        if (in.readByte() == 0) {
            charCount = null;
        } else {
            charCount = in.readInt();
        }
    }
@Ignore
    public static final Creator<GameStats> CREATOR = new Creator<GameStats>() {
        @Override
        public GameStats createFromParcel(Parcel in) {
            return new GameStats(in);
        }

        @Override
        public GameStats[] newArray(int size) {
            return new GameStats[size];
        }
    };

    public static int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }


    public long getDurationInMilis() {
        return durationInMilis;
    }

    public void setDurationInMilis(long durationInMilis) {
        this.durationInMilis = durationInMilis;
    }



    public float getWordsPerMinute() {
        float charpersec = (float) charCount / ((float) durationInMilis / 1000);
        return (charpersec * 60) / 5;
    }

    public long getAccuracy() {
        return (long) (100 - (((float) errorInWords.size() / (float) totalWords) * 100));
    }

    public int pointsEarned() {
        return (int) (100 - (errorInWords.size() / (float) totalWords * 100)) * 100;
    }

    public long getLevel(){
        return (getPoints()/10000)+1;
    }

    @Override
    public String toString() {
        return "GameStats{" +
                "durationInMilis=" + durationInMilis +
                ", Tota l word count=" + totalWords +
                ", errorInWords=" + errorInWords +
                '}';
    }

    public int getWordsCount() {
        return totalWords;
    }

    public long getPoints() {
        return getAccuracy() * 123;
    }

    public List<String> getErrorInWords() {
        return errorInWords;
    }

    public int[] getValues() {
        return values;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeIntArray(values);
        dest.writeLong(durationInMilis);
        if (totalWords == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalWords);
        }
        dest.writeStringList(errorInWords);
        if (charCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(charCount);
        }
    }

    public Integer getCharCount() {
        return charCount;
    }
}

