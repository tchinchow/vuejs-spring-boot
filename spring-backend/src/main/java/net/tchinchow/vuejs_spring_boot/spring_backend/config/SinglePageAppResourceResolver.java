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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Code from https://stackoverflow.com/questions/47381362/configure-react-to-be-served-on-spring-boot-app/47427729#47427729
 */
@Accessors(chain=true)
@NoArgsConstructor
public class SinglePageAppResourceResolver implements ResourceResolver, ServletContextAware {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Internal resource loader based on a reference to the servlet context.
     * 
     * This resource loader is automatically initialized when {@link #setServletContext(ServletContext)} is set
     * and is used for accessing all servlet resources including those that are stored in external libraries.
     * 
     * @see #setServletContext(ServletContext)
     */
    private ResourceLoader servletContextResourceLoader = null;

    /**
     * The fully qualified path that indicates where the single page app resources can be found.
     */
    @Getter
    @Setter
    private String publicPathLocation;

    /**
     * The path of the single page app assets folder relative to its public path.
     * 
     * Resources in this folder will be served as-is and are automatically treated like the files
     * listed in {@link #staticFiles}. 
     */
    @Getter
    @Setter
    private String assetsDir = "static";

    /**
     * Name of the resource that will be used as the index for the Single Page App.
     * 
     * This index is the file that is served by default when the requested path does not exist.
     */
    @Getter
    @Setter
    @NonNull
    private String indexFilename = "index.html";

    /**
     * TODO: Document
     */
    // @Getter
    // @Setter
    // private boolean automaticRedirectWhenResourceNotFound = true;

    /**
     * List of the static files of the Single Page App.
     * 
     * The files listed in this property are served as-is from the resource.
     * 
     * This property shall only contain filenames without wildcards.
     * 
     * The single page app index must not be included (see {@link #setIndexFilename(String)
     * for defining the index file).
     * 
     * This property must not include folders such like the {@link #assetsDir}
     */
    @Getter
    @Setter
    @NonNull
    private List<String> staticFiles = Arrays.asList("favicon.ico");

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
        } catch (final IOException ioe) {
            return resolvedResource.getFilename();
        }
    }

    private Resource resolve(String requestPath, List<? extends Resource> locations) {
        if (servletContextResourceLoader == null) {
            throw new IllegalStateException("Cannot resolve request path now: the 'servletContextResourceLoader' is not initialized !");
        }
        if (requestPath == null) {
            logger.warn("Cannot resolve request path: null request !");
            return null;
        }

        // If the request is for a static file (i.e. in the sigle page app assets dir), then we serve the resource as-is
        if (this.staticFiles.contains(requestPath) || requestPath.startsWith(this.assetsDir)) {
            final Resource resource = servletContextResourceLoader.getResource(this.publicPathLocation + requestPath);
            if (resource == null) {
                logger.warn("Cannot resolve static resource for request path '" + requestPath + "': null resource !");
            } else {
                logger.debug("Resolved static resource for request path '" + requestPath + "'...");
            }
            return resource;
        } else {
            final Resource resource = servletContextResourceLoader.getResource(this.publicPathLocation + this.indexFilename);
            if (resource == null) {
                logger.warn("Cannot resolve index resource for request path '" + requestPath + "': null resource !");
            } else {
                logger.debug("Using index resource for request path '" + requestPath + "'...");
            }
            return resource;
        }
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        servletContextResourceLoader = new ServletContextResourceLoader(servletContext);
    }

}
