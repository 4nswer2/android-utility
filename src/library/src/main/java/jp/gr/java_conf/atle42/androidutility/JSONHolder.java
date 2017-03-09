package jp.gr.java_conf.atle42.androidutility;

import com.annimon.stream.Stream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by murata_to on 2017/01/27.
 */

public class JSONHolder {
	private static final Object NULL = null;

	private final Object current;

	//--------------------------------------------------
	//  create method
	//--------------------------------------------------
	public static JSONHolder create(String rawJson) {
		Object object = null;
		try {
			object = new JSONObject(rawJson);
		} catch (JSONException e) {
			try { object = new JSONArray(rawJson); } catch (JSONException e1) {}
		}
		return new JSONHolder(object);
	}

	public static JSONHolder create(JSONObject object) {
		return new JSONHolder(object);
	}

	public static JSONHolder create(JSONArray array) {
		return new JSONHolder(array);
	}

	//--------------------------------------------------
	//  constructor
	//--------------------------------------------------
	private JSONHolder(Object object) {
		this.current = object;
	}

	//--------------------------------------------------
	//  public method
	//--------------------------------------------------
	public JSONHolder get(String name) throws JSONException {
		return new JSONHolder(current instanceof JSONObject ? ((JSONObject)current).get(name) : NULL);
	}

	public JSONHolder get(int index) throws JSONException {
		return new JSONHolder(current instanceof JSONArray  ? ((JSONArray)current).get(index) : NULL);
	}

	public JSONHolder opt(String name) {
		return new JSONHolder(current instanceof JSONObject ? ((JSONObject)current).opt(name) : NULL);
	}

	public JSONHolder opt(int index) {
		return new JSONHolder(current instanceof JSONArray  ? ((JSONArray)current).opt(index) : NULL);
	}

	public JSONHolder opt(List<Object> path) {
		return Stream.of(path).reduce(this, (holder, node) -> {
			if (node instanceof Integer) {
				return holder.opt((Integer) node);
			} else if (node instanceof String) {
				return holder.opt((String) node);
			} else {
				return holder;
			}
		});
	}

	public JSONHolder opt(Object... objects) {
		return opt(Arrays.asList(objects));
	}

	public boolean has(List<Object> path) {
		return opt(path).current == null ? false : true;
	}

	public boolean has(Object... path) {
		return has(Arrays.asList(path));
	}

	public boolean isNull() {
		return current == null ? true : false;
	}

	// デフォルト指定あり
	public List<JSONHolder> asList(List<JSONHolder> v) { return (current instanceof JSONArray)  ? toList((JSONArray) current)          : v; }
	public String           asRawJson(String v)        { return (current instanceof JSONObject) ? ((JSONObject) current).toString()    : v; }
	public String           asString(String v)         { return (current instanceof String)     ? (String)      current                : v; }
	public Boolean          asBoolean(Boolean v)       { return (current instanceof Boolean)    ? (Boolean)     current                : v; }
	public Long             asLong(Long v)             { return (current instanceof Number)     ? ((Number)     current).longValue()   : v; }
	public Integer          asInt(Integer v)           { return (current instanceof Number)     ? ((Number)     current).intValue()    : v; }
	public Double           asDouble(Double v)         { return (current instanceof Number)     ? ((Number)     current).doubleValue() : v; }

	// デフォルト指定なし
	public List<JSONHolder> asList()    { return asList(new ArrayList<>()); }
	public String           asRawJson() { return asRawJson(""); }
	public String           asString()  { return asString(""); }
	public Boolean          asBoolean() { return asBoolean(false); }
	public Long             asLong()    { return asLong((long)0); }
	public Integer          asInt()     { return asInt(0); }
	public Double           asDouble()  { return asDouble((double)0); }

	//--------------------------------------------------
	//  private method
	//--------------------------------------------------
	private List<JSONHolder> toList(JSONArray array) {
		List<JSONHolder> list = new ArrayList<>();
		if (array == null) { return list; }

		for (int i = 0; i < array.length(); i++) {
			Object object = null;
			try { object = array.get(i); } catch (JSONException e) {}
			if (object != null) { list.add(new JSONHolder(object)); }
		}

		return list;
	}

}
