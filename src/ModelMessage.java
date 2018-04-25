/**
 * Created by rishabhkhanna on 22/03/18.
 */
public class ModelMessage {
    String messageName;
    String messageValue;

    public ModelMessage(String messageName, String messageValue) {
        this.messageName = messageName;
        this.messageValue = messageValue;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public String getMessageValue() {
        return messageValue;
    }

    public void setMessageValue(String messageValue) {
        this.messageValue = messageValue;
    }

//    Helper methods to construct message

}
