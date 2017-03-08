package jp.gr.java_conf.atle42.androidutility;

/**
 * Created by murata_to on 2017/03/01.
 */

public class Result<T> {
	public final T result;
	public final Error error;

	public Result(T result, Error error) {
		if (result == null && error == null) {
			error = new Error("result is null.");
		}
		this.result = result;
		this.error = error;
	}

	public <U> Result<U> convert(Converter<T, U> converter) {
		if (this.result == null) {
			return new Result<>(null, this.error);
		} else {
			U result = converter.convert(this.result);
			return new Result<>(result, null);
		}
	}

	public interface Converter<T, U> {
		U convert(T before);
	}
}
