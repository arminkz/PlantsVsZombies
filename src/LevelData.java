/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.logging.Logger;
/**
 *
 * @author Vaibhav Singh Sikarwar
 */
public class LevelData {
    static String Lvl = "1";
   static String [][] Level = {{"NormalZombie"},{"NormalZombie","ConeHeadZombie"}};
   static int [][][] LevelValue = {{{0,99}},{{0,49},{50,99}}} ;
   public LevelData() {
       try {
           File f = new File("Level.vbhv");
           
           if(!f.exists()) {
               BufferedWriter bwr = new BufferedWriter(new FileWriter(f));
               bwr.write("1");
               bwr.close();
               Lvl = "1";
           } else {
               BufferedReader br = new BufferedReader(new FileReader(f));
               Lvl = br.readLine();
           }
       } catch (Exception ex) {
           
           
       }
   }
   public static void write(String lvl) {
       File f = new File("Level.vbhv");
        try {
            BufferedWriter bwr = new BufferedWriter(new FileWriter(f));
            bwr.write(lvl);
            bwr.close();
            Lvl = lvl;
        } catch (IOException ex) {
            Logger.getLogger(LevelData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
               
   }
}
