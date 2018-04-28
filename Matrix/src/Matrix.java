import java.util.List;

public class Matrix {

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                {0,1,2},
                {3,0,1},
                {-2,3,0}
        };
        int[][] matrix2 = new int[][] {
                {4,4,0},
                {4,0,4},
                {0,4,4}
        };
        int[][] matrix3 = new int[][] {
                {1,1,3,4},
                {2,0,0,8},
                {3,0,0,2},
                {4,4,7,5}
        };
        int[][] matrix5 = new int[][] {
                {5,0,0,0,0},
                {0,0,0,0,-4},
                {0,0,3,0,0},
                {0,0,0,1,0},
                {0,-2,0,0,0}
        };
        int[][] matrixg = new int[][] {
                {1,2,3},
                {3,1,-1},
                {2,-1,-4}
        };
        int[][] matrixd = new int[][] {
                {2,-2,1},
                {5,6,3},
                {2,3,1}
        };
        int[][] matrixf = new int[][] {
                {1,2,1},
                {0,1,-4},
                {-1,2,1}
        };
        int[][] matrixa = new int[][] {{0,1,2},{3,0,1},{-2,3,0}};
//        System.out.println("+=========================+");
//        invertibleMatrix(matrixa);
//        invertibleMatrix(matrixf);
//        invertibleMatrix(matrixd);
//        invertibleMatrix(matrixg);
        int[][] matrixinx = new int[][] {
                {2,3,2},
                {1,1,2},
                {-10,8,0}
        };
        determinant3x3(matrixinx);

    }

    private static double[][] invertibleMatrix(int[][] matrix) {
        int determinant = determinantBig(matrix);
        if (determinant == 0) {
            System.out.println("Determinant 0, ei saa arvutada pöördmaatriksit.");
            return null;
        }
        double[][] transponeeritav = new double[matrix.length][matrix.length];
        for (int noPickrow = 0; noPickrow < matrix.length; noPickrow++) {
            for (int noPickColumn = 0; noPickColumn < matrix.length; noPickColumn++) {
                transponeeritav[noPickrow][noPickColumn] = Math.pow(-1, noPickColumn + noPickrow + 2) *
                        determinantBig(subMatrix(matrix, noPickrow, noPickColumn));
            }
        }
        double transponeeritud[][] = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                transponeeritud[j][i] = transponeeritav[i][j];
            }
        }
        for (double[] roww: transponeeritud) {
            StringBuilder builder = new StringBuilder();
            builder.append("||");
            for (double num: roww) {
                builder.append(String.format("% 3.0f/%d |", num, determinant));
            }
            builder.append("|");
            System.out.println(builder.toString());
        }
        System.out.println("+=========================+");
        return transponeeritud;
    }

    private static int determinant3x3(int[][] matrix) {
        for (int[] row: matrix) {
            if (row.length != matrix.length) {
                System.out.println("Ei ole ruutmaatriks");
                return 0;
            }
        }
        int determinant = 0;
        if (matrix.length == 3) {
            determinant = matrix[0][0] * matrix[1][1] * matrix[2][2] +
                    matrix[1][0] * matrix[2][1] * matrix[0][2] +
                    matrix[0][1] * matrix[1][2] * matrix[2][0] -
                    matrix[2][0] * matrix[1][1] * matrix[0][2] -
                    matrix[2][1] * matrix[1][2] * matrix[0][0] -
                    matrix[1][0] * matrix[0][1] * matrix[2][2];
        }
        System.out.println(determinant);
        return determinant;
    }

    private static int determinantBig(int[][] matrix) {
        for (int[] row: matrix) {
            if (row.length != matrix.length) {
                System.out.println("Ei ole ruutmaatriks");
                return 0;
            }
        }
        if (matrix.length == 2) return determinant2x2(matrix);
        if (matrix.length == 3) return determinant3x3(matrix);
        int result = 0;
        for (int noPickRow = 0; noPickRow < matrix.length; noPickRow++) {
            int[][] newMatrix = subMatrix(matrix, noPickRow, 0);
            result += Math.pow(-1, noPickRow + 2) * matrix[noPickRow][0] * determinantBig(newMatrix);
        }
        return result;
    }

    private static int[][] subMatrix(int[][] matrix, int noPickRow, int noPickCol) {
        int[][] newMatrix = new int[matrix.length-1][matrix.length-1];
        int nextX = 0, nextY = 0;

        for (int i = 0; i < matrix.length; i++) {
            if (i == noPickRow) continue;
            for (int j = 0; j < matrix.length; j++) {
                if (noPickCol == j) continue;
                //System.out.println(String.format("nextY = %d, nextX = %d, i = %d, j = %d", nextY, nextX, i, j));
                newMatrix[nextY][nextX++] = matrix[i][j];
                if (nextX == matrix.length - 1) nextX = 0;
            }
            nextY++;
        }
//        pridi alammaatriks
//        for (int[] roww: newMatrix) {
//            StringBuilder builder = new StringBuilder();
//            for (int num: roww) {
//                builder.append(String.format(" %d ", num));
//            }
//            System.out.println(builder.toString());
//        }
//        System.out.println("-------------------");
        return newMatrix;
    }

    private static int determinant2x2(int[][] matrix) {
        for (int[] row: matrix) {
            if (row.length != matrix.length) {
                System.out.println("Ei ole ruutmaatriks");
                return 0;
            }
        }
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }
}
