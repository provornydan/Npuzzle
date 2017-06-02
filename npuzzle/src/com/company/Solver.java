package com.company;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by cmiron on 5/31/17.
 */
public class Solver {


    private int size;
    private Board board;

    private ArrayList<Node>  open = new ArrayList<>();
    private ArrayList<Node> close = new ArrayList<>();
    private int nrOpen;
    private int maxOpen;
    private int gx;
    private int ids;

    public Solver(Board board)
    {
        this.board = board;
        this.size = board.getSize();
    }

    public class CustomComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            Integer a = o1.fx;
            Integer b = o2.fx;
            return a.compareTo(b);
        }
    }

    public boolean matrixEqual(int [][]a, int [][]b)
    {
        for(int i = 0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                if(a[i][j] != b[i][j])
                    return false;
            }
        }
        return true;
    }

    public void showPath(Node path)
    {
        int name = path.getID_name();

        while(name != 0)
        {
            for(Node o : close)
            {
                if(o.getID_name() == name)
                    name = o.getID_parent();

            }
        }
    }

    public void solve()
    {
        Node first = new Node(gx, board.Size, board.getMatrix(), board.goal);
        first.setID_name(++ids);
        first.setID_parent(0);
        open.add(first);
        while(!open.isEmpty())
        {
            nrOpen++;
            if(open.size() > maxOpen)
                maxOpen = open.size();
            open.sort(new CustomComparator());
            if(matrixEqual(open.get(0).getState(), board.goal))
                showPath(open.get(0));
        }
    }


}
