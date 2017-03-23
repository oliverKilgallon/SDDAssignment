package JUnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import me.oliver.textgame.gui.MainGame;

public class MainGameTest {
	MainGame game = new MainGame(1);
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void checkAnswerTest() {
		game.setAnswer(game.getQuestionText());
		assertEquals(game.checkAnswer(), true);
	}

}
