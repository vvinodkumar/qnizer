package com.nizeapps.qnizer.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI;

public class SecurityFilter implements Filter {

	@Override
	public void destroy() {
		//do nothing
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		ESAPI.httpUtilities().setCurrentHTTP(request, response);
		
		chain.doFilter(request, response);
		// set up response with content type
		
		ESAPI.httpUtilities().setContentType( response );

        // set no-cache headers on every response
        // only do this if the entire site should not be cached
        // otherwise you should do this strategically in your controller or actions
		ESAPI.httpUtilities().setNoCacheHeaders( response );
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String path = filterConfig.getInitParameter("resourceDirectory");
		if ( path != null ) {
			ESAPI.securityConfiguration().setResourceDirectory( path );
		}
		
	}

}
