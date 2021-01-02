package Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.String;

public class FileProcessor {

        private String filename;
        private BufferedReader scn;
        /**
         * Constructor, used for opening the file and initializing buffers.
         * @param fname - name of the input file.
         */
        public FileProcessor(String fname) {
            try {
                filename = fname;
                File file = new File(filename);
                scn = new BufferedReader(new FileReader(file));
                System.out.println("opened the file");
            } catch (Exception e) {
                MyLogger.writeMessage(getClass().getName()+" can't open the file "+e.toString(), MyLogger.DebugLevel.ERROR);
                MyLogger.writeMessage(getClass().getName()+" can't open the file "+e.toString(), MyLogger.DebugLevel.FILEPROCESSOR);
                System.exit(0);
            }
        }

        /**
         * Used to read the file, line by line
         * @return a line from the file
         */
        public String readLine(){
            try{
                String str;
                str = scn.readLine();
                if(str == null) {
                    return null;
                } else {
                    return str;
                }
            } catch (Exception e) {
                MyLogger.writeMessage(getClass().getName()+" can't read the file "+e.toString(), MyLogger.DebugLevel.ERROR);
                MyLogger.writeMessage(getClass().getName()+" can't read the file "+e.toString(), MyLogger.DebugLevel.FILEPROCESSOR);
                System.exit(0);
            }
            closeMyFile();
            return null;
        }

        /**
         * Used to close the file after reading.
         */
        public void closeMyFile() {
            try {
                scn.close();
            } catch (Exception e) {
                MyLogger.writeMessage(getClass().getName()+" Problem in closing the file "+e.toString(), MyLogger.DebugLevel.ERROR);
                MyLogger.writeMessage(getClass().getName()+" can't close the file "+e.toString(), MyLogger.DebugLevel.FILEPROCESSOR);
                System.exit(0);
            }
        }
    }
