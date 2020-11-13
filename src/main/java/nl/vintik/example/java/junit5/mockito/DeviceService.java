package nl.vintik.example.java.junit5.mockito;

import nl.vintik.example.java.junit5.DeviceType;

public interface DeviceService {

    DeviceType getDeviceTypeByUserAgent(String userAgent);

}
