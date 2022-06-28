package bstorm.akimts.oo.avance.exo.lambdas;

import java.util.function.Consumer;

public class Thermometer {

    private int temperature;
    private Consumer<Integer> actionTempUp;
    private Consumer<Integer> actionTempDown;

    public Thermometer(int temperature) {
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        if( this.temperature < temperature && actionTempUp != null ){
            actionTempUp.accept(temperature);
        }
        else if( this.temperature > temperature && actionTempDown != null ){
            actionTempDown.accept(temperature);
        }
        this.temperature = temperature;
    }

    public void subTempUp(Consumer<Integer> action){
        this.actionTempUp = action;
    }

    public void subTempDown(Consumer<Integer> action){
        this.actionTempDown = action;
    }
}
