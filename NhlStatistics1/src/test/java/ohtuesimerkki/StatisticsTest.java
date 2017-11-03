/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author pelsaara
 */
public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  



    @Test
    public void testSearch() {
        assertNull(stats.search("Matti"));
        assertNotNull(stats.search("Semenko"));
        Player i = new Player("Semenko", "EDM", 4, 12);
        assertEquals(i.getName(), stats.search("Semenko").getName());
        assertEquals(i.getAssists(), stats.search("Semenko").getAssists());
        assertEquals(i.getGoals(), stats.search("Semenko").getGoals());
        assertEquals(i.getPoints(), stats.search("Semenko").getPoints());
        assertEquals(i.getTeam(), stats.search("Semenko").getTeam());
    }


    @Test
    public void testTeam() {
        List<Player> i = stats.team("HJK");
        assertTrue(i.isEmpty());
        List<Player> j = stats.team("EDM");
        assertTrue(j.size()==3);
    }

    @Test
    public void testTopScorers() {
        assertEquals(stats.topScorers(0).get(0).getName(), "Gretzky");
        assertEquals(stats.topScorers(3).get(3).getName(), "Kurri");
    }
    
}
