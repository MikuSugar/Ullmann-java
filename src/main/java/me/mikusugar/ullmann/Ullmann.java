package me.mikusugar.ullmann;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mikusugar
 * @version 1.0, 2022/10/27 15:42
 */
public class Ullmann
{
    private final int[][] largeGraphMatrix;

    private final int[][] searchGraphMatrix;

    private final int[][] m0Matrix;

    public Ullmann(int[][] largeGraphMatrix, int[][] searchGraphMatrix)
    {
        if (largeGraphMatrix == null || searchGraphMatrix == null)
        {
            throw new IllegalArgumentException("参数不为空");
        }
        this.largeGraphMatrix = largeGraphMatrix;
        this.searchGraphMatrix = searchGraphMatrix;

        m0Matrix = creatM0Matrix();
    }

    public List<int[][]> cal()
    {
        List<int[][]> res = new ArrayList<>();
        int[][] m1 = new int[m0Matrix.length][m0Matrix[0].length];
        dfs(m1, res, 0);
        return res;
    }

    private void dfs(int[][] m1, List<int[][]> res, int d)
    {
        for (int i = 0; i < m1[d].length; i++)
        {
            if (m0Matrix[d][i] == 1)
            {
                m1[d][i] = 1;
                if (d == m1.length - 1)
                {
                    if (check(m1))
                    {
                        res.add(copy(m1));
                    }
                }
                else
                {
                    dfs(m1, res, d + 1);
                }
                m1[d][i] = 0;
            }
        }
    }

    private int[][] copy(int[][] m1)
    {
        int[][] res = new int[m1.length][m1[0].length];
        for (int i = 0; i < res.length; i++)
        {
            System.arraycopy(m1[i], 0, res[i], 0, res[i].length);
        }
        return res;
    }

    private boolean check(int[][] m1)
    {
        final int[][] mc = getMC(m1);
        for (int i = 0; i < searchGraphMatrix.length; i++)
        {
            for (int j = 0; j < searchGraphMatrix[i].length; j++)
            {
                if (searchGraphMatrix[i][j] == 1 && mc[i][j] != 1)
                {
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] getMC(int[][] m1)
    {
        //M‘⋅B
        final int[][] m1b = MatrixUtils.multiplication(m1, largeGraphMatrix);
        //（M‘⋅B)T
        final int[][] m1bt = MatrixUtils.transpose(m1b);
        //MC=M‘（M‘⋅B)T
        return MatrixUtils.multiplication(m1, m1bt);
    }

    /**
     * 创建M0矩阵 M0 矩阵以小图的点为行，大图的点为列
     * <p>
     * 当大图的j点的度大于小图j点的度，m[i][j]=1,否则m[i][j]=0。
     */
    private int[][] creatM0Matrix()
    {
        int[][] res = new int[searchGraphMatrix.length][largeGraphMatrix.length];
        for (int i = 0; i < res.length; i++)
        {
            for (int j = 0; j < res[i].length; j++)
            {
                final int l = getDegree(j, largeGraphMatrix);
                final int s = getDegree(i, searchGraphMatrix);
                res[i][j] = l >= s ? 1 : 0;
            }
        }
        return res;
    }

    /**
     * 获取点的度
     *
     * @param i           点的编号
     * @param graphMatrix 图的矩阵
     */
    private int getDegree(int i, int[][] graphMatrix)
    {
        int res = 0;
        for (int k = 0; k < graphMatrix[i].length; k++)
        {
            res += graphMatrix[i][k] == 1 ? 1 : 0;
        }
        return res;
    }


}
