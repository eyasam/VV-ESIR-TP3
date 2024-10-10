package fr.istic.vv;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {
	
	 static String[] s1,s2; 
	 static TLSSocketFactory factory;
	 
	@BeforeAll
	public static void setUp() {
		s1=new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"};
		s2=new String[]{ "SSLv3", "TLSv1"};

		factory = new TLSSocketFactory();
	}
	
	
	// Verify that setEnabledProtocols is never called
	@Test
	public void testPreparedSocket_NullProtocols() {

		SSLSocket socket = Mockito.mock(SSLSocket.class);

		when(socket.getSupportedProtocols()).thenReturn(null);
	    when(socket.getEnabledProtocols()).thenReturn(null);

	    factory.prepareSocket(socket);

	    verify(socket, never()).setEnabledProtocols(null);
	}

	
	// Test with typical supported and enabled protocols to verify that `setEnabledProtocols` is correctly called.
	@Test
    public void testTypical() {
		
        SSLSocket socket = Mockito.mock(SSLSocket.class);

        when(socket.getSupportedProtocols()).thenReturn(s1);
        when(socket.getEnabledProtocols()).thenReturn(s2);

        factory.prepareSocket(socket);

        verify(socket).setEnabledProtocols(new String[]{ "TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
    }
	
	
  	// Test to verify that when supported protocols are null, the enabled protocols are preserved.
	@Test
	public void testPreparedSocket_EnabledProtocolsOnly() {
	    SSLSocket socket = Mockito.mock(SSLSocket.class);

	    when(socket.getSupportedProtocols()).thenReturn(null);
	    when(socket.getEnabledProtocols()).thenReturn(s2);

	    factory.prepareSocket(socket);

	    verify(socket).setEnabledProtocols(s2);
	}

    // Test to verify that only supported protocols are used when enabled protocols are null.
    @Test
    public void testPreparedSocket_SupportedProtocolsOnly() {
	    SSLSocket socket = Mockito.mock(SSLSocket.class);

        when(socket.getSupportedProtocols()).thenReturn(new String[]{"xx","TLSv1", "TLSv1.2"});
        when(socket.getEnabledProtocols()).thenReturn(null);

        factory.prepareSocket(socket);

        verify(socket).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1"});
    }
    
    // Test to ensure that supported protocols are ordered correctly and only valid protocols are set.
    @Test
    public void testPreparedSocket_ProtocolsOrder() {
        SSLSocket socket = Mockito.mock(SSLSocket.class);

        when(socket.getSupportedProtocols()).thenReturn(s1);
        when(socket.getEnabledProtocols()).thenReturn(null);

        factory.prepareSocket(socket);

        verify(socket).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1"});
    }
    




}