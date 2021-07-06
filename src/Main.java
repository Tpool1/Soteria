import java.util.*;

public class Main {
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 5;

    public static void main(String[] args) {
        // make genesis block
        Block b = new Block(0, null, "genesis");
        b.mineBlock(difficulty);
        blockchain.add(b);
        System.out.println(b.toString());

        // make second block
        Block b2 = new Block(1, b.currentHash, "second block");
        b2.mineBlock(difficulty);
        blockchain.add(b2);
        System.out.println(b2.toString());
    } 
}
