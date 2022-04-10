import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;



public class Transpose {

    @Argument(usage = "Input file name")
    private String inputFileName;

    @Option(name = "-o", usage = "Output file name")
    private String outputFileName;

    @Option(name = "-a", usage = "Maximum word length")
    private int number = -1;

    @Option(name = "-t", usage = "Trim lines that are longer -a")
    private boolean trim;

    @Option(name = "-r", usage = "Align to the right edge")
    private boolean isRight;


    public static void main(String[] args) throws IOException {
        new Transpose().launch(args);
    }

    void launch(String[] args) throws IOException {

        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java SampleMain [options...] arguments...");
            parser.printUsage(System.err);
            return;
        }

        TransposeMethods tr;

        if (!Objects.equals(inputFileName, ""))
            tr = new TransposeMethods(inputFileName);
        else
            tr = new TransposeMethods();


        tr.transpose();


        if (trim) {
            if (number == -1)
                number = 10;
            tr.cut(number);
        }


        if (isRight) {
            if (number == -1)
                number = 10;
            tr.right(number);
        } else
            tr.left(number);


        if (!outputFileName.equals(""))
            tr.writeToFile(outputFileName);
        else
            tr.writeToConsole();
    }

}



    class TransposeMethods {

    private ArrayList<String> line = new ArrayList<>();//Список для хранения текста в строках
    private int max = 0;//Кол-во столбцов в самый длинной строчке


    public TransposeMethods(String file) throws IOException {
        Scanner sc = new Scanner(Paths.get(file));
        makeArrayOfWords(sc);
    }


    public TransposeMethods() {
        System.out.println("Введите текст");
        Scanner sc = new Scanner(System.in);
        makeArrayOfWords(sc);
    }


    private void makeArrayOfWords(Scanner sc) {

        ArrayList<String> list = new ArrayList<>();

        while (sc.hasNext()) {
            list.add(sc.nextLine());
            if (list.get(list.size() - 1).length() == 0)
                break;
        }

        String[] massWords;//массив строк massWords

        for (String value : list) {
            massWords = value.split("\\s+");
            if (massWords.length > max)
                max = massWords.length;
            for (String s : massWords) {
                if (!s.equals(""))
                    line.add(s);
            }
            line.add("\n");
        }
    }


    public void transpose() {
        ArrayList<String> newLine = new ArrayList<>();
        int x = 0;
        for (int i = 0; i < max; i++) {
            for (int j = 0; j <= (line.size()-1); j++) {
                if (line.get(j).equals("\n")) {
                    if ((i + x) < j)
                        newLine.add(line.get(i + x));
                    x = j + 1;
                }
            }
            newLine.add("\n");
            x = 0;
        }
        line = newLine;
    }


    public void cut(int number) {
        for (int i = 0; i < line.size(); i++) {
            if (line.get(i).length() > number && !line.get(i).equals("\n"))
                line.set(i, line.get(i).substring(0, number));
        }
    }


    public void left(int maxLenght) {
        for (int i = 0; i < line.size(); i++)
            if (!line.get(i).equals("\n")) {
                while (line.get(i).length() < maxLenght) {
                    line.set(i, line.get(i) + " ");
                }
            }
    }


    public void right(int maxLenght) {
        for (int i = 0; i < line.size(); i++)
            if (!line.get(i).equals("\n")) {
                while (line.get(i).length() < maxLenght) {
                    line.set(i, " " + line.get(i));
                }
            }
    }


    public void writeToFile(String ofile) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ofile));
            writer.write(this.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(this);
        }
    }


    public void writeToConsole() {
        System.out.println(this);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransposeMethods that = (TransposeMethods) o;

        if (max != that.max) return false;
        return Objects.equals(line, that.line);
    }

    @Override
    public int hashCode() {
        int result = line != null ? line.hashCode() : 0;
        result = 31 * result + max;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < line.size() - 1; i++)
            if (!line.get(i).equals("\n") && !line.get(i + 1).equals("\n"))
                s.append(line.get(i)).append(" ");
            else
                s.append(line.get(i));

        return s.toString();
    }
}