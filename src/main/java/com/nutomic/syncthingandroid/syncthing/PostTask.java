package com.nutomic.syncthingandroid.syncthing;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

import java.io.IOException;

/**
 * Performs a POST request with no parameters to the URL in uri[0]  with the path in uri[1].
 */
public class PostTask extends AsyncTask<String, Void, Void> {

	private static final String TAG = "PostTask";

	public static final String URI_SHUTDOWN = "/rest/shutdown";

	/**
	 * params[0] Syncthing hostname
	 * params[1] URI to call
	 * params[2] Syncthing API key
	 */
	@Override
	protected Void doInBackground(String... params) {
		String fullUri = params[0] + params[1];
		Log.i(TAG, "Sending POST request to " + fullUri);
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost post = new HttpPost(fullUri);
		post.addHeader(new BasicHeader("X-API-Key", params[2]));
		try {
			httpclient.execute(post);
		}
		catch (IOException e) {
			Log.w(TAG, "Failed to call Rest API at " + fullUri, e);
		}
		return null;
	}

}
