package by.pzh.jedi.activity.gateway;

import static com.netflix.hystrix.HystrixCommandProperties.defaultSetter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

import feign.Feign;
import feign.Logger.Level;
import feign.Request.Options;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.hystrix.FallbackFactory;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;

public class ServiceFactory {

	public static <T> Builder<T> builder(Class<T> clazz, String url) {
		return new Builder<>(clazz, url);
	}

	public static <T> T factory(Class<T> clazz, String url, FallbackFactory<T> fallback) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		return new Builder<>(clazz, url).encoder(new JacksonEncoder(mapper)).decoder(new JacksonDecoder(mapper))
				.fallback(fallback).build();
	}

	public static class Builder<T> {

		private final Class<T> clazz;
		private final String url;
		private FallbackFactory<T> fallback;
		private Encoder encoder;
		private Decoder decoder;

		public Builder(Class<T> clazz, String url) {
			this.clazz = clazz;
			this.url = url;
			this.encoder = new feign.codec.Encoder.Default();
			this.decoder = new feign.codec.Decoder.Default();
		}

		public Builder<T> encoder(Encoder encoder) {
			this.encoder = encoder;
			return this;
		}

		public Builder<T> decoder(Decoder decoder) {
			this.decoder = decoder;
			return this;
		}

		public Builder<T> fallback(FallbackFactory<T> fallback) {
			this.fallback = fallback;
			return this;
		}

		public T build() {
			Logger logger = LogManager.getLogger(clazz);
			logger.info(url);
			int timeout = 5 * 60 * 1000;
			final HystrixFeign.Builder builder = HystrixFeign.builder().setterFactory((target, method) -> {
				String groupKey = target.type().getSimpleName();
				String commandKey = Feign.configKey(clazz, method);
				return HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
						.andCommandKey(HystrixCommandKey.Factory.asKey(commandKey)).andCommandPropertiesDefaults(
								defaultSetter().withExecutionIsolationThreadInterruptOnTimeout(true)
										.withExecutionTimeoutInMilliseconds(timeout));
			}).options(new Options(timeout, timeout)).encoder(encoder).decoder(decoder).logLevel(Level.FULL)
					.logger(new Slf4jLogger(clazz));
			if (fallback != null) {
				return builder.target(clazz, url, fallback);
			}
			return builder.target(clazz, url);
		}
	}
}
