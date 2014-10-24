package com.onequest.dungeon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Main class in LibGdx library. Controls rendering.
 * 
 * @author Rob Vogel
 * 
 */
public class MainClass extends ApplicationAdapter {
	SpriteBatch batch;
	public static World world;
	ShapeRenderer shapes;

	@Override
	/**
	 * Initial method to be run at application start
	 */
	public void create() {
		batch = new SpriteBatch();
		world = new WorldDesktop(batch);

		switch (Gdx.app.getType()) {
		case Android:
			world = new WorldAndroid(batch);
			break;
		case Desktop:
			world = new WorldDesktop(batch);
			break;
		case WebGL:
			break;
		case Applet:
			break;
		case HeadlessDesktop:
			break;
		case iOS:
			break;
		default:
			System.out.println("The app type wasn't loaded properly.");
			System.exit(0);
			break;
		}
	}

	@Override
	/**
	 * Controls what is done on application update
	 */
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		world.update();

		batch.begin();
		world.draw();
		batch.end();

	}
}
