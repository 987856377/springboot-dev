package com.spring.development.common.annotation;

import com.spring.development.common.enums.LogMode;
import com.spring.development.common.selector.LogConfigurationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @program: springboot-dev
 * @package com.spring.development.common.annotation
 * @description
 * @author: XuZhenkui
 * @create: 2020-11-19 09:44
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LogConfigurationSelector.class)
public @interface EnableLog {
    LogMode mode() default LogMode.INFO;
}
