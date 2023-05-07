import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.swing.*;

public class Criptografia {

	public static void main(String[] args) {
		KeyGenerator keyGenerator;
		SecretKey secretKey;
		Cipher cipher;
		
		String senha = JOptionPane.showInputDialog(null, "Digite o que deseja criptografar");
		
		JOptionPane.showMessageDialog(null, "Mensagem a ser criptografada: " + senha);
		
		int op = Integer.parseInt (JOptionPane.showInputDialog(null, "Escolha o algoritmo criptográfico simétrico desejado: \n" + "1 - Algoritmo AES\n" + "2 - Algoritmo DES\n" + "3 - Algoritmo DESede\n"));
		
		try {
			
			switch (op) {
			
				case 1 :
					
					keyGenerator = KeyGenerator.getInstance ("AES");
					secretKey = keyGenerator. generateKey () ;
					cipher = Cipher.getInstance("AES");
		
					algorithmImplementation (secretKey, cipher, senha);
					break;
					
				case 2 :
		
					keyGenerator = KeyGenerator.getInstance("DES");
					secretKey = keyGenerator. generateKey();
					cipher = Cipher.getInstance("DES");
		
					algorithmImplementation(secretKey, cipher, senha);
					break;
		
				case 3 :
		
					keyGenerator = KeyGenerator.getInstance("DESede");
					secretKey = keyGenerator.generateKey();
					cipher = Cipher.getInstance("DESede");
					
					algorithmImplementation(secretKey, cipher, senha);
					break;
		
				default:
					JOptionPane.showMessageDialog(null,"Opção Inválida");
					break;
			}
		
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
		
			e.printStackTrace();
		}
		
	}
	
	private static void algorithmImplementation(SecretKey secretKey, Cipher cipher, String senha) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] criptografia;
		byte[] descriptografia;
		
		//Encriptação
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		criptografia = cipher.doFinal(senha.getBytes());
		JOptionPane.showMessageDialog(null, "Mensagem criptografada: "+ criptografia);
		
		//Decriptação
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		descriptografia = cipher.doFinal(criptografia);
		JOptionPane.showMessageDialog(null, "Mensagem Descriptografada: " + new String(descriptografia));
	}
}
