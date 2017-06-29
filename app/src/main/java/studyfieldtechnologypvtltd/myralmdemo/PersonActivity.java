package studyfieldtechnologypvtltd.myralmdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import studyfieldtechnologypvtltd.myralmdemo.app.MyApplication;
import studyfieldtechnologypvtltd.myralmdemo.model.Person;
import studyfieldtechnologypvtltd.myralmdemo.realm.RealmController;

public class PersonActivity extends AppCompatActivity {

    private Button btnSaveData;
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtWebsite;
    private EditText edtAge;
    private Realm realm;
    private TextInputLayout edtNameWrapper;
    private TextInputLayout edtEmailWrapper;
    private TextInputLayout edtWebsiteWrapper;
    private TextInputLayout edtAgeWrapper;
    private RecyclerView rv_persondata;
    private Context context = this;

    private ArrayList<Person> list_person = new ArrayList<Person>();
    private AdapterDisplayPersonDetails adapter;
    private String TAG = PersonActivity.class.getSimpleName();

      long POSITION;


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

        edtNameWrapper = (TextInputLayout) findViewById(R.id.edtNameWrapper);
        edtEmailWrapper = (TextInputLayout) findViewById(R.id.edtEmailWrapper);
        edtWebsiteWrapper = (TextInputLayout) findViewById(R.id.edtWebsiteWrapper);
        edtAgeWrapper = (TextInputLayout) findViewById(R.id.edtAgeWrapper);

        edtName = (EditText) findViewById(R.id.edtName);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtWebsite = (EditText) findViewById(R.id.edtWebsite);
        edtAge = (EditText) findViewById(R.id.edtAge);


        rv_persondata = (RecyclerView) findViewById(R.id.rv_persondata);
        LinearLayoutManager lManager = new LinearLayoutManager(this);
        rv_persondata.setLayoutManager(lManager);


        //get realm instance
        this.realm = Realm.getDefaultInstance();


        adapter = new AdapterDisplayPersonDetails();
        btnSaveData = (Button) findViewById(R.id.btnSaveData);


        FillDataOnRecylerView();
        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isError = false;
                if (edtName.getText().toString().equals("")) {


                    isError = true;
                    edtNameWrapper.setErrorEnabled(true);
                    edtNameWrapper.setError("Enter Name");
                } else {
                    edtNameWrapper.setErrorEnabled(false);

                }

                if (edtEmail.getText().toString().equals("")) {
                    isError = true;


                    edtEmailWrapper.setErrorEnabled(true);
                    edtEmailWrapper.setError("Enter Email");
                } else {
                    edtEmailWrapper.setErrorEnabled(false);

                }

                if (edtWebsite.getText().toString().equals("")) {
                    isError = true;


                    edtWebsiteWrapper.setErrorEnabled(true);
                    edtWebsiteWrapper.setError("Enter Website");
                } else {
                    edtWebsiteWrapper.setErrorEnabled(false);

                }

                if (edtAge.getText().toString().equals("")) {
                    isError = true;


                    edtAgeWrapper.setErrorEnabled(true);
                    edtAgeWrapper.setError("Enter Age");
                } else {
                    edtAgeWrapper.setErrorEnabled(false);

                }

                if (isError == false) {

                    if(btnSaveData.getText().toString().toLowerCase().equals("update data"))
                    {

                        RealmResults<Person> results = realm.where(Person.class).findAll();


                        realm.beginTransaction();

                        Toast.makeText(context, "Position  : "+POSITION, Toast.LENGTH_SHORT).show();
                        results.get((int) POSITION).setPersonEmail(edtEmail.getText().toString());
                        results.get((int) POSITION).setPersonName(edtName.getText().toString());
                        results.get((int) POSITION).setPersonWebsite(edtWebsite.getText().toString());
                        results.get((int) POSITION).setPersonAge(Integer.parseInt(edtAge.getText().toString()));

                        realm.commitTransaction();

                        Toast.makeText(PersonActivity.this, "Person details has been updated", Toast.LENGTH_SHORT).show();

                        edtAge.setText("");
                        edtName.setText("");
                        edtWebsite.setText("");
                        edtEmail.setText("");



                    }
                    else
                    {
                        realm.beginTransaction();
                        Person person = new Person();
                        person.setPersonid(realm.where(Person.class).findAll().size() + 1);
                        person.setPersonAge(Integer.parseInt(edtAge.getText().toString()));
                        person.setPersonEmail(edtEmail.getText().toString());
                        person.setPersonWebsite(edtWebsite.getText().toString());
                        person.setPersonName(edtName.getText().toString());

                        realm.copyToRealm(person);
                        realm.commitTransaction();

                        Toast.makeText(PersonActivity.this, "Person details has been added", Toast.LENGTH_SHORT).show();

                        edtAge.setText("");
                        edtName.setText("");
                        edtWebsite.setText("");
                        edtEmail.setText("");


                    }



                    // realm.refresh();


                    FillDataOnRecylerView();

                    /*realm.beginTransaction();
                    RealmList<Person> persons = realm.where(Person.class).findAll();*/


                }


            }
        });


    }

    private void FillDataOnRecylerView() {

        realm.refresh();

        realm.beginTransaction();


        RealmResults<Person> personsData = realm.where(Person.class).findAll();
        //personsData.deleteAllFromRealm();
        realm.commitTransaction();

        Log.d(TAG, "Total " + personsData.size() + " Records Found in person master");
        list_person.clear();
        for (int i = 0; i < personsData.size(); i++) {
            /*Person per = new Person();
            per.setPersonid(personsData.get(i).getPersonid());
            per.setPersonAge(personsData.get(i).getPersonAge());
            per.setPersonName(personsData.get(i).getPersonName());
            per.setPersonWebsite(personsData.get(i).getPersonWebsite());
            per.setPersonEmail(personsData.get(i).getPersonEmail());*/

            Person per = new Person(personsData.get(i).getPersonid(), personsData.get(i).getPersonName(), personsData.get(i).getPersonEmail(), personsData.get(i).getPersonWebsite(), personsData.get(i).getPersonAge());


            list_person.add(per);
        }
        adapter = new AdapterDisplayPersonDetails();
        rv_persondata.setAdapter(adapter);


    }


    /**
     * Custom Adapte For dispaly Person Details
     */


    public class AdapterDisplayPersonDetails extends RecyclerView.Adapter<AdapterDisplayPersonDetails.MyViewHolder> {


        public class MyViewHolder extends RecyclerView.ViewHolder {

            private final EditText edtNameH, edtEmailH, edtWebsiteH, edtAgeH;
            private final Button btnEdit;
            private final Button btnDelete;

            public MyViewHolder(View itemView) {
                super(itemView);

                edtNameH = (EditText) itemView.findViewById(R.id.edtName);
                edtEmailH = (EditText) itemView.findViewById(R.id.edtEmail);
                edtWebsiteH = (EditText) itemView.findViewById(R.id.edtWebsite);
                edtAgeH = (EditText) itemView.findViewById(R.id.edtAge2);

                btnEdit = (Button) itemView.findViewById(R.id.btnEdit);
                btnDelete = (Button) itemView.findViewById(R.id.btnDelete);


            }
        }

        @Override
        public AdapterDisplayPersonDetails.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            View view = LayoutInflater.from(context).inflate(R.layout.row_single_person, parent, false);
            MyViewHolder v = new MyViewHolder(view);
            return v;
        }

        @Override
        public void onBindViewHolder(AdapterDisplayPersonDetails.MyViewHolder holder, final int position) {

            try {
                Person person = list_person.get(position);
                Log.d(TAG, "Perosn Age : " + person.getPersonEmail().toString());
                Log.d(TAG, "Perosn Age : " + list_person.get(position).getPersonAge());
                holder.edtAgeH.setText(String.valueOf(list_person.get(position).getPersonAge()));
                holder.edtEmailH.setText(list_person.get(position).getPersonEmail());
                holder.edtNameH.setText(list_person.get(position).getPersonName());
                holder.edtWebsiteH.setText(list_person.get(position).getPersonWebsite());

                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(context, "Records Deleted", Toast.LENGTH_SHORT).show();

                        realm.beginTransaction();
                RealmResults<Person> per = realm.where(Person.class).findAll();
                /*        Person pp = per.get(position);
                        pp.deleteFromRealm();
*/
                        per.deleteFromRealm(position);
                        realm.commitTransaction();


                        FillDataOnRecylerView();

                    }
                });


                holder.btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(context, "Record Edited", Toast.LENGTH_SHORT).show();

                        edtEmail.setText(list_person.get(position).getPersonEmail());
                        edtWebsite.setText(list_person.get(position).getPersonWebsite());
                        edtAge.setText(String.valueOf(list_person.get(position).getPersonAge()));
                        edtName.setText(list_person.get(position).getPersonName());

                        POSITION  =position;

                        Toast.makeText(context, "Position Holder : "+POSITION, Toast.LENGTH_SHORT).show();

                        btnSaveData.setText("Update Data");
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        @Override
        public int getItemCount() {
            return list_person.size();
        }
    }
}
