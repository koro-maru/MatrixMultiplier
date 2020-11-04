import java.util.*;

public class Main {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        Scanner sc = new Scanner(System.in);
        int[][] mat1 = new int[50][50];
        int[][] mat2 = new int[50][50];

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                mat1[i][j] = j + 1;
            }
        }

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                mat2[i][j] = j + 1;
            }
        }
        int[][] res = multiplyMatrix(mat1, mat2);

        System.out.println("Your result matrix: ");
        for (int[] x : res) {
            for (int y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;

        System.out.println("\n\n\nTotal Time: " + totalTime/(Math.pow(10,9.0)) + " seconds");

    }

    public static int[][] multiplyMatrix(int[][] matrix1, int[][] matrix2){
        //first matrix row counter is a global int starting at 0
            Thread[] threads = new Thread[matrix2.length];
            int[][] result = new int[matrix2[0].length][matrix1.length];
             final int[] rows = new int[matrix1.length];
            for(int i = 0; i < matrix1.length; i++){
                rows[i]=i;
            }
            for (int i = 0; i < matrix1.length; i++) {
                final int currRow = i;
                    threads[i] = new Thread(new Runnable() {
                    public void run() {
                        for (int j = 0; j < matrix2[0].length; j++) {
                            int res = 0;
                            for (int k = 0; k < matrix2.length; k++) {
                                res += matrix1[rows[currRow]][k] * matrix2[k][j];
                            }
                            result[rows[currRow]][j] = res;
                        }
                    }
                });
                threads[i].start();
            }
            return result;
        }}

//    public static int[][] multiplyMatrix(int[][] matrix1, int[][] matrix2) {
//        int[][] result = new int[matrix1.length][matrix2[0].length];
//        //row-major traversal of first array
//        for (int i = 0; i < matrix1.length; i++) {
//            //column-major traversal of second array
//            for (int j = 0; j < matrix2[0].length; j++) {
//                //iterates through columns
//                int tempRes = 0;
//                //row traversal of second array
//                for (int k = 0; k < matrix2.length; k++) {
//                    //iterates down a given column; matrix1[i][k] corresponds to
//                    tempRes += matrix1[i][k] * matrix2[k][j];
//                }
//                result[i][j] = tempRes;
//            }
//        }
//        return result;
//    }
//}

