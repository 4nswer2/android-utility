package jp.gr.java_conf.atle42.androidutility;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by atle-tomoya on 2017/03/08.
 */

@RunWith(AndroidJUnit4.class)
public class JSONHolderTest {
	private static final String TAG = "JSONHolderTest";

	@Test
	public void testAsRawJson() throws Exception {
		JSONHolder holder = new JSONHolder(testRawJson);
		Log.d(TAG, "[asRawJson]" + holder.opt("json").asRawJson());
		assertEquals("{\"string\":\"test\",\"boolean\":false,\"numerical\":65535,\"array\":[{\"string\":\"long\",\"numerical\":9223372036854775807},{\"string\":\"int\",\"numerical\":2147483647},{\"string\":\"short\",\"numerical\":32767},{\"string\":\"double\",\"numerical\":34.6820959}]}", holder.opt("json").asRawJson());
	}

	@Test
	public void testAsRawJsonList() throws Exception {
		JSONHolder holder = new JSONHolder(testRawJson);
		assertEquals("{\"string\":\"long\",\"numerical\":9223372036854775807}", holder.opt("json", "array").asRawJsonList().get(0));
	}

	//--------------------------------------------------
	//  test rawJson
	//--------------------------------------------------
	private String testRawJson = "{\"json\":{\"string\":\"test\",\"boolean\":false,\"numerical\":65535,\"array\":[{\"string\":\"long\",\"numerical\":9223372036854775807},{\"string\":\"int\",\"numerical\":2147483647},{\"string\":\"short\",\"numerical\":32767},{\"string\":\"double\",\"numerical\":34.6820959}]}}";
}
