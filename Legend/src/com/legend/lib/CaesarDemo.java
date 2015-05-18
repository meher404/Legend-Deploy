package com.legend.lib;
class CaesarCipher {
     public String encrypt(String plainText,int shiftKey)
     {
          // plainText = plainText.toLowerCase();
           String cipherText="";
           for(int i=0;i<plainText.length();i++)
           {
                int charPosition = plainText.charAt(i);
                int keyVal = (shiftKey+charPosition);
                char replaceVal =(char)(keyVal);
                cipherText += replaceVal;
           }
           return cipherText;
     }
     public String decrypt(String cipherText, int shiftKey)
     {
      //     cipherText = cipherText.toLowerCase();
           String plainText="";
           for(int i=0;i<cipherText.length();i++)
           {
                int charPosition = cipherText.charAt(i);
                int keyVal = (charPosition-shiftKey);
            /*    if(keyVal<0)
                {
                      keyVal = this.ALPHABET.length() + keyVal;
                }*/
                char replaceVal =(char)(keyVal);
                plainText += replaceVal;
           }
           return plainText;
     }
}
 
class CaesarDemo {
     public static void main(String args[])
     {
           String plainText = "!@#$%^&*()Nikhitha02NIJKHIT";
           int shiftKey=2;
          
           CaesarCipher cc = new CaesarCipher();
          
           String cipherText = cc.encrypt(plainText,shiftKey);
           System.out.println("Your Plain  Text :" + plainText);
           System.out.println("Your Cipher Text :" + cipherText);
          
           String cPlainText = cc.decrypt(cipherText,shiftKey);
           System.out.println("Your Plain Text  :" + cPlainText);
     }
}