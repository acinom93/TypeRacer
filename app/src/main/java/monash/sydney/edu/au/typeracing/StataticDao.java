package monash.sydney.edu.au.typeracing;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
/***
 * @author Akash
 *
 * StataticDao
 */
@Dao
public interface StataticDao {
    // sorted by last updated Time
    @Query("SELECT * FROM Statistics")
    List<GameStats> listAll();

    @Insert
    void insert(GameStats toDoItem);


    @Query("DELETE FROM Statistics")
    void deleteAll();

}
