package es.brunomendoza.lab.osgi.consumer;

import es.brunomendoza.lab.osgi.service.IGreeter;
import org.osgi.framework.*;

public class Consumer implements BundleActivator, ServiceListener {

    private BundleContext bundleContext;
    private ServiceReference serviceReference;

    // The addServiceListener() method allows the client to ask the platform
    // to send notifications about the service that complies with the provided expression.
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        this.bundleContext = bundleContext;
        try {
            bundleContext.addServiceListener(
                    this,
                    String.format("(objectclass=%$)", IGreeter.class.getName())
            );
        } catch (InvalidSyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        if (serviceReference != null) {
            bundleContext.ungetService(serviceReference);
        }
    }

    @Override
    public void serviceChanged(ServiceEvent serviceEvent) {
        int type = serviceEvent.getType();

        switch (type) {
            case(ServiceEvent.REGISTERED):
                System.out.println("Notification of service registered.");
                serviceReference = serviceEvent
                        .getServiceReference();
                IGreeter service = (IGreeter) (bundleContext.getService(serviceReference));
                System.out.println(service.sayHiTo("John"));
                break;
            case(ServiceEvent.UNREGISTERING):
                System.out.println("Notification of service unregistered");
                bundleContext.ungetService(serviceEvent.getServiceReference());
                break;
            default:
                break;
        }
    }
}
