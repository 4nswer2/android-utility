package jp.gr.java_conf.atle42.androidutility;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by atle-tomoya on 2017/01/10.
 */

public abstract class BindableService extends Service {
	private final String TAG = "BindableService";

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "[onBind]");
		return new BaseServiceBinder(this);
	}

	//--------------------------------------------------
	//  inner class
	//--------------------------------------------------
	public class BaseServiceBinder extends Binder {
		private BindableService service;

		public BaseServiceBinder(BindableService service) {
			this.service = service;
		}

		public BindableService getService() {
			return service;
		}
	}
}
