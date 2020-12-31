package com.spring.development.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/home").setViewName("home");
//        registry.addViewController("/").setViewName("home");
//        registry.addViewController("/hello").setViewName("hello");
//        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/upload").setViewName("uploadForm");
    }


    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
//                .allowedOrigins("*")
                .allowedOriginPatterns("*")
                .allowedMethods("OPTIONS","GET","POST","PUT","DELETE")
                .exposedHeaders("Authorization")
                .maxAge(3600);
    }

    /**
     * Override this method to configure content negotiation.
     *
     * @param configurer
     * @see DefaultServletHandlerConfigurer
     */
    @Override
    protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML);
    }

}