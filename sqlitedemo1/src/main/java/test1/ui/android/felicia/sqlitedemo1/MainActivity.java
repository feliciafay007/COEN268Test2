package test1.ui.android.felicia.sqlitedemo1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends Activity {
    private Button buttonShow;
    private Button buttonAdd ;
    private Button buttonDelete;
    private Button buttonUpdate;
    private EditText editTextName;
    private EditText editTextDes;
    private EditText editTextPath;
    private EditText editTextUpdate;
    private EditText editTextDelete;
    private TextView textViewShow;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonShow = (Button) findViewById(R.id.buttonShow);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextDes = (EditText) findViewById(R.id.editTextDes);
        editTextPath= (EditText) findViewById(R.id.editTextPath);
        editTextUpdate= (EditText) findViewById(R.id.editTextUpdate);
        editTextDelete = (EditText) findViewById(R.id.editTextDelete);
        textViewShow = (TextView)  findViewById(R.id.textViewShow);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);

        db = new ZooDbHelper(getApplicationContext()).getWritableDatabase();

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String where = null;
                String whereArgs[] = null;
                String groupBy = null;
                String having = null;
                String order = null;
                String[] resultColumns = {ZooDbHelper.ID_COLUMN, ZooDbHelper.NAME_COLUMN,
                        ZooDbHelper.DESCRIPTION_COLUMN, ZooDbHelper.FILE_PATH_COLUMN};
                Cursor cursor = db.query(ZooDbHelper.DATABASE_TABLE, resultColumns, where,
                        whereArgs, groupBy, having, order);
                StringBuilder sb = new StringBuilder();
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    String description = cursor.getString(2);
                    String filepath = cursor.getString(3);
                    sb.append(id).append(", ").append(name).append(", ").append(description);
                   // Log.d("ZOO", String.format("%s,%s,%s,%s", id, name, description,filepath));
                }
                sb.append("\n");
                textViewShow.setText(sb.toString());
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentName = editTextName.getText().toString();
                String currentDes= editTextDes.getText().toString();
                String currentPath= editTextPath.getText().toString();
                SQLiteDatabase db = new ZooDbHelper(getApplicationContext()).getWritableDatabase();
                ContentValues newValues = new ContentValues();
                if (currentName == null ||
                    currentDes == null ||
                    currentPath == null ||
                    currentName.isEmpty() ||
                    currentDes.isEmpty() ||
                    currentPath.isEmpty())
                {
                    newValues.put(ZooDbHelper.NAME_COLUMN, "alpaca");
                    newValues.put(ZooDbHelper.DESCRIPTION_COLUMN, "An alpaca looks like a lama.");
                    newValues.put(ZooDbHelper.FILE_PATH_COLUMN, "/storage/alpaca.png");
                } else {
                    newValues.put(ZooDbHelper.NAME_COLUMN, currentName);
                    newValues.put(ZooDbHelper.DESCRIPTION_COLUMN, currentDes);
                    newValues.put(ZooDbHelper.FILE_PATH_COLUMN, currentPath);
                }
                db.insert(ZooDbHelper.DATABASE_TABLE, null, newValues);
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idInEditText = editTextUpdate.getText().toString();
                String whereClause = ZooDbHelper.ID_COLUMN + "= ?";
                String[] whereArgs = {idInEditText};
                ContentValues newValues = new ContentValues();
                newValues.put(ZooDbHelper.NAME_COLUMN, "alpaca");
                newValues.put(ZooDbHelper.DESCRIPTION_COLUMN, "An alpaca is ugly.");
                newValues.put(ZooDbHelper.FILE_PATH_COLUMN, "/storage/alpaca.png");
                db.update(ZooDbHelper.DATABASE_TABLE, newValues, whereClause, whereArgs);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String whereClause = ZooDbHelper.ID_COLUMN + "=?";
                //String[] whereArgs = {"4"};
                String idInEditText = editTextDelete.getText().toString();
                String[] whereArgs = {idInEditText};
                db.delete(ZooDbHelper.DATABASE_TABLE, whereClause, whereArgs);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.card_view) {
            Intent intent = new Intent(MainActivity.this, CardDisplayActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.CRUD_view) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
