// Name: Waleed Albishri
// Section: XA (40202)
// Email: wwalbishri@stu.kau.edu.sa
package BST;

public class PlayerNode {
    //BST NODE
    private int playerID;
    private String Playername;
    private int stamina;
    private PlayerNode right;
    private PlayerNode left;

    public PlayerNode() {
        left = right = null;
    }

    public PlayerNode(int playerID, String Playername) {
        this(playerID,Playername,15,null,null);
    }

    public PlayerNode(int playerID, String Playername, int stamina, PlayerNode right, PlayerNode left) {
        this.playerID = playerID;
        this.Playername = Playername;
        this.stamina = stamina;
        this.right = right;
        this.left = left;
    }

    public int getPlayerID() {
        return playerID;
    }

    public String getPlayername() {
        return Playername;
    }

    public int getStamina() {
        return stamina;
    }

    public PlayerNode getRight() {
        return right;
    }

    public PlayerNode getLeft() {
        return left;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public void setPlayername(String Playername) {
        this.Playername = Playername;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public void setRight(PlayerNode right) {
        this.right = right;
    }

    public void setLeft(PlayerNode left) {
        this.left = left;
    }

    @Override
    public String toString() {
        return "\t "+playerID+"\t\t"+Playername+"\t\t"+stamina;
    }
    
    
    
}
