
import java.util.ArrayList;

public class diskStack {
    private String name;
    private int size;
    private int limit;
    private Node topItem;

    public diskStack(String name){
        this.name = name;
        this.size = 0;
        this.limit = 15;
        this.topItem = null;
    }

    public void push(Object value){
        if (this.hasSpace()){
            Node item = new Node(value);
            item.setNextNode(this.topItem);
            this.topItem = item;
            this.size++;
        } else
            System.out.println("No more room!");
    }

    public Object pop(){
        if (this.size > 0){
            Node itemToRemove = this.topItem;
            this.topItem = itemToRemove.getNextNode();
            this.size--;
            return itemToRemove.getValue();
        }
        System.out.println("This stack is totally empty!");
        return null;
    }

    public Object peek(){
        if (this.size > 0){
            return this.topItem.getValue();
        }
        System.out.println("Nothing to see here");
        return null;
    }

    public boolean hasSpace(){
        return this.limit > this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }
    
    public int getSize(){
        return this.size;
    }

    public String getName(){
        return this.name;
    }

    public String printItems(){
        String result = "Stack " + this.getName().substring(0, 1) + ": | ";
        Node pointer = this.topItem;
        ArrayList<Object> list = new ArrayList<>();
        while (pointer != null){
            list.add(pointer.getValue());
            pointer = pointer.getNextNode();
        }
        for (int i = list.size()-1; i >= 0; i--)
            result += list.get(i) + " | ";
        return result;
    }
}
