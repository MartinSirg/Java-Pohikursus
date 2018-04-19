package ee.ttu.iti0202.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Node {

    private Integer value;

    public Node(){}

    public Node(Integer value){
        this.value = value;
    }
    public void addValue(Integer value){}

    public List<Integer> getValues(boolean asc){
        return new ArrayList<Integer>();
    }

    public Optional<Node> getNode(Integer value) {
        return Optional.empty();
    }

    public int getMaxDepth() {
        return 1;
    }

    public Integer getMinValue() {
        return 1;
    }

    public Integer getMaxValue() {
        return 1;
    }

    public Integer getValue() {
        return 1;
    }
}
