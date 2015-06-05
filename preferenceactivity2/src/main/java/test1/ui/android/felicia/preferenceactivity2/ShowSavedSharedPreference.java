package test1.ui.android.felicia.preferenceactivity2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class ShowSavedSharedPreference extends Activity {
    private Button buttonShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_saved_shared_preference);
        buttonShow = (Button) findViewById(R.id.buttonShowSavedPreferences);
        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this will retrieve the Shared Prenference
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                try {
                    String name = preferences.getString("list_preference", "");
                    Toast.makeText(getApplicationContext(), "retrieved name  = " + name, Toast.LENGTH_SHORT).show();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
  /*
    public void toShowSavedPreferences () {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        try {
            String name = preferences.getString("list_preference", "");
            Toast.makeText(this, "retrieved name  = " + name, Toast.LENGTH_SHORT).show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
