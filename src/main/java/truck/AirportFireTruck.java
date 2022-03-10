package truck;

import cabin.Cabin;
import com.google.common.eventbus.Subscribe;
import drive.*;
import drive.battery.BatteryBox;
import drive.battery.charger.Receiver;
import drive.battery.charger.ThreePoleChargingPort;
import lights.*;
import truck.events.SelfProtectionEvent;
import truck.events.Subscriber;
import truck.water.*;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class AirportFireTruck extends Subscriber implements IAirportFireTruck {
    private final Light[] headLightsFrontLeft;
    private final Light[] headLightsFrontRight;
    private final Light[] headLightsRoof;
    private final Light[] sideLightsLeft;
    private final Light[] sideLightsRight;
    private final Light[] turnSignalLightsLeft;
    private final Light[] turnSignalLightsRight;
    private final Light brakeLightLeft;
    private final Light brakeLightRight;
    private final Light[] blueLights;
    private final Light[] warningLights;
    private final IDrive drive;
    private final Cabin cabin;
    private final ICentralUnit centralUnit;
    private final ITank waterTank;
    private final ITank foamPowderTank;
    private final Object mixingUnit;
    private final FrontLauncher frontLauncher;
    private final RoofLauncher roofLauncher;
    private final FloorSprayingNozzle[] floorSprayingNozzles;

    private AirportFireTruck(Builder builder) {
        this.headLightsFrontLeft = builder.headLightsFrontLeft;
        this.headLightsFrontRight = builder.headLightsFrontRight;
        this.headLightsRoof = builder.headLightsRoof;
        this.sideLightsLeft = builder.sideLightsLeft;
        this.sideLightsRight = builder.sideLightsRight;
        this.turnSignalLightsLeft = builder.turnSignalLightsLeft;
        this.turnSignalLightsRight = builder.turnSignalLightsRight;
        this.brakeLightLeft = builder.brakeLightLeft;
        this.brakeLightRight = builder.brakeLightRight;
        this.blueLights = builder.blueLights;
        this.warningLights = builder.warningLights;
        this.drive = builder.drive;
        this.centralUnit = new CentralUnit(this, builder.name, builder.code);
        this.cabin = new Cabin(centralUnit, builder.smartJoySticks);
        this.waterTank = builder.waterTank;
        this.foamPowderTank = builder.foamPowderTank;
        this.mixingUnit = builder.mixingUnit;
        this.frontLauncher = builder.frontLauncher;
        this.roofLauncher = builder.roofLauncher;
        this.floorSprayingNozzles = builder.floorSprayingNozzles;

        this.waterTank.getObserver().addListener(this.cabin.getControlPanel().getWaterIndicationLight());
        this.foamPowderTank.getObserver().addListener(this.cabin.getControlPanel().getFoamIndicationLight());

        for (Light light : this.blueLights)
            this.centralUnit.addSubscriber(light);

        for (Light light : this.warningLights)
            this.centralUnit.addSubscriber(light);

        for (Light light : this.headLightsFrontLeft)
            this.centralUnit.addSubscriber(light);

        for (Light light : this.headLightsFrontRight)
            this.centralUnit.addSubscriber(light);

        for (Light light : this.headLightsRoof)
            this.centralUnit.addSubscriber(light);

        for (Light light : this.sideLightsLeft)
            this.centralUnit.addSubscriber(light);

        for (Light light : this.sideLightsRight)
            this.centralUnit.addSubscriber(light);

        this.centralUnit.addSubscriber((Subscriber) this.drive);
        this.centralUnit.addSubscriber(this);
    }

    public void chargeTruck(int amount) {
        this.getDrive().charge(amount);
        this.getCabin().getBatteryDisplay().setValue(String.valueOf(this.getDrive().getBatteryPercentage()));
    }

    @Subscribe
    public void receive(SelfProtectionEvent event) {
        useFloorNozzles(event.getAmount());
    }

    private void useFloorNozzles(int amount) {
        for (FloorSprayingNozzle nozzle : floorSprayingNozzles) {
            nozzle.sprayWater(amount);
        }
    }

    public Light[] getHeadLightsFrontLeft() {
        return headLightsFrontLeft;
    }

    public Light[] getHeadLightsFrontRight() {
        return headLightsFrontRight;
    }

    public Light[] getHeadLightsRoof() {
        return headLightsRoof;
    }

    public Light[] getSideLightsLeft() {
        return sideLightsLeft;
    }

    public Light[] getSideLightsRight() {
        return sideLightsRight;
    }

    public Light[] getTurnSignalLightsLeft() {
        return turnSignalLightsLeft;
    }

    public Light[] getTurnSignalLightsRight() {
        return turnSignalLightsRight;
    }

    public Light getBrakeLightLeft() {
        return brakeLightLeft;
    }

    public Light getBrakeLightRight() {
        return brakeLightRight;
    }

    public Light[] getBlueLights() {
        return blueLights;
    }

    public Light[] getWarningLights() {
        return warningLights;
    }

    public IDrive getDrive() {
        return drive;
    }

    public ICentralUnit getCentralUnit() {
        return centralUnit;
    }

    public Cabin getCabin() {
        return cabin;
    }

    public ITank getWaterTank() {
        return waterTank;
    }

    public ITank getFoamPowderTank() {
        return foamPowderTank;
    }

    public Object getMixingUnit() {
        return mixingUnit;
    }

    public FrontLauncher getFrontLauncher() {
        return frontLauncher;
    }

    public RoofLauncher getRoofLauncher() {
        return roofLauncher;
    }

    public FloorSprayingNozzle[] getFloorSprayingNozzles() {
        return floorSprayingNozzles;
    }


    public static class Builder {
        private final Configuration config = Configuration.INSTANCE;

        private final String name;
        private final String code;
        private final FrontLight[] headLightsFrontLeft;
        private final FrontLight[] headLightsFrontRight;
        private final RoofLight[] headLightsRoof;
        private final SideLight[] sideLightsLeft;
        private final SideLight[] sideLightsRight;
        private final TurnSignalLight[] turnSignalLightsLeft;
        private final TurnSignalLight[] turnSignalLightsRight;
        private final BrakeLight brakeLightLeft;
        private final BrakeLight brakeLightRight;
        private final BlueLight[] blueLights;
        private final WarningLight[] warningLights;
        private final Drive drive;
        private final Tank waterTank;
        private final Tank foamPowderTank;
        private final Object mixingUnit;
        private final FrontLauncher frontLauncher;
        private final RoofLauncher roofLauncher;
        private final FloorSprayingNozzle[] floorSprayingNozzles;
        private boolean smartJoySticks;

        public Builder(String name, String accessCode) {
            this.name = name;
            this.code = accessCode;

            final int headLightsFront = 3;
            this.headLightsFrontLeft = new FrontLight[headLightsFront];
            this.headLightsFrontRight = new FrontLight[headLightsFront];
            for (int i = 0; i < headLightsFront; i++) {
                this.headLightsFrontLeft[i] = new FrontLight();
                this.headLightsFrontRight[i] = new FrontLight();
            }

            this.headLightsRoof = new RoofLight[4];
            for (int i = 0; i < headLightsRoof.length; i++) this.headLightsRoof[i] = new RoofLight();

            final int sideLights = 5;
            this.sideLightsLeft = new SideLight[sideLights];
            this.sideLightsRight = new SideLight[sideLights];
            for (int i = 0; i < sideLights; i++) {
                this.sideLightsLeft[i] = new SideLight();
                this.sideLightsRight[i] = new SideLight();
            }

            final int turnSignalLights = 2;
            this.turnSignalLightsLeft = new TurnSignalLight[turnSignalLights];
            this.turnSignalLightsRight = new TurnSignalLight[turnSignalLights];
            for (int i = 0; i < turnSignalLights; i++) {
                this.turnSignalLightsLeft[i] = new TurnSignalLight();
                this.turnSignalLightsRight[i] = new TurnSignalLight();
            }

            this.brakeLightLeft = new BrakeLight();
            this.brakeLightRight = new BrakeLight();

            this.blueLights = new BlueLight[10];
            for (int i = 0; i < 2; i++) this.blueLights[i] = new BlueLight(1); // front small
            for (int i = 2; i < 6; i++) this.blueLights[i] = new BlueLight(4); // front roof big
            for (int i = 6; i < 10; i++) this.blueLights[i] = new BlueLight(2); // back roof medium

            this.warningLights = new WarningLight[2];
            for (int i = 0; i < warningLights.length; i++) this.warningLights[i] = new WarningLight(1);

            ElectricMotor[] electricMotors = new ElectricMotor[2];
            for (int i = 0; i < electricMotors.length; i++) electricMotors[i] = new ElectricMotor();

            BatteryBox batteryBox = new BatteryBox();
            Receiver energyReceiver = new ThreePoleChargingPort();

            SteeringAxle[] frontAxles = new SteeringAxle[2];
            for (int i = 0; i < frontAxles.length; i++) frontAxles[i] = new SteeringAxle();

            Axle[] backAxles = new Axle[2];
            for (int i = 0; i < backAxles.length; i++) backAxles[i] = new Axle();
            this.drive = new Drive(electricMotors, batteryBox, energyReceiver, frontAxles, backAxles);

            this.waterTank = new Tank(ExtinguishingType.WATER, 75, 45, 30);
            this.foamPowderTank = new Tank(ExtinguishingType.FOAM_POWDER, 75, 45, 10);
            this.mixingUnit = buildMixingUnit();
            this.frontLauncher = new FrontLauncher(this.mixingUnit, this.waterTank, this.foamPowderTank);
            this.roofLauncher = new RoofLauncher(this.mixingUnit, this.waterTank, this.foamPowderTank);
            this.floorSprayingNozzles = new FloorSprayingNozzle[7];
            for (int i = 0; i < floorSprayingNozzles.length; i++)
                this.floorSprayingNozzles[i] = new FloorSprayingNozzle(this.waterTank);
        }

        public Object buildMixingUnit() {
            Object mixingUnitPort = null;
            try {
                URL[] urls = {new File(config.pathToMixingUnitJavaArchive + config.mixingUnitJarArchive).toURI().toURL()};
                URLClassLoader urlClassLoader = new URLClassLoader(urls, AirportFireTruck.class.getClassLoader());

                Class<?> mixingUnitClass = Class.forName("MixingUnit", true, urlClassLoader);
                Object mixingUnitInstance = mixingUnitClass.getMethod("getInstance").invoke(null);

                mixingUnitPort = mixingUnitClass.getDeclaredField("port").get(mixingUnitInstance);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mixingUnitPort;
        }

        public Builder enableSmartJoySticks(boolean enable) {
            this.smartJoySticks = enable;
            return this;
        }

        public AirportFireTruck build() {
            return new AirportFireTruck(this);
        }

    }

}
