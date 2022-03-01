package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
 
import java.sql.Connection;
import java.sql.SQLException;
 
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
 
import model.BlogList;
import service.SongDao;
 
class SongDaoTest {
     
    private static SongDao dao;
     
    @BeforeAll
    static void init() {
        Connection conn = ConnectionManager.getConnection();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dao = new SongDao(conn);
    }
     
    @AfterAll
    static void teardown() {
        Connection conn = ConnectionManager.getConnection();
        try {
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
    @Test
    void addListTest() {
        Song list = new Song();
        list.setSongName("XYZ");
		list.setSingerName("ARR");
		list.setImageUrl("xyz.jpg");
		list.setSongUrl("www.xyzsong.com");
        dao.addSong(list);
        Song listFromDb = dao.viewSongById(1);
        assertEquals("XYZ", listFromDb.getSongName(), "Song Name must be equal");
    }
    
    @Test
    void deleteListTest() {
        dao.deleteSong(1);
        Song listFromDb = dao.viewSongById(1);
        assertNull(listFromDb.getSongName(), "Song should be null");
    }
    
    @Test
    void updateListTest() {
        Song list = new Song();
        list.setSongName("XYZ");
		list.setSingerName("ARR");
		list.setImageUrl("xyz.jpg");
		list.setSongUrl("www.xyzsong.com");
        dao.addSong(list);
        list.setSongName("ABC");
        dao.updateSong(list);
        Song listFromDb = dao.viewSongById(1);
        assertEquals("ABC", listFromDb.getSongName(), "Song Name must be equal");
    }
 
}