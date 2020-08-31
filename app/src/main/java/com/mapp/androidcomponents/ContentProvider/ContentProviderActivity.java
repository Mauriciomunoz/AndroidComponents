package com.mapp.androidcomponents.ContentProvider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.room.Room;

import android.content.ContentProvider;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mapp.androidcomponents.R;
import com.mapp.androidcomponents.databinding.ActivityContentProviderBinding;
/*
 You need to build a content provider if you want to provide one or more of the following features:
    -You want to offer complex data or files to other applications.
    -You want to allow users to copy complex data from your app into other apps.
    -You want to provide custom search suggestions using the search framework.
    -You want to expose your application data to widgets.
    -You want to implement the AbstractThreadedSyncAdapter, CursorAdapter, or CursorLoader classes.

You don't need a provider to use databases or other types of persistent storage
if the use is entirely within your own application and you don’t need any of the features listed above.
 */

public class ContentProviderActivity extends AppCompatActivity implements View.OnClickListener{

    MyRoomDatabase myDB;
    ActivityContentProviderBinding binding;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_content_provider);
        binding.btnAdd.setOnClickListener(this);
        binding.btnRead.setOnClickListener(this);
        myDB = MyRoomDatabase.getInstance(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnAdd:

                //Add a new field in the Room Database
                String data="Information";

                MyDataFields myData=new MyDataFields();
                myData.setDataInfo(data);

                myDB.myDataDAO().addData(myData);
                break;
            case R.id.btnRead:

                //Took the URL to match with our content provider
                String URL = MyContentProvider.URL;//"content://com.mapp.androidcomponents.ContentProvider";

                Uri dataInformation = Uri.parse(URL);
                //Get the information from content provider
                Cursor c = getContentResolver().query(dataInformation, null, null, null, null);

                if (c.moveToLast()) {
                    do{
                        binding.dataInfo.setText(c.getString(c.getColumnIndex("data")));
                    } while (c.moveToNext());
                }

                break;
        }
    }

}