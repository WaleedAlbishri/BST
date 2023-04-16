// Name: Waleed Albishri
// Section: XA (40202)
// Email: wwalbishri@stu.kau.edu.sa
package BST;

import java.io.PrintWriter;

public class GameTree {
    // BST METHODS
    private PlayerNode root;

    public GameTree() {
        root = null;
    }

    public void add_player(int playerID, String Playername) {
        root = add_player(root, playerID, Playername);
    }

    private PlayerNode add_player(PlayerNode root, int playerID, String Playername) {
        if (root == null) {
            root = new PlayerNode(playerID, Playername);
            return root;
        } else {
            if (root.getPlayerID() > playerID) {
                root.setLeft(add_player(root.getLeft(), playerID, Playername));
            } else {
                root.setRight(add_player(root.getRight(), playerID, Playername));
            }
        }
        return root;
    }

    public void delete_player(int playerID) {
        root = delete_player(root, playerID);
    }

    private PlayerNode delete_player(PlayerNode p, int id) {
        PlayerNode r, d, parent;
        int saveID, saveStamina;
        String saveName;

        r = findNode(p, id);

        if (r == null) {
            return root;
        }

        parent = parent(p, r);

        if (isLeaf(r)) {

            if (parent == null) {
                return null;
            }

            if (id < parent.getPlayerID()) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }

            return p;
        }

        if (hasOnlyLeftChild(r)) {

            if (parent == null) {
                return r.getLeft();
            }

            if (id < parent.getPlayerID()) {
                parent.setLeft(parent.getLeft().getLeft());
            } else {
                parent.setRight(parent.getRight().getLeft());
            }

            return p;
        }

        if (hasOnlyRightChild(r)) {

            if (parent == null) {
                return r.getRight();
            }

            if (id < parent.getPlayerID()) {
                parent.setLeft(parent.getLeft().getRight());
            } else {
                parent.setRight(parent.getRight().getRight());
            }
            return p;
        }

        d = maxNode(r.getLeft());
        saveID = d.getPlayerID();
        saveName = d.getPlayername();
        saveStamina = d.getStamina();
        p = delete_player(p, saveID);
        r.setPlayerID(saveID);
        r.setPlayername(saveName);
        r.setStamina(saveStamina);
        return p;
    }

    public void displayall(PrintWriter write) {
        displayall(root, write);
    }

    private void displayall(PlayerNode root, PrintWriter write) {
        if (root != null) {
            write.println(root.toString());
            displayall(root.getLeft(), write);
            displayall(root.getRight(), write);
        }
    }

    public int numplayers() {
        return numplayers(root);
    }

    private int numplayers(PlayerNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + numplayers(root.getLeft()) + numplayers(root.getRight());
        }
    }

    public PlayerNode displayplayerinfo(int id) {
        return search(id, root);
    }

    private PlayerNode search(int id, PlayerNode root) {
        PlayerNode now = root;
        while (now.getPlayerID() != id) {
            if (id < now.getPlayerID()) {
                now = now.getLeft();
            } else {
                now = now.getRight();
            }
            if (now == null) {
                return null;
            }
        }
        return now;
    }

    public void update(int id) {
        root = update(id, root);
    }

    private PlayerNode update(int id, PlayerNode root) {
        PlayerNode a = search(id, root);
        a.setStamina(a.getStamina() - 5);
        return root;
    }

    public PlayerNode maxNode(PlayerNode root) {
        if (root == null) {
            return null;
        } else {
            if (root.getRight() == null) {
                return root;

            } else {
                return maxNode(root.getRight());

            }
        }
    }

    public PlayerNode findNode(int data) {
        return findNode(root, data);
    }

    private PlayerNode findNode(PlayerNode p, int data) {
        if (p == null) {
            return null;
        } else {
            if (data == p.getPlayerID()) {
                return p;
            } else if (data < p.getPlayerID()) {
                return findNode(p.getLeft(), data);
            } else {
                return findNode(p.getRight(), data);
            }
        }
    }

    public PlayerNode parent(PlayerNode p) {
        return parent(root, p);
    }

    private PlayerNode parent(PlayerNode root, PlayerNode p) {

        if (root == null || root == p) {
            return null;
        }

        if (root.getLeft() == p || root.getRight() == p) {
            return root;
        }

        if (p.getPlayerID() < root.getPlayerID()) {
            return parent(root.getLeft(), p);
        } else if (p.getPlayerID() > root.getPlayerID()) {
            return parent(root.getRight(), p);
        } else {
            return null;
        }
    }

    public Boolean isLeaf(PlayerNode p) {
        return (p.getRight()== null && p.getLeft() == null);
    }

    public Boolean hasOnlyLeftChild(PlayerNode p) {
        return (p.getRight()!= null && p.getLeft()== null);
    }

    public Boolean hasOnlyRightChild(PlayerNode p) {
        return (p.getRight()== null && p.getLeft()!= null);
    }
}
