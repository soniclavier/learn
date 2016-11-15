import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class GridSort {
	
	public String sort(int n, int m, int[] grid) {
		Tuple[] gridCoord = buildCoord(grid, n, m);
		sortGrid(grid, gridCoord);
		return checkValid(gridCoord, n, m);
	}

	private String checkValid(Tuple[] coord, int rows, int cols) {
		int[][] rowArr = new int[rows][cols];
		int[][] colArr = new int[rows][cols];

		int currRow = 0;
		int currCol = 0;
		for(int i = 0; i < coord.length; i++) {
			Tuple tup = coord[i];
			int r = tup.x;
			int c = tup.y;
			if (currCol == cols) {
				currCol = 0;
				currRow++;
			}
			rowArr[currRow][currCol] = r;
			colArr[currRow][currCol] = c;
			currCol++;
		}

		//rowArr should have all the elements in the row same and the cols should be in same order;
		for (int row = 0; row < rowArr.length; row++) {
			int prev = rowArr[row][0];
			for (int col = 1; col < rowArr[0].length; col++) {
				if (prev != rowArr[row][col]) {
					return "Impossible";
				}
				prev = rowArr[row][col];
			}
		}

		String first = "";
		for (int row = 0; row < rowArr.length; row++) {
			first += rowArr[row][0];
		}
		//compare this string with all other column
		for (int col = 1; col < rowArr[0].length; col++) {
			String curr = "";
			for (int row = 0; row < rowArr.length; row++) {
				curr += rowArr[row][col];
			}
			if (!first.equals(curr)) {
				return "Impossible";
			}
		}


		//colArr should have all the elements in the col same and the rows should be in same order;
		for (int col = 0; col < colArr[0].length; col++) {
			int prev = colArr[0][col];
			for (int row = 1; row < colArr.length; row++) {
				if (prev != colArr[row][col]) {
					return "Impossible";
				}
				prev = colArr[row][col];
			}
		}

		first = "";
		for (int col = 0; col < colArr[0].length; col++) {
			first += colArr[0][col];
		}
		for (int row = 1; row < colArr.length; row++) {
			String curr = "";
			for (int col = 0; col < colArr[0].length; col++) {
				curr += colArr[row][col];
			}
			if (!first.equals(curr)) {
				return "Impossible";
			}
		}
		return "Possible";
	}

	private Tuple[] buildCoord(int[] grid, int rows, int cols) {
		int currRow = 0;
		int currCol = 0;
		Tuple[] coord = new Tuple[grid.length];
		for(int i = 0; i < grid.length; i++) {
			if (currCol == cols) {
				currCol = 0;
				currRow++;
			}
			coord[i] = new Tuple(currRow, currCol++);
		}
		return coord;
	}

	private void sortGrid(int[] grid, Tuple[] coord) {
		for(int i = 0; i<grid.length;i++) {
			for(int j = i+1; j<grid.length; j++) {
				if (grid[i] > grid[j]) {
					Tuple temp = coord[i];
					coord[i] = coord[j];
					coord[j] = temp;
					int t = grid[i];
					grid[i] = grid[j];
					grid[j] = t;
				}
			}
		}
	}

	class Tuple {
		int x;
		int y;

		public Tuple(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
