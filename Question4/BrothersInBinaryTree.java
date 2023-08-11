package Question4;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class BrothersInBinaryTree {
    private int xDepth;
    private int yDepth;
    private TreeNode xParent;
    private TreeNode yParent;

    public boolean areBrothers(TreeNode root, int x, int y) {
        // Initialize the depth and parent variables
        xDepth = -1;
        yDepth = -1;
        xParent = null;
        yParent = null;

        // Perform DFS to find the depths and parents of nodes x and y
        dfs(root, null, x, y, 0);

        // Check if the nodes have the same depth and different parents
        return xDepth == yDepth && xParent != yParent;
    }

    private void dfs(TreeNode node, TreeNode parent, int x, int y, int depth) {
        if (node == null) {
            return;
        }

        if (node.val == x) {
            xDepth = depth;
            xParent = parent;
        } else if (node.val == y) {
            yDepth = depth;
            yParent = parent;
        }

        // Continue DFS for the left and right subtrees
        dfs(node.left, node, x, y, depth + 1);
        dfs(node.right, node, x, y, depth + 1);
    }

    public static void main(String[] args) {
        // Example input
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);

        int x = 4;
        int y = 3;

        // Create an instance of BrothersInBinaryTree
        BrothersInBinaryTree binaryTree = new BrothersInBinaryTree();

        // Call the areBrothers() function to check if nodes x and y are brothers
        boolean result = binaryTree.areBrothers(root, x, y);
        System.out.println(result);
    }
}
