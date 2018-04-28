package ee.ttu.iti0202.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Node {

    public static void main(String[] args) {
    }
    private Integer value;
    private Node left;
    private Node right;

    public Node() {
    }

    public Node(Integer value) {
        this.value = value;
    }

    public void addValue(Integer value) {
        if (this.value == null) {
            this.value = value;
        } else if (value < this.value) {
            if (left == null) left = new Node();
            left.addValue(value);
        } else if (value > this.value) {
            if (right == null) right = new Node();
            right.addValue(value);
        } else {
            return;
        }
    }

    public List<Integer> getValues(boolean asc) {
        List<Integer> result = new ArrayList<>();
        if (asc) {
            if (left != null) result.addAll(left.getValues(true));
            result.add(this.value);
            if (right != null) result.addAll(right.getValues(true));
        } else {
            if (right != null) result.addAll(right.getValues(false));
            result.add(this.value);
            if (left != null) result.addAll(left.getValues(false));
        }
        return result;
    }

    public Optional<Node> getNode(Integer value) {
        if (this.value == value) return Optional.of(this);
        if (value < this.value && left != null) return left.getNode(value);
        if (value > this.value && right != null) return right.getNode(value);
        return Optional.empty();
    }

    public int getMaxDepth() {
        int leftResult = 0;
        int rightResult = 0;
        if (left == null && right == null) return 0;
        if (left != null) leftResult = left.getMaxDepth() + 1;
        if (right != null) rightResult = right.getMaxDepth() + 1;
        return Math.max(rightResult, leftResult);

    }

    public Integer getMinValue() {
        if (left != null) return left.getMinValue();
        return value;
    }

    public Integer getMaxValue() {
        if (right != null) return right.getMaxValue();
        return value;
    }

    public Integer getValue() {
        return value;
    }
}
