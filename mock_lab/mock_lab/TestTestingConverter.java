import javax.servlet.http.*;

import org.junit.Before;
import org.junit.Test;

import com.mockobjects.servlet.MockHttpServletRequest;
import com.mockobjects.servlet.MockHttpServletResponse;

import javax.servlet.ServletException;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import junit.framework.*;


public class TestTestingConverter {
	TestingLabConverterServlet testServlet;
    MockHttpServletRequest request;
    MockHttpServletResponse response;

    @Before
    public void setup(){
			testServlet = new TestingLabConverterServlet();
            MockHttpServletRequest request =
                new MockHttpServletRequest();
            MockHttpServletResponse response =
                new MockHttpServletResponse();
		    response.setExpectedContentType("text/html");
	}

    @Test
	public void testNullTemp() throws ServletException, IOException{
		testServlet.doPost(request, response);
		response.verify();
		assertEquals("<html><head><title>No Temperature</title>"
				+ "</head><body><h2>Need to enter a temperature!"
				+ "</h2></body></html>\n",response.getOutputStreamContents());
	}
	
    @Test
	public void testTempValid() throws ServletException, IOException{
		request.setupAddParameter("farenheitTemperature", "32.0");
		testServlet.doPost(request, response);
		response.verify();
		assertTrue(response.getOutputStreamContents().contains("<html><head><title>Temperature Converter Result</title>"
				+ "</head><body><h2>" + 32.0 + " Farenheit = " + 0 + " Celsius "
				+ "</h2>\n"));
	}
	
    @Test
	public void testTempTrim() throws ServletException, IOException{
		request.setupAddParameter("farenheitTemperature", "   32.0   ");
		testServlet.doPost(request, response);
		response.verify();
		assertTrue(response.getOutputStreamContents().contains("<html><head><title>Temperature Converter Result</title>"
				+ "</head><body><h2>" + 32.0 + " Farenheit = " + 0 + " Celsius "
				+ "</h2>\n"));
	}
	
    @Test
	public void testTempInValid() throws ServletException, IOException{
		request.setupAddParameter("farenheitTemperature", " abc32.0");
		testServlet.doPost(request, response);
		response.verify();
		assertTrue(response.getOutputStreamContents().contains("<html><head><title>Bad Temperature</title>"
				+ "</head><body><h2>Need to enter a valid temperature!"
			    + "Got a NumberFormatException on " 
				+ "abc32.0" 
				+ "</h2></body></html>"));
	}
	
    @Test
	public void testTempAustin() throws ServletException, IOException{
		request.setupAddParameter("farenheitTemperature", "   32.0   ");
		testServlet.doPost(request, response);
		response.verify();
		assertTrue(response.getOutputStreamContents().contains("<p><h3>The temperature in Austin is "));
	}
	
    @Test
	public void testGetNullTemp() throws ServletException, IOException{
		testServlet.doGet(request, response);
		response.verify();
		assertEquals("<html><head><title>No Temperature</title>"
				+ "</head><body><h2>Need to enter a temperature!"
				+ "</h2></body></html>\n",response.getOutputStreamContents());
	}
	
    @Test
	public void testGetTempValid() throws ServletException, IOException{
		request.setupAddParameter("farenheitTemperature", "32.0");
		testServlet.doGet(request, response);
		response.verify();
		assertTrue(response.getOutputStreamContents().contains("<html><head><title>Temperature Converter Result</title>"
				+ "</head><body><h2>" + 32.0 + " Farenheit = " + 0 + " Celsius "
				+ "</h2>\n"));
	}
    
	@Test
	public void testGetTempTrim() throws ServletException, IOException{
		request.setupAddParameter("farenheitTemperature", "   32.0   ");
		testServlet.doGet(request, response);
		response.verify();
		assertTrue(response.getOutputStreamContents().contains("<html><head><title>Temperature Converter Result</title>"
				+ "</head><body><h2>" + 32.0 + " Farenheit = " + 0 + " Celsius "
				+ "</h2>\n"));
	}
	
    @Test
	public void testGetTempInValid() throws ServletException, IOException{
		request.setupAddParameter("farenheitTemperature", " abc32.0");
		testServlet.doGet(request, response);
		response.verify();
		assertTrue(response.getOutputStreamContents().contains("<html><head><title>Bad Temperature</title>"
				+ "</head><body><h2>Need to enter a valid temperature!"
			    + "Got a NumberFormatException on " 
				+ "abc32.0" 
				+ "</h2></body></html>"));
	}
	
    @Test
	public void testGetTempAustin() throws ServletException, IOException{
		request.setupAddParameter("farenheitTemperature", "   32.0   ");
		testServlet.doGet(request, response);
		response.verify();
		assertTrue(response.getOutputStreamContents().contains("<p><h3>The temperature in Austin is "));
	}
	
}
