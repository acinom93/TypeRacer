package monash.sydney.edu.au.typeracing;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {GameStats.class}, version = 3, exportSchema = false)
@TypeConverters({StatusConverter.class})
/***
 * @author Akash
 *
 * get database instance
 */
public abstract class StatisicDB extends RoomDatabase {
    private static final String DATABASE_NAME = "statistic_db";
    private static StatisicDB dbinstance;

    public static StatisicDB getDatabase(Context context) {
        if (dbinstance == null) {
            synchronized (ShowStat.class) {
                //fallbackToDestructiveMigration to delete old database when version of DB changes.
                dbinstance = Room.databaseBuilder(context.getApplicationContext(),
                        StatisicDB.class, DATABASE_NAME).fallbackToDestructiveMigration()
                        .build();

            }
        }
        return dbinstance;
    }

    public static void destroyInstance() {
        dbinstance = null;
    }

    public abstract StataticDao toDoItemDao();
}
