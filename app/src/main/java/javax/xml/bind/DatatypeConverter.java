package javax.xml.bind;

public class DatatypeConverter {
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
  
    public static String printBase64Binary( byte[] val ) {
        return "dummy";
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public static native String stringFromJNI();
  
}
