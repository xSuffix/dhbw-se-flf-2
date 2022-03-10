package truck;

import cabin.controls.PedalType;
import cabin.controls.TurningKnobType;
import cabin.controls.button.ButtonType;
import id_card.RFIDChip;
import truck.events.Subscriber;

public interface ICentralUnit {
    void turnSteeringWheel(int rotation);

    void buttonPress(ButtonType type, boolean state);

    void pedalPress(PedalType type);

    <E> void turningKnobTurn(TurningKnobType type, E setting);

    String getID();

    String getCode();

    void authorizePerson(String name);

    void checkAuthentication(RFIDChip chipData);

    void addSubscriber(Subscriber subscriber);

}
