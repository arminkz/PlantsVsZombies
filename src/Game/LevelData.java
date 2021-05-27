package Game;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.logging.Logger;

/**
 * @author Vaibhav Singh Sikarwar
 */
public class LevelData {

    public static String LEVEL_NUMBER = "1";
    public static String[][] LEVEL_CONTENT = {{"zombie.model.NormalZombie"}, {"zombie.model.NormalZombie", "zombie.model.ConeHeadZombie"}};
    public static int[][][] LEVEL_VALUE = {{{0, 99}}, {{0, 49}, {50, 99}}};

    public LevelData() {
        try {
            File f = new File("LEVEL_CONTENT.vbhv");

            if (!f.exists()) {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(f));
                bufferedWriter.write("1");
                bufferedWriter.close();
                LEVEL_NUMBER = "1";
            } else {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
                LEVEL_NUMBER = bufferedReader.readLine();
            }
        } catch (Exception ex) {


        }
    }

    public static void write(String lvl) {
        File f = new File("LEVEL_CONTENT.vbhv");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(f));
            bufferedWriter.write(lvl);
            bufferedWriter.close();
            LEVEL_NUMBER = lvl;
        } catch (IOException ex) {
            Logger.getLogger(LevelData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }
}
