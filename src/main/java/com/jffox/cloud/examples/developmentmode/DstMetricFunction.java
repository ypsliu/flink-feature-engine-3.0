package com.jffox.cloud.examples.developmentmode;

import org.apache.flink.api.common.functions.IterationRuntimeContext;
import org.apache.flink.api.common.functions.RichFunction;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.configuration.Configuration;

public class DstMetricFunction implements RichFunction {
    @Override
    public void open(Configuration configuration) throws Exception {

    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public void setRuntimeContext(RuntimeContext runtimeContext) {

    }

    @Override
    public IterationRuntimeContext getIterationRuntimeContext() {
        return null;
    }

    @Override
    public RuntimeContext getRuntimeContext() {
        return null;
    }
}
