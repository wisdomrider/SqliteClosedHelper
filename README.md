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
	        implementation 'com.github.wisdomrider:SqliteClosedHelper:2.4.0'
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





  