package com.company.core.validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author mukulbansal
 */
public class GenericBeanValidator<T> {
    private static final Logger LOG = LoggerFactory.getLogger(GenericBeanValidator.class);

    T bean;
    private String errorMessage;
    private String propertyPath;

    /**
     * @param bean
     */
    public GenericBeanValidator(final T bean) {
        this.bean = bean;
    }

    /**
     * @return the propertyPath
     */
    public String getPropertyPath() {
        return propertyPath;
    }

    /**
     * @param propertyPath the propertyPath to set
     */
    public void setPropertyPath(final String propertyPath) {
        this.propertyPath = propertyPath;
    }

    /**
     * @return the bean
     */
    public T getBean() {
        return bean;
    }

    /**
     * @param bean the bean to set
     */
    public void setBean(final T bean) {
        this.bean = bean;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return getPropertyPath() + " " + errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return errorMessage
     */
    public String getRawErrorMessage() {
        return errorMessage;
    }

    /**
     * @return true/false as par validation
     */
    public boolean validate() {
        LOG.debug("Bean validation called");

        final ValidatorFactory factory   = Validation.buildDefaultValidatorFactory();
        final Validator        validator = factory.getValidator();

        final Set<ConstraintViolation<T>> violations = validator.validate(bean);

        if (!violations.isEmpty()) {
            for (final ConstraintViolation<T> violation : violations) {
                setPropertyPath(violation.getPropertyPath().toString());
                setErrorMessage(violation.getMessage());
                LOG.error("Bean validation failed due to reason : {}", violation.getMessage());
                return false;
            }
        }
        LOG.debug("Bean validation completed");
        return true;
    }
}