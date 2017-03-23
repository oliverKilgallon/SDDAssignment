package JUnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import me.oliver.textgame.entities.Level;



public class LevelTest {
	
	Level testLevel = new Level(1);
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetLevelDataSize() {
		assertEquals(14, testLevel.getLevelDataSize(), 1);
	}

}
