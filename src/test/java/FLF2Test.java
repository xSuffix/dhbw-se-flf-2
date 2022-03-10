import cabin.controls.button.Button;
import cabin.controls.button.ButtonStateOff;
import cabin.controls.button.ButtonStateOn;
import drive.battery.Battery;
import drive.battery.BatteryManagement;
import drive.battery.cell.BatteryCell;
import drive.battery.cell.Cell;
import drive.battery.cell.MainCell;
import drive.battery.charger.EChargingStation;
import drive.battery.charger.OneToThreePoleAdapter;
import drive.battery.charger.ThreePoleChargingPort;
import id_card.IDCard;
import id_card.IDCardEncoder;
import org.junit.jupiter.api.*;
import truck.Configuration;
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
        initialize(false);
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
    @Order(3)
    public void testBattery() {
        for (Battery[] row : airportFireTruck.getDrive().getBatteryBox().getBatteries()){
            for (Battery battery : row){
                for (MainCell mainCell : battery.getMainCells()){
                    assertTrue(mainCell.isComposite());
                    for (BatteryCell subCell : mainCell.getUnitList()){
                        assertTrue(subCell.isComposite());
                        for (BatteryCell cell : subCell.getUnitList()){
                            assertFalse(cell.isComposite());
                        }
                    }
                }
            }
        }
        checkDriving(8, 5, 8, 0);
    }
    
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

        cardEncoder.encode(airportFireTruck.getCentralUnit(), driverCard, "Red Adair");
        cardEncoder.encode(airportFireTruck.getCentralUnit(), operatorCard, "Sam");

        driver.useIDCard(driverCard);
        checkIfDoorsOpen(true);
        driver.takeSeat();
        operator.useIDCard(driverCard);
        driver.pressInnerDoorButton();
        checkIfDoorsOpen(false);

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
}
