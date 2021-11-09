package truck;

import enums.ButtonType;
import lights.BlueLight;
import lights.HeadLight;
import lights.WarningLight;

public class CentralUnit {

    private AirportFireTruck airportFireTruck;

    public CentralUnit(AirportFireTruck airportFireTruck){
        this.airportFireTruck = airportFireTruck;
    }

    public void buttonPress(ButtonType type){
        switch (type){
            case motorSwitch -> {
                airportFireTruck.getDrive().startMotors();
            }
            case blueLightSwitch -> {
                for (BlueLight light : airportFireTruck.getBlueLights()){
                    light.switchState();
                }
            }
            case warningLightSwitch -> {
                for (WarningLight light : airportFireTruck.getWarningLights()){
                    light.switchState();
                }
            }
            case roofHeadLightSwitch -> {
                for (HeadLight light : airportFireTruck.getHeadLightsRoof()){
                    light.switchState();
                }
            }
            case frontHeadlightSwitch -> {
                for (HeadLight light : airportFireTruck.getHeadLightsFrontLeft()){
                    light.switchState();
                }
                for (HeadLight light : airportFireTruck.getHeadLightsFrontRight()){
                    light.switchState();
                }

            }

        }
    }
}
