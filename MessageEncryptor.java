
package za.ac.tut.encryption;

import za.ac.tut.message.Message;

/**
 *
 * @author Osego
 */
public class MessageEncryptor extends Message{

    public MessageEncryptor(String message) {
        super(message);
    }
    
    public String encrypt() {
        
        String encryptMessage="";

        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);

            // Check if the character is a letter
            if (Character.isLetter(currentChar)) {
                char encryptedChar = (char)(currentChar + 3);
                encryptMessage += encryptedChar;
            } else {
                // If it's not a letter, append the character as it is
                encryptMessage += currentChar;
            }
        }

        return encryptMessage;

    }
    
}
