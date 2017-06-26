package studyfieldtechnologypvtltd.myralmdemo.adapters;

import android.content.Context;


import io.realm.RealmResults;
import studyfieldtechnologypvtltd.myralmdemo.model.Book;

public class RealmBooksAdapter extends RealmModelAdapter<Book> {

    public RealmBooksAdapter(Context context, RealmResults<Book> realmResults, boolean automaticUpdate) {

        super(context, realmResults, automaticUpdate);
    }
}