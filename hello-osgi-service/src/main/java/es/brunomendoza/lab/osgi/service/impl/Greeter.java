package es.brunomendoza.lab.osgi.service.impl;

import es.brunomendoza.lab.osgi.service.IGreeter;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import java.util.Hashtable;

public class Greeter implements IGreeter, BundleActivator {

    private ServiceReference<IGreeter> reference;
    private ServiceRegistration<IGreeter> registration;

    @Override
    public String sayHiTo(String name) {
        return String.format("Hello, %s!", name);
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Registering service.");
        registration = bundleContext.registerService(
                IGreeter.class,
                new Greeter(),
                new Hashtable<String, String>()
        );
        reference = registration.getReference();
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Unregistering service.");
        registration.unregister();
    }
}
