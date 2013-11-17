package com.example.mydatabaseproject.entity.model;

import android.content.Context;
import android.content.Entity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ListViewEntity extends SQLiteOpenHelper {
	final static private int DB_VERSION = 1;
	public ListViewEntity(Context context){
		super(context, "entity.db", null, DB_VERSION);
	}
	@Override 
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "create table if not exists person_table ("
					+ "id integer primary key autoincrement, "
					+ "name text not null,"
					+ "age integer"
					+ ");";
		db.execSQL(sql);
		db.execSQL(
				"insert into person_table (name, age) values " +
				"('ñ{ìc å\óC', 24)," +
				"('âìì° ï€êm', 30)," +
				"('èºà‰ ëÂï„', 29);"
				);

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
