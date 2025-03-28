package org.proto.proto.context;

import org.proto.proto.strategy.ApiStrategy;

public class ApiContext {
    private ApiStrategy apiStrategy;

    public ApiContext(ApiStrategy apiStrategy) {
        this.apiStrategy = apiStrategy;
    }
}
