package com.company;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
public class Main {

    public static void create_file(String path, String file_name)
    {
        File file = new File(path+file_name); //initialize File object and passing path as argument
        boolean result;
        try
        {
            result = file.createNewFile();  //creates a new file
            if(result)      // test if successfully created a new file
            {
                System.out.println("file created "+file.getCanonicalPath()); //returns the path string
            }
            else
            {
                System.out.println("File already exist at location: "+file.getCanonicalPath());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();    //prints exception if any
        }
    }

    public static void delete_file(String path, String file_name)
    {
        try
        {
            File f= new File(path+file_name);           //file to be deleted
            if(f.delete())                      //returns Boolean value
            {
                System.out.println(f.getName() + " deleted");   //getting and printing the file name
            }
            else
            {
                System.out.println("failed ");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void display_tree(String path)
    {
        File fileName = new File(path);
        File[] fileList = fileName.listFiles();

        assert fileList != null;
        for (File file: fileList) {

            System.out.println(file);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        String defaultPath = "./";
        Scanner input = new Scanner(System.in);
        System.out.println("Loading System...");
        Thread.sleep(3000);
        System.out.println("System has booted.");
        String s ="";
        while (!s.equals("exit")) {
            s = input.nextLine();
            switch (s) {
                case "help":
                    System.out.println("[CLI OPTIONS]");
                    System.out.println("create name - creates a file");
                    System.out.println("delete name - deletes a file");
                    System.out.println("path name [default] - sets a path on your machine | [default option sets the default path] ");
                    System.out.println("display [name] - displays the tree of the current path | [name option used to view another path directory tree] ");
                    System.out.println("exit - exits system");
                    break;
                default:
                        if (s.indexOf("create") == 0)
                            try { create_file(defaultPath, s.substring(7));}
                            catch(StringIndexOutOfBoundsException e)
                            {
                                System.out.println("File name missing!");
                            }
                    else
                        if (s.indexOf("delete") == 0)
                    try { delete_file(defaultPath, s.substring(7));}
                    catch(StringIndexOutOfBoundsException e)
                    {
                        System.out.println("File name missing!");
                    }
                    else
                        if (s.indexOf("path") == 0 && s.indexOf("default") == 5) defaultPath = "./";
                    else
                        if (s.indexOf("path") == 0) defaultPath = s.substring(5) + "\\\\" ;
                    else
                        if (s.indexOf("display") == 0 && s.length() == 7) display_tree(defaultPath);
                    else
                        if (s.indexOf("display") == 0 && s.length() > 8)
                            try
                            { display_tree(s.substring(8));}
                            catch(NullPointerException e)
                            {
                                System.out.println("Not a valid path");
                            }
                    else if (!s.equals("exit")) System.out.println("Not a valid command");
                    break;
            }
        }

    }
}
