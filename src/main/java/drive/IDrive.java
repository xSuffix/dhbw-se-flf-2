package drive;

public interface IDrive {
    void stopMotors();

    void startMotors();

    boolean motorsOn();

    void charge(int amount);

    void drive(int vel);

    int getCurrentVelocity();

    void rotateAxles(int rotation);

    int getAxleRotation();

    int getBatteryCharge();

    float getBatteryPercentage();

    ElectricMotor[] getElectricMotors();

    BatteryManagement getBatteryManagement();

    BatteryBox getBatteryBox();

    SteeringAxle[] getFrontAxles();

    Axle[] getBackAxles();
}
