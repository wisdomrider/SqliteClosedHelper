## SqliteClosedHelper


## How To Add to Your Project? 



Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.wisdomrider:SqliteClosedHelper:2.5.2'
	}

## How to Use?

First Initialize SqliteClosedHelper 

           SqliteClosedHelper helper = new SqliteClosedHelper(this, "DBNAME"); 
	 

  Here SqliteClosedHelper takes first context and then databasename.
  
  Then Create a Class
  
           public class Book {
               @SqliteAnnotations.Primary
               @SqliteAnnotations.AutoIncrement
               int bookId; //here bookId is primary key and it increases by +1 everytime
           
               @SqliteAnnotations.Nullable(isNullable = false)
               float cost; //cost cannot be null
           
           
               @MethodAnnotations.Exclude
               ImageView image; //image is excluded from database as this is not a valid data type
           
           
               public int getBookId() {
                   return bookId;
               }
           
               public void setBookId(int bookId) {
                   this.bookId = bookId;
               }
           
               public float getCost() {
                   return cost;
               }
           
               public void setCost(float cost) {
                   this.cost = cost;
               }
           
               public ImageView getImage() {
                   return image;
               }
           
               public void setImage(ImageView image) {
                   this.image = image;
               }
           }

You have to add a annotation in each field if necessary SqliteclosedHelper supports int,float,string,boolean,long,double in its data type except that you
should add @Exclude 



## Lets Insert Data
## Before Inserting Make Sure to Create a Table

           Book book1=new Book();
           book1.setBookId(01);
           book1.setCost(45.2f);
           ImageView image = null; //suppose you saved a image here
           book1.setImage(image);
           helper.createTable(book1); //using the same object you created at top. If your table doesnot exist it creates one.

## Insert the Values

      helper.insertTable(book1); //same object is inserted into sqlite database

## Update the Values (If Exists Else It Will Create One)

           Book book1=new Book();
           book1.setBookId(01); //here bookId is primary key which helps the database to know which field to update
           book1.setCost(47.2f); //changing from 45.2 to 47.2
           ImageView image = null; //suppose you saved a image here
           book1.setImage(image);
           helper.updateTable(book1); //same object is inserted into sqlite database

Make Sure Not to Change BookId as it is primary key database checks the primary key if got it will update else it will create a new field.

## Query 
   ## AND CASE
   
           Book book = new Book();
           book.setBookId(01);
           book.setCost(47.2f);
           ArrayList<Book> books = helper.whereAND(book);

 Here Books With Id 01 and Cost 47.2 Will Be Listed in ArrayList
 
   ## OR CASE
        
                Book book = new Book();
                book.setBookId(01);
                book.setCost(47.2f);
                ArrayList<Book> books = helper.whereOR(book);

Here Books with Id 01 or Cost 47.2 Will Appear in books

## INSERTING/UPDATING LARGE AMOUNT OF DATA (FROM ARRAYLIST)

 ## Lets Suppose The Items Are 
 
                  ArrayList<Book> books=new ArrayList<>();
                  Book book1=new Book();
                  Book book2=new Book();
                  Book book3=new Book();
                  book1.setBookId(01);
                  book2.setBookId(02);
                  book3.setBookId(03);
                  book1.setCost(12f);
                  book2.setCost(13f);
                  book3.setCost(14f);
                  books.add(book1);
                  books.add(book2);
                  books.add(book3);

 ## TO INSERT ALL
               helper.insertAll(books);
               
 ## TO UPDATE ALL              
                 helper.updateAll(books);

## Pass Your Custom Query 

            helper.Query("SELECT * FROM TABLENAME");

## Remove a field

             Book book1=new Book();
             book1.setBookId(01); //Id with 01 With Removed
             helper.delete(book1); 
             
                          OR
                           
             Book book1=new Book();
             book1.setBookId(01); 
             book1.setCost(47.2f);
             helper.delete(book1); //Id 01 with Cost 47.2 Will be Deleted (Uses And Case)


## Remove All Fields
      
              helper.removeAll(new Book()); //here all the fields are removed 
            

   
   

 
 

  
  ## IF YOU NEED HELP OR ANYTHING EMAIL ME AVISHEKZONE@GMAIL.COM OR PLACE A ISSUE
