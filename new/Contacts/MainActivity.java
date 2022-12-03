package my.app.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    String[] permissions = new String[]{Manifest.permission.READ_CONTACTS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermissions();
    }

    public void get(View v)
    {
        Intent i=new Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Cursor cursor = null;
            String phoneNumber = "";
            List<String> allNumbers = new ArrayList<String>();
            int phoneIdx = 0;
            try {
                Uri result = data.getData();
                String id = result.getLastPathSegment();
                cursor = getContentResolver().query(Phone.CONTENT_URI, null, Phone.CONTACT_ID + "=?", new String[]{id}, null);
                phoneIdx = cursor.getColumnIndex(Phone.DATA);
                if (cursor.moveToFirst()) {
                    while (cursor.isAfterLast() == false) {
                        phoneNumber = cursor.getString(phoneIdx);
                        allNumbers.add(phoneNumber);
                        cursor.moveToNext();
                    }
                } else {
                    //no results actions
                }
            } catch (Exception e) {
                //error actions
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
                final EditText phoneInput = (EditText) findViewById(R.id.editText1);

                final CharSequence[] items = allNumbers.toArray(new String[allNumbers.size()]);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Choose a number");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        String selectedNumber = items[item].toString();
                        selectedNumber = selectedNumber.replace("-", "");
                        phoneInput.setText(selectedNumber);
                    }
                });
                AlertDialog alert = builder.create();
                if (allNumbers.size() > 1) {
                    alert.show();
                } else {
                    String selectedNumber = phoneNumber.toString();
                    selectedNumber = selectedNumber.replace("-", "");

                    phoneInput.setText(selectedNumber);
                }

                if (phoneNumber.length() == 0) {
                    //no numbers found actions
                }
            }

        }
    }

    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(MainActivity.this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(MainActivity.this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // do something
            }
            return;
        }
    }
}