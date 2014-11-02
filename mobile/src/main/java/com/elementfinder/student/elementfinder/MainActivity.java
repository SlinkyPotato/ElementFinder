package com.elementfinder.student.elementfinder;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity {
    private AutoCompleteTextView actv;
    private ImageView imgElement;
    private String element;
    private String[] elements;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actv = (AutoCompleteTextView) findViewById(R.id.atxtElement);
        imgElement = (ImageView) findViewById(R.id.imgElement);

        //Getting the array of elements
        elements = getResources().getStringArray(R.array.arry_elements);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, elements);

        //Using the element array for suggestions
        actv.setAdapter(adapter);

        //Setting the keyListener
        actv.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    element = actv.getText().toString().toLowerCase();
                    //This line is important
                    int id = getResources().getIdentifier(element, "drawable", getPackageName());
                    imgElement.setImageResource(id); //element can't be directly placed

                    //Hides the keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                    return true;
                }
                return false;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

}