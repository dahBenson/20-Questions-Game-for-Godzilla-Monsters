import java.io.*;
import java.util.*;
/**
 * This is a 20 Questions based using Godzilla Monsters
 * Use this link if you don't know any of them https://imgur.com/gallery/CFnku 
 *
 * @author (Ryan Benson)
 * @version (1.0)
 */
public class TwentyQuestions
{
    // instance variables - replace the example below with your own
    public TreeNode myRoot = null;
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("QuestionsOut.txt")));
    private static Scanner scanner = new Scanner(System.in);
    public boolean done = false;
    BufferedReader f = new BufferedReader(new FileReader("Questions.txt"));
    Scanner scan = new Scanner(new FileReader("Questions.txt"));

    /**
     * Constructor runs the entire game
     */
    public TwentyQuestions()throws Exception
    {

        myRoot = read();
        run(myRoot);

    }

    /**
     * Runs the main 20 questions
     */
    public void run(TreeNode root)throws Exception
    {
        System.out.println("\f");

        if(root != null)
        {
            System.out.println(root.getValue());

            String response = scanner.next().toLowerCase();
            if(response.equals("yes") && root.getLeft() == null)
            {
                System.out.println("Congrats it works!");
                if(response.equals("restart"))
                {
                    run(myRoot);
                }
                else if(response.equals("quit"))
                {
                    System.exit(0);
                    
                }

            }
            else if(response.equals("yes"))
            {
                run(root.getLeft());
            }
            else if(response.equals("no") && root.getLeft() == null)
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("I give up, what is your question?");
                String answer = br.readLine();
                TreeNode notOld = new TreeNode(answer);
                TreeNode old = new TreeNode(root.getValue());
                System.out.println( "What question has a yes answer if "+root.getValue()+" and a no question if " + answer);
                String meh2 = br.readLine();

                root.setValue("#Q:"+meh2);
                root.setLeft(old);
                root.setRight(notOld);

            }
            else if(response.equals("no"))
            {
                run(root.getRight());
            }
            else if(response.equals("quit"))
            {
                System.exit(0);

            }
            else
            {
                System.out.println("Not a response allowed!!");
                run(root);
            }

            write(myRoot);
            out.close();
        }
    }

    /**
     * Takes the txt file input and turn it into a TreeList using pre order
     */
    public TreeNode read()throws Exception
    {
        TreeNode root = null;

        String line =  scan.nextLine();

        if (line != null)
        {

            if(line.substring(0,2).equals("#Q"))
            {
                root = new TreeNode(line);

                root.setLeft(read());
                root.setRight(read());

            }
            else
            {
                root = new TreeNode(line);

            }

        }
        return root;

    }

    /**
     * Takes the altered Tree List after a new term is added and writes it in Pre Order form to another file to use later with an updated game
     */
    public void write(TreeNode root) 
    {

        if(root == null)
        {
            return ;
        }
        else  
        {

            out.println(root.getValue());
            write(root.getLeft());
            write(root.getRight());

        }

    }
}

