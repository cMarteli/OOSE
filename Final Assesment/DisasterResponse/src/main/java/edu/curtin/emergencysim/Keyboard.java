/**
 * Wrapper class for scanner methods
 * Used to so scanner doesn't have to be constantly be instantiated
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
// Marteli, C (2021) source code (Version 1.0) [Source code]. https://github.com/cMarteli/
package edu.curtin.emergencysim;
import java.util.Scanner;
public class Keyboard
{
    private static final Scanner KBD = new Scanner(System.in);

    private Keyboard() {} // empty constructor to prevent instantiation

    public static String next()
    {
        return KBD.next();
    }

    public static double nextDouble()
    {
        return KBD.nextDouble();
    }

    public static int nextInt()
    {
        return KBD.nextInt();
    }

    public static boolean hasNextInt()
    {
        return KBD.hasNextInt();
    }

    public static String nextLine()
    {
        return KBD.nextLine();
    }

    public static long nextLong()
    {
        return KBD.nextLong();
    }

    public static void close()
    {
        KBD.close();
    }



}
