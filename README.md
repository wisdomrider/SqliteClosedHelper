## SqliteClosedHelper







Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'implementation 'com.github.wisdomrider:SqliteClosedHelper:0.05'
	}


# How to Implement ?


 
 # Initialize First


       SqliteClosedHelper  sq=new SqliteClosedHelper(context,"dbname");


# To create a new table


        sq.setTable("table_name")
                .setTableFields("id", Wisdom.INTEGER(), Wisdom.PRIMARY_AUTOINCREMENT())   // for integer with fieldname id and primary autoincreament
                .setTableFields("field_name", Wisdom.TEXT()) //for text for pass fieldname and type
                .setTableFields("field_2", Wisdom.DATETIME()) //for date time
                .setTableFields("field_name2", Wisdom.TEXT(), Wisdom.NOTNULL()) //text filed and cannot be null
                .setTableFields("field_name4", Wisdom.CUSTOMTYPE("text"), Wisdom.CUSTOMPARAMETER("not null ")) //defining other custom type and parameter of sqliteopenhelper
                .setTableFields("field_5", Wisdom.TEXT(), Wisdom.PRIMARY()) //making primary
                .create(); //it creates a table


# Insert data

          sq.clearAll() //clears the  previous values do it when you use again
                .setTable("table_name") //if already used above default table name is table_name
                .insertFields("field_name", 0) //inserting a integer
                .insertFields("field_name", "asjgdjhag") //inserting a text
                .insert(); //inserts
		
# Set a table
                  sq.setTable("table name")
		  . 
		  .... Other Operations .....
		  

	
# Quering	(Make Sure to Set Table Name At First)

                  Cursor a=sq.getAll(); //provides all the data in a cursor
		  Cursor aa = sq.getWhere("field_name", "value"); //querying if field_name == value
		  Cursor ab = sq.get("where a = 'b'"); //custom query
		  boolean checkIfFieldExist=sq.isFieldExist("field_name","value"); //checking if field exist in database
		  
 # Checking if Table Exist or Not
                 
		  if (sq.ifTableExist("table name")) { //checking if table Exist
                      //insert fields in table
                  }
		  else{ //create a table }


# Clear all fields in table 
                
		        sq.setTable("table_name").clearAllFields();


# To Update a Table

                     sq.setTable("table_name")
                .updateFields("field_name", "aaaa") //update using fieldname and text
                .updateFields("field_name3", 1) //update using fieldname and integer
                .update("field_name1", 2); //provides where to update can be any field with value

# Delete a Value 

                     sq.setTable("table_name").delete("field_name","value"); //deletes a value from field 


# Drop a table
                      sq.dropTable("table_name"); //drop a table
 
 # Rename a table
                  sq.renameTable("old_name","new_name");






# If you got any Error Submit it on Issue or email me  at avishekzone@gmail.com



	









