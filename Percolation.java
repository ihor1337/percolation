
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private boolean isOpened [][];
    private int virt_top = 0;
    private int virt_bot;
    private int grid;

    public Percolation(int N){
        if (N<=0) throw new IllegalArgumentException("N must be greater than 0");
        grid = N;
        virt_bot = grid*grid+1;
        uf = new WeightedQuickUnionUF(grid*grid+2);
        isOpened = new boolean[grid][grid];
    }

    public boolean isOpen(int i, int j){
        return isOpened[i-1][j-1];
    }

    private int mapIndex(int i, int j){
        return grid*(i-1)+j;
    }

/*    private void validateInput(int i, int j){
        if (i < 1 || i > grid){
            throw new IndexOutOfBoundsException("out of bounds, i should be > 0 and < "+grid+ " value of i is "+ i);
        }
        if (j < 1 || j > grid){
            throw new IndexOutOfBoundsException("out of bounds, j should be > 0 and < "+grid+ "value of j is "+j);
        }
    }*/

    public void open(int i, int j){
        isOpened[i-1][j-1] = true;
        if (i==1){
            uf.union(mapIndex(i, j), virt_top);
        }
        if (i==grid){
            uf.union(mapIndex(i, j), virt_bot);
        }
        if (j>1 && isOpen(i, j-1)){
            uf.union(mapIndex(i,j), mapIndex(i, j-1));
        }
        if (i<grid && isOpen(i+1,j)){
            uf.union(mapIndex(i,j), mapIndex(i+1,j));
        }
        if(j<grid && isOpen(i, j+1)){
            uf.union(mapIndex(i,j), mapIndex(i, j+1));
        }
        if(i>1 && isOpen(i-1, j)){
            uf.union(mapIndex(i,j), mapIndex(i-1, j));
        }
    }

    public boolean isFull(int i, int j){
        if(i>0 && j>0 && i<=grid && j<=grid){
            return uf.connected(virt_top,mapIndex(i,j));
        }else {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean percolates(){
        return uf.connected(virt_top, virt_bot);
    }

    public static void main(String[] args) {
	// write your code here
    }
}