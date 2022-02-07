package encipher;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;


public class AESencryption {
	static String file ;
	AESencryption(String fileName)
	{
		file = fileName;
		try
		{
			KeyStore keyStore = KeyStore.getInstance("JCEKS");
			FileInputStream stream = new FileInputStream("mykeystore.jks");
			keyStore.load(stream , "***".toCharArray());
			Key key = keyStore.getKey("mykey", "***".toCharArray());
			
			String input =file;
			String output = "Output.txt";
			encryptedUsingAESKey(key.getEncoded() , input ,output);
			new MailGUI();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AESencryption("");

	} 

	
	private static void encryptedUsingAESKey(byte[] key , String input , String output) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
    InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException
	{
		SecretKeySpec secKey = new SecretKeySpec(key , "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secKey);
		
		FileInputStream inputStream = new FileInputStream(input);
        FileOutputStream outputStream = new FileOutputStream(output);
        byte[] buffer = new byte[64];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byte[] output1 = cipher.update(buffer, 0, bytesRead);
            if (output1 != null) {
                outputStream.write(output1);
            }
        }
        byte[] outputBytes = cipher.doFinal();
        if (outputBytes != null) {
            outputStream.write(outputBytes);
        }
        inputStream.close();
        outputStream.close();		
		System.out.println("Done");
	}
}