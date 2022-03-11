import cabin.controls.button.Button;
import cabin.controls.button.ButtonStateOff;
import cabin.controls.button.ButtonStateOn;
import drive.battery.Battery;
import drive.battery.BatteryManagement;
import drive.battery.cell.BatteryCell;
import drive.battery.cell.MainCell;
import drive.battery.charger.EChargingStation;
import drive.battery.charger.OneToThreePoleAdapter;
import drive.battery.charger.ThreePoleChargingPort;
import id_card.IDCard;
import id_card.IDCardEncoder;
import lights.LightColor;
import org.junit.jupiter.api.*;
import truck.water.ExtinguishingType;
import truck.water.MixingRatio;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class FLF2Test extends FLFTest {

    @BeforeEach
    public void setup() {
        initialize();
    }

    @Test
    @Order(1)
    public void useMixingUnitComponent() {
        final int waterCount = 3;
        final int foamCount = 1;
        ExtinguishingType[] water = new ExtinguishingType[waterCount];
        ExtinguishingType[] foam = new ExtinguishingType[foamCount];
        for (int i = 0; i < waterCount; i++) water[i] = ExtinguishingType.WATER;
        for (int i = 0; i < foamCount; i++) foam[i] = ExtinguishingType.FOAM_POWDER;

        Object mixingUnit = airportFireTruck.getMixingUnit();
        List<Object> mixedAgent;

        try {
            Class<?>[] argTypes = new Class[]{List.class};
            List<Object[]> args = new ArrayList<>();
            args.add(water);
            args.add(foam);

            Method method = mixingUnit.getClass().getDeclaredMethod("getMixedAgent", argTypes);
            //noinspection unchecked
            mixedAgent = (List<Object>) method.invoke(mixingUnit, args);
            System.out.println(mixedAgent);
            assertEquals(mixedAgent.stream().filter(o -> o == ExtinguishingType.WATER).count(), waterCount);
            assertEquals(mixedAgent.stream().filter(o -> o == ExtinguishingType.FOAM_POWDER).count(), foamCount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        preDeployment();
        armFrontLauncher();
        testFrontLauncher(3, 3000, MixingRatio.C);
    }

    @Test
    @Order(2)
    public void testEventBus() {

        driver.pressMotorSwitch();
        checkMotors(true);
        driver.pressMotorSwitch();
        checkMotors(false);

        int waterLevel = airportFireTruck.getWaterTank().getCurrentCapacity() - 700;
        driver.toggleProtection();
        assertEquals(waterLevel , airportFireTruck.getWaterTank().getCurrentCapacity());

        turnAllLightsOn();
        turnAllLightsOff();

    }

    @Test
    @Order(3)
    public void testBattery() {
        for (Battery[] row : airportFireTruck.getDrive().getBatteryBox().getBatteries()) {
            for (Battery battery : row) {
                for (MainCell mainCell : battery.getMainCells()) {
                    assertTrue(mainCell.isComposite());
                    for (BatteryCell subCell : mainCell.getUnitList()) {
                        assertTrue(subCell.isComposite());
                        for (BatteryCell cell : subCell.getUnitList()) {
                            assertFalse(cell.isComposite());
                        }
                    }
                }
            }
        }
        checkDriving(8, 5, 8, 0);
    }

    @Test
    @Order(4)
    public void useIDCardStrategy() {
        airportFireTruck.getCabin().getLeftDoor().toggleLock();
        airportFireTruck.getCabin().getRightDoor().toggleLock();
        checkIfDoorsOpen(false);
        assertTrue(airportFireTruck.getCabin().getLeftDoor().isLocked());
        assertTrue(airportFireTruck.getCabin().getRightDoor().isLocked());

        IDCardEncoder cardEncoder = new IDCardEncoder();
        IDCard driverCard = new IDCard();
        IDCard operatorCard = new IDCard();

        cardEncoder.encode(airportFireTruck.getCentralUnit(), driverCard, driver);
        cardEncoder.encode(airportFireTruck.getCentralUnit(), operatorCard, operator);

        driver.useIDCard(driverCard);
        checkIfDoorsOpen(true);
        driver.takeSeat();
        operator.useIDCard(driverCard);
        driver.pressInnerDoorButton();
        checkIfDoorsOpen(false);
        cardEncoder.erase(airportFireTruck.getCentralUnit(), driverCard);
        cardEncoder.erase(airportFireTruck.getCentralUnit(), operatorCard);
    }

    @Test
    @Order(5)
    public void useChargingAdapter() {
        BatteryManagement batteryManagement = airportFireTruck.getDrive().getBatteryManagement();
        EChargingStation chargingStation;
        OneToThreePoleAdapter adapter;
        ThreePoleChargingPort chargingPort;
        System.out.println(airportFireTruck.getDrive().getBatteryCharge());
        batteryManagement.discharge();
        assertEquals(0, batteryManagement.getCharge());

        int chargingSpeed = 1000;
        int[] weights = new int[]{3, 3, 4};
        chargingStation = new EChargingStation(chargingSpeed);
        adapter = new OneToThreePoleAdapter(weights);
        chargingPort = (ThreePoleChargingPort) batteryManagement.getReceiver();
        System.out.printf("Energy after charge: %d%n", checkCharge(chargingStation, adapter, chargingPort, batteryManagement));

        batteryManagement.discharge();
        assertEquals(batteryManagement.getCharge(), 0);

        chargingSpeed = 16;
        weights = new int[]{2, 4, 8};
        chargingStation = new EChargingStation(chargingSpeed);
        adapter = new OneToThreePoleAdapter(weights);
        chargingPort = (ThreePoleChargingPort) batteryManagement.getReceiver();
        System.out.printf("Energy after charge: %d%n", checkCharge(chargingStation, adapter, chargingPort, batteryManagement));
    }

    @Test
    @Order(6)
    public void useButtonStates() {
        // Light button
        Button blueLightButton = airportFireTruck.getCabin().getControlPanel().getBlueLightSwitch();
        assertEquals(blueLightButton.getState().getClass(), ButtonStateOff.class);
        checkLights(airportFireTruck.getBlueLights(), false);

        blueLightButton.press();
        checkLights(airportFireTruck.getBlueLights(), true);
        assertEquals(blueLightButton.getState().getClass(), ButtonStateOn.class);

        blueLightButton.press();
        assertEquals(blueLightButton.getState().getClass(), ButtonStateOff.class);
        checkLights(airportFireTruck.getBlueLights(), false);

        // Synced buttons
        Button outerButton = airportFireTruck.getCabin().getLeftDoor().getOuterButton();
        Button innerButton = airportFireTruck.getCabin().getLeftDoor().getInnerButton();

        assertTrue(airportFireTruck.getCabin().getLeftDoor().isOpen());
        outerButton.press();
        assertFalse(airportFireTruck.getCabin().getLeftDoor().isOpen());
        innerButton.press();
        assertTrue(airportFireTruck.getCabin().getLeftDoor().isOpen());
    }

    @Disabled
    @Test
    @Order(7)
    public void testCommand(){
        //every other test will fail if buttons don't work...
    }

    @Test
    @Order(8)
    public void testObserver(){

        // 100 %
        triggerObserversManual();
        assertFalse(airportFireTruck.getCabin().getControlPanel().getFoamIndicationLight().isOn());
        assertFalse(airportFireTruck.getCabin().getControlPanel().getWaterIndicationLight().isOn());

        // < 50%
        airportFireTruck.getWaterTank().getAgent(55000);
        airportFireTruck.getFoamPowderTank().getAgent(18000);

        triggerObserversManual();
        assertTrue(airportFireTruck.getCabin().getControlPanel().getFoamIndicationLight().isOn());
        assertEquals(LightColor.YELLOW, airportFireTruck.getCabin().getControlPanel().getFoamIndicationLight().getColor());
        assertTrue(airportFireTruck.getCabin().getControlPanel().getWaterIndicationLight().isOn());
        assertEquals(LightColor.YELLOW, airportFireTruck.getCabin().getControlPanel().getWaterIndicationLight().getColor());

        // < 25 %
        airportFireTruck.getWaterTank().getAgent(27000);
        airportFireTruck.getFoamPowderTank().getAgent(9000);

        triggerObserversManual();
        assertTrue(airportFireTruck.getCabin().getControlPanel().getFoamIndicationLight().isOn());
        assertEquals(LightColor.ORANGE, airportFireTruck.getCabin().getControlPanel().getFoamIndicationLight().getColor());
        assertTrue(airportFireTruck.getCabin().getControlPanel().getWaterIndicationLight().isOn());
        assertEquals(LightColor.ORANGE, airportFireTruck.getCabin().getControlPanel().getWaterIndicationLight().getColor());

        // < 10 %
        airportFireTruck.getWaterTank().getAgent(12000);
        airportFireTruck.getFoamPowderTank().getAgent(5000);

        triggerObserversManual();
        assertTrue(airportFireTruck.getCabin().getControlPanel().getFoamIndicationLight().isOn());
        assertEquals(LightColor.RED, airportFireTruck.getCabin().getControlPanel().getFoamIndicationLight().getColor());
        assertTrue(airportFireTruck.getCabin().getControlPanel().getWaterIndicationLight().isOn());
        assertEquals(LightColor.RED, airportFireTruck.getCabin().getControlPanel().getWaterIndicationLight().getColor());

        // > 50 %
        airportFireTruck.getWaterTank().fill(55000, ExtinguishingType.WATER);
        airportFireTruck.getFoamPowderTank().fill(18000, ExtinguishingType.FOAM_POWDER);

        triggerObserversManual();
        assertFalse(airportFireTruck.getCabin().getControlPanel().getFoamIndicationLight().isOn());
        assertFalse(airportFireTruck.getCabin().getControlPanel().getWaterIndicationLight().isOn());
    }

    @Test
    @Order(9)
    public void checkVisitor(){
        // if self check fails, RunTimeException gets thrown
        try {
            driver.pressMotorSwitch();
        } catch (RuntimeException e) {
            e.printStackTrace();
            fail();
        }
    }

    private void triggerObserversManual(){
        airportFireTruck.getWaterTank().getObserver().checkTankCapacity();
        airportFireTruck.getFoamPowderTank().getObserver().checkTankCapacity();
    }
}
