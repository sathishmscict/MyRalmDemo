package studyfieldtechnologypvtltd.myralmdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;
import studyfieldtechnologypvtltd.myralmdemo.realm.RealmController;

public class PersonActivity extends AppCompatActivity {

    private Button btnSaveData;
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtWebsite;
    private EditText edtAge;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        try {
            getSupportActionBar().show();
            getSupportActionBar().setHomeButtonEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        edtName  =(EditText)findViewById(R.id.edtName);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtWebsite  =(EditText)findViewById(R.id.edtWebsite);
        edtAge = (EditText)findViewById(R.id.edtAge);



        //get realm instance
        this.realm = RealmController.with(this).getRealm();




        btnSaveData = (Button)findViewById(R.id.btnSaveData);

        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

            }
        });






    }

}
