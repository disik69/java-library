package ua.pp.disik;

import feign.Logger;
import feign.Request;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Slf4jFeignLogger extends Logger {
    @Override
	protected void logRequest(String configKey, Level logLevel, Request request) {
		if (log.isDebugEnabled()) {
			super.logRequest(configKey, logLevel, request);
		}
	}

    @Override
    protected void log(String configKey, String format, Object... args) {
        log.debug(String.format(methodTag(configKey) + format, args));
    }
}
