import org.junit.jupiter.api.Test;
import java.io.*;

public class Tests {

    @Test
    public void test1() throws IOException {
        String[] args1 = new String[1];
        args1[0] = "file1.txt";

        Transpose.main(args1);
        TransposeMethods tr = new TransposeMethods("file1.txt");
        tr.transpose();
        System.out.println("transpose: " + tr.toString().equals(
                "AAA BB CCCC D\n" +
                        "AAAA B CC DDDD\n" +
                        "A CCC DDD\n" +
                        "AA DD"
        ) + "\n");
    }

    @Test
    public void test2() throws IOException {
        String[] args2 = new String[2];
        args2[0] = "-t";
        args2[1] = "file1.txt";

        Transpose.main(args2);
        TransposeMethods tr = new TransposeMethods("file1.txt");
        tr.transpose();
        tr.cut(10);
        tr.left(10);
        System.out.println("transpose and -t: " + tr.toString().equals(
                "AAA        BB         CCCC       D         \n" +
                        "AAAA       B          CC         DDDD      \n" +
                        "A          CCC        DDD       \n" +
                        "AA         DD        "
        ) + "\n");
    }

    @Test
    public void test3() throws IOException {
        String[] args3 = new String[2];
        args3[0] = "-r";
        args3[1] = "file1.txt";

        Transpose.main(args3);
        TransposeMethods tr = new TransposeMethods("file1.txt");
        tr.transpose();
        tr.right(10);
        System.out.println("transpose and -r: " + tr.toString().equals(
                "       AAA         BB       CCCC          D\n" +
                        "      AAAA          B         CC       DDDD\n" +
                        "         A        CCC        DDD\n" +
                        "        AA         DD"
        ) + "\n");
    }

    @Test
    public void test4() throws IOException {
        String[] args4 = new String[3];
        args4[0] = "-a";
        args4[1] = "5";
        args4[2] = "file1.txt";

        Transpose.main(args4);
        TransposeMethods tr = new TransposeMethods("file1.txt");
        tr.transpose();
        tr.left(5);
        System.out.println("transpose and -a: " + tr.toString().equals(
                "AAA   BB    CCCC  D    \n" +
                        "AAAA  B     CC    DDDD \n" +
                        "A     CCC   DDD  \n" +
                        "AA    DD   "
        ) + "\n");
    }

    @Test
    public void test5() throws IOException {
        String[] args5 = new String[4];
        args5[0] = "-a";
        args5[1] = "2";
        args5[2] = "-t";
        args5[3] = "file1.txt";

        Transpose.main(args5);
        TransposeMethods tr = new TransposeMethods("file1.txt");
        tr.transpose();
        tr.cut(2);
        tr.left(2);
        System.out.println("transpose, -a and -t: " + tr.toString().equals(
                "AA BB CC D \n" +
                        "AA B  CC DD\n" +
                        "A  CC DD\n" +
                        "AA DD"
        ) + "\n");
    }

    @Test
    public void test6() throws IOException {
        String[] args6 = new String[5];
        args6[0] = "-a";
        args6[1] = "3";
        args6[2] = "-t";
        args6[3] = "-r";
        args6[4] = "file1.txt";

        Transpose.main(args6);
        TransposeMethods tr = new TransposeMethods("file1.txt");
        tr.transpose();
        tr.cut(3);
        tr.right(3);
        System.out.println("transpose, -a, -t and -r: " + tr.toString().equals(
                "AAA  BB CCC   D\n" +
                        "AAA   B  CC DDD\n" +
                        "  A CCC DDD\n" +
                        " AA  DD"
        ) + "\n");
    }

    @Test
    public void test7() throws IOException {
        String[] args7 = new String[7];
        args7[0] = "-a";
        args7[1] = "2";
        args7[2] = "-t";
        args7[3] = "-r";
        args7[4] = "-o";
        args7[5] = "file2.txt";
        args7[6] = "file1.txt";

        Transpose.main(args7);
        TransposeMethods tr = new TransposeMethods("file1.txt");
        tr.transpose();
        tr.cut(2);
        tr.right(2);
        tr.writeToFile("file2.txt");
        System.out.println("all flags: " + tr.toString().equals(
                "AA BB CC  D\n" + "AA  B CC DD\n" + " A CC DD\n" + "AA DD"
        ) + "\n");
    }

    @Test
    public void test8() throws IOException {
        String[] args8 = new String[7];
        args8[0] = "-a";
        args8[1] = "4";
        args8[2] = "-t";
        args8[3] = "-r";
        args8[4] = "-o";
        args8[5] = "file2.txt";
        args8[6] = "file3.txt";

        Transpose.main(args8);
        TransposeMethods tr = new TransposeMethods("file3.txt");
        tr.transpose();
        tr.cut(4);
        tr.right(4);
        tr.writeToFile("file2.txt");
        System.out.println("mixed letters: " + tr.toString().equals(
                "ABBB ABCD  BAC    A\n" + "DCDA BBBB CADA   BB\n" + " BBC ADCB ACAB  CCC\n" + " DDD"
        ) + "\n");
    }

    @Test
    public void test9() throws IOException {
        String[] args9 = new String[7];
        args9[0] = "-a";
        args9[1] = "7";
        args9[2] = "-t";
        args9[3] = "-r";
        args9[4] = "-o";
        args9[5] = "file5.txt";
        args9[6] = "file4.txt";

        Transpose.main(args9);
        TransposeMethods tr = new TransposeMethods("file4.txt");
        tr.transpose();
        tr.cut(7);
        tr.right(7);
        tr.writeToFile("file5.txt");
        System.out.println("real words: " + tr.toString().equals(
                "     HD     TRY     KEY DISCOVE\n" +
                        "LANGUAG    GAME INTERNA     SEA\n" +
                        "UNIVERS    JAVA      OH\n" +
                        "SAMURAI COMPUTE"
        ) + "\n");
    }
}