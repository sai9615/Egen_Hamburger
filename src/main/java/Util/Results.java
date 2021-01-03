package Util;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;

import static Driver.App.logger;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
        private ArrayList<String> result = new ArrayList();
        private FileWriter fw;
        private BufferedWriter bw;

        /**
         * Constructor, used for creating output file if it doesn't exsist and initializing buffers.
         * @param outfname - name of output file.
         */
        public Results(String outfname) {
            try {
                File file = new File(outfname);
                if (!file.exists()) {
                    file.createNewFile();
                }
                fw = new FileWriter(file.getAbsoluteFile());
                bw = new BufferedWriter(fw);
            } catch (Exception e) {
                logger.error(getClass().getName()+" Can't write to file "+e.toString());
                System.exit(0);
            }
        }

        /**
         * Used to display result on stdout.
         * @param s - a string.
         */
        public void writeToStdout(String s){
            System.out.println(s);
        }

        /**
         * Used to write result to file.
         * @param s - a string.
         */
        public void writeToFile(String s) {
            try{
                bw.write(s);
            }
            catch (Exception e){
                logger.error(getClass().getName()+" error while writing to file "+e.toString());
                System.exit(0);
            }
        }

        /**
         * Calls write to file to write all the results into the file.
         */
        public void writeResults(){
            try {
                for (String temp : result){
                    //    System.out.println(temp);
                    writeToFile(temp);
                }
            } catch (Exception e){
                logger.error(getClass().getName()+" couldn't write results to the file "+e.toString());
            }
        }

        /**
         * Used to store final result in a result array.
         * @param value - final result as a string.
         */

        public void storeNewResult(String value){
            // System.out.println("stored the result : "+value);
            value = value + "\n";
            result.add(value);
        }

        /**
         * Used to close the file after writing all the results to the file.
         */
        public void closeMyFile() {
            try {
                bw.close();
            } catch (Exception e) {
                logger.error(getClass().getName()+" Problem in closing the file "+e.toString());
                System.exit(0);
            }
        }
    }
