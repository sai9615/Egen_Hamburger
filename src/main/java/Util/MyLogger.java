
package Util;

public class MyLogger{

    /*DEBUG_VALUE=4 [Print to stdout everytime a constructor is called]
      DEBUG_VALUE=3 [Print to stdout operations related to file processor]
      DEBUG_VALUE=2 [Print to stdout whenever we encounter an error]
      DEBUG_VALUE=1 [Print to stdout every time results are being written to file]
      DEBUG_VALUE=0 [No output should be printed from the applicatio to stdout. It is ok to write to the output file though" ]
    */

    public static enum DebugLevel {RELEASE, ERROR , RESULT, FILEPROCESSOR, CONSTRUCTOR
    };

    private static DebugLevel debugLevel;


    public static void setDebugValue (int levelIn) {
        switch (levelIn) {
            case 4: debugLevel = DebugLevel.CONSTRUCTOR; break;
            case 3: debugLevel = DebugLevel.FILEPROCESSOR; break;
            case 2: debugLevel = DebugLevel.ERROR; break;
            case 1: debugLevel = DebugLevel.RESULT; break;
            case 0: debugLevel = DebugLevel.RELEASE; break;
        }
    }

    public static void setDebugValue (DebugLevel levelIn) {
        debugLevel = levelIn;
    }

    // @return None
    public static void writeMessage (String  message  ,
                                     DebugLevel levelIn ) {
        if (levelIn == debugLevel && levelIn != DebugLevel.RELEASE)
            System.out.println(message);
    }

    /**
     * @return String
     */
    public String toString() {
        return "Debug Level set to " + debugLevel;
    }
}
