package com.spring.development;

import com.spring.development.common.annotation.EnableBeans;
import com.spring.development.common.annotation.EnableLog;
import com.spring.development.common.enums.LogMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@EnableCaching
@EnableLog(mode = LogMode.INFO)
@EnableBeans(packages = "com.spring.development.module.user.entity")
public class Application {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    Springboot 启动流程及扩展点:
//    ApplicationContextInitializer(initialize) -> AbstractApplicationContext(refresh)
//        -> BeanDefinitionRegistryPostProcessor(postProcessBeanDefinitionRegistry) -> BeanDefinitionRegistryPostProcessor(postProcessBeanFactory)
//        -> BeanFactoryPostProcessor(postProcessBeanFactory) -> InstantiationAwareBeanPostProcessor(postProcessBeforeInstantiation)
//        -> SmartInstantiationAwareBeanPostProcessor(determineCandidateConstructors) -> MergedBeanDefinitionPostProcessor(postProcessMergedBeanDefinition)
//        -> InstantiationAwareBeanPostProcessor(postProcessAfterInstantiation) -> SmartInstantiationAwareBeanPostProcessor(getEarlyBeanReference)
//        -> BeanFactoryAware(setBeanFactory) -> InstantiationAwareBeanPostProcessor(postProcessProperties) -> ApplicationContextAwareProcessor(invokeAwareInterfaces)
//        -> BeanNameAware(setBeanName) -> InstantiationAwareBeanPostProcessor(postProcessBeforeInitialization) -> @PostConstruct -> InitializingBean(afterPropertiesSet)
//        -> InstantiationAwareBeanPostProcessor(postProcessAfterInitialization) -> FactoryBean(getObject) -> SmartInitializingSingleton(afterSingletonsInstantiated)
//        -> CommandLineRunner(run) -> DisposableBean(destroy)

//    我们从这些spring&springboot的扩展点当中，大致可以窥视到整个bean的生命周期。在业务开发或者写中间件业务的时候，可以合理利用spring提供给我们的扩展点，在spring启动的各个阶段内做一些事情。以达到自定义初始化的目的。

//    打印注册到spring boot中的bean
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            logger.info("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                logger.info(beanName);
            }
        };
    }
}
