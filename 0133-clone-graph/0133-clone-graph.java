/*
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {

    // Original Node -> Cloned Node
    HashMap<Node, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {

        // Empty graph
        if (node == null)
            return null;

        // Already cloned
        if (map.containsKey(node))
            return map.get(node);

        // Create clone of current node
        Node clone = new Node(node.val);

        // Store it before visiting neighbors
        map.put(node, clone);

        // Clone all neighbors
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }

        return clone;
    }
}