package co.com.smartgeeks;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EspeciesDao {

    @Query("SELECT * FROM especies")
    List<Especies> getAll();

    @Query("SELECT * FROM especies WHERE idEspecie LIKE :first LIMIT 1")
    Especies findByName(String first);

    @Query("DELETE FROM especies")
    public void nukeTable();

    @Insert
    Long insert(Especies especies);


}
