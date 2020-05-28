package seedu.fractal.storage;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Formatter;
import java.util.stream.Collectors;

public class CryptoUtil {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String HMAC = "HmacSHA512";

    private static final String NEWLINE = "\n";

    private String secretKey;

    /**
     * Constructor for the Cryptographic Utility class.
     *
     * @param secretKey
     *  The secret key for encrypting and decrypting the text
     */
    public CryptoUtil(String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * Encrypts a given plaintext and saves the encrypted content into a file.
     *
     * @param plaintext
     *  The plaintext to be encrypted
     * @param filePath
     *  The path to the file
     * @throws Exception
     *  If there is an error in the encryption or saving of file
     */
    public void encryptToFile(String plaintext, String filePath) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            SecretKey aesSecretKey = generateSecretKey(secretKey.toCharArray(), "abc123".getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, aesSecretKey);

            byte[] iV = cipher.getIV();

            FileOutputStream fileOutput = new FileOutputStream(filePath);
            CipherOutputStream cipherOutput = new CipherOutputStream(fileOutput, cipher);

            fileOutput.write(iV);
            cipherOutput.write(plaintext.getBytes());

            cipherOutput.close();
            fileOutput.close();
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            System.out.println(e.getMessage());
            System.out.println("CryptoUtil: Error encrypting plaintext.");
            throw new Exception("CryptoUtil: Error encrypting plaintext.");
        } catch (FileNotFoundException e) {
            System.out.println("CryptoUtil: File is not found.");
            throw new Exception("CryptoUtil: File is not found.");
        } catch (IOException e) {
            System.out.println("CryptoUtil: Error writing to file.");
            throw new Exception("CryptoUtil: Error writing to file.");
        }
    }

    /**
     * Decrypts the encrypted content from a file into its corresponding plaintext.
     *
     * @param filePath
     *  The path to the file
     * @return
     *  The corresponding plaintext of the encrypted file
     * @throws Exception
     *  If there is an error in retrieving or decrypting the encrypted file
     */
    public String decryptFromFile(String filePath) throws Exception {
        try {
            FileInputStream fileInput = new FileInputStream(filePath);
            byte[] iV = new byte[16];
            fileInput.read(iV);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            SecretKey aesSecretKey = generateSecretKey(secretKey.toCharArray(), "abc123".getBytes());
            cipher.init(Cipher.DECRYPT_MODE, aesSecretKey, new IvParameterSpec(iV));

            CipherInputStream cipherInput = new CipherInputStream(fileInput, cipher);
            InputStreamReader inputReader = new InputStreamReader(cipherInput);
            BufferedReader bufferedReader = new BufferedReader(inputReader);

            String plaintext = bufferedReader.lines().collect(Collectors.joining(NEWLINE));

            bufferedReader.close();
            inputReader.close();
            cipherInput.close();

            return plaintext;
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                InvalidAlgorithmParameterException e) {
            System.out.println("CryptoUtil: Error decrypting encrypted text.");
            throw new Exception("CryptoUtil: Error decrypting encrypted text.");
        } catch (FileNotFoundException e) {
            System.out.println("CryptoUtil: File is not found.");
            throw new Exception("CryptoUtil: File is not found.");
        } catch (IOException e) {
            System.out.println("CryptoUtil: Error reading from file.");
            throw new Exception("CryptoUtil: Error reading from file.");
        }
    }

    /**
     * Encrypts the plaintext via a rather bad encryption scheme.
     *
     * @param plaintext
     *  The plaintext to be encrypted
     * @return
     *  The encrypted data
     */
    public String encrypt(String plaintext) {
        String encryptedData = plaintext;

        for (int i = 0; i < 5; ++i) {
            encryptedData = Base64.getEncoder().encodeToString(encryptedData.getBytes());
        }

        return encryptedData;
    }

    public String decrypt(String encryptedData) {
        byte[] plaintext = encryptedData.getBytes();

        for (int i = 0; i < 5; ++i) {
            plaintext = Base64.getDecoder().decode(plaintext);
        }

        return new String(plaintext);
    }

    /**
     * Generates the hash digest of the given plaintext.
     *
     * @param plaintext
     *  The plaintext to be hashed
     * @return
     *  The corresponding hash digest of the plaintext
     * @throws Exception
     *  If there is an error in computing the hash
     */
    public String generateHash(String plaintext) throws Exception {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), HMAC);
            Mac hMac = Mac.getInstance(HMAC);
            hMac.init(secretKeySpec);

            byte[] hMacData = hMac.doFinal(plaintext.getBytes());

            return Base64.getEncoder().encodeToString(hMacData);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            System.out.println("CryptoUtil: Unable to generate hash.");
            throw new Exception("CryptoUtil: Unable to generate hash.");
        }
    }

    /**
     * Checks if the content is corrupted by comparing its hash with the given hash.
     *
     * @param plaintext
     *  The content to be checked
     * @param hash
     *  The supposed hash of the content
     * @return
     *  True if the content is NOT corrupted, or False otherwise
     * @throws Exception
     *  If there is an error in computing the hash
     */
    public boolean isNotCorrupted(String plaintext, String hash) throws Exception {
        return generateHash(plaintext).equals(hash);
    }

    private SecretKey generateSecretKey(char[] key, byte[] salt) throws Exception {
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec keySpec = new PBEKeySpec(key, salt, 65536, 256);

            return new SecretKeySpec(secretKeyFactory.generateSecret(keySpec).getEncoded(), ALGORITHM);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.out.println(e.getMessage());
            throw new Exception("CryptoUtil: Error generating secret key.");
        }
    }

    /**
     * Converts a byte array into it corresponding hex string.
     *
     * @param bytes
     *  The array of bytes to be converted
     * @return
     *  The corresponding hex string represented by the byte array
     */
    private static String bytesToHex(byte[] bytes) {
        Formatter formatter = new Formatter();

        for (byte oneByte : bytes) {
            formatter.format("%02x", oneByte);
        }

        return formatter.toString();
    }
}
