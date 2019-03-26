package helpers;

public class Device
{

    public static device platform  = device.android;

    public enum device {
        android,
        ios
    }

    public static void getPlatform() throws Exception {
        if (System.getProperty("device") != null && System.getProperty("device").equalsIgnoreCase("ios")) {
            platform = device.ios;
        }
    }
}



