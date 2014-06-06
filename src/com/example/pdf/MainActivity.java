package com.example.pdf;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends Activity {
	
	public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
	private ProgressDialog mProgressDialog;
	Button pdf_download, pdf_view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		
		pdf_download = (Button)findViewById(R.id.Download);
		pdf_view = (Button)findViewById(R.id.pdf_view);
		
		pdf_download.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DownloadFileAsync().execute();
				
			}
		});
		
		pdf_view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				showPdf();
				
//				setDocumentPath();
				
			}
		});
	}
	 
	 @Override
	 protected Dialog onCreateDialog(int id) 
	 {
	      switch (id) 
	      {
			case DIALOG_DOWNLOAD_PROGRESS:
					mProgressDialog = new ProgressDialog(this);
					mProgressDialog.setMessage("Downloading file..");
					mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
					mProgressDialog.setCancelable(false);
					mProgressDialog.show();
					return mProgressDialog;
			default:
				return null;
	        }
	    }
	 
	 
	 class DownloadFileAsync extends AsyncTask<String, String, String> {
		   
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				showDialog(DIALOG_DOWNLOAD_PROGRESS);
			}

			@Override
			protected String doInBackground(String... aurl) {
	  
				 String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
				 File folder = new File(extStorageDirectory, "pdf");
				 folder.mkdir();
				 File file = new File(folder, "Read_pdf.pdf");
				 try 
				 {
					file.createNewFile();
				 } 
				 catch (IOException e1) 
				 {
					e1.printStackTrace();
				 }
				
				 Downloader.DownloadFile("http://www.tutorialspoint.com/android/android_tutorial.pdf", file);
				 
			 
			return null;

			}
			protected void onProgressUpdate(String... progress) 
			{
				 mProgressDialog.setProgress(Integer.parseInt(progress[0]));
			}

			@Override
			protected void onPostExecute(String unused)
			{
				dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
			}
		}
	 
	 
	 
	 public void setDocumentPath() 
	 {
	     WebView webView = (WebView) findViewById(R.id.webview);
	     webView.getSettings().setJavaScriptEnabled(true);
	      
	     webView.loadUrl("https://docs.google.com/viewer?url=http://www.selab.isti.cnr.it/ws-mate/example.pdf");
	      
	 }

	    
	public void showPdf()
	    {
//			WebView webView = (WebView) findViewById(R.id.webview);
//	        webView.getSettings().setJavaScriptEnabled(true); 
//	        webView.getSettings().setPluginsEnabled(true);
//	        File file = new File(Environment.getExternalStorageDirectory()+"/pdf/Readdd.pdf");
//	        Uri uri = Uri.fromFile(file);
//	        webView.loadUrl(uri.toString());
	    }
}