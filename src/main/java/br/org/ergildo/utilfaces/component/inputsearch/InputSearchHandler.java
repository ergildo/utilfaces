/**
 * 
 */
package br.org.ergildo.utilfaces.component.inputsearch;

import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.MetaRule;
import javax.faces.view.facelets.MetaRuleset;

import org.primefaces.facelets.MethodRule;

import br.org.ergildo.utilfaces.event.SearchEvent;

/**
 * @author ergildo.dias
 * 
 */
public class InputSearchHandler extends ComponentHandler {

	private static final MetaRule SEARCH_LISTENER = new MethodRule("searchListener", String.class,
			new Class[] { SearchEvent.class });

	public InputSearchHandler(ComponentConfig config) {
		super(config);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.view.facelets.DelegatingMetaTagHandler#createMetaRuleset(
	 * java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	protected MetaRuleset createMetaRuleset(Class type) {
		MetaRuleset metaRuleset = super.createMetaRuleset(type);
		metaRuleset.addRule(SEARCH_LISTENER);
		return metaRuleset;
	}

}
