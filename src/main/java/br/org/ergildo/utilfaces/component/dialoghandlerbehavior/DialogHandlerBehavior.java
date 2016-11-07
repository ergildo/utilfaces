package br.org.ergildo.utilfaces.component.dialoghandlerbehavior;

import javax.el.MethodExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.component.visit.VisitContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.BehaviorEvent;

import org.primefaces.behavior.ajax.AjaxBehavior;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.context.RequestContext;
import org.primefaces.expression.SearchExpressionFacade;
import org.primefaces.util.ComponentUtils;
import org.primefaces.visit.ResetInputVisitCallback;

import br.org.ergildo.utilfaces.util.FacesContextUtil;
import br.org.ergildo.utilfaces.util.StringUtil;

/**
 * @author ergildo.dias
 * 
 */
@ResourceDependencies({
		@ResourceDependency(library = "primefaces", name = "jquery/jquery.js"),
		@ResourceDependency(library = "primefaces", name = "primefaces.js"),
		@ResourceDependency(library = "utilfaces", name = "core/js/util-faces.js") })
public class DialogHandlerBehavior extends AjaxBehavior {

	public final static String BEHAVIOR_ID = "br.org.ergildo.utilfaces.component.DialogHandlerBehavior";
	private final String DEFAULT_RENDERER = "br.org.ergildo.utilfaces.component.DialogHandlerBehaviorRenderer";
	private UIComponent parent;

	private Dialog dialog;

	public enum PropertyKeys {
		forDialog(String.class, "for"), update(String.class), process(
				String.class), global(Boolean.class), async(Boolean.class), oncomplete(
				String.class), onerror(String.class), onsuccess(String.class), onstart(
				String.class), listener(MethodExpression.class), immediate(
				Boolean.class), disabled(Boolean.class), partialSubmit(
				Boolean.class), resetValues(Boolean.class), ignoreAutoUpdate(
				Boolean.class), delay(String.class), timeout(Integer.class);

		final Class<?> expectedType;
		String toString;

		PropertyKeys(Class<?> expectedType, String toString) {
			this.toString = toString;
			this.expectedType = expectedType;
		}

		PropertyKeys(Class<?> expectedType) {
			this.expectedType = expectedType;

		}

		@Override
		public String toString() {
			return ((toString != null) ? toString : super.toString());
		}

	}

	public DialogHandlerBehavior() {
		super();
	}

	@Override
	public String getRendererType() {
		return DEFAULT_RENDERER;
	}

	public String getFor() {
		return eval(PropertyKeys.forDialog.toString, null);
	}

	public void setFor(String forDialog) {
		setLiteral(PropertyKeys.forDialog.toString, forDialog);
	}

	@Override
	public String getProcess() {
		return eval(PropertyKeys.process, "@this");
	}

	@Override
	public void setProcess(String process) {
		setLiteral(PropertyKeys.process, process);
	}

	/**
	 * @return the parent
	 */
	public UIComponent getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(UIComponent parent) {
		this.parent = parent;
	}

	/**
	 * @return the dialog
	 */
	public Dialog getDialog() {
		if (dialog == null) {
			dialog = (Dialog) SearchExpressionFacade.resolveComponent(
					FacesContextUtil.getFacesContext(), parent,
					getExpressionFor());
		}
		return dialog;
	}

	public String getExpressionFor() {
		return StringUtil.concatenar("@form:", getFor());
	}

	@Override
	public void broadcast(BehaviorEvent event) throws AbortProcessingException {
		super.broadcast(event);
		if (!FacesContextUtil.hasError()) {
			resetInputValues();
			showDialog();
		}
	}

	private void resetInputValues() {
		VisitContext visitContext = VisitContext.createVisitContext(
				FacesContextUtil.getFacesContext(), null,
				ComponentUtils.VISIT_HINTS_SKIP_UNRENDERED);
		getDialog().visitTree(visitContext, ResetInputVisitCallback.INSTANCE);
	}

	private void showDialog() {
		String widgetVar = getDialog().resolveWidgetVar();
		String script = StringUtil.concatenar("PF('", widgetVar, "').show();");
		RequestContext.getCurrentInstance().execute(script);
	}

}