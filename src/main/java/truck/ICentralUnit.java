package truck;

import cabin.controls.PedalType;
import cabin.controls.TurningKnobType;
import cabin.controls.button.ButtonType;
import id_card.RFIDChip;
import staff.Person;
import truck.command.ICommand;
import truck.events.Subscriber;

public interface ICentralUnit {
    void turnSteeringWheel(int rotation);

    void execute(ButtonType type, boolean state);

    void setCommand(ICommand command);

    ICommand getButtonCommand();

    void pedalPress(PedalType type);

    <E> void turningKnobTurn(TurningKnobType type, E setting);

    String getID();

    String getCode();

    void addAuthorization(Person person);

    void removeAuthorization(Person person);

    void checkAuthentication(RFIDChip chipData);

    void addSubscriber(Subscriber subscriber);

}
