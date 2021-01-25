package com.example.sqlite_apptronix;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Sql {

	String database = "apptronix";
	String table = "table_name";
	int version = 1;

	String s_no = "S_No";
	String name = "name";
	String marks = "marks";

	SQLiteDatabase s;
	Context c;
	umang u;

	public class umang extends SQLiteOpenHelper {

		public umang(Context context) {
			super(context, database, null, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase arg0) {
			// TODO Auto-generated method stub
			String abc = "CREATE TABLE " + table + " ( " + s_no
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + name
					+ " TEXT NOT NULL, " + marks + " TEXT NOT NULL );";
			arg0.execSQL(abc);
		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub

		}

	}

	public Sql(MainActivity mainActivity) {
		c = mainActivity;
		// TODO Auto-generated constructor stub
	}

	public void insert(String n, String m) {
		// TODO Auto-generated method stub
		ContentValues values = new ContentValues();
		values.put(name, n);
		values.put(marks, m);
		s.insert(table, null, values);
	}

	public void open() {
		// TODO Auto-generated method stub
		u = new umang(c);
		s = u.getWritableDatabase();
	}

	public void close() {
		// TODO Auto-generated method stub
		s.close();
	}

	public void delete(String string) {
		// TODO Auto-generated method stub
		s.delete(table, s_no + "=" + string, null);
	}

	public void update(String string, String string2, String string3) {
		// TODO Auto-generated method stub
		ContentValues values1 = new ContentValues();
		values1.put(name, string2);
		values1.put(marks, string3);
		s.update(table, values1, s_no + "=" + string, null);
	}

	public String view() {
		String st = "";
		String[] columns = { s_no, name, marks };
		Cursor cu = s.query(table, columns, null, null, null, null, null); // cursor
																			// is
																			// used
																			// to
																			// read
																			// data
		cu.moveToFirst(); // move to the first row
		int snum = cu.getColumnIndex(s_no);
		int na = cu.getColumnIndex(name);
		int ag = cu.getColumnIndex(marks);
		for (cu.moveToFirst(); !cu.isAfterLast(); cu.moveToNext()) {
			st = st + cu.getString(snum) + " " + cu.getString(na) + " "
					+ cu.getString(ag) + " " + "\n";
		}
		// TODO Auto-generated method stub
		return st;
	}

	public Cursor getinfo() {
		s = u.getReadableDatabase();
		String[] columns = { name, marks };
		Cursor CR = s.query(table, columns, null, null, null, null, null);
		return CR;
		// TODO Auto-generated method stub

	}

}
