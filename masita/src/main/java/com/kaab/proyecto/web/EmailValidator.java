package com.kaab.proyecto.web;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Map;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.validate.ClientValidator;
/**
 * Plantilla JSF Validator para entradas de correo.
 */
@FacesValidator("custom.emailValidator")
public class EmailValidator implements Validator, ClientValidator {
    /**
     * El patrón a usar.
     */
    private final Pattern pattern;
    /**
     * Formato del correo electrónico.
     */
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "ciencias.unam.mx";
    /**
     * Constructor que inicializa el patrón.
     */
    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }
    /**
     * Valida el patrón.
     * @param context el contexto en el que se ingresa el correo
     * @param component componente de la interfaz de usuario
     * @param value el valor dado
     * @throws ValidatorException si no es un correo válido
     */
    @Override
    public final void validate(final FacesContext context,
            final UIComponent component, final Object value)
            throws ValidatorException {
        if (value == null) {
            return;
        }
        if (!pattern.matcher(value.toString()).matches()) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error de Validación",
                        value + " no es un correo válido."));
        }
    }
    /**
     * Obtiene los datos externos.
     * @return diccionario
     */
    @Override
    public final Map<String, Object> getMetadata() {
        return null;
    }
    /**
     * Obtiene el id del validator.
     * @return el id del validator
     */
    @Override
    public final String getValidatorId() {
        return "custom.emailValidator";
    }
}