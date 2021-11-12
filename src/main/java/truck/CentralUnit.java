package truck;

import enums.ButtonType;
import enums.PedalType;
import lights.BlueLight;
import lights.HeadLight;
import lights.WarningLight;

public class CentralUnit {

    private final AirportFireTruck airportFireTruck;

    public CentralUnit(AirportFireTruck airportFireTruck){
        this.airportFireTruck = airportFireTruck;
    }

    public void pedalPress(PedalType type){
        switch (type){
            case Gas -> airportFireTruck.getDrive().drive(airportFireTruck.getDrive().getCurrentVelocity()+4);
            case Brake -> airportFireTruck.getDrive().drive(airportFireTruck.getDrive().getCurrentVelocity()-4);
        }
        //update speed/battery displays while driving
        airportFireTruck.getCabin().getSpeedDisplay().writeValue(String.valueOf(airportFireTruck.getDrive().getCurrentVelocity()));
        airportFireTruck.getCabin().getBatteryDisplay().writeValue(String.valueOf(airportFireTruck.getDrive().getBatteryPercentage()));
    }

    //rotates axles to the exact rotation of steering wheel
    public void turnSteeringWheel(int rotation){
        airportFireTruck.getDrive().rotateAxles(rotation);
    }

    public void buttonPress(ButtonType type){
        switch (type){
            case motorSwitch -> airportFireTruck.getDrive().startMotors();
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
