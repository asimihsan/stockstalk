package config;

import calculation.MoneyFlowIndex;
import calculation.MoneyFlowIndexImpl;
import calculation.SimpleMovingAverage;
import calculation.SimpleMovingAverageImpl;
import com.google.inject.AbstractModule;

/**
 * Created by ai on 3/21/15.
 */
public class AppInjector extends AbstractModule {
    @Override
    protected void configure() {
        bind(SimpleMovingAverage.class).to(SimpleMovingAverageImpl.class);
        bind(MoneyFlowIndex.class).to(MoneyFlowIndexImpl.class);
    }
}
