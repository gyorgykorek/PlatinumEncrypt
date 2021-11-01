
package aestest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/*
Please read before use:

 */

public class AEStest {
    public static char[] characters_set = {
            '§','°','a','á', 'b', 'c', 'd', 'e','é', 'f', 'g', 'h',
            'i','í', 'j', 'k', 'l', 'm', 'n', 'o','ó','ö','ő', 'p',
            'q', 'r', 's', 't', 'u','ú','˙','ü','ű', 'v', 'w', 'x',
            'y', 'z', '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z', '!', '@',
            '#', '$', '%', '^', '&', '(', ')', '+', '¨',
            '-', '*', '/', '[', ']', '{', '}', '=', 'ł', '×',
            '<', '>', '?', '_', '"', '.', ',', 'ß','¤','Đ','đ','Ł','ä',' '
    };

    public static String encrypted;
    private static AEStest Crypto;
    private static String choice;
    static String text;
    static String s;
    static String textbackup;
    static byte[] outputBytes;
    static File f = new File("text.txt");
    public static ArrayList<String> lista= new ArrayList<>();

    static void fileProcessor(int cipherMode, String key, File inputFile, File outputFile){
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(cipherMode, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        //Choice
        System.out.println("_______________________________\nPlatinum encryption and decryption algorithm (file: text.txt)\nv1.0.0 Coded: György Korek\n_______________________________");
        System.out.println("1. encrypt\t2.decrypt\t3.quit");
        System.out.print("Input:");
        Scanner input = new Scanner(System.in);
        choice = input.next();
        //Key randomize
        Random rnd = new Random();
        int keyrandom = rnd.nextInt(99999999-10000000)+10000000;
        int keyrandom2 = rnd.nextInt(99999999-10000000)+10000000;
        String key = keyrandom2+""+keyrandom;
        //Files
        File inputFile = new File("text.txt");
        File encryptedFile = new File("encrypt.PLATINUMencrypt");
        File keyencrypted = new File("key.PLATINUMencrypt");
        File decryptedFile = new File("decrypted.PLATINUMencrypt");

        try {
            if (choice.equals("1")){
                //File beolvasás
                MyRead scan = new MyRead();
                scan.textread(f);

                //Common encrypt my algorithm
                MyEncrypt enc = new MyEncrypt();
                encrypted = enc.encrypt(text,6);
                System.out.println("The decrypt key: "+key);
                System.out.println("\nOriginal: " + textbackup);
                //text file manipulated with my encrypt
                FileOutputStream mytext = new FileOutputStream(inputFile, false);
                OutputStreamWriter myos = new OutputStreamWriter(mytext, "UTF-8");
                myos.write(encrypted);
                myos.flush();
                myos.close();

                System.out.println("\nMyEncrypt: " + encrypted);
                //AES encrypt in action
            Crypto.fileProcessor(Cipher.ENCRYPT_MODE,key,inputFile,encryptedFile);
            //Write key
            FileOutputStream os = new FileOutputStream(keyencrypted, false);
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(key);
            osw.flush();
            osw.close();

            //Text file restored
                FileOutputStream backup = new FileOutputStream(inputFile, false);
                OutputStreamWriter b = new OutputStreamWriter(backup, "UTF-8");
                b.write(text);
                b.flush();
                b.close();
                Base64.getEncoder().encodeToString(outputBytes);
                s = Base64.getEncoder().encodeToString(outputBytes);
                System.out.println("\nMy Algorithm with randomized AES encrypt:");
                System.out.println((s));
                System.out.println("\nPlatinum encryption sucess!");

            }
            else if (choice.equals("2")) {
                Scanner keyscan = new Scanner(keyencrypted,"UTF-8");
                key = keyscan.nextLine();
                keyscan.close();
                Crypto.fileProcessor(Cipher.DECRYPT_MODE, key, encryptedFile, decryptedFile);
                Scanner scan = new Scanner(decryptedFile,"UTF-8");
                AEStest.text = scan.nextLine();
                scan.close();
                MyDecrypt dec = new MyDecrypt();
                encrypted = dec.decrypt(text,6);//System.out.println(encrypted);
                Files.deleteIfExists(Paths.get("decrypted.PLATINUMencrypt"));
                System.out.println("\nPlatinum decryption sucess!\n\nDecrypted data: "+encrypted);

            }
            else if(choice.equals("3")){
                System.out.println("Program terminated!");
                System.exit(3);
            }
            else {
                System.out.println("Wrong input!(1/2/3)");
            }
            System.out.println("\n-Press ENTER to exit!-");
            Scanner exit = new Scanner(System.in);
            String ex = exit.nextLine();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("FAILED PLEASE TRY AGAIN\n-Press ENTER to exit!-");
            Scanner exit = new Scanner(System.in);
            String e = exit.nextLine();
        }
    }

}
