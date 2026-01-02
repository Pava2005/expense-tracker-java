import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
//create cSv file
public class handling{
    public static void main (String args[]) throws IOException{
        File f=new File("C:\\socket_program\\folder11\\folder22\\project.csv");
        if(f.createNewFile()){
            FileWriter f1=new FileWriter(f);
            f1.write("date,category,amount,note"+System.lineSeparator());
            f1.close();
            System.out.println("csv file created with header");
        }
        else{
            System.out.println("csv file already exists");
        }
        Scanner sc=new Scanner(System.in);
        //menu
        while(true){
            System.out.println("1.ADD EXPENSIVE");
            System.out.println("2.REPLACE EXPENSIVE");
            System.out.println("3.DELETE EXPENSIVE");
            System.out.println("4.VIEW TOTAL");
            System.out.println("5.EXIT");
            System.out.println("Enter your choice:");
        
        //user input 
        //add expensive
            int choice=sc.nextInt();
            sc.nextLine().trim();
            switch(choice){
             case 1:
              /*nextline()=scanner method-reads full line
                trim()=string method-remove spaces
                line separator=add new line*/
               System.out.println("date:xx-yy-zzzz");
               String date=sc.nextLine().trim();
               System.out.println("category: ");
               String category=sc.nextLine().trim();
               System.out.println("amount: ");
               int amount=sc.nextInt();
               sc.nextLine().trim();
               System.out.println("note: ");
               String note=sc.nextLine().trim();
               //append data always
               FileWriter f1=new FileWriter(f,true);
               f1.write(date+","+category+","+amount+","+note+","+System.lineSeparator());
               f1.close();
               System.out.println("expensive added successfully");
               break;

             //replace data
             case 2:
               System.out.print("Enter OLD date to replace: ");
               String olddate = sc.nextLine().trim();
               System.out.print("Enter new date: ");
               String newdate = sc.nextLine().trim();
               System.out.print("Enter new category: ");
               String newcategory = sc.nextLine().trim();
               System.out.print("Enter new amount: ");
               int newamount = sc.nextInt();
               sc.nextLine();
               System.out.print("Enter new note: ");
               String newnote = sc.nextLine().trim();

               //stringbuilder=mutual
               StringBuilder content = new StringBuilder();
               Scanner reader = new Scanner(f);
               //check-another line to read
               while (reader.hasNextLine()) {
                 String line = reader.nextLine();
                 if (!line.startsWith("date") && line.startsWith(olddate + ",")) {
                  content.append(
                  newdate + "," + newcategory + "," + newamount + "," + newnote
                  + System.lineSeparator()
                  );
                 } else {
                    content.append(line + System.lineSeparator());
                    }
                 }
                 reader.close();
               // overwrite same file
               FileWriter overwriteWriter = new FileWriter(f, false);
               overwriteWriter.write(content.toString());
               overwriteWriter.close();
               System.out.println("Expense replaced successfully");
               break;

             //delete
             case 3:
               System.out.print("Enter date to DELETE: ");
               String delDate = sc.nextLine().trim();
               //stringbuilder-mutual
               StringBuilder contentt = new StringBuilder();
               Scanner reader1 = new Scanner(f);
               boolean found = false;
               while (reader1.hasNextLine()) {
                   String line = reader1.nextLine();

                   if (!line.startsWith("date") && line.startsWith(delDate + ",")) {
                    found = true; // skip this line (delete)
                   } else {
                    contentt.append(line + System.lineSeparator());
                   }
               }
               reader1.close();
               FileWriter writer = new FileWriter(f, false); // overwrite
               writer.write(contentt.toString());
               writer.close();
               if (found) {
                System.out.println("Expense deleted successfully");
               } else {
                System.out.println("Date not found. No expense deleted");
                }
               break; 
               
             //calculate total amount
             case 4:
               int total = 0;
               Scanner readerr = new Scanner(f);
               if (readerr.hasNextLine()) {
                   readerr.nextLine();
               }
               while (readerr.hasNextLine()) {
                  String line = readerr.nextLine();
                  String[] data = line.split(",");
                  int amt = Integer.parseInt(data[2]);
                  total += amt;
               }
                readerr.close();
               System.out.println("Total Expense Amount = " + total);
               break;
               
             //exit
             case 5:
               System.out.println("Thank you.");
               System.out.println("exiting...");
               sc.close();
               return;
              
            //default line
             default:
               System.out.println("invalid choice");
            }
        }
       

    }
}
