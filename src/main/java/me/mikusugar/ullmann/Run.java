package me.mikusugar.ullmann;

import java.util.List;

/**
 * @author mikusugar
 * @version 1.0, 2022/10/27 15:00
 */
public class Run
{
    public static void main(String[] args)
    {

        int[][] a = {{0, 1, 1}, {1, 0, 0}, {1, 0, 0}};
        int[][] b = {{0, 1, 1, 0}, {1, 0, 1, 1}, {1, 1, 0, 1}, {0, 1, 1, 0}};

        @SuppressWarnings ("unchecked") List<String>[] aTags = new List[] {List.of("A"), List.of("B"), List.of("C")};
        @SuppressWarnings ("unchecked") List<String>[] bTags = new List[] {List.of("A"), List.of("B"), List.of("C"),
                List.of("A")};

        final Ullmann ullmannNoTags = new Ullmann(b, a);
        final List<int[][]> resNoTags = ullmannNoTags.cal();
        System.out.println("no tags res size:" + resNoTags.size());
        resNoTags.forEach(m -> MatrixUtils.show(m, "res"));

        System.out.println("++++++++++++++++++++++++++");

        final Ullmann ullmann = new Ullmann(b, bTags, a, aTags);
        final List<int[][]> res = ullmann.cal();
        System.out.println("res size:" + res.size());
        res.forEach(m -> MatrixUtils.show(m, "res"));

    }
}
