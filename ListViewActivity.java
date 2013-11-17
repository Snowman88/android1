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
		// helper��Instance
		ListViewEntity helper = new ListViewEntity(getApplicationContext());
		// adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
		// DB object���擾
		SQLiteDatabase db = helper.getReadableDatabase(); // or getWriteableDatabase()
		// insert
		ContentValues values = new ContentValues();
		values.put("name", "�z���_�@�P�C�X�P");
		values.put("age", 38);
		long id = db.insert("person_table", null, values);
		// update
		values = new ContentValues();
		values.put("name", "�z���_�@�P�C�X�P2");
		values.put("age", 48);
		db.update("person_table", values, "name= ?", new String[] {"�z���_�@�P�C�X�P"});
		Cursor c = null;
		List<Area> list = null;
		try{
			// adapter��DB����擾�����f�[�^��}��
			list = new ArrayList<Area>();
			c = db.query("person_table", new String[] {"id","name", "age"}, null, null, null, null, null);
			while(c.moveToNext()){
				list.add(new Area(c.getString(1), c.getInt(2), c.getInt(0)));
				// adapter.add(String.format("%d: %s %d��", c.getInt(0), c.getString(1), c.getInt(2)));
			}
		}
		finally{
			// ���
			if(c != null){
				c.close();
			}
			if(db != null){
				db.close();
			}
		}
		
		
		
		// adapter��set
		lv.setAdapter(new AreaListAdapter(getApplicationContext(), list));
		
		Button saveBtn = (Button) findViewById(R.id.addBtn);
		saveBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText nameField = (EditText) findViewById(R.id.nameField);
				EditText ageField = (EditText) findViewById(R.id.ageField);
				ListViewEntity helper = new ListViewEntity(ListViewActivity.this);
				// helper�̃C���X�^���X
				SQLiteDatabase db = helper.getWritableDatabase();
				
				// insert�̂��߂̃f�[�^
				ContentValues values = new ContentValues();
				values.put("name",	nameField.getText().toString());
				values.put("age", ageField.getText().toString());
				try{
					// insert
					long id = db.insert("person_table", null, values);
					if(id > 0){
						// insert���ListView�ɔ��f
//						adapter.add(String.format("%d: %s %d��",
//												id, 
//												nameField.getText().toString(), 
//												Integer.valueOf(ageField.getText().toString())
//												)
//									);
					}
				}
				finally{
					// ���
					if(db != null){
						db.close();
					}
				}
				// EditText����ɂ���
				nameField.setText("");
				ageField.setText("");
			}
		});
		// listView���N���b�N���ꂽ��
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				ListView listView = (ListView) parent;
				// ListView�̓��e���擾
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
