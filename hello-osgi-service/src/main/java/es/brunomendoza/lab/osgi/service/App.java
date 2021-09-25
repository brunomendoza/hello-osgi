package es.brunomendoza.lab.osgi.service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class App implements BundleActivator {
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Hello, Bundle!");
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Bye, Bundle");
    }
}
