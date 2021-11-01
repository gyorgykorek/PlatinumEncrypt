package aestest;

public class MyDecrypt {

    public static String decrypt(String cip, int offset)
    {


        char[] rechange = cip.toCharArray();
        for (int i = 0; i < rechange.length; i++) {

            for (int j = 0; j < AEStest.characters_set.length; j++) {
                if (j >= offset && rechange[i] == AEStest.characters_set[j]) {
                    rechange[i] = AEStest.characters_set[j - offset];
                    break;
                }
                if (rechange[i] == AEStest.characters_set[j] && j < offset) {
                    rechange[i] = AEStest.characters_set[(AEStest.characters_set.length - offset +1) + j];
                    break;
                }
            }
        }


        return String.valueOf(rechange);

    }
}
