package structure;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class KDTree {
    private Node root;
    private int max;
    private int min;

    public KDTree(int max, int min, Hashtable data) {
        this.max = max;
        this.min = min;
        if(!data.isEmpty()) {
            for (String key : (Set<String>) data.keySet()) {
                insert((Geo) data.get(key));
            }
        }
    }

    public void insert(Geo geo) {
        if (geo == null) throw new java.lang.NullPointerException(
                "called insert() with a null Geo");

        // new double[] {x_min, y_min, x_max, y_max)
        root = insert(root, geo, true, new KDTreeRectangle(min, min, max, max));
    }

    private Node insert(Node node, Geo geo, boolean evenLevel, KDTreeRectangle rect) {
        if (node == null) {
            return new Node(geo, rect, null, null);
        }

        double cmp = comparePoints(geo, node, evenLevel);
        KDTreeRectangle newRect;

        // Handle Nodes which should be inserted to the left
        if (cmp < 0 && evenLevel) {
            newRect = new KDTreeRectangle( // lessen x_max
                    rect.xmin(),
                    rect.ymin(),
                    node.getGeo().getLatitude(),
                    rect.ymax());
            node.setLeftNode(insert(node.getLeftNode(), geo, !evenLevel, newRect));
        }

        // Handle Nodes which should be inserted to the bottom
        else if (cmp < 0 && !evenLevel) {
            newRect = new KDTreeRectangle( // lessen y_max
                    rect.xmin(),
                    rect.ymin(),
                    rect.xmax(),
                    node.getGeo().getLongitude());
            node.setLeftNode(insert(node.getLeftNode(), geo, !evenLevel, newRect));
        }

        // Handle Nodes which should be inserted to the right
        else if (cmp > 0 && evenLevel) {
            newRect = new KDTreeRectangle( // increase x_min
                    node.getGeo().getLatitude(),
                    rect.ymin(),
                    rect.xmax(),
                    rect.ymax());
            node.setRightNode(insert(node.getRightNode(), geo, !evenLevel, newRect));
        }

        // Handle Nodes which should be inserted to the top
        else if (cmp > 0 && !evenLevel) {
            newRect = new KDTreeRectangle( // increase y_min
                    rect.xmin(),
                    node.getGeo().getLongitude(),
                    rect.xmax(),
                    rect.ymax());
            node.setRightNode(insert(node.getRightNode(), geo, !evenLevel, newRect));
        }

        else if (!node.getGeo().equals(geo))
            node.setRightNode(insert(node.getRightNode(), geo, !evenLevel, rect));

        return node;
    }

    public List<Node> near(Geo point, double radius) {
        KDTreeRectangle rect = new KDTreeRectangle(point.getLatitude() - radius, point.getLongitude() - radius,
                point.getLatitude() + radius, point.getLongitude() + radius);
        if (rect == null) throw new java.lang.NullPointerException(
                "called range() with a null RectHV");

        Stack<Node> points = new Stack<>();

        if (root == null) return points;

        Stack<Node> nodes = new Stack<>();
        nodes.push(root);
        while (!nodes.isEmpty()) {

            Node tmp = nodes.pop();

            double distance = Math.sqrt(
                    Math.pow(tmp.getGeo().getLatitude() - point.getLatitude(), 2) +
                            Math.pow(tmp.getGeo().getLongitude() - point.getLongitude(), 2));

            if (distance < radius) {
                points.push(tmp);
            }

            if (tmp.getLeftNode() != null && rect.intersects(tmp.getLeftNode().getRect())) {
                nodes.push(tmp.getLeftNode());
            }
            if (tmp.getRightNode() != null && rect.intersects(tmp.getRightNode().getRect())) {
                nodes.push(tmp.getRightNode());
            }
        }
        return points;
    }

    private double comparePoints( Geo geo, Node node, boolean evenLevel) {
        if (evenLevel) {
            return geo.getLatitude() - node.getGeo().getLatitude();
        }
        else return geo.getLongitude() - node.getGeo().getLongitude();
    }

}