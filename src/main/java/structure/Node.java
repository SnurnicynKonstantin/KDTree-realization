package structure;

public class Node {

    private Geo geo;
    private KDTreeRectangle rect;
    private Node leftNode;
    private Node rightNode;

    public Node(Geo geo, KDTreeRectangle rect, Node leftNode, Node rightNode) {
        this.geo = geo;
        this.rect = rect;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public KDTreeRectangle getRect() {
        return rect;
    }

    public Geo getGeo() {
        return geo;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }
}
