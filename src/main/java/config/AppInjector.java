package config;

import calculation.*;
import calculation.util.CalculationUtil;
import com.google.inject.AbstractModule;

/**
 * Created by ai on 3/21/15.
 */
public class AppInjector extends AbstractModule {

    @Override
    protected void configure() {
        bind(CalculationUtil.class).toInstance(new CalculationUtil());
    }

}
