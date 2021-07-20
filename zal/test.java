public class test {
    public static void main(String[] args) {
        int [][] matrix = new int [5][10];
        for(int i = 0; i < matrix.length; i++){
            matrix[i] = new int [i + 1];
            for(int j = 0; j < i + 1; j++){
                matrix[i][j] = i + j;
                System.out.println(matrix[i] + " " + matrix[i][j]);
            }
            
        }

    }
}
