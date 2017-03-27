import javax.swing.*;
import java.io.File;
import java.util.Scanner;
/**
 * @author Amir Torabi
 * @studentid 5293010
 * @email torabi10@gmail.com
 * @assignment.number A19004
 * @version 1.00 - 02/21/2016
 * @screenprint <a href='A19004.gif'>ScreenPrint</a>
 * @prgm.usage Called directly from OS
 * @link <a href='http://jcouture.net/cisc190/A19002.php'>Program Specification</a>
 * @link <br><a href='http://docs.oracle.com/javase/7/docs/
 * technotes/guides/Javadoc/index.html'>Javadoc Documentation</a>
 * Created by Amir. java application. A19004
 * The Purpose of this program is to examine data from a winds aloft data file. Reformat that information to
 * make the data relevant to the user reading it. It assigns a set of numbers across a table to readable data.

 */

public class A19004 {

    static String strStationWeather;

    /**
     * Main method executing the data exchanges
     * @param args
     * @throws Exception
     */


    public static void main(String[] args) throws Exception {


        String strInputFileName = "data/FBIN.txt"; //input file data
        String strAlt;
        String strWindDir;
        String strWindSpeed;
        String strWindTemp;
        String strStationID;


        //spec 6.1
        strStationID = JOptionPane.showInputDialog("Please Enter the Station ID");

        if (strStationID.length() > 2) {
            strStationWeather = getStationData(strStationID, strInputFileName);

            if (strStationWeather.length() > 60) {
                strAlt = JOptionPane.showInputDialog("Input Altitude");
                strWindDir = getWindDir(strAlt);
                strWindSpeed = getWindSpeed(strAlt);
                strWindTemp = getWindTemp(strAlt);
                displayData(strStationID, strAlt, strWindDir, strWindSpeed, strWindTemp);


            }
            System.exit(0);


        }
    }

    /**
     * This extracts the stationData for that particular city
     * @param strStationID
     * @param strInputFileName
     * @return
     * @throws Exception
     */


    public static String getStationData(String strStationID, String strInputFileName) throws Exception
    {

        String strRet = "station not found";
        String strRecord;

        //Open File for input

        File myFile = new File(strInputFileName);

        if (myFile.exists())
        {
            Scanner inputFile = new Scanner(myFile);

            while (inputFile.hasNext())
            {
                strRecord = inputFile.nextLine() + " ";

                if ( (strRecord.length() > 10))
                {
                    if (strRecord.substring(0, 3).equals(strStationID))
                    {
                        strRet = strRecord;
                        //System.out.println(strRet);
                    }
                }


            }

            inputFile.close();
        } else {

            System.out.printf("The file %s does not exist\r\n", strInputFileName);
            System.exit(1);
        }

        return strRet;


    }


    //format altitude -----------------------------
  //  String strAlt;
   // String strAltSub = strAlt.substring(0, 2);
  //  int i = Integer.parseInt(strAltSub) * 1000;

    //---------------------------------------------

    /**
     * Find the position of the strstationweather readout and assign the position to use later
     * @param strAlt
     * @return
     */

    public static int getPos(String strAlt)
    {
        int intRet = 0;

        int intAlt = Integer.parseInt(strAlt);


        switch (intAlt)
        {
            case 3:
                intRet = 4;
                break;
            case 6:
                intRet = 9;
                break;
            case 9:
                intRet = 17;
                break;
            case 12:
                intRet = 25;
                break;
            case 18:
                intRet = 33;
                break;
            case 24:
                intRet = 41;
                break;
            case 30:
                intRet = 49;
                break;
            case 34:
                intRet = 56;
                break;
            case 39:
                intRet = 63;
                break;

        }

        return intRet;

    }

    /**
     * grab the Altitude Weather
     * @param strAlt
     * @return
     */

    public static String getAltitudeWeather(String strAlt)
    {
        // get the position in the station weather string
        int intPos = getPos(strAlt);

        // strAltitudeWeather contains a seven character string
      String strRet = strStationWeather.substring(intPos,intPos+7);

        // return the result
        return strRet;
    }

    /**
     * Grab the Wind Direction
     * @param strAlt
     * @return
     */

    public static String getWindDir (String strAlt)
    {
     // String strRet = "";

       String strRet = getAltitudeWeather(strAlt);
       return strRet.substring(0,2);
       // return strRet;

    }

    /**
     * grab the wind speed
     * @param strAlt
     * @return
     */

    public static String getWindSpeed (String strAlt)
    {
        // String strRet = "";
        String strRet = getAltitudeWeather(strAlt);
       return strRet.substring(2,4);
        // return strRet;
    }

    /**
     * Grab the Wind Temperature
     * @param strAlt
     * @return
     */

    public static String getWindTemp (String strAlt)
    {
        // String strRet = "";
       String strRet = getAltitudeWeather(strAlt);
       return strRet.substring(4,7);
        // return strRet;

    }

    /**
     * Display the data on command prompt
     * @param strStationID
     * @param strAlt
     * @param strWindDir
     * @param strWindSpeed
     * @param strWindTemp
     */

    public static void displayData(String strStationID, String strAlt, String strWindDir, String strWindSpeed, String strWindTemp)
    {
        System.out.println("Enter the Station ID");
        System.out.println(strStationID);
        System.out.println("Enter the Altitude");
        System.out.println(strAlt);
        System.out.println("");
        System.out.println("Black Mountain Aviation");
        System.out.println("");
        String strAltSub = strAlt.substring(0, 2);
        int i = Integer.parseInt(strAltSub) * 1000;
        System.out.println("Weather for" + " " + strStationID + " " + "at an altitude of " + (i + " "));
        System.out.println("Wind Direction" + "    " + strWindDir + "0" +  " " + "Degrees");
        System.out.println("Wind Speed" + "        " + strWindSpeed +  " " + "knots");
        System.out.println("Wind Temperature" + "  " + strWindTemp + "C");
        System.out.println("");
        System.out.println("");
        System.out.println("Program by Amir Torabi");
    }

}



