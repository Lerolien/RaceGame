package com.fogok.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.fogok.game.view.GameScreen;

public class CarController {

    private Polygon carBounds;
    public CarController(Polygon carBounds){
        this.carBounds = carBounds;
    }

    private float carSpeed, speedVelocity = 10f, speedMax = 10f;
    private float rotationSpeed = 30f;

    public void handle(){
        //все что сыязано со скоростью
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            carSpeed += speedVelocity * GameScreen.deltaC;
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            carSpeed -= speedVelocity * GameScreen.deltaC;
        else
            downSpeed();

        carSpeed = sliceSpeed();
        //

        //все что связано с поворотом
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            carBounds.rotate(rotationSpeed * carSpeed * GameScreen.deltaC);
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            carBounds.rotate(-rotationSpeed * carSpeed * GameScreen.deltaC);
        //

        carBounds.setPosition(carBounds.getX() + MathUtils.cosDeg(carBounds.getRotation() + 90) * carSpeed * GameScreen.deltaC,
                carBounds.getY() + MathUtils.sinDeg(carBounds.getRotation() + 90) * carSpeed * GameScreen.deltaC);
    }

    private void downSpeed(){ //гасим скорость
        if(carSpeed > speedVelocity  * GameScreen.deltaC)
            carSpeed -= speedVelocity * GameScreen.deltaC;
        else if(carSpeed < -speedVelocity * GameScreen.deltaC)
            carSpeed += speedVelocity * GameScreen.deltaC;
        else
            carSpeed = 0f;
    }

    private float sliceSpeed(){ //ограничиваем скорость
        if(carSpeed > speedMax)
            return speedMax;
        if (carSpeed < -speedMax)
            return -speedMax;

        return carSpeed;
    }
}
