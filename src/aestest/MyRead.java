package aestest;

import java.io.File;
import java.util.Scanner;

public class MyRead {

    public static void textread(File f){
        try {
            //Read common file
            Scanner scan = new Scanner(f,"UTF-8");
            while(scan.hasNext()){
                AEStest.text = scan.nextLine();
                String[]adatok = AEStest.text.split("\n");
                AEStest.lista.add(AEStest.text);

            }
            scan.close();
        } catch (Exception e) {
            System.out.println("Error in textread:" + e.getMessage());
        }
        //ArrayList to simple String
        AEStest.text = String.join(" ", AEStest.lista);
        AEStest.textbackup = AEStest.text;
        //System.out.println("\nText scanned successfuly!");
    }
}

