package br.org.ergildo.utilfaces.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.Map;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.org.ergildo.utilfaces.constantes.Constantes;
import br.org.ergildo.utilfaces.core.exception.UtilitarioException;

/**
 * @author ergildo.dias
 * 
 */
public class FacesContextUtil {

	/**
	 * Retorna {@link FacesContext}.
	 * 
	 * @return
	 */
	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	/**
	 * Retorna {@link Application}.
	 * 
	 * @return
	 */
	public static Application getApplication() {
		return getFacesContext().getApplication();
	}

	/**
	 * Retorna o {@link ResourceBundle} conforme definido no faces-config.xml.
	 * 
	 * @return resourceBundle
	 */
	public static ResourceBundle getResourceBundle() {
		return getResourceBundle(Constantes.RESOURCE_MESSAGE_VAR);
	}

	/**
	 * Retorna o {@link ResourceBundle} conforme definido no faces-config.xml.
	 * 
	 * @param var
	 * @return resourceBundle
	 */
	public static ResourceBundle getResourceBundle(String var) {
		return getApplication().getResourceBundle(getFacesContext(), var);
	}

	/**
	 * Retorna {@link ELContext}.
	 * 
	 * @return
	 */
	public static ELContext getElContext() {
		return getFacesContext().getELContext();
	}

	/**
	 * Retorna {@link ExternalContext}.
	 * 
	 * @return
	 */
	public static ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	/**
	 * Retorna {@link HttpServletRequest}.
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) getExternalContext().getRequest();
	}

	/**
	 * Retorna o mapa da sessão corrente.
	 * 
	 * @return
	 */
	public static Map<String, Object> getSessionMap() {
		return getExternalContext().getSessionMap();
	}

	/**
	 * Recupera atributo na sessão.
	 * 
	 * @param chave
	 * @return
	 */
	public static <T> Object getSessionAtributo(String chave) {
		return getSessionMap().get(chave);
	}

	/**
	 * @param chave
	 * @param valor
	 */
	public static void setSessionAtributo(String chave, Object valor) {
		getSessionMap().put(chave, valor);
	}

	/**
	 * Retorna {@link MethodExpression}.
	 * 
	 * @param expressao
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static MethodExpression getMethodExpression(String expressao) {

		return getMethodExpression(expressao, new Class[] {});
	}

	/**
	 * Retorna {@link MethodExpression}.
	 * 
	 * @param expressao
	 * @param classesEvent
	 * @return
	 */
	public static <T> MethodExpression getMethodExpression(String expressao,
			Class<T>[] classesEvent) {

		return getExpressionFactory().createMethodExpression(getElContext(),
				expressao, String.class, classesEvent);
	}

	/**
	 * Retorna {@link ExpressionFactory}.
	 * 
	 * @return
	 */
	public static ExpressionFactory getExpressionFactory() {
		return getApplication().getExpressionFactory();
	}

	/**
	 * Retorna manager bean apartir do nome informado.
	 * 
	 * @param managerBean
	 * @return
	 */
	public static Object getManagerBean(String managerBean) {

		return  getElContext().getELResolver().getValue(getElContext(),
				null, managerBean);
	}

	/**
	 * Retorna {@link UIComponent}.
	 * 
	 * @param componentId
	 * @return
	 */
	public static UIComponent getComponent(String componentId) {
		return getViewRoot().findComponent(componentId);
	}

	/**
	 * Retorna {@link UIViewRoot}.
	 * 
	 * @return
	 */
	private static UIViewRoot getViewRoot() {
		return getFacesContext().getViewRoot();
	}

	/**
	 * Retorna a url atual.
	 * 
	 * @return
	 */
	public static String getUrlAtual() {
		try {
			return URLEncoder.encode(getRequest().getRequestURI(),
					Constantes.ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new UtilitarioException(e);
		}

	}

	/**
	 * Retorna mensagem por chave.
	 * 
	 * @param chave
	 * @return mensagem
	 */
	public static String getMensagem(String chave) {
		return getMensagem(Constantes.RESOURCE_MESSAGE_VAR, chave);
	}

	/**
	 * Retorna mensagem por chave e variável.
	 * 
	 * @param var
	 * @param chave
	 * @return mensagem
	 */
	public static String getMensagem(String var, String chave) {
		return getResourceBundle(var).getString(chave);
	}

	/**
	 * Retorna resquest path conforme a library padrão.
	 * 
	 * @see Constantes#M3_FACES_LIBRARY
	 * @param name
	 * @param library
	 * @return
	 */
	public static String getRequestPath(String name) {
		return createResource(name, Constantes.M3_FACES_LIBRARY)
				.getRequestPath();
	}

	/**
	 * Retorna resquest path.
	 * 
	 * @param name
	 * @param library
	 * @return
	 */
	public static String getRequestPath(String name, String library) {
		return createResource(name, library).getRequestPath();
	}

	/**
	 * Cria resource.
	 * 
	 * @param name
	 * @param library
	 * @return
	 */
	private static Resource createResource(String name, String library) {
		return getResourceHandler().createResource(name, library);
	}

	/**
	 * Retorna resource handler.
	 * 
	 * @return
	 */
	private static ResourceHandler getResourceHandler() {
		return getApplication().getResourceHandler();
	}

	/**
	 * Retorna o nome do usuário logado na aplicação.
	 * 
	 * @return nome
	 */
	public static String getNomeUsuarioLogado() {
		if (getUserPrincipal() == null) {
			/*
			 * TODO O código deve ser removido. Solução temporária para evitar
			 * erros ao persistir. Após ativar o spring security para todas as
			 * paginas esse trecho de código deverá ser removido.
			 */
			return Constantes.USUARIO_TESTE_UNITARIO;
		}
		return getUserPrincipal().getName();
	}

	/**
	 * Retorna o usuário logado na aplicação.
	 * 
	 * @return
	 */
	private static Principal getUserPrincipal() {
		return getExternalContext().getUserPrincipal();
	}

	/**
	 * Retorna o caminho real do diretorio informado.
	 * 
	 * @param diretorio
	 * @return {@link String}
	 */
	public static String getDiretorioReal(String diretorio) {
		return getServletContext().getRealPath(diretorio);
	}

	/**
	 * Retorna o {@link ServletContext} da aplicação.
	 * 
	 * @return servletContext
	 */
	private static ServletContext getServletContext() {
		return getHttpSession().getServletContext();

	}

	/**
	 * Retorna o {@link HttpSession} da aplicação.
	 * 
	 * @return httpSession
	 */
	private static HttpSession getHttpSession() {
		return (HttpSession) getExternalContext().getSession(false);
	}

	/**
	 * Adiciona mensagem ao contexto.
	 * 
	 * @param mensagem
	 * @param componenteId
	 * @param severity
	 */
	public static void adicionarMensagem(FacesMessage message,
			String componenteId) {
		getFacesContext().addMessage(componenteId, message);
	}

	/**
	 * Navega de pagina de acordo com o outcome informado.
	 * 
	 * @param outcome
	 */
	public static void navegarPara(String outcome) {
		getApplication().getNavigationHandler().handleNavigation(
				getFacesContext(), null, outcome);
	}

	/**
	 * Redireciona para url informada.
	 * 
	 * @param url
	 */
	public static void redirecionarPara(String url) {
		try {
			getExternalContext().redirect(url);
		} catch (IOException e) {
			throw new UtilitarioException(e);
		}
	}

	/**
	 * @return
	 */
	public static boolean hasError() {
		return FacesMessage.SEVERITY_ERROR.equals(getMaximumSeverity());
	}

	/**
	 * @return
	 */
	private static Severity getMaximumSeverity() {
		return getFacesContext().getMaximumSeverity();
	}

}
