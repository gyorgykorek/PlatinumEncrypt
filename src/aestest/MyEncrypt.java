package aestest;

import java.lang.*;


public class MyEncrypt {

    static String encrypt(String text, int offset)
    {
        char[] enc_change = text.toCharArray();

        for (int i = 0; i < enc_change.length; i++) {
            for (int j = 0; j < AEStest.characters_set.length; j++) {
                if (j <= AEStest.characters_set.length - offset) {
                    if (enc_change[i] == AEStest.characters_set[j]) {
                        enc_change[i] = AEStest.characters_set[j + offset];
                        break;
                    }
                }
                else if (enc_change[i] == AEStest.characters_set[j]) {
                    enc_change[i] = AEStest.characters_set[j - (AEStest.characters_set.length - offset + 1)];
                }
            }
        }

        return String.valueOf(enc_change);
    }

}
