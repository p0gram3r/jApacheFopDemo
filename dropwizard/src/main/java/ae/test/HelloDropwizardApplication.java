package ae.test;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloDropwizardApplication extends Application<Configuration>
{
    public static void main(String[] args) throws Exception
    {
        new HelloDropwizardApplication().run(args);
    }



    @Override
    public void initialize(Bootstrap<Configuration> bootstrap)
    {
    }



    @Override
    public void run(Configuration configuration, Environment environment)
    {
        environment.jersey().register(HelloDropwizardResource.class);
    }
}