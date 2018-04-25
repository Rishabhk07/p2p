import java.io.BufferedWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rishabhkhanna on 24/04/18.
 */
class Logs{
    private DateFormat timeFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private Date time = new Date();
    private BufferedWriter writer;

    private int peerID;
    private String hostName;
    private int portNumber;
    private int haveFile;
    private int[] bitfield;
    private int numOfPieces = 0;

    public void printBitfield(){
        for(int bit : bitfield)
            System.out.print(bit);
    }

    public int getNumOfPieces() {
        return numOfPieces;
    }

    public void updateNumOfPieces() {
        this.numOfPieces++;
        if(this.numOfPieces == bitfield.length)
            this.haveFile = 1;
    }


//


    public int getPeerID() {
        return peerID;
    }

    public void setPeerID(int peerID) {
        this.peerID = peerID;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public int getHaveFile() {
        return haveFile;
    }

    public void setHaveFile(int haveFile) {
        this.haveFile = haveFile;
    }

    public int[] getBitfield() {
        return bitfield;
    }

    public void setBitfield(int[] bitfield) {
        this.bitfield = bitfield;
    }

    public void updateBitfield(int index){
        bitfield[index] = 1;
    }



    public Logs(BufferedWriter writer){
        timeFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.writer = writer;
    }

    public void connectionTo(int id1,int id2){
        time = new Date();
        StringBuffer log = new StringBuffer();
        log.append(timeFormat.format(time));
        log.append(':');
        log.append(" Peer ");
        log.append(id1);
        log.append(" makes a connection to Peer ");
        log.append(id2);
        log.append('.');
        try{
            writer.write(log.toString());
            writer.newLine();
            writer.flush();
        }
        catch(Exception e){
//            e.printStackTrace();
        }
    }

    public void connectionFrom(int id1, int id2){
        time = new Date();
        StringBuffer log = new StringBuffer();
        log.append(timeFormat.format(time));
        log.append(": Peer");
        log.append(id1);
        log.append(" is connected from Peer ");
        log.append(id2);
        log.append('.');
        try{
            writer.write(log.toString());
            writer.newLine();
            writer.flush();
        }
        catch(Exception e){
//            e.printStackTrace();
        }
    }



    public void changePreferredNeighbors(int id1, int[] ids){
        time = new Date();
        StringBuffer log = new StringBuffer();
        log.append(timeFormat.format(time));
        log.append(": Peer");
        log.append(id1);
        log.append(" has the preferred neighbors ");
        for(int id : ids){
            log.append(id);
            log.append(',');
        }
        log.deleteCharAt(log.length() - 1);
        log.append('.');
        try{
            writer.write(log.toString());
            writer.newLine();
            writer.flush();
        }
        catch(Exception e){
//            e.printStackTrace();
        }
    }

    public void changeOptimisticallyUnchokedNeighbor(int id1, int id2){
        time = new Date();
        StringBuffer log = new StringBuffer();
        log.append(timeFormat.format(time));
        log.append(": Peer ");
        log.append(id1);
        log.append(" has the optimistically unchoked neighbor ");
        log.append(id2);
        log.append('.');
        try{
            writer.write(log.toString());
            writer.newLine();
            writer.flush();
        }
        catch(Exception e){
//            e.printStackTrace();
        }
    }

    public void unchoked(int id1, int id2){
        time = new Date();
        StringBuffer log = new StringBuffer();
        log.append(timeFormat.format(time));
        log.append(':');
        log.append(" Peer ");
        log.append(id1);
        log.append(" is unchoked by ");
        log.append(id2);
        log.append('.');
        try{
            writer.write(log.toString());
            writer.newLine();
            writer.flush();
        }
        catch(Exception e){
//            e.printStackTrace();
        }
    }

    public void choked(int id1, int id2){
        time = new Date();
        StringBuffer log = new StringBuffer();
        log.append(timeFormat.format(time));
        log.append(':');
        log.append(" Peer ");
        log.append(id1);
        log.append(" is choked by ");
        log.append(id2);
        log.append('.');
        try{
            writer.write(log.toString());
            writer.newLine();
            writer.flush();
        }
        catch(Exception e){
//            e.printStackTrace();
        }
    }

    public void receiveHave(int id1, int id2, int index){
        time = new Date();
        StringBuffer log = new StringBuffer();
        log.append(timeFormat.format(time));
        log.append(':');
        log.append(" Peer ");
        log.append(id1);
        log.append(" received the 'have' message from ");
        log.append(id2);
        log.append(" for the piece ");
        log.append(index);
        log.append('.');
        try{
            writer.write(log.toString());
            writer.newLine();
            writer.flush();
        }
        catch(Exception e){
//            e.printStackTrace();
        }
    }

    public void receiveInterested(int id1, int id2){
        time = new Date();
        StringBuffer log = new StringBuffer();
        log.append(timeFormat.format(time));
        log.append(':');
        log.append(" Peer ");
        log.append(id1);
        log.append(" received the 'interested' message from ");
        log.append(id2);
        log.append('.');
        try{
            writer.write(log.toString());
            writer.newLine();
            writer.flush();
        }
        catch(Exception e){
//            e.printStackTrace();
        }
    }

    public void receiveNotInterested(int id1, int id2){
        time = new Date();
        StringBuffer log = new StringBuffer();
        log.append(timeFormat.format(time));
        log.append(':');
        log.append(" Peer ");
        log.append(id1);
        log.append(" received the 'not interested' message from ");
        log.append(id2);
        log.append('.');
        try{
            writer.write(log.toString());
            writer.newLine();
            writer.flush();
        }
        catch(Exception e){
//            e.printStackTrace();
        }
    }

    public void downloadingPiece(int id1, int id2, int index, int numOfPieces){
        time = new Date();
        StringBuffer log = new StringBuffer();
        log.append(timeFormat.format(time));
        log.append(':');
        log.append(" Peer ");
        log.append(id1);
        log.append(" has downloaded the piece ");
        log.append(index);
        log.append(" from ");
        log.append(id2);
        log.append(".\n");
        log.append("Now the number of pieces it has is ");
        log.append(numOfPieces);
        log.append('.');
        try{
            writer.write(log.toString());
            writer.newLine();
            writer.flush();
        }
        catch(Exception e){
//            e.printStackTrace();
        }
    }

    public void downloadCompleted(int id1){
        time = new Date();
        StringBuffer log = new StringBuffer();
        log.append(timeFormat.format(time));
        log.append(':');
        log.append(" Peer ");
        log.append(id1);
        log.append(" has downloaded the complete file ");
        try{
            writer.write(log.toString());
            writer.newLine();
            writer.flush();
        }
        catch(Exception e){
//            e.printStackTrace();
        }
    }

}