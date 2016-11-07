package br.org.ergildo.utilfaces.component.image;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;

import com.sun.faces.util.FacesLogger;

/**
 * @author ergildo.dias
 *
 */
@FacesComponent(Image.COMPONENT_TYPE)
@ResourceDependencies(value = { @ResourceDependency(library = "utilfaces/image", name = "image.css") })
public class Image extends UIComponentBase {
	public static final String COMPONENT_TYPE = "br.org.ergildo.utilfaces.component.Image";
	private static final String DEFAULT_RENDERER = "br.org.ergildo.utilfaces.component.image.ImageRenderer";
	public static final String COMPONENT_FAMILY = "br.org.ergildo.utilfaces.component";
	private static final String OPTIMIZED_PACKAGE = "br.org.ergildo.utilfaces.component.";
	private static final Logger log = FacesLogger.FACELETS_INCLUDE.getLogger();

	protected enum PropertyKeys {

		value, height, width;

		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {
		}

		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());

		}
	}

	public Image() {
		setRendererType(DEFAULT_RENDERER);

		log.log(Level.INFO, "Image construct...");
	}

	@SuppressWarnings("unchecked")
	public void handleAttribute(String name, Object value) {
		List<String> setAttributes = (List<String>) this.getAttributes().get(
				"javax.faces.component.UIComponentBase.attributesThatAreSet");
		if (setAttributes == null) {
			String cname = this.getClass().getName();
			if (cname != null && cname.startsWith(OPTIMIZED_PACKAGE)) {
				setAttributes = new ArrayList<String>(6);
				this.getAttributes()
						.put("javax.faces.component.UIComponentBase.attributesThatAreSet",
								setAttributes);
			}
		}
		if (setAttributes != null) {
			if (value == null) {
				ValueExpression ve = getValueExpression(name);
				if (ve == null) {
					setAttributes.remove(name);
				} else if (!setAttributes.contains(name)) {
					setAttributes.add(name);
				}
			}
		}
	}

	public byte[] getValue() {
		return (byte[]) getStateHelper()
				.eval(PropertyKeys.value, new byte[] {});
	}

	public void setValue(byte[] _value) {
		getStateHelper().put(PropertyKeys.value, _value);
		handleAttribute("value", _value);
	}

	public java.lang.String getHeight() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.height,
				null);
	}

	public void setHeight(java.lang.String _height) {
		getStateHelper().put(PropertyKeys.height, _height);
		handleAttribute("height", _height);
	}

	public void setWidth(java.lang.String _width) {
		getStateHelper().put(PropertyKeys.width, _width);
		handleAttribute("width", _width);
	}

	public java.lang.String getWidth() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.width,
				null);
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
}
