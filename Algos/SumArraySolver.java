package Algos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SumArraySolver {

    public static class SumArrayConfig implements Configuration<SumArrayConfig> {
  
      private final int n;
      private final int k;
      private final int[] current;
      private final int index;
  
      public SumArrayConfig(int n, int k, int[] current, int index) {
        this.n = n;
        this.k = k;
        this.current = current;
        this.index = index;
      }
  
      @Override
      public Collection<SumArrayConfig> getSuccessors() {
        List<SumArrayConfig> successors = new ArrayList<>();
        // Loop from i = 1 to k/2 (inclusive) to generate successors
        for (int i = 1; i <= k / 2; i++) {
          if (index < n - 1 && current[index] == 0) {
            int[] nextCurrent = Arrays.copyOf(current, current.length);
            nextCurrent[index] = i;
            nextCurrent[index + 1] = k - i;
            successors.add(new SumArrayConfig(n, k, nextCurrent, index + 1));
          }
        }
        return successors;
      }
  
      @Override
      public boolean isValid() {
        // Check if all elements are filled and sum matches k
        return index == n && Arrays.stream(current).sum() == k;
      }
  
      @Override
      public boolean isGoal() {
        return isValid();
      }
    }
  
    public static int[] solve(int n, int k) {
      SumArrayConfig initialConfig = new SumArrayConfig(n, k, new int[n], 0);
      Backtracker<SumArrayConfig> backtracker = new Backtracker<>(false);
      SumArrayConfig solution = backtracker.solve(initialConfig);
      return solution != null ? solution.current : null;
    }
  
    public static void main(String[] args) {
      System.out.println("n = 3, k = 48: " + Arrays.toString(solve(3, 48)));
      System.out.println("n = 4, k = 48: " + Arrays.toString(solve(4, 48)));
      System.out.println("n = 10, k = 100: " + Arrays.toString(solve(10, 100)));
    }
  }
  