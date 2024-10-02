import java.util.Arrays;

public class DynamicProgrammingKnapsackAlgorithm implements KnapsackAlgorithm {

	@Override
	public boolean[] solveKnapsack(int[] utilities, int[] weights, int maxWeight) {
		// TODO: Step one find max utility
		boolean [] answer = new boolean [utilities.length];
		Arrays.fill(answer, false);
		int p= maxWeight;
		int n= utilities.length;
		int[][] m = new int[n+1][p+1];
		for (int i=0;i<n;i++) {
			for(int j=0;j<p;j++) {
				if (i==0) m[i][j]=0;
				if (i>0 && weights[i-1]<=j) {
					m[i][j]=m[i-1][j];}
				if (i>0 && weights[i-1]<=j) {
					if (m[i-1][j]>=m[i-1][j-weights[i-1]]) {
						m[i][j]=m[i-1][j]+utilities[i-1];
					}
					if (m[i-1][j-weights[i-1]]>m[i-1][j]) {
						m[i][j]=m[i-1][j-weights[i-1]+utilities[i-1]];
					}
				}
				j++;
			}
		}
		// TODO: Step two: traceback to find subset
		int i=n;
		int j=p;
		while (i>0) {
			if (weights[i-1]<=j && m[i][j]==m[i-1][j-weights[i-1]]+utilities[i-1]){
				answer[i-1]=true;
				j=j-weights[i-1];
				i=i-1;
			if (m[i][j]==m[i-1][j]) {
				i=i-1;
			}
			}
		}
		return answer;
	}

}
