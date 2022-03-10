package truck;

import id_card.encryption.AESEncoder;
import id_card.encryption.IEncoder;

public enum Configuration {
    INSTANCE;

    public final String userDirectory = System.getProperty("user.dir");
    public final String fileSeparator = System.getProperty("file.separator");
    public final String pathToMixingUnitJavaArchive = userDirectory + fileSeparator + "mixing_unit" + fileSeparator + "jar" + fileSeparator;
    public final String mixingUnitJarArchive = "MixingUnit.jar";

    public final IEncoder encryptionAlgorithm = new AESEncoder();
}
