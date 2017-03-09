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
		JSONHolder holder = JSONHolder.create(testRawJson);
		Log.d(TAG, "[asRawJson]" + holder.opt("json").asRawJson());
		assertEquals(answerRawJson, holder.opt("json").asRawJson());
	}

	@Test
	public void testAsList() throws Exception {
		JSONHolder holder = JSONHolder.create(testRawJson);
		assertEquals("{\"string\":\"long\",\"numerical\":9223372036854775807}", holder.opt("json", "json-array").asList().get(0).asRawJson());
		assertEquals("hoge", holder.opt("json", "string-array").asList().get(0).asString());
		assertEquals(true, holder.opt("json", "boolean-array").asList().get(0).asBoolean());
		assertEquals(9223372036854775807L, holder.opt("json", "long-array").asList().get(0).asLong().longValue());
		assertEquals(2147483647, holder.opt("json", "int-array").asList().get(0).asInt().intValue());
		assertEquals(34.6786737, holder.opt("json", "double-array").asList().get(0).asDouble().doubleValue(), 0.0);
	}

	//--------------------------------------------------
	//  test rawJson
	//--------------------------------------------------
	private String testRawJson =
			"{\n" +
			"  \"json\" : {\n" +
			"    \"string\" : \"test\",\n" +
			"    \"boolean\" : false,\n" +
			"    \"numerical\" : 65535,\n" +
			"    \"json-array\" : [\n" +
			"      { \"string\" : \"long\", \"numerical\" : 9223372036854775807 },\n" +
			"      { \"string\" : \"int\", \"numerical\" : 2147483647 },\n" +
			"      { \"string\" : \"double\", \"numerical\" : 34.6820959 }\n" +
			"    ],\n" +
			"    \"string-array\" : [\n" +
			"      \"hoge\",\n" +
			"      \"fuga\"\n" +
			"    ],\n" +
			"    \"boolean-array\" : [\n" +
			"      true,\n" +
			"      false\n" +
			"    ],\n" +
			"    \"long-array\" : [\n" +
			"      9223372036854775807,\n" +
			"      9223372036854775806\n" +
			"    ],\n" +
			"    \"int-array\" : [\n" +
			"      2147483647,\n" +
			"      2147483646\n" +
			"    ],\n" +
			"    \"double-array\" : [\n" +
			"      34.6786737,\n" +
			"      135.177107\n" +
			"    ]\n" +
			"  }\n" +
			"}";

	private String answerRawJson     = "{\"string\":\"test\",\"boolean\":false,\"numerical\":65535,\"json-array\":[{\"string\":\"long\",\"numerical\":9223372036854775807},{\"string\":\"int\",\"numerical\":2147483647},{\"string\":\"double\",\"numerical\":34.6820959}],\"string-array\":[\"hoge\",\"fuga\"],\"boolean-array\":[true,false],\"long-array\":[9223372036854775807,9223372036854775806],\"int-array\":[2147483647,2147483646],\"double-array\":[34.6786737,135.177107]}";
}
