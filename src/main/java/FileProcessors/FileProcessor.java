package FileProcessors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.String;

import static Driver.App.logger;

public class FileProcessor {

        private String filename;
        private BufferedReader scn;
        /**
         * Constructor, used for opening the file and initializing buffers.
         * @param fname - name of the input file.
         */
        public FileProcessor(String filename) {
            try {
                this.filename = filename;
                File file = new File(filename);
                scn = new BufferedReader(new FileReader(file));
                logger.info("opened the file");
            } catch (Exception e) {
                logger.error(getClass().getName()+" can't open the file "+e.toString());
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
                logger.error(getClass().getName()+" can't read the file "+e.toString());
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
                logger.error(getClass().getName()+" Problem in closing the file "+e.toString());
                System.exit(0);
            }
        }
    }
