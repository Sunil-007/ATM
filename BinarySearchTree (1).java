import java.util.Scanner;

public class BinarySearchTree
{
  class TreeNode
  {
    TreeNode leftChild;
    TreeNode rightChild;
    int frequency;
    String nodeValue;

    public TreeNode(String nodeValue)
    {
      this.frequency = 1;
      this.nodeValue = nodeValue;
      leftChild = null;
      rightChild = null;
    }
  }

  public static TreeNode rootNode;

  public BinarySearchTree()
  {
    this.rootNode = null;
  }

  public void delete(String value)
  {
    TreeNode parent = rootNode;
    TreeNode current;
    boolean isLeftChild = false;
    current = this.findNodeWithValue(value);
    if (current == null)
    {
      return;
    }
    if (current.leftChild == null && current.rightChild == null)
    {
      if (current == rootNode)
      {
        rootNode = null;
      }
      if (isLeftChild == true)
      {
        parent.leftChild = null;
      }
      else
      {
        parent.rightChild = null;
      }
    }
    else if (current.rightChild == null)
    {
      if (current == rootNode)
      {
        rootNode = current.leftChild;
      }
      else if (isLeftChild)
      {
        parent.leftChild = current.leftChild;
      }
      else
      {
        parent.rightChild = current.leftChild;
      }
    }
    else if (current.leftChild == null)
    {
      if (current == rootNode)
      {
        rootNode = current.rightChild;
      }
      else if (isLeftChild)
      {
        parent.leftChild = current.rightChild;
      }
      else
      {
        parent.rightChild = current.rightChild;
      }
    }
    else if (current.leftChild != null && current.rightChild != null)
    {
      TreeNode successor = getSuccessor(current);
      if (current == rootNode)
      {
        rootNode = successor;
      }
      else if (isLeftChild)
      {
        parent.leftChild = successor;
      }
      else
      {
        parent.rightChild = successor;
      }
      successor.leftChild = current.leftChild;
    }
  }

  public void insert(String value)
  {
    TreeNode node1 = this.findNodeWithValue(value);
    if (node1 == null)
    {
      TreeNode node = new TreeNode(value);
      if (rootNode == null)
      {
        rootNode = node;
        return;
      }
      TreeNode current = rootNode;
      TreeNode parent;
      boolean flag = true;
      while (flag)
      {
        parent = current;
        if (value.compareTo(current.nodeValue) < 0)
        {
          current = current.leftChild;
          if (current == null)
          {
            parent.leftChild = node;
            flag = false;
          }
        }
        else
        {
          current = current.rightChild;
          if (current == null)
          {
            parent.rightChild = node;
            flag = false;
          }
        }
      }
    }
    else
    {
      node1.frequency++;
    }
  }

  public TreeNode getSuccessor(TreeNode deleteNode)
  {
    TreeNode successsor = null;
    TreeNode parent = null;
    TreeNode current = deleteNode.rightChild;
    while (current != null)
    {
      parent = successsor;
      successsor = current;
      current = current.leftChild;
    }
    if (successsor != deleteNode.rightChild)
    {
      parent.leftChild = successsor.rightChild;
      successsor.rightChild = deleteNode.rightChild;
    }
    return successsor;
  }

  public TreeNode findNodeWithValue(String value)
  {
    TreeNode current = rootNode;
    while (current != null)
    {
      if (current.nodeValue.equalsIgnoreCase(value))
      {
        return current;
      }
      else if (current.nodeValue.compareTo(value) > 0)
      {
        current = current.leftChild;
      }
      else
      {
        current = current.rightChild;
      }
    }
    return null;
  }

  public void printTree(TreeNode root)
  {
    if (root != null)
    {
      printTree(root.leftChild);
      System.out.print(root.nodeValue + "," + root.frequency + "; ");
      printTree(root.rightChild);
    }
  }


  public static void main(String arg[]){
    Scanner scanner = new Scanner(System.in);  // Create a Scanner object
    System.out.print("Enter a sentence: ");
    String sentence = scanner.nextLine();
    BinarySearchTree b = new BinarySearchTree();
    String[]tokens = sentence.split("\\W");
    for (String word: tokens)
    {
      if (!word.equalsIgnoreCase(""))
      {
        b.insert(word);
      }
    }
    System.out.print("The words and their frequencies in the BST: ");
    b.printTree(b.rootNode);
    System.out.println("");
    System.out.println("Enter the word to be searched: ");
    String s = scanner.nextLine();
    TreeNode node = b.findNodeWithValue(s);
    if (null == node) {
      System.out.println("The word is not found");
    }
    else {
      if (node.frequency > 1) {
        node.frequency--;
        System.out.println("The frequency of " + s + " is now " + node.frequency);
      }
      else
      {
        b.delete(s);
        System.out.println("The word is removed from the BST");
      }
    }
  }
}
