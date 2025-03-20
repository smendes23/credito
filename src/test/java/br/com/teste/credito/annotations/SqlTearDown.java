package br.com.teste.credito.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.jdbc.Sql;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public @interface SqlTearDown {
    @AliasFor(
            annotation = Sql.class
    )
    String[] value() default {};
}
