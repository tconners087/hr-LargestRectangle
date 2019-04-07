import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Node{
    public int height;
    public int index;
    public int leftPosition;
    public int rightPosition;
    public Node(int index, int data) {
        this.index = index;
        height = data;
        leftPosition = index;
        rightPosition = index;
    }

    public String toString() {
        return "Node::" + index + "\nheight: " + height + " leftPosition: " + leftPosition + " rightPosition: " + rightPosition + " index: " + index;
    }
}

public class LargestRectangle {
	
	static long largest(int[] h) {
        long largest = Long.MIN_VALUE;
        if(h == null)
            return largest;
        ArrayDeque<Node> stack = new ArrayDeque<Node>();
        Node prevTop = null;
        Node newNode = null;
        Node popped = null;
        
        for(int i = 0; i < h.length; i++) {
            long localMin = Long.MAX_VALUE;

            if(!stack.isEmpty())
                prevTop = stack.peek();

            newNode = new Node(i, h[i]);

            if(prevTop != null && newNode.height < prevTop.height) {
                // At least 1 rectangular area can be calculated.
                while(!stack.isEmpty() && stack.peek().height > newNode.height) {
                    // The inner loop ceases execution when it finds a node where stack.peek().height < prevTop.height, meaning stack.peek()'s rightPosition is prevTop's rightPosition.
                    stack.peek().rightPosition = prevTop.rightPosition; 
                    prevTop = stack.peek();
                    
                    while(!stack.isEmpty() && stack.peek().height >= prevTop.height) {
                        popped = stack.pop();
                        localMin = Math.min(localMin, popped.height);
                        popped.rightPosition = prevTop.rightPosition;
                        prevTop.leftPosition = popped.leftPosition;
                        newNode.leftPosition = popped.leftPosition;
                    }

                    if(!stack.isEmpty() && stack.peek().height >= newNode.height)
                        newNode.leftPosition = stack.peek().leftPosition;

                    long tmpLarge = localMin * (prevTop.rightPosition - popped.leftPosition + 1);
                    
                    if(tmpLarge > largest) 
                        largest = tmpLarge;
                    
                    localMin = popped.height;
                }
            }

            stack.push(newNode);
        }

        // If the stack is not empty, the heights are descending.
        if(!stack.isEmpty()) {
            while(!stack.isEmpty()) {
                prevTop = stack.pop();

                if(!stack.isEmpty())
                    stack.peek().rightPosition = prevTop.rightPosition;

                long tmpLarge = prevTop.height * (prevTop.rightPosition - prevTop.leftPosition + 1);

                if(tmpLarge > largest)
                    largest = tmpLarge;
            }
        }

        return largest;
	}
	
	
	
	public static void main(String[] args) throws IOException {
        if(args.length != 1){
            System.out.println("Usage: java LargestRectangle <file_path>");
            return;
        }

        int[] h = null;
        final Scanner sc = new Scanner(System.in);
        Scanner in;
        File file = null;

        try{
            file = new File(args[0]);
        } catch(IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        try{
            in = new Scanner(file);
            String[] hItems = in.nextLine().split(" ");
            h = new int[hItems.length];
            for(int i = 0; i < hItems.length; i++){
                h[i] = Integer.parseInt(hItems[i]);
            }
            in.close();
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        sc.close();
        
        long largestRectangle = largest(h);
        System.out.println("Largest Rectangle: " + largestRectangle);
	}
}
