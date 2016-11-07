package br.org.ergildo.utilfaces.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.Id;

import br.org.ergildo.utilfaces.util.ReflexaotUtil;

/**
 * Converter para entidade. Obs.: a entidade dever conter o identicador anotado
 * com {@link Id}.
 * 
 * @author ergildo.dias
 * 
 */
@FacesConverter(value = "entityConverter")
public class EntityConverter implements Converter {

	private final String ENTITY_PREFIX = "entity_";

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.
	 * FacesContext , javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String id) {
		if (id != null) {
			String entityKey = getEntityKey(id);
			Object entity = component.getAttributes().get(entityKey);
			return entity;
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.
	 * FacesContext , javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object entity) {
		if (entity != null) {
			String id = getId(entity);
			String entityKey = getEntityKey(id);
			component.getAttributes().put(entityKey, entity);
			return getId(entity);
		}
		return null;
	}

	/**
	 * retorna o valor do id da entidade.
	 * 
	 * @param entity
	 * @return
	 */
	private String getId(Object entity) {
		Object id = ReflexaotUtil.getIdValue(entity);
		return String.valueOf(id);
	}

	/**
	 * @param id
	 * @return
	 */
	private String getEntityKey(String id) {
		String entityKey = ENTITY_PREFIX.concat(id);
		return entityKey;
	}

}
