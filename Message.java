
package za.ac.tut.message;

/**
 *
 * @author Osego
 */
public class Message {
    
    protected String message;

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString(){
       
        return "Message: "+message;
    }
    
   
}
