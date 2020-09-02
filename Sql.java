package com.umang.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

public class Sql {
	private final String database_name="College";
	private final String table1="my_college";
	private final int version=1;
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	private final String S_No="S_No";
	private final String Roll="Roll_No";
	private final String Name="Student_Name";
	private final String Age="Student_Age";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	Context c;
	umang u;
	SQLiteDatabase s;
	
	private class umang extends SQLiteOpenHelper
	{

		public umang(Context context) {
			
			super(context, database_name, null, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase data) {
			String abc="CREATE TABLE "+table1+" ( "+S_No+" INTEGER PRIMARY KEY AUTOINCREMENT, "
			+Roll+" TEXT NOT NULL, "+
		Name+" TEXT NOT NULL, "+Age+" TEXT NOT NULL );"	;
			data.execSQL(abc);
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
		
		
	}

	public Sql(Context sqlite_UmangActivity) {
		c=sqlite_UmangActivity;
		// TODO Auto-generated constructor stub
	}

	public void savemydata(String roll, String name, String age)throws SQLException {
		// TODO Auto-generated method stub
		ContentValues values=new ContentValues();
		values.put(Roll, roll);
		values.put(Name, name);
		values.put(Age, age);
		s.insert(table1, null, values);
	}
	
	public void open()throws SQLException {
		u=new umang(c);
		s=u.getWritableDatabase();
		// TODO Auto-generated method stub
		
	}

	public void close() throws SQLException {
		// TODO Auto-generated method stub
		s.close();
	}

	public String getmydata() {
		String st="";
		String[] columns={S_No,Roll,Name,Age};
		Cursor cu=s.query(table1, columns, null, null, null, null, null);
		cu.moveToFirst();
		int snum=cu.getColumnIndex(S_No);
		int rol=cu.getColumnIndex(Roll);
		int nam=cu.getColumnIndex(Name);
		int ag=cu.getColumnIndex(Age);
		for(cu.moveToFirst();!cu.isAfterLast();cu.moveToNext())
		{
		st=st+cu.getString(snum)+" "+cu.getString(rol)+" "+cu.getString(nam)+" "+cu.getString(ag)+"\n";	
		}
		
		// TODO Auto-generated method stub
		return st;
	}

	
	

	public void deletemydata(String string) {
		s.delete(table1, S_No+"="+string, null);
		// TODO Auto-generated method stub
		
	}

	public void updatemydata(String string, String string2, String string3,
			String string4) {
		// TODO Auto-generated method stub
		ContentValues values1=new ContentValues();
		values1.put(Roll, string2);
		values1.put(Age, string3);
		values1.put(Name, string4);
		s.update(table1, values1, S_No+"="+string, null);
		
	}

	

	
}
