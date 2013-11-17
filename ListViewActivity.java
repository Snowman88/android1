package com.example.mydatabaseproject;

import java.util.ArrayList;
import java.util.List;

import com.example.mydatabaseproject.adapter.AreaListAdapter;
import com.example.mydatabaseproject.entity.model.ListViewEntity;
import com.example.mydatabaseproject.model.Area;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewActivity extends Activity {
	ListView lv;
	// ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		
		lv = (ListView) findViewById(R.id.listView1);
		// helperをInstance
		ListViewEntity helper = new ListViewEntity(getApplicationContext());
		// adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
		// DB objectを取得
		SQLiteDatabase db = helper.getReadableDatabase(); // or getWriteableDatabase()
		// insert
		ContentValues values = new ContentValues();
		values.put("name", "ホンダ　ケイスケ");
		values.put("age", 38);
		long id = db.insert("person_table", null, values);
		// update
		values = new ContentValues();
		values.put("name", "ホンダ　ケイスケ2");
		values.put("age", 48);
		db.update("person_table", values, "name= ?", new String[] {"ホンダ　ケイスケ"});
		Cursor c = null;
		List<Area> list = null;
		try{
			// adapterにDBから取得したデータを挿入
			list = new ArrayList<Area>();
			c = db.query("person_table", new String[] {"id","name", "age"}, null, null, null, null, null);
			while(c.moveToNext()){
				list.add(new Area(c.getString(1), c.getInt(2), c.getInt(0)));
				// adapter.add(String.format("%d: %s %d才", c.getInt(0), c.getString(1), c.getInt(2)));
			}
		}
		finally{
			// 解放
			if(c != null){
				c.close();
			}
			if(db != null){
				db.close();
			}
		}
		
		
		
		// adapterにset
		lv.setAdapter(new AreaListAdapter(getApplicationContext(), list));
		
		Button saveBtn = (Button) findViewById(R.id.addBtn);
		saveBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText nameField = (EditText) findViewById(R.id.nameField);
				EditText ageField = (EditText) findViewById(R.id.ageField);
				ListViewEntity helper = new ListViewEntity(ListViewActivity.this);
				// helperのインスタンス
				SQLiteDatabase db = helper.getWritableDatabase();
				
				// insertのためのデータ
				ContentValues values = new ContentValues();
				values.put("name",	nameField.getText().toString());
				values.put("age", ageField.getText().toString());
				try{
					// insert
					long id = db.insert("person_table", null, values);
					if(id > 0){
						// insert後にListViewに反映
//						adapter.add(String.format("%d: %s %d才",
//												id, 
//												nameField.getText().toString(), 
//												Integer.valueOf(ageField.getText().toString())
//												)
//									);
					}
				}
				finally{
					// 解放
					if(db != null){
						db.close();
					}
				}
				// EditTextを空にする
				nameField.setText("");
				ageField.setText("");
			}
		});
		// listViewがクリックされた時
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				ListView listView = (ListView) parent;
				// ListViewの内容を取得
				//String item = (String) listView.getItemAtPosition(position); 
				TextView tv = (TextView) findViewById(R.id.list_item);
				tv.setTag("foo");
				Toast.makeText(getApplicationContext(), tv.getTag().toString(), Toast.LENGTH_SHORT).show();
				Area itemArea = (Area) listView.getAdapter().getItem(position); 
				Toast.makeText(getApplicationContext(), 
						"Name: " + itemArea.getName() +
						", Age: " + String.valueOf(itemArea.getAge()) + 
						", id:  " + String.valueOf(itemArea.getId()),
						Toast.LENGTH_SHORT).show();
			}
		});
		
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_view, menu);
		return true;
	}

}
