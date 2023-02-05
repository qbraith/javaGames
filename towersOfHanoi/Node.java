public class Node {
    Object value;
    Node nextNode;

    public Node(Object value){
        this.value = value;
    }

    public Node(Object value, Node nextNode){
        this.value = value;
        this.nextNode = nextNode;
    }

    public Node getNextNode(){
        return this.nextNode;
    }

    public Object getValue(){
        return this.value;
    }
    
    public void setNextNode(Node node){
        this.nextNode = node;
    }
}
