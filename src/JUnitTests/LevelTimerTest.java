package JUnitTests;

import static org.junit.Assert.*;

import javax.swing.JLabel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import me.oliver.textgame.controllers.LevelTimer;

public class LevelTimerTest {

	LevelTimer timer = new LevelTimer(new JLabel(), new JLabel());
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetElapsedSeconds() {
		if( !(timer.getElapsedSeconds() >= 0) )
		{
			fail("Elapsed seconds is not greater than or equal to 0");
		}
	}
	
	@Test
	public void testGetElapsedMinutes() {
		assertEquals(0, timer.getElapsedMinutes(), 0);
	}

}
