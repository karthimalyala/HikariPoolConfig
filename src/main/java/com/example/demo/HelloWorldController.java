package com.example.demo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.zaxxer.hikari.HikariDataSource;

@RestController
public class HelloWorldController {
	@Autowired
	private HikariDataSource datasource;

	@GetMapping("/")
	public String welcome() {
		int maxPoolSize =  datasource.getMaximumPoolSize();
		
		return "Maximum Pool Size = " +maxPoolSize;
	}

	@GetMapping("/check-host-reachability")
    public HostReachability checkHostReachability() throws Exception {
		String hostName = "google.com";
       
		
		try {
            InetAddress address = InetAddress.getByName(hostName);

            if (!address.isReachable(5000)) { // Timeout in milliseconds (5 seconds)
                throw new Exception("Host is not reachable within 5 seconds.");
            }

            return new HostReachability(hostName, true, "");
        } catch (Exception exception) {
            return new HostReachability(hostName, false, exception.getMessage());
        }
	       

	        // If no exception occurred and reachability check failed
	        
	    }

    
    public static class HostReachability {
        private String hostName;
        private boolean reachable;
        private String errorMessage;

        public HostReachability(String hostName, boolean reachable,String errorMessage) {
            this.hostName = hostName;
            this.reachable = reachable;
            this.errorMessage = errorMessage;
        }

        public String getHostName() {
            return hostName;
        }

        public boolean isReachable() {
            return reachable;
        }
        
        public String geterrorMessage() {
        	return errorMessage;
        }
        
    }
}