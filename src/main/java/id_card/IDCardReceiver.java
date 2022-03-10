package id_card;

import truck.ICentralUnit;

public record IDCardReceiver(ICentralUnit centralUnit) {

    public void read(IDCard idCard) {
        centralUnit.checkAuthentication(idCard.getChip());
    }
}
