package com.example.myfirstproject;

import java.io.InputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ImageActivity extends Activity implements OnClickListener{
	private static final int REQUEST_GALLERY = 0;
	private ImageView imgView;
	private Button getImageBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		
		imgView = (ImageView) findViewById(R.id.imgview_id);
		
		
		
		getImageBtn = (Button) findViewById(R.id.button);
		getImageBtn.setOnClickListener(this);
		
		
		
	}
	@Override
	public void onClick(View v) {
		if(v == getImageBtn){
			String[] str_items = {"�ʐ^���B��", "�ʐ^��I��", "�ʐ^���폜", "�L�����Z��"};
		    new AlertDialog.Builder(this)
		        .setTitle("�I��")
		        .setItems(str_items, new DialogInterface.OnClickListener(){
		            public void onClick(DialogInterface dialog, int which) {
		                switch(which){
		                case 0:
		                    // // �ʐ^���B��
		                    break;
		                case 1:
		                    // // �ʐ^��I��
		        			Intent intent = new Intent();
		        			intent.setType("image/*");
		        			intent.setAction(Intent.ACTION_GET_CONTENT);
		        			startActivityForResult(intent, REQUEST_GALLERY);
		                    break;
		                case 2:
		                	// "�ʐ^���폜"
		                default:
		                    // �L�����Z��
		                    break;
		                }
		            }
		        }
		    ).show();
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQUEST_GALLERY && resultCode == RESULT_OK){
			try{
				InputStream in = getContentResolver().openInputStream(data.getData());
				Bitmap  img= BitmapFactory.decodeStream(in);
				in.close();
				
				imgView.setImageBitmap(img);
			}catch(Exception e){
				
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.check_box, menu);
		return true;
	}

}
