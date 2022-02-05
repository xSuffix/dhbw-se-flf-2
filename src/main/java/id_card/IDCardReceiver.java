package id_card;


import truck.ICentralUnit;

public record IDCardReceiver(ICentralUnit centralUnit) {

    public void read(IDCard idCard) {
        byte[] token = idCard.rfidChip().getToken();
        centralUnit.checkAuthentication(token);
    }

}
