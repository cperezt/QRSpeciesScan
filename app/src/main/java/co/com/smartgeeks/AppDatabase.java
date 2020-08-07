package co.com.smartgeeks;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Especies.class}, version = 1)
public  abstract class AppDatabase extends RoomDatabase {

public abstract EspeciesDao especiesDao();

}
