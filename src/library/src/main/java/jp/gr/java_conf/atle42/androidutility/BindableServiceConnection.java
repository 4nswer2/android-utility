package jp.gr.java_conf.atle42.androidutility;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Created by atle-tomoya on 2016/12/15.
 */

public abstract class BindableServiceConnection implements ServiceConnection {
	@Override
	public final void onServiceConnected(ComponentName name, IBinder service) {
		onBind(((BindableService.BaseServiceBinder)service).getService());
	}

	@Override public final void onServiceDisconnected(ComponentName name) {}

	public abstract void onBind(BindableService service);
}
