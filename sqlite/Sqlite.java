package umang.kejriwal.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

public class Sqlite {
    String db_name = "mydata";
    String tb_name = "contact";
    int ver = 1;
    String id = "Id";
    String name = "Name";
    String mob = "Mobile";
    Helper help;
    SQLiteDatabase sql;
    Context c;

    public Sqlite(MainActivity mainActivity) {
        c = mainActivity;
    }

    public void insert(String na, String mo) {
        ContentValues cv = new ContentValues();
        cv.put(name,na);
        cv.put(mob,mo);
        sql.insert(tb_name,null,cv);
        Toast.makeText(c, "Data Inserted", Toast.LENGTH_SHORT).show();
    }

    public void open() {
        help = new Helper(c);
        sql = help.getWritableDatabase();
    }

    public void close() {
        sql.close();
    }

    public String viewMyData(){
        String data = "";
        String[] cols = {id,name,mob};
        Cursor cursor = sql.query(tb_name,cols,null,
                null,null,null,null);
        int i = cursor.getColumnIndex(id);
        int n = cursor.getColumnIndex(name);
        int m = cursor.getColumnIndex(mob);
        cursor.moveToFirst();
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            data = data + cursor.getString(i) + " " + cursor.getString(n)
                    + " " + cursor.getString(m) + "\n";
        }
        return data;
    }

    public void delete(String na) {
        String[] cols = {id,name};
        Cursor cursor = sql.query(tb_name,cols,null,
                null,null,null,null);
        int i = cursor.getColumnIndex(id);
        int n = cursor.getColumnIndex(name);
        cursor.moveToFirst();
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            if(cursor.getString(n).equals(na)){
                String id_name = cursor.getString(i);
                sql.delete(tb_name,id+"="+id_name,null);
                Toast.makeText(c, "Data Deleted", Toast.LENGTH_SHORT).show();
                break;
            }
        }

    }

    public void update(String na, String mo) {
        String[] cols = {id,name};
        Cursor cursor = sql.query(tb_name,cols,null,
                null,null,null,null);
        int i = cursor.getColumnIndex(id);
        int n = cursor.getColumnIndex(name);
        cursor.moveToFirst();
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            if(cursor.getString(n).equals(na)){
                String id_name = cursor.getString(i);
                ContentValues cv = new ContentValues();
                cv.put(mob,mo);
                sql.update(tb_name,cv,id+"="+id_name,null);
                Toast.makeText(c, "Data Updated", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    public class Helper extends SQLiteOpenHelper{

        public Helper(Context context) {
            super(context, db_name, null, ver);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String query = "create table "+tb_name+"("+id+" integer primary key,"+name+
                    " text not null,"+mob+" text not null);";
            sqLiteDatabase.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

}
