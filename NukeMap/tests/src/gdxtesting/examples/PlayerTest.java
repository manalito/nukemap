/*******************************************************************************
 * Copyright 2015 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package gdxtesting.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.gen.nukemap.GameObject.Player;
import gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;
import org.junit.runner.notification.RunListener;

@RunWith(GdxTestRunner.class)
public class PlayerTest {

	private World world = new World(new Vector2(0,0), true);
	private Texture texture = new Texture("bombarab.png");
	private Player player = new Player("42",world, new Vector2(3,4), texture, 0, 0, 0, 0, 1, 0);


	@Test
	public void positionYShouldBeCorrect(){

		assertEquals(3, player.getPosition().x, 0.1);
	}
	@Test
	public void positionXShouldBeCorrect(){
		assertEquals(4, player.getPosition().y, 0.1);
	}

	@Test
	public void playerShouldLoseLife(){
		int currentLife = player.getLife();
		player.decreaseLife();
		assertEquals(currentLife-1, player.getLife());
	}

	@Test
	public void playerShouldDieWhenLifeAtZero(){
		player.setLife(1);
		assertFalse(player.decreaseLife());
	}

	@Test
	public void playerShouldDecreaseBomb(){
		player.setBombOnField(3);
		player.decreaseBombOnField();
		assertEquals(2, player.getBombOnField());
	}

	@Test
	public void playerShouldIncreaseBomb(){
		player.setBombOnField(3);
		player.increaseBombOnField();
		assertEquals(4, player.getBombOnField());
	}


}
