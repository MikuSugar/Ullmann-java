package me.mikusugar.ullmann;

/**
 * @author Fang Jie
 * @version 1.0, 2022/10/27 15:16
 */
public class MatrixUtils
{
    public static int[][] multiplication(int[][] matrix1, int[][] matrix2)
    {
        if (matrix1 == null || matrix2 == null)
        {
            throw new IllegalArgumentException("参数不为空");
        }
        for (int[] m : matrix1)
        {
            if (m.length != matrix2.length)
            {
                throw new IllegalArgumentException("第一个矩阵的列数要等于第二个矩阵的行数");
            }
        }

        int[][] res = new int[matrix1.length][matrix2[0].length];

        for (int i = 0; i < res.length; i++)
        {
            for (int j = 0; j < res[i].length; j++)
            {
                for (int k = 0; k < matrix1[i].length; k++)
                {
                    res[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return res;
    }

    /**
     * 转置
     */
    public static int[][] transpose(int[][] matrix)
    {
        if (matrix == null)
        {
            throw new IllegalArgumentException("参数不为空");
        }
        int[][] res = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < res.length; i++)
        {
            for (int j = 0; j < res[i].length; j++)
            {
                res[i][j] = matrix[j][i];
            }
        }
        return res;
    }

    public static void show(int[][] matrix, String title)
    {
        if (title == null || title.isBlank())
        {
            System.out.println("+++++++++++++++++++++++++++");
        }
        else
        {
            System.out.println(title + ":");
        }
        for (int[] m : matrix)
        {
            for (int n : m)
            {
                System.out.printf("%s \t", n);
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        int[][] m1 = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}};
        int[][] m2 = {{0, 1, 1, 0}, {1, 0, 1, 1}, {1, 1, 0, 1}, {0, 1, 1, 0}};
        int[][] m3 = multiplication(m1, m2);
        show(m3,"m3");
        show(transpose(m3),"m3T");

    }
}
