
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

    private int count;
    private Percolation p;
    private double[] fracts;

    public PercolationStats(int N, int T){
        if (N<=0||T<=0) throw new IllegalArgumentException("N and T should be greater than 0");
        count = T;
        fracts = new double[count];
        int size = N;
        for (int i = 0; i < count; i++) {
            p = new Percolation(size);
            int opened = 0;
            while (p.percolates()==false){
                int randomRow = StdRandom.uniform(1, size+1);
                int randomCol = StdRandom.uniform(1, size+1);
                if (p.isOpen(randomRow, randomCol)==false){
                    p.open(randomRow, randomCol);
                    opened++;
                }
            }
            double fract = (double)opened/(size*size);
            fracts[i] = fract;
        }
    }

    public double mean(){
        return StdStats.mean(fracts);
    }

    public double stddev(){
        return StdStats.stddev(fracts);
    }

    public double confidenceLo(){
        return mean()-(1.96*stddev())/Math.sqrt(count);
    }

    public double confidenceHi(){
        return mean() + (1.96*stddev())/Math.sqrt(count);
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(N,T);

        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
    }
}