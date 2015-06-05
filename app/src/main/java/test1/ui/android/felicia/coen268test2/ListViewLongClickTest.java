package test1.ui.android.felicia.coen268test2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ListViewLongClickTest extends ActionBarActivity {
        ListView listView1;
        String contacts[]={"Ajay","Sachin","Sumit","Tarun","Yogesh"};
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list_view_long_click_test);
            listView1=(ListView)findViewById(R.id.listView1);
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contacts);
            listView1.setAdapter(adapter);
            // Register the ListView  for Context menu
            registerForContextMenu(listView1);
        }
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
        {
            super.onCreateContextMenu(menu, v, menuInfo);
            menu.setHeaderTitle("Select The Action");
            menu.add(0, v.getId(), 0, "Call");//groupId, itemId, order, title
            menu.add(0, v.getId(), 0, "SMS");
        }
        @Override
        public boolean onContextItemSelected(MenuItem item){
            if(item.getTitle()=="Call"){
                Toast.makeText(getApplicationContext(), "calling code", Toast.LENGTH_LONG).show();
            }
            else if(item.getTitle()=="SMS"){
                Toast.makeText(getApplicationContext(),"sending sms code",Toast.LENGTH_LONG).show();
            }else{
                return false;
            }
            return true;
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_list_view_long_click_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
//            case R.id.menu_forgot_password:
//                forgotPassword();
//                return true;
            case R.id.menu_share:
                share_your_app();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void share_your_app () {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Here is a nice app that we can use together";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hi, Let's use DogPlayDate");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

}