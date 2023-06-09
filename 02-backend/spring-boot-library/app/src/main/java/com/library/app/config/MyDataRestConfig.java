package com.library.app.config;


import com.library.app.entity.Book;
import com.library.app.entity.Message;
import com.library.app.entity.Review;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    private String theAllowedOrigins = "https://localhost:3000";
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration configuration,
                                               CorsRegistry corsRegistry){
        HttpMethod[] theUnsupportedActions = {
                HttpMethod.POST,
                HttpMethod.PATCH,
                HttpMethod.DELETE,
                HttpMethod.PUT};

        configuration.exposeIdsFor(Book.class);
        configuration.exposeIdsFor(Review.class);
        configuration.exposeIdsFor(Message.class);

        disableHttpMethods(Book.class, configuration, theUnsupportedActions);
        disableHttpMethods(Review.class, configuration, theUnsupportedActions);
        disableHttpMethods(Message.class, configuration, theUnsupportedActions);

        /*Configuration CORS Mapping*/
        corsRegistry.addMapping(configuration.getBasePath()+"/**")
                .allowedOrigins(theAllowedOrigins);
    }
    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration configuration,
                                    HttpMethod[] theUnsupportedActions){
        configuration.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) ->
                    httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) ->
                httpMethods.disable(theUnsupportedActions));
    }
}
