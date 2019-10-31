package net.tchinchow.vuejs_spring_boot.spring_backend.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.ServletContextResourceLoader;
import org.springframework.web.servlet.resource.ResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

/**
 * Code from https://stackoverflow.com/questions/47381362/configure-react-to-be-served-on-spring-boot-app/47427729#47427729
 */
@Component
public class VueJSResourceResolver implements ResourceResolver, ServletContextAware {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private ServletContext servletContext = null;

    private ResourceLoader servletContextResourceLoader = null;

    // root path of the VueJs application in the server / webjar
    @Value("${vuejs.publicPath:/webapp/}")
    private final String vuePublicPath;

    // TODO: see VueJS (this may not always be inside the vuePublicPath)
    @Value("${vuejs.staticDir:static}")
    private final String vueAssetsDir = "static";

//    private Resource index = new ClassPathResource(vuePublicPath + "index.html");

    // TODO: these were taken from a React project ... some do not exist in VueJS...
    private List<String> rootStaticFiles = Arrays.asList("index.html", "favicon.ico");

    public VueJSResourceResolver() {
        // TODO: put this in application.properties then initialize the private member from a ${application.propertie.stuff}
        this.vuePublicPath = "/webapp/";
    }

    @Override
    public Resource resolveResource(HttpServletRequest request, String requestPath, List<? extends Resource> locations, ResourceResolverChain chain) {
        return resolve(requestPath, locations);
    }

    @Override
    public String resolveUrlPath(String resourcePath, List<? extends Resource> locations, ResourceResolverChain chain) {
        Resource resolvedResource = resolve(resourcePath, locations);
        if (resolvedResource == null) {
            return null;
        }
        try {
            return resolvedResource.getURL().toString();
        } catch (IOException e) {
            return resolvedResource.getFilename();
        }
    }

    private Resource resolve(String requestPath, List<? extends Resource> locations) {
        if (requestPath == null) {
            return null;
        } else {
            if (requestPath.startsWith(this.vueAssetsDir) || (this.rootStaticFiles.contains(requestPath)) ) {
                final Resource resource = servletContextResourceLoader.getResource(vuePublicPath + requestPath);
                logger.debug("Resource for '"+requestPath+"': " + resource == null ? "NULL" : "OK");
                return resource;
            } else {
                logger.error("Resource for '"+requestPath+"' is not one of the registered files for VueJS applications !");
                return null;
            }
        }
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        if (this.servletContext != null) {
            logger.error("Servlet Context already set");
        } else {
            this.servletContext = servletContext;
            servletContextResourceLoader = new ServletContextResourceLoader(servletContext);
        }
    }

}
