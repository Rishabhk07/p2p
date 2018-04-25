import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by rishabhkhanna on 24/04/18.
 */
class Messages{
    private static final char CHOKE = '0';
    private static final char UNCHOKE = '1';
    private static final char INTERESTED = '2';
    private static final char NOT_INTERESTED = '3';
    private static final char HAVE = '4';
    private static final char BITFIELD = '5';
    private static final char REQUEST = '6';
    private static final char PIECE = '7';



    public byte[] makeMessage(int len, char type, byte[] payload){
        byte[] message;
        byte[] length;
        byte msgType = (byte)type;
        int counter;

        switch(type){
            case CHOKE:

            case UNCHOKE:
            case INTERESTED:
            case NOT_INTERESTED:
                message = new byte[len + 4];
                length = ByteBuffer.allocate(4).putInt(len).array();
                counter = 0;
                for(byte x : length) {
                    message[counter] = x;
                    counter++;
                }
                message[counter] = msgType;
                break;
            case HAVE:
            case BITFIELD:
            case REQUEST:
            case PIECE:
                message = new byte[len + 4];
                length = ByteBuffer.allocate(4).putInt(len).array();
                counter = 0;
                for(byte x : length) {
                    message[counter] = x;
                    counter++;
                }
                message[counter++] = msgType;
                for(byte x : payload) {
                    message[counter] = x;
                    counter++;
                }
                break;
            default:
                message = new byte[0];
                System.out.println("ERROR in Message: " + type);
        }
        return message;
    }

    public byte[] getChokeMessage(){
        return makeMessage(1, CHOKE, null);
    }

    public byte[] getUnchokeMessage(){
        return makeMessage(1, UNCHOKE, null);
    }

    public byte[] getInterestedMessage(){
        return makeMessage(1, INTERESTED, null);
    }

    public byte[] getNotInterestedMessage(){
        return makeMessage(1, NOT_INTERESTED, null);
    }

    public byte[] getHaveMessage(int pieceIndex){
        byte[] payload = ByteBuffer.allocate(4).putInt(pieceIndex).array();
        return makeMessage(5, HAVE, payload);
    }

    public byte[] getBitfieldMessage(int[] bitfield){
        int len = 1 + (4 * bitfield.length);
        byte[] payload = new byte[len - 1];
        int counter = 0;
        for(int bit : bitfield){
            byte[] bitBytes = ByteBuffer.allocate(4).putInt(bit).array();
            for(byte b : bitBytes){
                payload[counter] = b;
                counter++;
            }
        }
        return makeMessage(len, BITFIELD, payload);
    }

    public byte[] getRequestMessage(int pieceIndex){
        byte[] payload = ByteBuffer.allocate(4).putInt(pieceIndex).array();
        return makeMessage(5, REQUEST, payload);
    }

    public byte[] getPieceMessage(int pieceIndex, byte[] piece){
        byte[] payload = new byte[4 + piece.length];
        int counter = 0;
        byte[] indexBytes = ByteBuffer.allocate(4).putInt(pieceIndex).array();
        for(byte bit : indexBytes){
            payload[counter] = bit;
            counter++;
        }
        for(byte bit : piece){
            payload[counter] = bit;
            counter++;
        }
        return makeMessage((5 + piece.length), PIECE, payload);
    }

    public byte[] buildMessage(ModelMessage thisMessage, String payload) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(thisMessage.getMessageName().getBytes());
        outputStream.write(thisMessage.getMessageValue().getBytes());
        if(payload != null) {
            outputStream.write(payload.getBytes());
        } else{
            outputStream.write("0000".getBytes());
        }

        return outputStream.toByteArray();
    }

    public byte[] getHandshakeMessage(int peerID){
        byte[] message = new byte[32];
        byte[] header = new String("P2PFILESHARINGPROJ").getBytes();
        byte[] zerobits = new String("0000000000").getBytes();
        byte[] id = ByteBuffer.allocate(4).putInt(peerID).array();
        int counter = 0;
        for(byte b : header){
            message[counter] = b;
            counter++;
        }
        for(byte b : zerobits){
            message[counter] = b;
            counter++;
        }
        for(byte b : id){
            message[counter] = b;
            counter++;
        }
        return message;
    }


    public byte[] getChoke() throws IOException {
        ModelMessage choke = new ModelMessage("CHOKE", "1");
        return buildMessage(choke,null);
    }
    public byte[] getUnChoke() throws IOException {
        ModelMessage choke = new ModelMessage("UNCHOKE", "2");
        return buildMessage(choke,null);
    }
    public byte[] getInterested() throws IOException {
        ModelMessage choke = new ModelMessage("INTERESTED", "3");
        return buildMessage(choke,null);
    }
    public byte[] getNotInterested() throws IOException {
        ModelMessage choke = new ModelMessage("NOT_INTERESTED", "4");
        return buildMessage(choke,null);
    }
    public byte[] getHave() throws IOException {
        ModelMessage choke = new ModelMessage("Have", "5");
        return buildMessage(choke,"indexfield");
    }
    public byte[] getRequest() throws IOException {
        ModelMessage choke = new ModelMessage("REQUEST", "6");
        return buildMessage(choke,"indexfield");
    }
    public byte[] getBitfield() throws IOException {
        ModelMessage choke = new ModelMessage("BITFIELD", "5");
        return buildMessage(choke,"indexfield");
    }
    public byte[] getPeice() throws IOException {
        ModelMessage choke = new ModelMessage("PIECE", "7");
        return buildMessage(choke,"indexfield");
    }



}
