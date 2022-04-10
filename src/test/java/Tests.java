import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
public class Tests {

    @Test
    public void test1() throws IOException {

        TransposeMethods tr = new TransposeMethods("file1.txt");

        tr.left(10);
        tr.writeToFile("file2.txt");
        tr.writeToConsole();
        System.out.println("left: " + tr.toString().equals
                ("AA         AAAA       AAAA       AAA       \n" +
                        "BBBB       BBB        BBBBB      BBB       \n" +
                        "C          CC        \n" +
                        "D          D          D         ") + "\n");
    }

    @Test
    public void test2() throws IOException {

        TransposeMethods tr = new TransposeMethods("file1.txt");

        tr.right(10);
        tr.writeToFile("file3.txt");
        tr.writeToConsole();
        System.out.println("right: " + tr.toString().equals(
                "        AA       AAAA       AAAA        AAA\n" +
                        "      BBBB        BBB      BBBBB        BBB\n" +
                        "         C         CC\n" +
                        "         D          D          D") + "\n");
    }

    @Test
    public void test3() throws IOException {

        TransposeMethods tr = new TransposeMethods("file1.txt");

        tr.transpose();
        tr.writeToFile("file4.txt");
        tr.writeToConsole();
        System.out.println("transpose: " + tr.toString().equals
                ("AA BBBB C D\n" +
                        "AAAA BBB CC D\n" +
                        "AAAA BBBBB D\n" +
                        "AAA BBB") + "\n");
    }

    @Test
    public void test4() throws IOException {

        TransposeMethods tr = new TransposeMethods("file1.txt");

        tr.cut(2);
        tr.writeToFile("file4.txt");
        tr.writeToConsole();
        System.out.println("cut: " + tr.toString().equals
                ("AA AA AA AA\n" + "BB BB BB BB\n" + "C CC\n" + "D D D") + "\n");
    }

    @Test
    public void test5() throws IOException {
        String[] args1 = new String[7];
        args1[0] = "file5.txt";
        args1[1] = "-a";//Максимальная длина
        args1[2] = "3";
        args1[3] = "-t";//Обрезка
        args1[4] = "-r";//Выравнивание по правому
        args1[5] = "-o";//Выходной файл
        args1[6] = "file6.txt";

        Transpose.main(args1);
        TransposeMethods tr = new TransposeMethods("file6.txt");
        tr.right(3);
        tr.writeToFile("file6.txt");
        System.out.println("all flags: " + tr.toString().equals(
                "AAA  BB CCC   D\n" + "AAA   B  CC DDD\n" + "  A CCC DDD\n" + " AA  DD"
        ) + "\n");
    }

    @Test
    public void test6() throws IOException {
        String[] args2 = new String[3];
        args2[0] = "file5.txt";
        args2[1] = "-o";
        args2[2] = "file7.txt";

        Transpose.main(args2);
        TransposeMethods tr = new TransposeMethods("file7.txt");
        tr.left(10);
        tr.writeToFile("file7.txt");
        System.out.println("only -o: " + tr.toString().equals(
                "AAA        BB         CCCC       D         \n" +
                        "AAAA       B          CC         DDDD      \n" +
                        "A          CCC        DDD       \n" +
                        "AA         DD        "
        ) + "\n");
    }

    @Test
    public void test7() throws IOException {
        String[] args3 = new String[4];
        args3[0] = "file5.txt";
        args3[1] = "-t";
        args3[2] = "-o";
        args3[3] = "file8.txt";

        Transpose.main(args3);
        TransposeMethods tr = new TransposeMethods("file8.txt");
        tr.left(10);
        System.out.println("only -t: " + tr.toString().equals(
                "AAA        BB         CCCC       D         \n" +
                        "AAAA       B          CC         DDDD      \n" +
                        "A          CCC        DDD       \n" +
                        "AA         DD        "
        ) + "\n");
    }

    @Test
    public void test8() throws IOException {

    }
}