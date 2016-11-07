/**
 * 
 */
package br.org.ergildo.utilfaces.component.dialoghandlerbehavior;

import java.util.Collection;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.facelets.BehaviorConfig;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagException;

import org.primefaces.behavior.ajax.AjaxBehaviorListenerImpl;
import org.primefaces.behavior.base.AbstractBehaviorHandler;

import br.org.ergildo.utilfaces.util.StringUtil;

/**
 * @author ergildo.dias
 * 
 */
public class DialogHandlerBehaviorHandler extends AbstractBehaviorHandler<DialogHandlerBehavior> {
	private final TagAttribute forDialog;
	private final TagAttribute process;
	private final TagAttribute update;
	private final TagAttribute onstart;
	private final TagAttribute onerror;
	private final TagAttribute onsuccess;
	private final TagAttribute oncomplete;
	private final TagAttribute disabled;
	private final TagAttribute immediate;
	private final TagAttribute listener;
	private final TagAttribute global;
	private final TagAttribute async;
	private final TagAttribute partialSubmit;
	private final TagAttribute resetValues;
	private final TagAttribute ignoreAutoUpdate;
	private final TagAttribute delay;
	private final TagAttribute timeout;

	public DialogHandlerBehaviorHandler(BehaviorConfig config) {
		super(config);
		this.forDialog = this.getRequiredAttribute(DialogHandlerBehavior.PropertyKeys.forDialog.toString);
		this.process = this.getAttribute(DialogHandlerBehavior.PropertyKeys.process.name());
		this.update = this.getAttribute(DialogHandlerBehavior.PropertyKeys.update.name());
		this.onstart = this.getAttribute(DialogHandlerBehavior.PropertyKeys.onstart.name());
		this.onerror = this.getAttribute(DialogHandlerBehavior.PropertyKeys.onerror.name());
		this.onsuccess = this.getAttribute(DialogHandlerBehavior.PropertyKeys.onsuccess.name());
		this.oncomplete = this.getAttribute(DialogHandlerBehavior.PropertyKeys.oncomplete.name());
		this.disabled = this.getAttribute(DialogHandlerBehavior.PropertyKeys.disabled.name());
		this.immediate = this.getAttribute(DialogHandlerBehavior.PropertyKeys.immediate.name());
		this.listener = this.getRequiredAttribute(DialogHandlerBehavior.PropertyKeys.listener.name());
		this.global = this.getAttribute(DialogHandlerBehavior.PropertyKeys.global.name());
		this.async = this.getAttribute(DialogHandlerBehavior.PropertyKeys.async.name());
		this.partialSubmit = this.getAttribute(DialogHandlerBehavior.PropertyKeys.partialSubmit.name());
		this.resetValues = this.getAttribute(DialogHandlerBehavior.PropertyKeys.resetValues.name());
		this.ignoreAutoUpdate = this.getAttribute(DialogHandlerBehavior.PropertyKeys.ignoreAutoUpdate.name());
		this.delay = this.getAttribute(DialogHandlerBehavior.PropertyKeys.delay.name());
		this.timeout = this.getAttribute(DialogHandlerBehavior.PropertyKeys.timeout.name());
	}

	@Override
	public void applyAttachedObject(FaceletContext faceletContext, UIComponent parent) {
		ClientBehaviorHolder holder = (ClientBehaviorHolder) parent;

		String eventName = getEventName();

		if (null == eventName) {
			eventName = holder.getDefaultEventName();
			if (null == eventName) {
				throw new TagException(this.tag,
						StringUtil.concatenar("O Atributo event não pôde ser determinado:", eventName));
			}
		} else {
			Collection<String> eventNames = holder.getEventNames();

			if (!eventNames.contains(eventName)) {
				throw new TagException(this.tag, StringUtil.concatenar("Evento", eventName, "não é suportado"));
			}
		}

		DialogHandlerBehavior behavior = createBehavior(faceletContext, eventName, parent);
		behavior.setParent(parent);
		holder.addClientBehavior(eventName, behavior);
	}

	private void setAttributes(FaceletContext ctx, DialogHandlerBehavior behavior) {
		setBehaviorAttribute(ctx, behavior, this.forDialog, DialogHandlerBehavior.PropertyKeys.forDialog.expectedType);
		setBehaviorAttribute(ctx, behavior, this.process, DialogHandlerBehavior.PropertyKeys.process.expectedType);
		setBehaviorAttribute(ctx, behavior, this.update, DialogHandlerBehavior.PropertyKeys.update.expectedType);
		setBehaviorAttribute(ctx, behavior, this.onstart, DialogHandlerBehavior.PropertyKeys.onstart.expectedType);
		setBehaviorAttribute(ctx, behavior, this.onerror, DialogHandlerBehavior.PropertyKeys.onerror.expectedType);
		setBehaviorAttribute(ctx, behavior, this.onsuccess, DialogHandlerBehavior.PropertyKeys.onsuccess.expectedType);
		setBehaviorAttribute(ctx, behavior, this.oncomplete,
				DialogHandlerBehavior.PropertyKeys.oncomplete.expectedType);
		setBehaviorAttribute(ctx, behavior, this.disabled, DialogHandlerBehavior.PropertyKeys.disabled.expectedType);
		setBehaviorAttribute(ctx, behavior, this.immediate, DialogHandlerBehavior.PropertyKeys.immediate.expectedType);
		setBehaviorAttribute(ctx, behavior, this.global, DialogHandlerBehavior.PropertyKeys.global.expectedType);
		setBehaviorAttribute(ctx, behavior, this.async, DialogHandlerBehavior.PropertyKeys.async.expectedType);
		setBehaviorAttribute(ctx, behavior, this.partialSubmit,
				DialogHandlerBehavior.PropertyKeys.partialSubmit.expectedType);
		setBehaviorAttribute(ctx, behavior, this.listener, DialogHandlerBehavior.PropertyKeys.listener.expectedType);
		setBehaviorAttribute(ctx, behavior, this.resetValues,
				DialogHandlerBehavior.PropertyKeys.resetValues.expectedType);
		setBehaviorAttribute(ctx, behavior, this.ignoreAutoUpdate,
				DialogHandlerBehavior.PropertyKeys.ignoreAutoUpdate.expectedType);
		setBehaviorAttribute(ctx, behavior, this.delay, DialogHandlerBehavior.PropertyKeys.delay.expectedType);
		setBehaviorAttribute(ctx, behavior, this.timeout, DialogHandlerBehavior.PropertyKeys.timeout.expectedType);

		behavior.addAjaxBehaviorListener(
				new AjaxBehaviorListenerImpl(this.listener.getMethodExpression(ctx, Object.class, new Class[] {}),
						this.listener.getMethodExpression(ctx, Object.class, new Class[] { AjaxBehaviorEvent.class })));
	}

	@Override
	protected DialogHandlerBehavior createBehavior(FaceletContext ctx, String eventName, UIComponent parent) {
		Application application = ctx.getFacesContext().getApplication();
		DialogHandlerBehavior behavior = (DialogHandlerBehavior) application
				.createBehavior(DialogHandlerBehavior.BEHAVIOR_ID);

		setAttributes(ctx, behavior);

		return behavior;
	}

}
